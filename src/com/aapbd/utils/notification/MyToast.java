package com.aapbd.utils.notification;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

	/*
	 * show toast
	 */

	public static void showToast(Context c, String text) {
		Toast.makeText(c, text, Toast.LENGTH_LONG).show();

	}

	public static void showToast(Context c, String text, int length) {
		Toast.makeText(c, text, length).show();

	}

}
