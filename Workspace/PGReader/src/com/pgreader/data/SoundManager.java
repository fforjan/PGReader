package com.pgreader.data;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * provide sound management.
 */
public final class SoundManager {

	/**
	 * private constructor for utility class.
	 */
	private SoundManager() {
		
	}
	/**
	 * our sound library.
	 * Mapping between any object and a resource id
	 */
	private static Map<Object, Integer> sSoundLibrary = new HashMap<Object, Integer>();

	/**
	 * check if the sound library contains a specific object.
	 * @param object object id
	 * @return true if a sound is register for it
	 */
	public static boolean containsSound(Object object) {
		return SoundManager.sSoundLibrary.containsKey(object);
	}
	
	/**
	 * register a sound into the resource library.
	 * @param object object 
	 * @param resourceId resource id
	 */
	public static void registerSound(Object object, Integer resourceId) {
		SoundManager.sSoundLibrary.put(object, resourceId);
	}
	
	/**
	 * play current sound.
	 * @remarks if there is no sound register for the object, nothing is happening.
	 * @param object object id 
	 * @param context context
	 */
	public static void playSound(Object object, Context context) {
		
		if (SoundManager.containsSound(object)) {
			MediaPlayer mp = MediaPlayer.create(context, SoundManager.sSoundLibrary.get(object));

			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.release();
				}

			});   
			mp.start();
		}
	}
}
