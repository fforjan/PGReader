package com.pgreader.ui.view;

import com.pgreader.data.IconHeader;
import com.pgreader.data.IconResources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


/**
 * This class is displaying one item of the PGShp.
 * It supports updating the current icon by using the setIconIndex method
 * @author FREDERIC FORJAN <GeoVah>
 *
 */
public class IconsResourceSingleView extends View {
	
	/**
	 * the SHP containing the icons to be displayed.
	 */
	private IconResources mIcons = null;
	
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
	/**
	 * set the icons resources.
	 * @param icons icons resources
	 */
	public void setIconResources(IconResources icons) {
		if (icons != this.mIcons) {
			this.mIcons = icons;
		}
	}

	/***
	 * instantiate a new view.
	 * The background color would be magenta by default.
	 * There is no icon set by default
	 * @param context context
	 */
	public IconsResourceSingleView(Context context) {
		super(context);
		mIcons = null;
		mIconIndex = -1;
	}
	
	/***
	 * instantiate a new view.
	 * The background color would be magenta by default.
	 * There is no icon set by default
	 * @param context context
	 * @param attrs attribute set
	 */
	public IconsResourceSingleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mIcons = null;
		mIconIndex = -1;
	}
	
	/***
	 * instantiate a new view.
	 * The background color would be magenta by default.
	 * There is no icon set by default
	 * @param context context
	 * @param attrs attribute set
	 * @param defStyle default style
	 */
	public IconsResourceSingleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setBackgroundColor(Color.MAGENTA);
		mIcons = null;
		mIconIndex = -1;
	}
	
	 

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		final int iPadding = 5;
		
		if (mIconIndex > 0 && mIcons != null) {
			IconHeader currentIcon = mIcons.getHeaders().get(mIconIndex);
			
			Rect source = new Rect(currentIcon.getIconBound());
			source.offset(0, mIcons.getOffsets().get(mIconIndex));
			
			// The image will be scaled so it will fill the width, and the
			// height will preserve the image’s aspect ration
			double aspectRatio = ((double) source.width()) / source.height();
			Rect dest = new Rect(iPadding, iPadding, this.getWidth() - iPadding,
					(int) ((this.getHeight() - iPadding) / aspectRatio));
			canvas.drawBitmap(mIcons.getSurface(), source, dest, null);
		}
	}

}
