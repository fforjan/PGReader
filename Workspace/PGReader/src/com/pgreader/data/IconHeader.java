package com.pgreader.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Icon header. (24 byte)
 * height:        height of icon - 1
 * width:         width of icon - 1
 * x1,x2,y1,y2:   position and size of actual icon
 * actual_width:  
 * actual_height: size of rect (x1,y1,x2,y2)
 */
public class IconHeader {
    
	/**
	 * maximum width for an icon.
	 */
	private static final int ICONMAXWIDTH = 60;
	
	/**
	 * maximum height for an icon.
	 */
	private static final int ICONMAXHEIGHT = 50;
	
	/**
	 * size of header.
	 */
	public static final int SIZE = 24;
	
	/**
	 * height of the icon.
	 */
	private int mHeight; 
	
	/**
	 * @return height of the icon
	 */
	public int getHeight() {
		return mHeight;
	}
	
	/**
	 * width of the icon.
	 */
	private int mWidth; 
	
	/**
	 * @return width of the icon
	 */
	public int getWidth() {
		return mWidth;
	}
	
	/**
	 * Point for the center of the icon.
	 */
	private Point mCenter;
	
	/**
	 * the current iconBound within the image.
	 */
	private Rect mIconBound;
	
	/**
	 * true if  x1,x2,y1,y2 make senses else false.
	 */
	private boolean mIsValid; 
    
    /**
	 *Read icon header from file.
	 * @param file input stream to use for reading the header
	 * @throws IOException if there is any issues with the file
	 * @return the header 
	 */
	public static IconHeader read(InputStream file)
			throws IOException {
	    IconHeader header = new IconHeader();
	    
	    byte[] rawBuffer = new byte[SIZE];
	    file.read(rawBuffer);
	    ByteBuffer buffer = ByteBuffer.wrap(rawBuffer);
	    buffer.order(ByteOrder.LITTLE_ENDIAN);
	    
	    header.mHeight = buffer.getShort();
	    header.mHeight++; /* if y1 == y2 it is at least one line anyway */
	    
	    header.mWidth = buffer.getShort();
	    header.mWidth++;
	 
	    int cx = buffer.getShort();
	    int cy = buffer.getShort();
	    header.mCenter = new Point(cx, cy);
	    
	    int left = buffer.getInt();
	    int top = buffer.getInt();
	    
	    int right = buffer.getInt();
	    int bottom = buffer.getInt();
	    
	    header.mIconBound = new Rect(left, top, right, bottom);
	    
	    header.validateHeader();
		return header;
	}

	/**
	 * update the validate property and correct any issues if possible.
	 */
	private void validateHeader() {
		
		this.mIsValid = true;
	    
		if (this.mIconBound.left >= this.mWidth || this.mIconBound.right >= this.mWidth) {
	    	this.mIsValid = false;
	    }
	    if (this.mIconBound.top >= this.mHeight || this.mIconBound.bottom >= this.mHeight) {
	    	this.mIsValid = false;
	    }
	    if (this.mIconBound.left < 0) {
	    	this.mIconBound.left = 0;
	    }
	    if (this.mIconBound.top < 0) {
	    	this.mIconBound.top = 0;
	    }
	    
	    /* if icon is too large, ignore it and replace with an empty
         * icon of maximum size; use hard coded limit which is basically okay
         * as we convert PG data and can assume the icons have size of map
         * tile at maximum. */
        if (this.mWidth > ICONMAXWIDTH || this.mHeight > ICONMAXHEIGHT) {
            System.err.format("Icon is too large (%dx%d), replacing with empty icon\n", 
            		 this.mWidth, this.mHeight);
            this.mWidth = ICONMAXWIDTH;
            this.mHeight = ICONMAXHEIGHT;
            this.mIsValid = false;
        }
	}

	/**
	 * @return true if the header information are valid else false
	 */
	public boolean isValid() {
		return mIsValid;
	}

	/**
	 * @return the center
	 */
	public Point getCenter() {
		return mCenter;
	}

	/**
	 * @return the iconBound
	 */
	public Rect getIconBound() {
		return mIconBound;
	}
}

