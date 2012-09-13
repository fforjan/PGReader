package com.panzergeneral.shp; 

import java.util.List;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * define a SHP document.
 * this contains one bitmap where all icons are stack vertically
 * and the information for the icon and the offset into this image.
 * @author FREDERIC FORJAN
 */
public class PGShp {
	
	/**
	 * surface for the icons.
	 */
	private Bitmap mSurface;
	
	/**
	 * list of header per icon.
	 */
	private List<IconHeader> mHeaders = new Vector<IconHeader>();
	
	/**
	 * list of start offset in the surface per icon.
	 */
    private List<Integer> mOffsets = new Vector<Integer>();
    
    /**
     * @return surface
     */
	public Bitmap getSurface() {
		return mSurface;
	}
	
	/**
	 * create the surface for the targeting information.
	 * The background will be set to transparent
	 * @param width targeted width
	 * @param height targeted height
	 */
	public void createSurface(int width, int height) {
		this.mSurface = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		this.mSurface.eraseColor(Color.TRANSPARENT);
	}
	
	/**
	 * @return the headers
	 */
	public List<IconHeader> getHeaders() {
		return mHeaders;
	}

	/**
	 * @return the offsets
	 */
	public List<Integer> getOffsets() {
		return mOffsets;
	}
}
