package com.aapbd.utils.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import android.util.Base64;

import com.aapbd.utils.print.print;

public class HTTPHandlerWithBasicAuth {

	public static String postJSON(String URL, String json, boolean isAuthentic,
			String token1, String token2) throws ClientProtocolException,
			IOException, JSONException {
		String text = "";
		try {
			final HttpPost httpPost = new HttpPost(URL);

			if (isAuthentic) {
				final String credentials = token1 + ":" + token2;
				final String base64EncodedCredentials = Base64.encodeToString(
						credentials.getBytes(), Base64.NO_WRAP);
				httpPost.addHeader("Authorization", "Basic "
						+ base64EncodedCredentials);
			}
			/*
			 * if not empty
			 */

			if (!json.endsWith("")) {

				final StringEntity se = new StringEntity(json.trim().toString());
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/json"));
				httpPost.setEntity(se);
			}

			text = HTTPHandler.dataFromStream(HTTPHandler
					.streamFromPost(httpPost));
		} catch (final URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;

	}

	public static String dataFromPost(HTTPPostHelper helper,
			boolean isAuthentic, String token1, String token2) {

		final HttpPost httpost = new HttpPost(helper.getURL().trim());

		if (isAuthentic) {
			final String credentials = token1 + ":" + token2;
			final String base64EncodedCredentials = Base64.encodeToString(
					credentials.getBytes(), Base64.NO_WRAP);
			httpost.addHeader("Authorization", "Basic "
					+ base64EncodedCredentials);
		}

		print.message("POST URL is ", helper.getURL().trim() + "");

		try {
			httpost.setEntity(new UrlEncodedFormEntity(helper.getNvps(),
					HTTP.UTF_8));

			return HTTPHandler.dataFromStream(HTTPHandler
					.streamFromPost(httpost));

		} catch (final UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";

	}

	// TODO: to make it more efficient, don't create http client every time
	public static String uploadFile(String URL, String pFileFullPathName,
			boolean isAuthentic, String token1, String token2)
			throws ClientProtocolException, IOException {
		String text = "";
		try {
			// 90 second
			final HttpPost httpPost = new HttpPost(URL);

			if (isAuthentic) {
				final String credentials = token1 + ":" + token2;
				final String base64EncodedCredentials = Base64.encodeToString(
						credentials.getBytes(), Base64.NO_WRAP);
				httpPost.addHeader("Authorization", "Basic "
						+ base64EncodedCredentials);
			}

			final SimpleMultipartEntity mpEntity = new SimpleMultipartEntity();

			final File vFile = new File(pFileFullPathName);
			mpEntity.addPart("uploadedfile", pFileFullPathName
					.substring(pFileFullPathName.lastIndexOf("/") + 1),
					new FileInputStream(vFile), "text/plain");

			httpPost.setEntity(mpEntity);

			text = HTTPHandler.dataFromStream(HTTPHandler
					.streamFromPost(httpPost));

			print.message("response is ", text + "");

		} catch (final URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return text;
	}

}
