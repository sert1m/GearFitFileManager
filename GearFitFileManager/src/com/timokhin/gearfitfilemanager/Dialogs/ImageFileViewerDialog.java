package com.timokhin.gearfitfilemanager.Dialogs;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.samsung.android.sdk.cup.ScupDialog;
import com.samsung.android.sdk.cup.ScupLabel;
/**
 * Dialog for dsplaying image
 * @author timokhin
 *
 */
public class ImageFileViewerDialog extends ScupDialog {

	private ScupLabel imageLabel;
	private File file;
	
	public ImageFileViewerDialog(Context arg0, File file) {
		super(arg0);
		this.file = file;
	}
	
	@Override
	protected void onCreate() {
		super.onCreate();
		imageLabel = new ScupLabel(this);
		@SuppressWarnings("deprecation")
		Bitmap image = new BitmapDrawable(file.getAbsolutePath()).getBitmap();
		image = Bitmap.createScaledBitmap(image, 432, 128, false);
		imageLabel.setIcon(image);
		imageLabel.show();
		setBackPressedListener(new BackPressedListener() {
			
			@Override
			public void onBackPressed(ScupDialog arg0) {
				finish();
				
			}
		});
	}

}
