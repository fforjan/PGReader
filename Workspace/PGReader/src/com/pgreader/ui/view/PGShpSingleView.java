package com.pgreader.ui.view;

import com.pgreader.data.IconHeader;
import com.pgreader.data.IconResources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;


/**
 * This class is displaying one item of the PGShp.
 * It supports updating the current icon by using the setIconIndex method
 * @author FREDERIC FORJAN <GeoVah>
 *
 */
public class PGShpSingleView extends View {
	
	/**
	 * the SHP containing the icons to be displayed.
	 */
	private IconResources mShpIcons = null;
	
	/**
	 * index of  the icon to be displayed.
	 */
	private int mIconIndex;
	
	/**
	 * set the index of the icon to be displayed.
	 * This will also trigger an update of the view
	 * @param index new icon index
	 * @remarks calling with index < 0 will display no icons
	 */
	public void setIconIndex(int index) {
		mIconIndex = index;
		this.invalidate();
	}

	/***
	 * instantiate a new view.
	 * The background color would be magenta by default.
	 * There is no icon set by default
	 * @param context context
	 */
	public PGShpSingleView(Context context) {
		super(context);
		throw new UnsupportedOperationException();
	}
	
	/***
	 * instantiate a new view.
	 * The background color would be magenta by default.
	 * There is no icon set by default
	 * @param context context
	 * @param icons SHP file to be used
	 */
	public PGShpSingleView(Context context, IconResources icons) {
		super(context);
		mShpIcons = icons;
		mIconIndex = -1;
		this.setBackgroundColor(Color.MAGENTA);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		final int iPadding = 5;
		
		if (mIconIndex > 0) {
			IconHeader currentIcon = mShpIcons.getHeaders().get(mIconIndex);
			
			Rect source = new Rect(currentIcon.getIconBound());
			source.offset(0, mShpIcons.getOffsets().get(mIconIndex));
			
			// The image will be scaled so it will fill the width, and the
			// height will preserve the image’s aspect ration
			double aspectRatio = ((double) source.width()) / source.height();
			Rect dest = new Rect(iPadding, iPadding, this.getWidth() - iPadding,
					(int) ((this.getHeight() - iPadding) / aspectRatio));
			canvas.drawBitmap(mShpIcons.getSurface(), source, dest, null);
		}
	}

}
