package com.aapbd.utils.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.aapbd.aapbdlib.R;

public class DialogWithOff {

	public static void showTurnOffDialog(final Context mContext, String title,
			String text) {

		final SharedPreferences prefs = mContext.getSharedPreferences(
				"dialogoff", 0);

		final SharedPreferences.Editor editor = prefs.edit();

		if (prefs.contains("dontshowagain")) {
			final boolean flag = prefs.getBoolean("dontshowagain", false);
			if (flag) {
				return;
			}
		}

		final AlertDialog.Builder aBuilder = new AlertDialog.Builder(mContext);
		aBuilder.setTitle(title);
		aBuilder.setIcon(R.drawable.ic_launcher);
		aBuilder.setMessage(text);

		aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}

		});

		aBuilder.setNegativeButton("Don't show again",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {

						if (editor != null) {
							editor.putBoolean("dontshowagain", true);
							editor.commit();
						}
						dialog.dismiss();

					}

				});

		aBuilder.show();
	}
}
