package com.yozio.android.helpers;

import com.facebook.internal.NativeProtocol;
import com.samsung.android.sdk.richnotification.Utilities;
import com.yozio.android.Constants;
import com.yozio.android.Yozio;
import com.yozio.android.YozioService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Api {
    private static Api _yozioApi = new Api();
    protected HttpClient _httpClient;

    public static Api getInstance() {
        return _yozioApi;
    }

    private Api() {
    }

    protected HttpClient getHttpClient() {
        if (this._httpClient != null) {
            return this._httpClient;
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, Yozio.YOZIO_CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(basicHttpParams, Yozio.YOZIO_SOCKET_TIMEOUT);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, HTTP.UTF_8);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        this._httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        return this._httpClient;
    }

    public HashMap<String, Object> getConfigs() {
        HashMap<String, Object> hashMap;
        YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - getConfigs");
        LinkedList linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("method", "app.get.config"));
        linkedList.add(new BasicNameValuePair("app_key", YozioService.getInstance().getAppKey()));
        linkedList.add(new BasicNameValuePair("device_id", YozioService.getInstance().getDeviceId()));
        String doPostRequest = doPostRequest(Constants.YOZIO_API_BASE_URL, linkedList);
        if (doPostRequest == null) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getConfigs: request error or timeout");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(doPostRequest);
            if (!"ok".equals(jSONObject.getString("status"))) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getConfigs: response status - " + jSONObject.getString("status"));
                hashMap = null;
            } else {
                hashMap = (HashMap) Json.toMap(jSONObject.getJSONObject("body"));
                YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - getConfigs: " + hashMap.toString());
            }
            return hashMap;
        } catch (JSONException e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getConfigs: json parse error - " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, Object> getMetaData() {
        HashMap<String, Object> hashMap;
        YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - getMetaData");
        LinkedList linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("method", "sdk.app.install.get.meta.data"));
        linkedList.add(new BasicNameValuePair("app_key", YozioService.getInstance().getAppKey()));
        linkedList.add(new BasicNameValuePair("device_id", YozioService.getInstance().getDeviceId()));
        String doPostRequest = doPostRequest(Constants.YOZIO_API_BASE_URL, linkedList);
        if (doPostRequest == null) {
            return new HashMap<>();
        }
        try {
            JSONObject jSONObject = new JSONObject(doPostRequest);
            if (!"ok".equals(jSONObject.getString("status"))) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getMetaData: response status - " + jSONObject.getString("status"));
                hashMap = new HashMap<>();
            } else {
                hashMap = (HashMap) Json.toMap(jSONObject.getJSONObject("body"));
                YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - getMetaData: " + hashMap.toString());
            }
            return hashMap;
        } catch (JSONException e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getMetaData: json parse error - " + e.toString());
            return new HashMap<>();
        }
    }

    public String getUrl() {
        String str = null;
        YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - getUrl");
        LinkedList linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("method", "app.get.url"));
        linkedList.add(new BasicNameValuePair("app_key", YozioService.getInstance().getAppKey()));
        linkedList.add(new BasicNameValuePair("device_id", YozioService.getInstance().getDeviceId()));
        String doPostRequest = doPostRequest(Constants.YOZIO_API_BASE_URL, linkedList);
        if (doPostRequest != null) {
            try {
                JSONObject jSONObject = new JSONObject(doPostRequest);
                if (!"ok".equals(jSONObject.getString("status"))) {
                    YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getUrl: response status - " + jSONObject.getString("status"));
                } else {
                    str = jSONObject.getJSONObject("body").getString(NativeProtocol.IMAGE_URL_KEY);
                }
            } catch (JSONException e) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - getUrl: json parse error - " + e.toString());
                e.printStackTrace();
            }
        }
        return str;
    }

    public boolean sendEmails(String str, String str2, Object[] objArr) {
        YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - sendEmails");
        LinkedList linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("method", "app.send.email"));
        linkedList.add(new BasicNameValuePair("app_key", YozioService.getInstance().getAppKey()));
        linkedList.add(new BasicNameValuePair("device_id", YozioService.getInstance().getDeviceId()));
        try {
            JSONArray jSONArray = new JSONArray();
            for (Object obj : objArr) {
                jSONArray.put(new JSONObject((HashMap) obj));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", str);
            jSONObject.put(Utilities.DB_KEY_IMAGE_NAME, str2);
            jSONObject.put("to", jSONArray);
            linkedList.add(new BasicNameValuePair("body", jSONObject.toString()));
            YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - sendEmails - " + jSONObject.toString());
            String doPostRequest = doPostRequest(Constants.YOZIO_API_BASE_URL, linkedList);
            if (doPostRequest == null) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject(doPostRequest);
            if (!"ok".equals(jSONObject2.getString("status"))) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - sendEmails: response status - " + jSONObject2.getString("status"));
                return false;
            }
            return true;
        } catch (JSONException e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - sendEmails: json parse error - " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public boolean flushEvents(JSONArray jSONArray) {
        YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - flushEvents");
        LinkedList linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("app_key", YozioService.getInstance().getAppKey()));
        linkedList.add(new BasicNameValuePair("device_id", YozioService.getInstance().getDeviceId()));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("system", new JSONObject(SystemInfo.getInstance().getSystemDictionary()));
            jSONObject.put("events", jSONArray);
            linkedList.add(new BasicNameValuePair("body", jSONObject.toString()));
            YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - flushEvents - " + jSONArray.toString());
            String doPostRequest = doPostRequest(Constants.YOZIO_EVENT_BASE_URL, linkedList);
            if (doPostRequest == null) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject(doPostRequest);
            if (!"ok".equals(jSONObject2.getString("status"))) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - flushEvents: response status - " + jSONObject2.getString("status"));
                return false;
            }
            return true;
        } catch (JSONException e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - flushEvents: json parse error - " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    protected String doPostRequest(String str, List<NameValuePair> list) {
        String str2;
        try {
            YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - Post request: " + str + " - " + list.toString());
            HttpPost httpPost = new HttpPost(str);
            httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
            httpPost.setHeader(HTTP.USER_AGENT, SystemInfo.getInstance().getUserAgent());
            HttpResponse execute = getHttpClient().execute(httpPost);
            HttpEntity entity = execute.getEntity();
            if (entity != null) {
                str2 = EntityUtils.toString(entity, HTTP.UTF_8);
                entity.consumeContent();
            } else {
                str2 = null;
            }
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode >= 400) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - Post http status: " + Integer.toString(statusCode));
                if (str2 == null) {
                    return null;
                }
                YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - Post response: " + str2);
                return null;
            }
            if (str2 == null) {
                return null;
            }
            YozioService.log(Constants.LOG_LEVEL.INFO, "YozioApi - Post response: " + str2);
            return str2;
        } catch (Exception e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "YozioApi - Post request: HttpException " + e.toString());
            return null;
        }
    }
}
