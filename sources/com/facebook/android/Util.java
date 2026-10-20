package com.facebook.android;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class Util {
    private static final String UTF8 = "UTF-8";

    @Deprecated
    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                sb.append("Content-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + ((String) obj));
                sb.append("\r\n--" + str + "\r\n");
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            if (bundle.get(str) instanceof String) {
                if (z) {
                    z = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                try {
                    if (split.length == 2) {
                        bundle.putString(URLDecoder.decode(split[0], "UTF-8"), URLDecoder.decode(split[1], "UTF-8"));
                    } else if (split.length == 1) {
                        bundle.putString(URLDecoder.decode(split[0], "UTF-8"), "");
                    }
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return bundle;
    }

    @Deprecated
    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str.replace("fbconnect", HttpHost.DEFAULT_SCHEME_NAME));
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    @Deprecated
    public static String openUrl(String str, String str2, Bundle bundle) {
        if (str2.equals(HttpGet.METHOD_NAME)) {
            str = str + "?" + encodeUrl(bundle);
        }
        Utility.logd("Facebook-Util", str2 + " URL: " + str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestProperty(HTTP.USER_AGENT, System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
        if (!str2.equals(HttpGet.METHOD_NAME)) {
            Bundle bundle2 = new Bundle();
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof byte[]) {
                    bundle2.putByteArray(str3, (byte[]) obj);
                }
            }
            if (!bundle.containsKey("method")) {
                bundle.putString("method", str2);
            }
            if (bundle.containsKey("access_token")) {
                bundle.putString("access_token", URLDecoder.decode(bundle.getString("access_token")));
            }
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "multipart/form-data;boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            httpURLConnection.connect();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            try {
                bufferedOutputStream.write(("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n").getBytes());
                bufferedOutputStream.write(encodePostBody(bundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
                bufferedOutputStream.write(("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n").getBytes());
                if (!bundle2.isEmpty()) {
                    for (String str4 : bundle2.keySet()) {
                        bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str4 + "\"\r\n").getBytes());
                        bufferedOutputStream.write(("Content-Type: content/unknown\r\n\r\n").getBytes());
                        bufferedOutputStream.write(bundle2.getByteArray(str4));
                        bufferedOutputStream.write(("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n").getBytes());
                    }
                }
                bufferedOutputStream.flush();
            } finally {
                bufferedOutputStream.close();
            }
        }
        try {
            return read(httpURLConnection.getInputStream());
        } catch (FileNotFoundException e) {
            return read(httpURLConnection.getErrorStream());
        }
    }

    @Deprecated
    private static String read(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1000);
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            sb.append(readLine);
        }
        inputStream.close();
        return sb.toString();
    }

    @Deprecated
    public static JSONObject parseJson(String str) {
        if (str.equals("false")) {
            throw new FacebookError("request failed");
        }
        if (str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            str = "{value : true}";
        }
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("error")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("error");
            throw new FacebookError(jSONObject2.getString("message"), jSONObject2.getString("type"), 0);
        }
        if (jSONObject.has(NativeProtocol.BRIDGE_ARG_ERROR_CODE) && jSONObject.has("error_msg")) {
            throw new FacebookError(jSONObject.getString("error_msg"), "", Integer.parseInt(jSONObject.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE)));
        }
        if (jSONObject.has(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
            throw new FacebookError("request failed", "", Integer.parseInt(jSONObject.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE)));
        }
        if (jSONObject.has("error_msg")) {
            throw new FacebookError(jSONObject.getString("error_msg"));
        }
        if (jSONObject.has("error_reason")) {
            throw new FacebookError(jSONObject.getString("error_reason"));
        }
        return jSONObject;
    }

    @Deprecated
    public static void showAlert(Context context, String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.create().show();
    }
}
