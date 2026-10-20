package com.facebook;

import android.content.Context;
import android.support.v4.os.EnvironmentCompat;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class Response {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_CACHE_TAG = "ResponseCache";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList<GraphObject> graphObjectList;
    private final boolean isFromCache;
    private final String rawResponse;
    private final Request request;

    /* loaded from: classes.dex */
    interface PagedResults extends GraphObject {
        GraphObjectList<GraphObject> getData();

        PagingInfo getPaging();
    }

    /* loaded from: classes.dex */
    public enum PagingDirection {
        NEXT,
        PREVIOUS
    }

    /* loaded from: classes.dex */
    interface PagingInfo extends GraphObject {
        String getNext();

        String getPrevious();
    }

    static {
        $assertionsDisabled = !Response.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Response(Request request, HttpURLConnection httpURLConnection, String str, GraphObject graphObject, boolean z) {
        this(request, httpURLConnection, str, graphObject, null, z, null);
    }

    Response(Request request, HttpURLConnection httpURLConnection, String str, GraphObjectList<GraphObject> graphObjectList, boolean z) {
        this(request, httpURLConnection, str, null, graphObjectList, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Response(Request request, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(request, httpURLConnection, null, null, null, false, facebookRequestError);
    }

    Response(Request request, HttpURLConnection httpURLConnection, String str, GraphObject graphObject, GraphObjectList<GraphObject> graphObjectList, boolean z, FacebookRequestError facebookRequestError) {
        this.request = request;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = graphObject;
        this.graphObjectList = graphObjectList;
        this.isFromCache = z;
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final GraphObject getGraphObject() {
        return this.graphObject;
    }

    public final <T extends GraphObject> T getGraphObjectAs(Class<T> cls) {
        if (this.graphObject == null) {
            return null;
        }
        if (cls == null) {
            throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
        }
        return (T) this.graphObject.cast(cls);
    }

    public final GraphObjectList<GraphObject> getGraphObjectList() {
        return this.graphObjectList;
    }

    public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(Class<T> cls) {
        if (this.graphObjectList == null) {
            return null;
        }
        return (GraphObjectList<T>) this.graphObjectList.castToListOf(cls);
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public Request getRequest() {
        return this.request;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    public Request getRequestForPagedResults(PagingDirection pagingDirection) {
        String str;
        PagingInfo paging;
        if (this.graphObject == null || (paging = ((PagedResults) this.graphObject.cast(PagedResults.class)).getPaging()) == null) {
            str = null;
        } else if (pagingDirection == PagingDirection.NEXT) {
            str = paging.getNext();
        } else {
            str = paging.getPrevious();
        }
        if (Utility.isNullOrEmpty(str)) {
            return null;
        }
        if (str != null && str.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new Request(this.request.getSession(), new URL(str));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public String toString() {
        String str;
        try {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            str = String.format("%d", objArr);
        } catch (IOException e) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return "{Response:  responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
    }

    public final boolean getIsFromCache() {
        return this.isFromCache;
    }

    static FileLruCache getResponseCache() {
        Context staticContext;
        if (responseCache == null && (staticContext = Session.getStaticContext()) != null) {
            responseCache = new FileLruCache(staticContext, RESPONSE_CACHE_TAG, new FileLruCache.Limits());
        }
        return responseCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(4:3|(2:5|(1:7)(1:8))|9|(1:67)(3:14|15|(5:17|18|19|20|21)(1:56)))(1:68)|27|28|(1:30)(2:34|(1:40))|31|32|21|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x010e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x010f, code lost:
    
        com.facebook.internal.Utility.closeQuietly(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0112, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009d, code lost:
    
        com.facebook.internal.Logger.log(com.facebook.LoggingBehavior.REQUESTS, com.facebook.Response.RESPONSE_LOG_TAG, "Response <Error>: %s", r0);
        r0 = constructErrorResponses(r9, r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b5, code lost:
    
        com.facebook.internal.Logger.log(com.facebook.LoggingBehavior.REQUESTS, com.facebook.Response.RESPONSE_LOG_TAG, "Response <Error>: %s", r0);
        r0 = constructErrorResponses(r9, r8, new com.facebook.FacebookException(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d2, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d3, code lost:
    
        com.facebook.internal.Logger.log(com.facebook.LoggingBehavior.REQUESTS, com.facebook.Response.RESPONSE_LOG_TAG, "Response <Error>: %s", r0);
        r0 = constructErrorResponses(r9, r8, new com.facebook.FacebookException(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f1, code lost:
    
        com.facebook.internal.Logger.log(com.facebook.LoggingBehavior.REQUESTS, com.facebook.Response.RESPONSE_LOG_TAG, "Response <Error>: %s", r0);
        r0 = constructErrorResponses(r9, r8, new com.facebook.FacebookException(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005d A[Catch: FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, all -> 0x010e, Merged into TryCatch #6 {all -> 0x010e, FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, blocks: (B:28:0x0055, B:30:0x005d, B:31:0x0061, B:34:0x008a, B:38:0x0094, B:45:0x009d, B:48:0x00b5, B:51:0x00d3, B:54:0x00f1), top: B:27:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[Catch: FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, all -> 0x010e, Merged into TryCatch #6 {all -> 0x010e, FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, blocks: (B:28:0x0055, B:30:0x005d, B:31:0x0061, B:34:0x008a, B:38:0x0094, B:45:0x009d, B:48:0x00b5, B:51:0x00d3, B:54:0x00f1), top: B:27:0x0055 }, TRY_ENTER] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.facebook.Response> fromHttpConnection(java.net.HttpURLConnection r8, com.facebook.RequestBatch r9) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Response.fromHttpConnection(java.net.HttpURLConnection, com.facebook.RequestBatch):java.util.List");
    }

    static List<Response> createResponsesFromStream(InputStream inputStream, HttpURLConnection httpURLConnection, RequestBatch requestBatch, boolean z) {
        String readStreamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
        return createResponsesFromString(readStreamToString, httpURLConnection, requestBatch, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Response> createResponsesFromString(String str, HttpURLConnection httpURLConnection, RequestBatch requestBatch, boolean z) {
        List<Response> createResponsesFromObject = createResponsesFromObject(httpURLConnection, requestBatch, new JSONTokener(str).nextValue(), z);
        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", requestBatch.getId(), Integer.valueOf(str.length()), createResponsesFromObject);
        return createResponsesFromObject;
    }

    private static List<Response> createResponsesFromObject(HttpURLConnection httpURLConnection, List<Request> list, Object obj, boolean z) {
        Object obj2;
        if (!$assertionsDisabled && httpURLConnection == null && !z) {
            throw new AssertionError();
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (size == 1) {
            Request request = list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(BODY_KEY, obj);
                jSONObject.put(CODE_KEY, httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                obj2 = jSONArray;
            } catch (IOException e) {
                arrayList.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, e)));
            } catch (JSONException e2) {
                arrayList.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, e2)));
                obj2 = obj;
            }
            if ((obj2 instanceof JSONArray) || ((JSONArray) obj2).length() != size) {
                throw new FacebookException("Unexpected number of results");
            }
            JSONArray jSONArray2 = (JSONArray) obj2;
            for (int i = 0; i < jSONArray2.length(); i++) {
                Request request2 = list.get(i);
                try {
                    arrayList.add(createResponseFromObject(request2, httpURLConnection, jSONArray2.get(i), z, obj));
                } catch (FacebookException e3) {
                    arrayList.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, e3)));
                } catch (JSONException e4) {
                    arrayList.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, e4)));
                }
            }
            return arrayList;
        }
        obj2 = obj;
        if (obj2 instanceof JSONArray) {
        }
        throw new FacebookException("Unexpected number of results");
    }

    private static Response createResponseFromObject(Request request, HttpURLConnection httpURLConnection, Object obj, boolean z, Object obj2) {
        Session session;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jSONObject, obj2, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                if (checkResponseAndCreateError.getErrorCode() == INVALID_SESSION_FACEBOOK_ERROR_CODE && (session = request.getSession()) != null) {
                    session.closeAndClearTokenInformation();
                }
                return new Response(request, httpURLConnection, checkResponseAndCreateError);
            }
            Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, BODY_KEY, NON_JSON_RESPONSE_PROPERTY);
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new Response(request, httpURLConnection, stringPropertyAsJSON.toString(), GraphObject.Factory.create((JSONObject) stringPropertyAsJSON), z);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new Response(request, httpURLConnection, stringPropertyAsJSON.toString(), (GraphObjectList<GraphObject>) GraphObject.Factory.createList((JSONArray) stringPropertyAsJSON, GraphObject.class), z);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new Response(request, httpURLConnection, obj.toString(), (GraphObject) null, z);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Response> constructErrorResponses(List<Request> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new Response(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, facebookException)));
        }
        return arrayList;
    }
}
