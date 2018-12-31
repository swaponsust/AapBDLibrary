package com.aapbd.utils.view;

import com.aapbd.utils.notification.BusyDialog;
import com.aapbd.utils.print.print;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewFormatter {

	@SuppressWarnings("deprecation")
	public static WebView formatWebView(WebView web, boolean isFitScreen) {

		web.setWebChromeClient(new WebChromeClient());

		web.setBackgroundColor(Color.parseColor("#00000000"));
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		web.getSettings().setSupportMultipleWindows(false);
		web.setVerticalScrollBarEnabled(false);
		web.setHorizontalScrollBarEnabled(true);

		web.getSettings()
				.setUserAgentString(
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16H) AppleWebKit/537.36(KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");

		if (isFitScreen) {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

				web.getSettings().setSupportZoom(true);

				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.TEXT_AUTOSIZING);

			} else {
				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.SINGLE_COLUMN);

			}

		}

		return web;
	}

	public static WebView formatWebViewWithClient(WebView web,
			boolean isFitScreen, boolean enableCLient) {

		web.setWebChromeClient(new WebChromeClient());

		web.setBackgroundColor(Color.parseColor("#00000000"));
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		web.getSettings().setSupportMultipleWindows(false);
		web.setVerticalScrollBarEnabled(false);
		web.setHorizontalScrollBarEnabled(true);

		web.getSettings()
				.setUserAgentString(
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16H) AppleWebKit/537.36(KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");

		if (isFitScreen) {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

				web.getSettings().setSupportZoom(true);

				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.TEXT_AUTOSIZING);

			} else {
				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.SINGLE_COLUMN);

			}

		}

		if (enableCLient) {

			web.setWebViewClient(new MyWebViewClient(true));
		}
		return web;
	}

	public static WebView formatWebViewWithClient(WebView web,
			boolean isFitScreen, boolean enableCLient, boolean loadWithinApp) {

		web.setWebChromeClient(new WebChromeClient());

		web.setBackgroundColor(Color.parseColor("#00000000"));
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		web.getSettings().setSupportMultipleWindows(false);
		web.setVerticalScrollBarEnabled(false);
		web.setHorizontalScrollBarEnabled(true);

		web.getSettings()
				.setUserAgentString(
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16H) AppleWebKit/537.36(KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");

		if (isFitScreen) {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

				web.getSettings().setSupportZoom(true);

				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.TEXT_AUTOSIZING);

			} else {
				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.SINGLE_COLUMN);

			}

		}

		if (enableCLient) {

			web.setWebViewClient(new MyWebViewClient(loadWithinApp));
		}
		return web;
	}

	@SuppressWarnings("deprecation")
	public static WebView formatWebView(WebView web, boolean isFitScreen,
			int BGColor) {

		web.setWebChromeClient(new WebChromeClient());

		web.setBackgroundColor(BGColor);
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		web.getSettings().setSupportMultipleWindows(false);
		web.setVerticalScrollBarEnabled(false);
		web.setHorizontalScrollBarEnabled(true);

		web.getSettings()
				.setUserAgentString(
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16H) AppleWebKit/537.36(KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");

		if (isFitScreen) {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

				web.getSettings().setSupportZoom(true);

				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.TEXT_AUTOSIZING);

			} else {
				web.getSettings().setLayoutAlgorithm(
						LayoutAlgorithm.SINGLE_COLUMN);

			}

		}

		return web;
	}

	private static class MyWebViewClient extends WebViewClient {

		BusyDialog busyNow = null;

		private boolean loadWithinApp = false;

		public MyWebViewClient(boolean loadWithinApp) {
			// TODO Auto-generated constructor stub
			this.loadWithinApp = loadWithinApp;
		}

		@Override
		public void onPageFinished(final WebView view, final String url) {

			if (busyNow != null) {
				busyNow.dismis();
			}
			print.message("web status", "onPageFinished");
			// progressBar.setVisibility(View.GONE);

			// This call inject JavaScript into the page which just finished
			// loading.
			view.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
			// adjust prev/next buttons, only if history is available

		}

		@Override
		public void onPageStarted(final WebView view, final String url,
				final Bitmap favicon) {
			print.message("web status", "onPageStarted");

			if (busyNow == null) {
				busyNow = new BusyDialog(view.getContext(), true);
				busyNow.show();
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(final WebView view,
				final String url) {

			print.message("clicked data is ", url + "");

			try {
				if (loadWithinApp) {

					if (url.startsWith("http")) {
						view.loadUrl(url);
					} else {
						try {
							final Intent intent = new Intent(
									Intent.ACTION_VIEW, Uri.parse(url));
							view.getContext().startActivity(intent);
						} catch (Exception e) {

						}
					}

				} else {

					try {
						final Intent intent = new Intent(Intent.ACTION_VIEW,
								Uri.parse(url));
						view.getContext().startActivity(intent);
					} catch (Exception e) {

					}
				}

			} catch (final Exception e) {

				print.message("Webview client error ", e.getMessage());
			}

			return true;
		}

	}

}
