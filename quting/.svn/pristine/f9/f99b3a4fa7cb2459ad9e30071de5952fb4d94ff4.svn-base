package com.example.quting.image;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class ApacheUtility {

	/**
	 * 获取图片流
	 * 
	 * @param uri
	 *            图片地址
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static InputStream GetImageByUrl(String uri)
			throws MalformedURLException {
		URL url = new URL(uri);
		URLConnection conn;
		InputStream is;
		try {
			conn = url.openConnection();
			conn.connect();
			is = conn.getInputStream();

			// 或者用如下方法

			// is=(InputStream)url.getContent();
			return is;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取Bitmap
	 * 
	 * 
	 * @param uri
	 *            图片地址
	 * @return
	 */
	public static Bitmap GetBitmapByUrl(String uri) {

		Bitmap bitmap;
		InputStream is;
		try {

			is = GetImageByUrl(uri);

			bitmap = BitmapFactory.decodeStream(is);
			is.close();

			return bitmap;

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取Drawable
	 * 
	 * 
	 * @param uri
	 *            图片地址
	 * 
	 * @return
	 */
	public static Drawable GetDrawableByUrl(String uri) {

		Drawable drawable;
		InputStream is;
		try {

			is = GetImageByUrl(uri);

			drawable = Drawable.createFromStream(is, "src");

			is.close();

			return drawable;

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}