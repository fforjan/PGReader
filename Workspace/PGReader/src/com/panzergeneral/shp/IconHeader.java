package com.panzergeneral.shp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *Icon header. (24 byte)
 * height:        height of icon - 1
 * width:         width of icon - 1
 * cx, cy:        center_x, center_y ???
 * x1,x2,y1,y2:   position and size of actual icon
 * actual_width:  
 * actual_height: size of rect (x1,y1,x2,y2)
 *valid:         do x1,x2,y1,y2 make sense?
 */
public class IconHeader {
    
	public int height; 
	public int width; 
	public int cx, cy;  
	public int x1, y1, x2, y2;
	public int actual_width;
	public int actual_height;
	public boolean valid; 
    
    /**
	 *Read icon header from file pos.
	 * @param file input stream to use for reading the header
	 * @throws IOException if there is any issues with the file
	 * @return the header 
	 */
	public static IconHeader read(InputStream file)
			throws IOException {
	    IconHeader header = new IconHeader();
	    
	    byte[] rawBuffer = new byte[24];
	    file.read(rawBuffer);
	    ByteBuffer buffer = ByteBuffer.wrap(rawBuffer);
	    buffer.order(ByteOrder.LITTLE_ENDIAN);
	    
	    header.height = buffer.getShort();
	    header.height++; /* if y1 == y2 it is at least one line anyway */
	    
	    header.width = buffer.getShort();
	    header.width++;
	 
	    header.cx = buffer.getShort();
	    header.cy = buffer.getShort();
	    
	    header.x1 = buffer.getInt();
	    header.y1 = buffer.getInt();
	    
	    header.x2 = buffer.getInt();
	    header.y2 = buffer.getInt();
	    
	    header.valid = true;
	    if (header.x1 >= header.width || header.x2 >= header.width) {
	        header.valid = false;
	    }
	    if (header.y1 >= header.height || header.y2 >= header.height) {
	        header.valid = false;
	    }
	    if (header.x1 < 0) {
	        header.x1 = 0;
	        header.actual_width = header.x2;
	    } else {
	        header.actual_width = header.x2 - header.x1 + 1;
	    }
	    if (header.y1 < 0) {
	        header.y1 = 0;
	        header.actual_height = Math.abs(header.y1) + header.y2 + 1;
	    } else {
	        header.actual_height = header.y2 - header.y1 + 1;
	    }
		return header;
	}
}

