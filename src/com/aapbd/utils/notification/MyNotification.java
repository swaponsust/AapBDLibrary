package com.aapbd.utils.notification;

import java.util.Vector;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.aapbd.aapbdlib.R;
import com.aapbd.utils.network.KeyValue;

public class MyNotification {

	private static Context context = null;
	private static final String ns = Context.NOTIFICATION_SERVICE;
	private static NotificationManager notificationManager;
	private static final int MHYH_ID = 1;
	public static final int NOTIFICATION_ID1 = 1001;

	public static Notification notification;

	public static void initNotification(Context con) {
		MyNotification.context = con;

		MyNotification.notificationManager = (NotificationManager) con
				.getSystemService(MyNotification.ns);

	}

	public static void cancelNotification() {
		MyNotification.notificationManager.cancel(MyNotification.MHYH_ID);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotification(String message, Class<?> destionation) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;
		final CharSequence tickerText = MyNotification.context
				.getText(R.string.app_name) + " is running in background";
		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, tickerText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotificationWithValue(String message,
			Class<?> destionation, Vector<KeyValue> data) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;
		final CharSequence tickerText = MyNotification.context
				.getText(R.string.app_name) + " is running in background";
		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, tickerText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		for (final KeyValue value : data) {

			notificationIntent.putExtra(value.getKey(), value.getValue());
		}

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotificationWithValue(String ticketText,
			String message, Class<?> destionation, Vector<KeyValue> data) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;
		final long when = System.currentTimeMillis();

		MyNotification.notification = new Notification(icon, ticketText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		for (final KeyValue value : data) {

			notificationIntent.putExtra(value.getKey(), value.getValue());
		}

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotification(String message, Class<?> destionation,
			boolean isSound, boolean isVibrate) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;
		final CharSequence tickerText = MyNotification.context
				.getText(R.string.app_name) + " is running in background";
		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, tickerText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		if (isSound) {
			MyNotification.notification.defaults |= Notification.DEFAULT_SOUND;
		}

		if (isVibrate) {
			MyNotification.notification.defaults |= Notification.DEFAULT_VIBRATE;

		}
		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotification(String ticketText, String message,
			Class<?> destionation, boolean isSound, boolean isVibrate) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;

		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, ticketText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		if (isSound) {
			MyNotification.notification.defaults |= Notification.DEFAULT_SOUND;
		}

		if (isVibrate) {
			MyNotification.notification.defaults |= Notification.DEFAULT_VIBRATE;

		}
		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotificationWithValue(String message,
			Class<?> destionation, Vector<KeyValue> data, boolean isSound,
			boolean isVibrate) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;
		final CharSequence tickerText = MyNotification.context
				.getText(R.string.app_name) + " is running in background";
		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, tickerText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		if (isSound) {
			MyNotification.notification.defaults |= Notification.DEFAULT_SOUND;
		}

		if (isVibrate) {
			MyNotification.notification.defaults |= Notification.DEFAULT_VIBRATE;

		}

		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		for (KeyValue value : data) {

			notificationIntent.putExtra(value.getKey(), value.getValue());
		}

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}

	@SuppressWarnings("deprecation")
	public static void sendNotificationWithValue(String ticketText,
			String message, Class<?> destionation, Vector<KeyValue> data,
			boolean isSound, boolean isVibrate) {
		// TODO Auto-generated method stub
		final int icon = R.drawable.ic_launcher;

		final long when = System.currentTimeMillis();
		MyNotification.notification = new Notification(icon, ticketText, when);
		MyNotification.notification.flags = Notification.FLAG_AUTO_CANCEL;

		if (isSound) {
			MyNotification.notification.defaults |= Notification.DEFAULT_SOUND;
		}

		if (isVibrate) {
			MyNotification.notification.defaults |= Notification.DEFAULT_VIBRATE;

		}

		final CharSequence contentTitle = MyNotification.context
				.getText(R.string.app_name);
		final CharSequence contentText = " " + message;
		final Intent notificationIntent = new Intent(MyNotification.context,
				destionation);

		for (KeyValue value : data) {

			notificationIntent.putExtra(value.getKey(), value.getValue());
		}

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		final PendingIntent contentIntent = PendingIntent.getActivity(
				MyNotification.context, 0, notificationIntent, 0);
		MyNotification.notification.setLatestEventInfo(MyNotification.context,
				contentTitle, contentText, contentIntent);
		MyNotification.notificationManager.notify(MyNotification.MHYH_ID,
				MyNotification.notification);
	}
}
