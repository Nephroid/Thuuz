package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class UrlRedirectCache {
    private static volatile FileLruCache urlRedirectCache;
    static final String TAG = UrlRedirectCache.class.getSimpleName();
    private static final String REDIRECT_CONTENT_TAG = TAG + "_Redirect";

    UrlRedirectCache() {
    }

    static synchronized FileLruCache getCache(Context context) {
        FileLruCache fileLruCache;
        synchronized (UrlRedirectCache.class) {
            if (urlRedirectCache == null) {
                urlRedirectCache = new FileLruCache(context.getApplicationContext(), TAG, new FileLruCache.Limits());
            }
            fileLruCache = urlRedirectCache;
        }
        return fileLruCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static URI getRedirectedUri(Context context, URI uri) {
        InputStreamReader inputStreamReader;
        Throwable th;
        boolean z = false;
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        try {
            FileLruCache cache = getCache(context);
            String str = uri2;
            InputStreamReader inputStreamReader2 = null;
            while (true) {
                try {
                    InputStream inputStream = cache.get(str, REDIRECT_CONTENT_TAG);
                    if (inputStream == null) {
                        break;
                    }
                    inputStreamReader = new InputStreamReader(inputStream);
                    try {
                        char[] cArr = new char[128];
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            int read = inputStreamReader.read(cArr, 0, cArr.length);
                            if (read <= 0) {
                                break;
                            }
                            sb.append(cArr, 0, read);
                        }
                        Utility.closeQuietly(inputStreamReader);
                        str = sb.toString();
                        inputStreamReader2 = inputStreamReader;
                        z = true;
                    } catch (IOException e) {
                        Utility.closeQuietly(inputStreamReader);
                        return null;
                    } catch (URISyntaxException e2) {
                        Utility.closeQuietly(inputStreamReader);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.closeQuietly(inputStreamReader);
                        throw th;
                    }
                } catch (IOException e3) {
                    inputStreamReader = inputStreamReader2;
                } catch (URISyntaxException e4) {
                    inputStreamReader = inputStreamReader2;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = inputStreamReader2;
                }
            }
            if (!z) {
                Utility.closeQuietly(inputStreamReader2);
                return null;
            }
            URI uri3 = new URI(str);
            Utility.closeQuietly(inputStreamReader2);
            return uri3;
        } catch (IOException e5) {
            inputStreamReader = null;
        } catch (URISyntaxException e6) {
            inputStreamReader = null;
        } catch (Throwable th4) {
            inputStreamReader = null;
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void cacheUriRedirect(Context context, URI uri, URI uri2) {
        OutputStream outputStream;
        Throwable th;
        if (uri != null && uri2 != null) {
            try {
                try {
                    OutputStream openPutStream = getCache(context).openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
                    try {
                        openPutStream.write(uri2.toString().getBytes());
                        Utility.closeQuietly(openPutStream);
                    } catch (Throwable th2) {
                        outputStream = openPutStream;
                        th = th2;
                        Utility.closeQuietly(outputStream);
                        throw th;
                    }
                } catch (IOException e) {
                    Utility.closeQuietly(null);
                }
            } catch (Throwable th3) {
                outputStream = null;
                th = th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearCache(Context context) {
        try {
            getCache(context).clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + e.getMessage());
        }
    }
}
