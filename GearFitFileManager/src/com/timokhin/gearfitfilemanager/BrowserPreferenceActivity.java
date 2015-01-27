package com.timokhin.gearfitfilemanager;

import com.timokhin.gearfitfilemanager.utils.Debug;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

/**
 * Preference activity that set values if shared preferences.
 * @author timokhin
 *
 */

public class BrowserPreferenceActivity extends PreferenceActivity {
	
	private DialogDisplayPreference pref;
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedUnstanceState) {
		super.onCreate(savedUnstanceState);
		Debug.out("DialogDisplayPreference");
		addPreferencesFromResource(R.xml.activity_browser_preference);
		pref = new DialogDisplayPreference(BrowserPreferenceActivity.this);
		
		Preference textSizePref = (Preference)findPreference("textSize");
		textSizePref.setDefaultValue(pref.getTextSize());
		textSizePref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				pref.setTextSize(Integer.valueOf(newValue.toString()));
				return true;
			}
		});
		
		Preference fileSizePref = (Preference)findPreference("browserTextSize");
		fileSizePref.setDefaultValue(pref.getFilenameSize());
		fileSizePref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				pref.setFilenameSize(Integer.valueOf(newValue.toString()));
				pref.setFileDesctSize(Integer.valueOf(newValue.toString()) - 2);
				return true;
			}
		});
	}
}
