package com.timokhin.gearfitfilemanager.utils;

import android.util.Log;

public class Debug {
	
	private Debug() {}
	
	public static void out(Object paramObject) {
		if (paramObject != null)
			Log.i("info", paramObject.toString());
		else
			Log.i("info", "print null message");
	}
	public static void exception(String tag, Exception e) {
		if (e != null) 
			Log.e(tag, "exception", e);
		else
			Log.e(tag, "null exception");
	}
}
