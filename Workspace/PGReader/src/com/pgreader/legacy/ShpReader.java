package com.pgreader.legacy; 

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Vector;

import com.pgreader.data.IconHeader;
import com.pgreader.data.IconResources;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Pair;

/**
 * Helper for reading SHP file.
 * SHP file format:
 * magic (4 byte)
 * icon_count (4 byte)
 * offset_list:
 *   icon_offset (4 byte)
 *   icon_palette_offset (4 byte)
 * Icon format:
 * header (24 byte)
 * raw data
 */
public final class ShpReader {
	
	/**
	 * Dummy private constructor for utility class.
	 */
	private ShpReader() { }
	
	/**
	 * SHP color coefficient.
	 */
	private static final int SHPCOLORCOEF = 4;
	
	/**
	 * Define the size of an int when reading.
	 */
	public static final int INTSIZE = 4;
	
	/**
	 * Default palette. Used if icon is not associated with a palette.
  	 */
	private static final int[] DEFAULTPALETTE = new int[] {
	Color.rgb(0, 0, 0), Color.rgb(0, 0, 171), Color.rgb(0, 171, 0), // 0-2
	Color.rgb(0, 171, 171), Color.rgb(171, 0, 0), Color.rgb(171, 0, 171), // 3-5
	Color.rgb(171, 87, 0), Color.rgb(171, 171, 171), Color.rgb(87, 87, 87), // 6-8
	Color.rgb(87, 87, 255), Color.rgb(87, 255, 87), Color.rgb(87, 255, 255), // 9-11
	Color.rgb(255, 87, 87), Color.rgb(255, 87, 255), Color.rgb(255, 255, 87), // 12-14
	Color.rgb(255, 255, 255), Color.rgb(79, 0, 0), Color.rgb(107, 0, 0), // 15-17
	Color.rgb(135, 0, 0), Color.rgb(167, 7, 0), Color.rgb(195, 11, 0), // 18-20
	Color.rgb(223, 19, 0), Color.rgb(255, 0, 0), Color.rgb(255, 83, 19), // 21-23
	Color.rgb(255, 131, 39), Color.rgb(255, 175, 59), Color.rgb(255, 211, 79), // 24-26
	Color.rgb(255, 243, 99), Color.rgb(243, 255, 123), Color.rgb(255, 255, 255), // 27-29
	Color.rgb(223, 235, 223), Color.rgb(195, 211, 195), Color.rgb(167, 191, 167), // 30-32
	Color.rgb(139, 171, 139), Color.rgb(119, 151, 119), Color.rgb(95, 131, 95), // 33-35
	Color.rgb(75, 111, 75), Color.rgb(59, 91, 59), Color.rgb(43, 71, 43), // 36-38
	Color.rgb(255, 255, 255), Color.rgb(227, 231, 243), Color.rgb(215, 219, 235), // 39-41
	Color.rgb(203, 211, 231), Color.rgb(191, 199, 227), Color.rgb(183, 191, 223), // 42-44
	Color.rgb(171, 183, 219), Color.rgb(163, 175, 211), Color.rgb(151, 163, 207), // 45-47
	Color.rgb(139, 155, 203), Color.rgb(131, 147, 199), Color.rgb(123, 139, 191), // 48-50
	Color.rgb(115, 131, 187), Color.rgb(107, 123, 183), Color.rgb(99, 119, 179), // 51-53
	Color.rgb(91, 111, 175), Color.rgb(255, 255, 255), Color.rgb(231, 239, 239), // 54-56
	Color.rgb(211, 219, 219), Color.rgb(191, 203, 203), Color.rgb(171, 187, 187), // 57-59
	Color.rgb(151, 171, 171), Color.rgb(135, 155, 155), Color.rgb(119, 139, 139), // 60-62
	Color.rgb(99, 123, 123), Color.rgb(87, 107, 107), Color.rgb(71, 91, 91), // 63-65
	Color.rgb(55, 75, 75), Color.rgb(43, 59, 59), Color.rgb(31, 43, 43), // 66-68
	Color.rgb(15, 27, 27), Color.rgb(7, 11, 11), Color.rgb(255, 243, 143), // 69-71
	Color.rgb(247, 231, 135), Color.rgb(239, 219, 127), Color.rgb(231, 211, 123), // 72-74
	Color.rgb(227, 199, 119), Color.rgb(219, 191, 111), Color.rgb(211, 179, 107), // 75-77
	Color.rgb(207, 171, 103), Color.rgb(187, 195, 39), Color.rgb(251, 243, 167), // 78-80
	Color.rgb(239, 235, 163), Color.rgb(227, 227, 159), Color.rgb(215, 215, 151), // 81-83
	Color.rgb(199, 203, 147), Color.rgb(187, 191, 143), Color.rgb(175, 179, 135), // 84-86
	Color.rgb(159, 167, 131), Color.rgb(147, 155, 123), Color.rgb(135, 143, 115), // 87-89
	Color.rgb(223, 219, 123), Color.rgb(207, 207, 119), Color.rgb(195, 195, 119), // 90-92
	Color.rgb(179, 179, 115), Color.rgb(163, 167, 111), Color.rgb(151, 155, 107), // 93-95
	Color.rgb(139, 143, 103), Color.rgb(127, 131, 95), Color.rgb(115, 119, 91), // 96-98
	Color.rgb(195, 195, 119), Color.rgb(51, 39, 27), Color.rgb(55, 43, 31), // 99-101
	Color.rgb(63, 47, 35), Color.rgb(71, 55, 39), Color.rgb(75, 59, 43), // 102-104
	Color.rgb(83, 67, 43), Color.rgb(91, 71, 51), Color.rgb(95, 79, 55), // 105-107
	Color.rgb(103, 83, 59), Color.rgb(111, 91, 63), Color.rgb(191, 131, 47), // 108-110
	Color.rgb(254, 254, 254), Color.rgb(255, 255, 151), Color.rgb(255, 87, 255), 
		// 111-113 111=blink white
	    // 112= blink gold
	Color.rgb(111, 91, 63), Color.rgb(115, 95, 67), Color.rgb(123, 103, 71), // 114-116
	Color.rgb(131, 111, 75), Color.rgb(139, 119, 83), Color.rgb(255, 255, 87), // 117-119
	Color.rgb(0, 171, 0), Color.rgb(87, 87, 255), Color.rgb(0, 0, 171), // 120-122
	Color.rgb(135, 143, 115), Color.rgb(159, 167, 131), Color.rgb(95, 223, 255), // 123-125
	Color.rgb(203, 211, 231), Color.rgb(231, 211, 171), Color.rgb(59, 0, 0), // 126-128
	Color.rgb(83, 0, 0), Color.rgb(103, 0, 0), Color.rgb(127, 7, 0), // 129-131
	Color.rgb(147, 11, 0), Color.rgb(167, 15, 0), Color.rgb(191, 0, 0), // 132-134
	Color.rgb(191, 63, 15), Color.rgb(191, 99, 31), Color.rgb(191, 131, 47), // 135-137
	Color.rgb(191, 159, 59), Color.rgb(191, 183, 75), Color.rgb(183, 191, 95), // 138-140
	Color.rgb(191, 191, 191), Color.rgb(167, 179, 167), Color.rgb(147, 159, 147), // 141-143
	Color.rgb(127, 143, 127), Color.rgb(107, 131, 107), Color.rgb(91, 115, 91), // 144-146
	Color.rgb(71, 99, 71), Color.rgb(59, 83, 59), Color.rgb(47, 71, 47), // 147-149
	Color.rgb(35, 55, 35), Color.rgb(191, 191, 191), Color.rgb(171, 175, 183), // 150-152
	Color.rgb(163, 167, 179), Color.rgb(155, 159, 175), Color.rgb(143, 151, 171), // 153-155
	Color.rgb(139, 143, 167), Color.rgb(131, 139, 167), Color.rgb(123, 131, 159), // 156-158
	Color.rgb(115, 123, 155), Color.rgb(107, 119, 155), Color.rgb(99, 111, 151), // 159-161
	Color.rgb(95, 107, 143), Color.rgb(87, 99, 143), Color.rgb(83, 95, 139), // 162-164
	Color.rgb(75, 91, 135), Color.rgb(71, 83, 131), Color.rgb(191, 191, 191), // 165-167
	Color.rgb(175, 179, 179), Color.rgb(159, 167, 167), Color.rgb(143, 155, 155), // 168-170
	Color.rgb(131, 143, 143), Color.rgb(115, 131, 131), Color.rgb(103, 119, 119), // 171-173
	Color.rgb(91, 107, 107), Color.rgb(75, 95, 95), Color.rgb(67, 83, 83), // 174-176
	Color.rgb(55, 71, 71), Color.rgb(43, 59, 59), Color.rgb(35, 47, 47), // 177-179
	Color.rgb(23, 35, 35), Color.rgb(11, 23, 23), Color.rgb(7, 11, 11), // 180-182
	Color.rgb(191, 183, 107), Color.rgb(187, 175, 103), Color.rgb(179, 167, 95), // 183-185
	Color.rgb(175, 159, 95), Color.rgb(171, 151, 91), Color.rgb(167, 143, 83), // 186-188
	Color.rgb(159, 135, 83), Color.rgb(155, 131, 79), Color.rgb(143, 147, 31), // 189-191
	Color.rgb(191, 183, 127), Color.rgb(179, 179, 123), Color.rgb(171, 171, 119), // 192-194
	Color.rgb(163, 163, 115), Color.rgb(151, 155, 111), Color.rgb(143, 143, 107), // 195-197
	Color.rgb(131, 135, 103), Color.rgb(119, 127, 99), Color.rgb(111, 119, 95), // 198-200
	Color.rgb(103, 107, 87), Color.rgb(167, 167, 95), Color.rgb(155, 155, 91), // 201-203
	Color.rgb(147, 147, 91), Color.rgb(135, 135, 87), Color.rgb(123, 127, 83), // 204-206
	Color.rgb(115, 119, 83), Color.rgb(107, 107, 79), Color.rgb(95, 99, 71), // 207-209
	Color.rgb(87, 91, 71), Color.rgb(147, 147, 91), Color.rgb(147, 127, 87), // 210-212
	Color.rgb(155, 135, 91), Color.rgb(163, 143, 95), Color.rgb(171, 151, 103), // 213-215
	Color.rgb(179, 155, 107), Color.rgb(187, 167, 115), Color.rgb(195, 175, 119), // 216-218
	Color.rgb(203, 183, 127), Color.rgb(211, 191, 131), Color.rgb(219, 199, 139), // 219-221
	Color.rgb(143, 91, 31), Color.rgb(255, 87, 255), Color.rgb(191, 191, 115), 
		// 222-224 224=blink dark
	Color.rgb(255, 87, 255), Color.rgb(223, 207, 143), Color.rgb(231, 215, 147), // 225-227
	Color.rgb(239, 223, 155), Color.rgb(247, 231, 163), Color.rgb(255, 239, 171), // 228-230
	Color.rgb(191, 191, 67), Color.rgb(0, 131, 0), Color.rgb(67, 67, 191), // 231-233
	Color.rgb(0, 0, 131), Color.rgb(103, 107, 87), Color.rgb(119, 127, 99), // 234-236
	Color.rgb(71, 167, 191), Color.rgb(155, 159, 175), Color.rgb(175, 159, 131), // 237-239
	Color.rgb(135, 143, 115), Color.rgb(159, 167, 131), Color.rgb(255, 35, 35), // 240-242
	Color.rgb(43, 199, 183), Color.rgb(43, 171, 199), Color.rgb(43, 127, 199), // 243-245
	Color.rgb(43, 83, 199), Color.rgb(47, 43, 199), Color.rgb(91, 43, 199), // 246-248
	Color.rgb(135, 43, 199), Color.rgb(179, 43, 199), Color.rgb(199, 43, 175), // 249-251
	Color.rgb(199, 43, 131), Color.rgb(199, 43, 87), Color.rgb(199, 43, 43), // 252-254
	Color.rgb(255, 119, 123)
	};                                   // 255 

	

	/**
	 * Read palette from file/input stream.
	 * @param file input stream to read. Read from the current position
	 * @throws IOException if there is any problem while reading the file
	 * @return the palette (array of Color)
	 */
	static int[] readPalette(InputStream file) throws IOException {
		int[] palette = new int[DEFAULTPALETTE.length];
	    
	    byte[] rawBuffer = new byte[INTSIZE];
	    file.read(rawBuffer);
	    ByteBuffer buffer = ByteBuffer.wrap(rawBuffer);
	    buffer.order(ByteOrder.LITTLE_ENDIAN);
	    
	    int count = buffer.getInt();
	    for (int i = 0; i < count; i++) {
	        int id = file.read();
	        if (id >= palette.length) {
	        	id = palette.length - 1;
	        }
	        int red   = file.read() * SHPCOLORCOEF;  
	        int green = file.read() * SHPCOLORCOEF;
	        int blue  = file.read() * SHPCOLORCOEF;
	        
	        palette[id] = Color.rgb(red, green, blue);
	    }
	    
	    return palette;
	}

	/**
	 * Read raw SHP icon data from file and draw it to 'surf' at 'y'
	 * interpreting the indices of the icon as palette entry indices.
	 * @param file input stream to read, using the current position
	 * @param surf where to draw the icon
	 * @param y  height offset for drawing it
	 * @param palette palette to use
	 * @param header icon header information
	 * @throws IOException if there is any problem while reading - the surface will be corrupted
	*/
	static void readIcon(InputStream file, Bitmap surf, int y, 
			int[] palette, IconHeader header) throws IOException {
	    int bytes, flag;
	    
	    Rect iconBound = header.getIconBound();
	    
	    int x = 0, i, y1 = iconBound.top;
	    int buf;
	    
	    /* read */
	    while (y1 <= iconBound.bottom) {
	    	buf = file.read();
	        flag = buf % 2; bytes = buf / 2;
	        if (bytes == 0 && flag == 1) { /* transparency */
	        	buf = file.read();
	            for (i = 0; i < buf; i++) {
	            	surf.setPixel(x++ + iconBound.left, y + y1, Color.TRANSPARENT);
	            }
	        } else {
	        	if (bytes == 0) { /* end of line */
	                y1++;
	                x = 0;
	            } else {
	                if (flag == 0) { /* byte row */
	                	buf = file.read();
	                    for (i = 0; i < bytes; i++) {
	                        surf.setPixel(x++ + iconBound.left, y + y1, palette[buf]);
	                    }
	                } else {
	                    /* bytes != 0 && flag == 1: read next bytes uncompressed */
	                    for (i = 0; i < bytes; i++) {
	                    	buf = file.read();
	                        surf.setPixel(x++ + iconBound.left, y + y1,  palette[buf]);
	                    }
	                }
	            }
	        }
	    }
	}

	/** 
	 * Load a SHP file to a PG_Shp.
	 * @throws IOException if there is any error while reading
	 * @param file input stream to use for reading, the input stream must support marking 
	 * @return the structure containing the bitmap loaded from the file or null 
	 * if the file stream doesn't support marking.
	 */
	public static IconResources load(InputStream file)
			throws IOException {
		if (!file.markSupported()) {
			return null;
		}
		
		file.mark(-1);
	    IconResources result = new IconResources(); 
	    
	    /* magic */
	    file.skip(INTSIZE);
	    
	    Vector<Pair<Integer, Integer>> headerAndPalettePositions = 
	    		readHeaderAndPalettePositions(file);	        
	    if (headerAndPalettePositions == null) {
	    	return null;
	    }
	    
	    Point size = readPalettes(file, result, headerAndPalettePositions);
	    
	    result.createSurface(size.x, size.y);
	    		
	    readIcons(file, result, headerAndPalettePositions);
	    return result;
	}

	/**
	 * Read the palettes from the input stream.
	 * @param file the input stream
	 * @param result the PGShp where the palettes will be set
	 * @param headerAndPalettePositions position information
	 * @throws IOException if there is any error while reading
	 * @return Point which is the size for a bitmap to contains all the icons stack vertically
	 */
	private static Point readPalettes(InputStream file, IconResources result,
			Vector<Pair<Integer, Integer>> headerAndPalettePositions)
			throws IOException {
		Point size = new Point();
	    for (Pair<Integer, Integer> headerPosition:headerAndPalettePositions) {
	    	result.getOffsets().add(size.y);
	    	file.reset();
	        file.skip(headerPosition.first);
	        
	        /* read header */
	        IconHeader header = IconHeader.read(file);
	        
	        size.x = Math.max(header.getWidth(), size.x);
	        size.y += header.getHeight();
	       
	        result.getHeaders().add(header);
	    }
	    return size;
	}

	/**
	 * Read the icons from the input stream.
	 * @param file the input stream
	 * @param result the PGShp where the icons will be set
	 * @param headerAndPalettePositions position information
	 * @throws IOException if there is any error while reading
	 */
	private static void readIcons(InputStream file, IconResources result,
			Vector<Pair<Integer, Integer>> headerAndPalettePositions)
			throws IOException {
		/* read icons */
	    for (int i = 0; i < headerAndPalettePositions.size(); i++) {
	    	Pair<Integer, Integer> headerAndPalettePosition = 
	    			headerAndPalettePositions.get(i);
	    	
	    	file.reset();
	    	int[] actualPal = DEFAULTPALETTE;
	    	
	    	/* read palette if needed */
	        if (headerAndPalettePosition.second > 0) {
	        	file.skip(headerAndPalettePosition.second);
	        	actualPal = readPalette(file);
	        }
	        
	        file.reset();
	        file.skip(headerAndPalettePosition.first);
	        file.skip(IconHeader.SIZE); //skip the header since we already have it !
	        
	        IconHeader header = result.getHeaders().get(i);
	        if (header.isValid()) {
	        	readIcon(file, result.getSurface(), result.getOffsets().get(i), 
	        			actualPal, result.getHeaders().get(i));
	        }
	    }
	}

	/**
	 * Read the header and palette positions per icon.
	 * @param file input stream.
	 * @return list of data <header,palette> for each icon
	 * @throws IOException if any errors occurs while reading
	 */
	private static Vector<Pair<Integer, Integer>> readHeaderAndPalettePositions(
			InputStream file) throws IOException {
		/* icon count */
	    byte[] rawBuffer =  new byte[INTSIZE];
	    file.read(rawBuffer);
	    ByteBuffer buffer = ByteBuffer.wrap(rawBuffer);
	    buffer.order(ByteOrder.LITTLE_ENDIAN);
	    int count = buffer.getInt();
	    
	    if (count == 0) {
	        System.err.println("no icons found");
	        return null;
	    }
	    rawBuffer = new byte[(INTSIZE + INTSIZE) * count]; // two int, header and data position
	    file.read(rawBuffer);
	    buffer = ByteBuffer.wrap(rawBuffer);
	    buffer.order(ByteOrder.LITTLE_ENDIAN);
	    
	    Vector<Pair<Integer, Integer>> headerAndPalettePositions = 
	    		new Vector<Pair<Integer, Integer>>();
	    /* create surface (measure size first) */
	    for (int i = 0; i < count; i++) {
	        /* read file position of actual data and palette */	
	    	headerAndPalettePositions.add(
	    			new Pair<Integer, Integer>(buffer.getInt(), buffer.getInt()));
	    }
		return headerAndPalettePositions;
	}
}
