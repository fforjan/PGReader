package com.pgreader;

import com.panzergeneral.shp.IconHeader;
import com.panzergeneral.shp.PGShp;
import com.panzergeneral.shp.ShpReader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;



public class PGShpSingleView extends View {
	
	PGShp shpIcons = null;
	
	private int iconIndex;
	public void setIconIndex(int index)
	{
		iconIndex= index;
		this.invalidate();
	}

	public PGShpSingleView(Context context, PGShp icons) {
		super(context);
		shpIcons = icons;
		iconIndex = -1;
		this.setBackgroundColor(Color.MAGENTA);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(iconIndex != -1)
		{
			IconHeader currentIcon = shpIcons.headers.get(iconIndex);
			Rect source = new Rect(currentIcon.x1, shpIcons.offsets.get(iconIndex) + currentIcon.y1, currentIcon.x2, shpIcons.offsets.get(iconIndex) + currentIcon.y2);
			// The image will be scaled so it will fill the width, and the
			// height will preserve the image’s aspect ration
			double aspectRatio = ((double) source.width()) / source.height();
			Rect dest = new Rect(5, 5, this.getWidth()-5,(int) ((this.getHeight()-5) / aspectRatio));
			canvas.drawBitmap(shpIcons.surface, source, dest, null);
		}
	}

}
