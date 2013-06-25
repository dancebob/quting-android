/*
 * Copyright 2011 the original author or authors.    
 */
package com.example.quting.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.os.Environment;

/**
 * @author Jun
 *
 */
public final class FileUtils {
	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			}
			if (file.canRead() == false) {
				throw new IOException("File '" + file + "' cannot be read");
			}
		} else {
			throw new FileNotFoundException("File '" + file + "' does not exist");
		}
		return new FileInputStream(file);
	}

	public static FileOutputStream openOutputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			}
			if (file.canWrite() == false) {
				throw new IOException("File '" + file + "' cannot be written to");
			}
		} else {
			File parent = file.getParentFile();
			if (parent != null && parent.exists() == false) {
				if (parent.mkdirs() == false) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}
		}
		return new FileOutputStream(file);
	}

	public static void writeStringToFile(File file, String data, String encoding) throws IOException {
		OutputStream out = null;
		try {
			out = openOutputStream(file);
			IOUtils.write(data, out, encoding);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static void writeStringToFile(File file, String data) throws IOException {
		writeStringToFile(file, data, null);
	}

	public static void write(File file, CharSequence data) throws IOException {
		String str = data == null ? null : data.toString();
		writeStringToFile(file, str);
	}

	public static void write(File file, CharSequence data, String encoding) throws IOException {
		String str = data == null ? null : data.toString();
		writeStringToFile(file, str, encoding);
	}

	public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
		OutputStream out = null;
		try {
			out = openOutputStream(file);
			out.write(data);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir == null ? null : sdDir.toString();
	}

	public static File getSDFile(String relativePath) {
		String sdDir = getSDPath();
		if (sdDir == null) {
			return null;
		}
		return new File(sdDir + "/" + relativePath);
	}

}
