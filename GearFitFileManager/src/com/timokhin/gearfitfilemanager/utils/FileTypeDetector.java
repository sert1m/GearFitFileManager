package com.timokhin.gearfitfilemanager.utils;

import java.io.File;

public class FileTypeDetector {

	public static boolean isTextFile(File file) {
		return file.getName().endsWith(".txt");
	}
	
	public static boolean isImageFile(File file) {
		return file.getName().endsWith(".png") || file.getName().endsWith(".jpeg") || 
			   file.getName().endsWith(".png") || file.getName().endsWith(".jpg");
	}
}
