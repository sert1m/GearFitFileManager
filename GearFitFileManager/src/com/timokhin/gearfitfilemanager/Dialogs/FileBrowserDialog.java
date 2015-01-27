package com.timokhin.gearfitfilemanager.Dialogs;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.samsung.android.sdk.cup.ScupDialog;
import com.samsung.android.sdk.cup.ScupListBox;
import com.samsung.android.sdk.cup.ScupListBox.ItemClickListener;
import com.timokhin.gearfitfilemanager.DialogDisplayPreference;
import com.timokhin.gearfitfilemanager.R;
import com.timokhin.gearfitfilemanager.utils.FileTypeDetector;

/**
 * Class that displays files in specified directory.
 * @author timokhin
 *
 */

@SuppressLint("SimpleDateFormat")
public class FileBrowserDialog extends ScupDialog {
	
	private File file;
	private ScupListBox list;
	private DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
	
	public FileBrowserDialog(Context arg0, File file) {
		super(arg0);
		this.file = file;
	}
	@Override
	protected void onCreate() {
		super.onCreate();
		// Get Preferences
		DialogDisplayPreference pref = new DialogDisplayPreference((Activity)getContext());
		
		setTitle(file.getPath());
		list = new ScupListBox(this);
		// Get file list and configure new item in ListBox
		File [] files = file.listFiles();
		if (files != null) {
			Arrays.sort(files);
			
			list.setWidth(ScupListBox.FILL_DIALOG);
			list.setItemMainTextSize(pref.getFilenameSize());
			list.setItemSubTextSize(pref.getFileDescrSize());
			list.setItemClickListener(new ItemClickListener() {
							
				@Override
				public void onClick(ScupListBox list, int id, int groupId, boolean buttonState) {
					File file = new File(FileBrowserDialog.this.file.getAbsolutePath() + File.separator + list.getItemMainText(id));
					// If clicked file is directory, crate a new FileBrowser Dialog
					if (file.isDirectory())
						new FileBrowserDialog(FileBrowserDialog.this.getContext(), file);
					else {
						// if cicked file is text file or image: display file content
						if (FileTypeDetector.isTextFile(file))
							new TextFileViewerDialog(FileBrowserDialog.this.getContext(), file);
						if (FileTypeDetector.isImageFile(file)) 
							new ImageFileViewerDialog(FileBrowserDialog.this.getContext(), file);
					}
				}
			});
			
			int index = 0;
			for(File f : files) {
				if (f.isHidden())
					continue;
				
				list.addItem(index, f.getName());
				StringBuilder subtext = new StringBuilder(formatter.format(new Date(f.lastModified())) + " ");
				
				if (f.isDirectory()) {
					list.setItemIcon(index, getIcon(R.drawable.directory));
					subtext.append("d");
				}
				else {
					list.setItemIcon(index, getIcon(R.drawable.file));
					subtext.append("-");
				}
				
				subtext.append(f.canRead() ? "r" : "-");
				subtext.append(f.canWrite() ? "w" : "-");
				subtext.append(f.canExecute() ? "x" : "-");

				list.setItemSubText(index, subtext.toString());
				index++;
			}
		}
		
        setBackPressedListener(new BackPressedListener() {			        	
        	@Override
        	public void onBackPressed(ScupDialog arg0) {
        		FileBrowserDialog.this.finish();		
        	}
		});   
        
		list.show();
	}
	private Bitmap getIcon(int id) {
        
        Bitmap image = BitmapFactory.decodeResource(getContext().getResources(), id);
		image = Bitmap.createScaledBitmap(image, 40 , 40, true);
        return image;
	}
}
