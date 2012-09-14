package com.pgreader.legacy;

import java.io.IOException;
import java.io.InputStream;

import com.pgreader.R;
import com.pgreader.data.DataRepository;
import com.pgreader.data.MoveType;
import com.pgreader.data.SoundManager;

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
	     * define the beginning of the progress bar
	     * this is used to update the second part.
	     */
	    private float mCurrentStepStarts;
	    
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
	    	mProgressDialog = new ProgressDialog(mContext);
	    	
	    	mProgressDialog.setTitle(R.string.pleaseWait);
	    	mProgressDialog.setMessage(mContext.getString(R.string.loadingData));
	    	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    	mProgressDialog.setMax(MAX);
	    	mProgressDialog.setProgress(0);
	    	mProgressDialog.setCancelable(false);
	    	mProgressDialog.setIndeterminate(false);
	    	mProgressDialog.setIcon(R.drawable.panzer_general_icon);
	    	mProgressDialog.show();
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	try {
	    		final float unitSteps = 0;
	    		final float iconSteps = 0.33f;
	    		final float flagSteps = 0.66f;
	    		
	    		Resources resources = mContext.getResources();
				publishProgress(mContext.getString(R.string.loadingUnit), unitSteps);
				UnitReader.loadUnits(resources, this);
				publishProgress(mContext.getString(R.string.loadingIcons), iconSteps);
				UnitReader.loadIcons(resources, this);
				
				publishProgress(mContext.getString(R.string.loadingFlags), flagSteps);
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
	    	final float stepSize  = 0.33f;
	    	
	    	if (progress.length == 2) {
	    		mProgressDialog.setMessage((String) progress[0]);
	    	
	    		mCurrentStepStarts = (Float) progress[1];
	    		mProgressDialog.setProgress(
	    				(int) (mProgressDialog.getMax() * mCurrentStepStarts));
	    	} else {
	    		float currentProgress = mCurrentStepStarts
	    				+ (stepSize * ((Float) progress[0]));
	    		mProgressDialog.setProgress(
	    				(int) (mProgressDialog.getMax() * currentProgress));
	    	}
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
			publishProgress(percentage);
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
		
		registerSound();
		new LoadLegacyTask(context).execute();
	}
	
	/**
	 * register sound into our sound system.
	 */
	private static void registerSound() {
		SoundManager.registerSound(MoveType.Air, R.raw.air);
		SoundManager.registerSound(MoveType.Leg, R.raw.leg);
		SoundManager.registerSound(MoveType.Naval, R.raw.sea);
		SoundManager.registerSound(MoveType.Tracked, R.raw.tracked);
		SoundManager.registerSound(MoveType.HalfTracked, R.raw.tracked);
		SoundManager.registerSound(MoveType.Wheeled, R.raw.wheeled);
	}

}
