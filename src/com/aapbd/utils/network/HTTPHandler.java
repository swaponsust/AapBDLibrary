package com.aapbd.utils.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import android.util.Log;

import com.aapbd.utils.print.print;

/**
 * HTTPHandler This class handles POST and GET requests and enables you to
 * upload files via post. Cookies are stored in the HttpClient.
 */
public class HTTPHandler {

	/*
	 * get https client
	 */
	public static DefaultHttpClient getClient() {

		DefaultHttpClient ret = null;
		// sets up parameters
		final HttpParams params = new BasicHttpParams();

		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		HttpProtocolParams.setContentCharset(params, "utf-8");
		params.setBooleanParameter("http.protocol.expect-continue", false);
		// registers schemes for both http and https
		final SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		final SSLSocketFactory sslSocketFactory = SSLSocketFactory
				.getSocketFactory();
		sslSocketFactory
				.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		registry.register(new Scheme("https", sslSocketFactory, 443));
		final ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(
				params, registry);
		ret = new DefaultHttpClient(manager, params);
		ret.getParams().setParameter("http.socket.timeout", new Integer(90000));

		return ret;

	}

	/*
	 * get input stream from GET request
	 */

	public static InputStream streamFromGet(final String url)
			throws URISyntaxException, ClientProtocolException, IOException {
		final DefaultHttpClient httpClient = HTTPHandler.getClient();

		URI uri;
		uri = new URI(url);
		final HttpGet method = new HttpGet(uri);

		method.addHeader("Accept-Encoding", "gzip");
		method.addHeader("Accept-Encoding", "utf-8");

		/*
		 * 
		 * send cookie
		 */

		if (Cookies.getCookies() != null) {

			print.message("Cookie is added to client");

			for (final Cookie cok : Cookies.getCookies()) {
				httpClient.getCookieStore().addCookie(cok);
			}
		} else {
			print.message("Cookie is empty");
		}

		final HttpResponse res = httpClient.execute(method);

		final List<Cookie> cookies = httpClient.getCookieStore().getCookies();

		if (cookies.isEmpty()) {
			System.out.println("None");
		} else {

			Cookies.setCookies(cookies);

			for (int i = 0; i < cookies.size(); i++) {
				Log.e("- " + cookies.get(i).toString(), "");
			}
		}

		printStatus(res);

		InputStream instream = res.getEntity().getContent();
		final Header contentEncoding = res.getFirstHeader("Content-Encoding");
		if (contentEncoding != null
				&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			instream = new GZIPInputStream(instream);
		}

		return instream;
	}

	private static void printStatus(HttpResponse res) {
		// TODO Auto-generated method stub

		try {
			final Header h1 = res.getEntity().getContentEncoding();

			print.message("hearder line ", h1.toString());

			final String code = res.getStatusLine().toString();
			print.message("status line ", code);
		} catch (Exception e) {

		}

	}

	/**
	 * Get string from stream
	 *
	 * @param InputStream
	 * @return
	 * @throws IOException
	 */
	public static String dataFromStream(final InputStream in)
			throws IOException {
		String text = "";

		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				in, "UTF-8"));
		final StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			text = sb.toString();

		} finally {

			in.close();

		}
		return text;
	}

	public static String dataFromGet(String url) throws URISyntaxException,
			ClientProtocolException, IOException {

		return dataFromStream(streamFromGet(url));

	}

	/*
	 * get input stream
	 */
	public static InputStream streamFromPost(final HttpPost httpost)
			throws URISyntaxException, ClientProtocolException, IOException {

		final HttpClient client = HTTPHandler.getClient();

		/*
		 * 
		 * send cookie
		 */

		if (Cookies.getCookies() != null) {

			for (final Cookie cok : Cookies.getCookies()) {
				((DefaultHttpClient) client).getCookieStore().addCookie(cok);
			}
		} else {
		}

		final HttpResponse res = client.execute(httpost);

		final List<Cookie> cookies = ((DefaultHttpClient) client)
				.getCookieStore().getCookies();

		if (cookies.isEmpty()) {
			System.out.println("None");
		} else {

			Cookies.setCookies(cookies);

			for (int i = 0; i < cookies.size(); i++) {
				Log.e("- " + cookies.get(i).toString(), "");
			}
		}

		printStatus(res);

		InputStream instream = res.getEntity().getContent();
		final Header contentEncoding = res.getFirstHeader("Content-Encoding");
		if (contentEncoding != null
				&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			instream = new GZIPInputStream(instream);
		}

		return instream;
	}

	/*
	 * refreshURL
	 */

	public static void refreshURL(final String url) throws URISyntaxException,
			ClientProtocolException, IOException {
		final DefaultHttpClient httpClient = HTTPHandler.getClient();

		URI uri;

		uri = new URI(url);
		final HttpGet method = new HttpGet(uri);

		final HttpResponse res = httpClient.execute(method);

		printStatus(res);
	}

	public static String dataFromPost(HTTPPostHelper helper) {

		final HttpPost httpost = new HttpPost(helper.getURL().trim());

		print.message("POST URL is ", helper.getURL().trim() + "");

		try {
			httpost.setEntity(new UrlEncodedFormEntity(helper.getNvps(),
					HTTP.UTF_8));

			return dataFromStream(streamFromPost(httpost));

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

	/*
	 * send image to server.
	 */

	public static String postDataWithSingleImage(
			HTTPPostWithSimpleMultipart helper) {

		final HttpPost httpost = new HttpPost(helper.getURL().trim());

		print.message("POST URL is ", helper.getURL().trim() + "");

		try {
			httpost.setEntity(helper.getEntity());

			return HTTPHandler.dataFromStream(streamFromPost(httpost));

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
	public static String uploadFile(String URL, String pFileFullPathName)
			throws ClientProtocolException, IOException {
		String text = "";
		try {
			// 90 second
			final HttpPost httpPost = new HttpPost(URL);

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

	public static String postJSON(String URL, String json)
			throws ClientProtocolException, IOException, JSONException {
		String text = "";
		try {
			final HttpPost httpPost = new HttpPost(URL);

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

}