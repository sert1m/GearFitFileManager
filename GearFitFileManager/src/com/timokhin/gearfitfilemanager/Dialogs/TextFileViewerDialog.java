package com.timokhin.gearfitfilemanager.Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.app.Activity;
import android.content.Context;

import com.samsung.android.sdk.cup.ScupDialog;
import com.samsung.android.sdk.cup.ScupLabel;
import com.timokhin.gearfitfilemanager.DialogDisplayPreference;
import com.timokhin.gearfitfilemanager.utils.Debug;
/**
 * Dialog for displaying text file content
 * @author timokhin
 *
 */
public class TextFileViewerDialog extends ScupDialog {

	private ScupLabel labelText;
	private RandomAccessFile file;
	private final static int count = 1000;
	
	public TextFileViewerDialog(Context arg0, File file) {
		super(arg0);
		try {
			this.file = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			Debug.exception("TextFileViewerDialog", e);
			showToast("File not found!", ScupDialog.TOAST_DURATION_SHORT);
		}
	}
	
	@Override
	protected void onCreate() {
		super.onCreate();
		
		DialogDisplayPreference pref = new DialogDisplayPreference((Activity)getContext());
		
		labelText = new ScupLabel(this);
		labelText.setSingleLineModeEnabled(false);
		labelText.setTextSize(pref.getTextSize());
		// Navigation through file content by user click
		this.setGestureListener(new GestureListener() {			
			@Override
			public void onSingleTap(ScupDialog arg0, float x, float y) {
				if (x < 40) 
					prev();
				else
					next();				
			}
			
			@Override
			public void onDoubleTap(ScupDialog arg0, float arg1, float arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		next();
		
		labelText.show();
		setBackPressedListener(new BackPressedListener() {
			
			@Override
			public void onBackPressed(ScupDialog arg0) {
				try {
					file.close();
				} catch (IOException e) {
					Debug.exception("TextFileViewerDialog", e);
					showToast("Cannot close file!", ScupDialog.TOAST_DURATION_SHORT);
				}
				finish();
			}
		});
	}
	
	private void next() {
		try {
			byte buffer [] = new byte [count];
			file.read(buffer);
			labelText.setText(new String(buffer, "windows-1251"));
			update();
		} catch (IOException e) {
			Debug.exception("TextFileViewerDialog", e);
			showToast("Read error!", ScupDialog.TOAST_DURATION_SHORT);
		}
		
	}
	private void prev() {
		try {
			Debug.out("Before a new offset " + file.getFilePointer());
			long newOffset = file.getFilePointer() - 2 * count;
			file.seek((newOffset <= 0) ? 0 : newOffset);
			Debug.out("After a new offset " + file.getFilePointer());
			next();
		} catch (IOException e) {
			Debug.exception("TextFileViewerDialog", e);
			showToast("Read error!", ScupDialog.TOAST_DURATION_SHORT);
		}		
	}
}
