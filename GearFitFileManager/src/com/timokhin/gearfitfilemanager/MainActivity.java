package com.timokhin.gearfitfilemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.cup.Scup;
import com.timokhin.gearfitfilemanager.Dialogs.FileBrowserDialog;
import com.timokhin.gearfitfilemanager.utils.Debug;

/**
 * Main activity class. It contains setting menu and initialize 
 * SCUP (Samsung Companion User Interface Profile) and creates 
 * a widget on wearable device side.
 * @author timokhin
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		try {
			// For displaying data on wearable device, scup must be initialized.
			Scup scup = new Scup();
			scup.initialize(this);
			// Creating a dialog on wearable device side.
			new FileBrowserDialog(this, Environment.getExternalStorageDirectory());
		} catch (SsdkUnsupportedException e) {
			Debug.exception("MainActivity", e);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, BrowserPreferenceActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
