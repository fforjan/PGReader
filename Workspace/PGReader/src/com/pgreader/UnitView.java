package com.pgreader;

import com.panzergeneral.shp.IconHeader;
import com.panzergeneral.shp.PGShp;
import com.panzergeneral.shp.ShpReader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;



public class UnitView extends View {
	
	PGShp shpIcons = null;
	
	private int iconIndex;
	public void setIconIndex(int index)
	{
		iconIndex= index;
		this.invalidate();
	}

	public UnitView(Context context, PGShp icons) {
		super(context);
		shpIcons = icons;
		iconIndex = -1;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(iconIndex != -1)
		{
			// called when view is drawn
			Paint paint = new Paint();
			paint.setFilterBitmap(true);
			
			IconHeader currentIcon = shpIcons.headers.get(iconIndex);
			Rect source = new Rect(0, shpIcons.offsets.get(iconIndex), currentIcon.actual_width, shpIcons.offsets.get(iconIndex) + currentIcon.actual_height);
			// The image will be scaled so it will fill the width, and the
			// height will preserve the image’s aspect ration
			double aspectRatio = ((double) source.width()) / source.height();
			Rect dest = new Rect(0, 0, this.getWidth(),(int) (this.getHeight() / aspectRatio));
			canvas.drawBitmap(shpIcons.surface, source, dest, null);
		}
	}

}
