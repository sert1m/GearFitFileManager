package com.timokhin.gearfitfilemanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Shared preference that contains setting for displaying text on Gear Fit
 * @author timokhin
 *
 */

public class DialogDisplayPreference {
	
	private SharedPreferences prefs;
	
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	public DialogDisplayPreference(Activity activity) {
		prefs = activity.getSharedPreferences("FileBrowserPref", Activity.MODE_WORLD_READABLE);
	}
	
    public int getTextSize(){
        return prefs.getInt("text size", 4);        
    }
     
    public void setTextSize(int size){
        prefs.edit().putInt("text size", size).commit();
    }
    
    public int getFilenameSize(){
        return prefs.getInt("filename size", 6);        
    }
     
    public void setFilenameSize(int size){
        prefs.edit().putInt("filename size", size).commit();
    }
    
    public int getFileDescrSize() {
    	return prefs.getInt("file description size", 4);
    }
    
    public void setFileDesctSize(int size) {
    	prefs.edit().putInt("file description size", size).commit();
    }
}
