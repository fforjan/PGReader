package com.pgreader.legacy;

import java.io.IOException;
import java.io.InputStream;

import com.pgreader.R;
import com.pgreader.data.DataRepository;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.AsyncTask;

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
	 * provide class back for updating progress status.
	 * @author GeoVah
	 *
	 */
	public interface ProgressStatus {
		/**
		 * set the secondary status.
		 * @param percentage percentage
		 */
		void setSecondaryStatus(float percentage);
	}

	/**
	 * Our async task loading data in background.
	 * @author GeoVah
	 */
	private static class LoadLegacyTask 
		extends AsyncTask <Void, Object, Void>
		implements ProgressStatus {
		
		/**
		 * value for the maximum for the progress bar.
		 * using 100 as 100%
		 */
		private static final int MAX = 100;

		/**
		 * progress dialog.
		 */
	    private ProgressDialog mProgressDialog;
	    
	    /**
	     * context to be use within the task.
	     */
	    private Context mContext;
	    
	    /**
	     * our task constructor.
	     * @param context context to use
	     */
	    public LoadLegacyTask(Context context) {
	    	this.mContext = context;
	    }
	    
	    @Override
	    protected void onPreExecute() {
	    	mProgressDialog = ProgressDialog.show(mContext, mContext.getString(R.string.pleaseWait),
					mContext.getString(R.string.loadingData), false, false);
	    	mProgressDialog.setMax(MAX);
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	try {
	    		final float unitSteps = 0;
	    		final float iconSteps = 0.33f;
	    		final float flagSteps = 0.66f;
	    		Resources resources = mContext.getResources();
				publishProgress(mContext.getString(R.string.loadingUnit), unitSteps, 0f);
				UnitReader.loadUnits(resources, this);
				publishProgress(mContext.getString(R.string.loadingIcons), iconSteps, 0.0f);
				UnitReader.loadIcons(resources, this);
				
				publishProgress(mContext.getString(R.string.loadingFlags), flagSteps, 0.0f);
				loadFlags(resources, this);
				
			} catch (IOException e) {
				AlertDialog ad = new AlertDialog.Builder(mContext).create();  
				ad.setCancelable(false); // This blocks the 'BACK' button  
				ad.setMessage(mContext.getString(R.string.pg_data_corrupted));
				ad.setButton(mContext.getString(R.string.closeDialog), 
						new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialog, int which) {  
						dialog.dismiss();                      
					}  
				});  
				ad.show(); 
			}
	    	return null;
	    }
	    
	    @Override
	    protected void onProgressUpdate(Object... progress) {
	    	if (progress[0] != null) {
	    		mProgressDialog.setMessage((String) progress[0]);
	    	}
	    	if (progress[1] != null) {
	    		mProgressDialog.setProgress(
	    				(int) (mProgressDialog.getMax() * ((Float) progress[1])));
	    	}
	    	mProgressDialog.setSecondaryProgress(
	    			(int) (mProgressDialog.getMax() * ((Float) progress[2])));
		}

	    @Override
	    protected void onPostExecute(Void params) {
	    	mProgressDialog.dismiss();
	    }
	    
	    /**
		 * set the secondary status.
		 * @param percentage percentage
		 */
		public void setSecondaryStatus(float percentage) {
			publishProgress(null, null, percentage);
		}
		
		/**
		 * load flags from resources.
		 * @param resources resources
		 * @param callback callback
		 * @throws IOException  if there is any IO error
		 */
		private static void loadFlags(Resources resources, ProgressStatus callback) 
				throws IOException {
			InputStream flagshp = resources.openRawResource(R.raw.flags);
			DataRepository.setFlags(ShpReader.load(flagshp, callback));
		}
	}

	
	/**
	 * load all resources.
	 * @param context context
	 */
	public static void loadData(Context context) {
		new LoadLegacyTask(context).execute();
	}

}
