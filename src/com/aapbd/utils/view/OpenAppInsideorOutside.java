package com.aapbd.utils.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public class OpenAppInsideorOutside {

	public static void openAppByPackageName(Context con, String packageName)

	{

		if (isPackageInstalled(con, packageName)) { // Open

			Intent i = con.getPackageManager().getLaunchIntentForPackage(
					packageName);
			i.addCategory(Intent.CATEGORY_LAUNCHER);
			con.startActivity(i);
		} else { // Open market
			try {
				con.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + packageName)));
			} catch (android.content.ActivityNotFoundException anfe) {
				con.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://play.google.com/store/apps/details?id="
								+ packageName)));
			}
		}
	}

	public static void openAppWithURL(Context con, String packageName,
			String localURl, String globalURL)

	{

		if (isPackageInstalled(con, packageName)) { // Open

			con.startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse(localURl)));

		} else { // Open market

			con.startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse(globalURL)));

		}
	}

	private static boolean isPackageInstalled(Context con, String packagename) {
		PackageManager pm = con.getPackageManager();
		try {
			pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

}
