package com.example.librarymanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;

public class Globals {
	static Typeface fontFamily;

	static final String urlJsonObj = "http://r3narang.in/Testcall/agents/verifyCredentials.php?username=";
	static final String showlist = "http://r3narang.in/Testcall/agents/getAgentCallLog.php?userId=";
	static String showdetail = "http://r3narang.in/Testcall/agents/getAgentCallDetails.php?id=";
	public static final int VOLLEY_TIMEOUT_MILLISECS = 10000;

	static public Point getAppButtonSize(Activity context) {

		int screenWidth = Globals.getScreenSize(context).x;
		Log.d("Abhinav", ""+screenWidth);

		Point size = new Point();

		size.x = 4 * screenWidth / 10;
		Log.d("Abhinav", "x---->"+size.x);
		size.y = size.x / 3;

		return size;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	static public Point getScreenSize(Activity currentActivity) {
		Display display = currentActivity.getWindowManager()
				.getDefaultDisplay();
		Point size = new Point();
		if (android.os.Build.VERSION.SDK_INT >= 13) {
			display.getSize(size);
		} else {
			size.x = display.getWidth();
			size.y = display.getHeight();
		}
		return size;
	}

	static public int getAppFontSize(Activity context) {
		return (getScreenSize(context).x / 120 + 12);
	}

	static public int getAppFontSize_Small(Activity context) {

		return (getScreenSize(context).x / 120 + 10);
	}

	static public int getAppFontSize_Large(Activity context) {

		return (getScreenSize(context).x / 120 + 15);
	}

	static public Bitmap scaleToWidth(Bitmap bitmap, int scaledWidth) {
		if (bitmap != null) {

			int bitmapHeight = bitmap.getHeight();
			int bitmapWidth = bitmap.getWidth();

			// scale According to WIDTH
			int scaledHeight = (scaledWidth * bitmapHeight) / bitmapWidth;

			try {

				bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
						scaledHeight, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

}
