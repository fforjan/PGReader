package com.pgreader.legacy;

import java.io.IOException;

import com.pgreader.R;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;

/**
 * utility class for loading resources from the legacy panzer general.
 * @author GeoVah
 */
public final class LegacyReader {

	/**
	 * private constructor for utility class.
	 */
	private LegacyReader() {

	}

	/**
	 * load all resources.
	 * @param context context
	 */
	public static void loadData(Context context) {
		try {
			Resources resources = context.getResources();
			UnitReader.loadUnits(resources);
			UnitReader.loadIcons(resources);
		} catch (IOException e) {
			AlertDialog ad = new AlertDialog.Builder(context).create();  
			ad.setCancelable(false); // This blocks the 'BACK' button  
			ad.setMessage(context.getString(R.string.pg_data_corrupted));
			ad.setButton(context.getString(R.string.closeDialog), 
					new DialogInterface.OnClickListener() {  
						public void onClick(DialogInterface dialog, int which) {  
							dialog.dismiss();                      
						}  
			});  
			ad.show(); 
		}
	}
	
}
