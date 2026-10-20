package defpackage;

import android.database.Cursor;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;
import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbd extends bzy {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static Integer g;
    private App h;
    private Uri i;
    private Map<String, String> j = new Hashtable();
    private Map<String, String> k = new Hashtable();
    private IService l;
    private long m;

    static {
        if (a()) {
            a = "com.google.android.tv.provider";
            b = "channel_listing";
            c = "channel_uri";
            d = "channel_name";
            e = "channel_number";
            f = "callsign";
            return;
        }
        a = "com.google.tv.mediadevicesapp.ChannelListingProvider";
        b = "channel_list";
        c = NativeProtocol.IMAGE_URL_KEY;
        d = Utilities.DB_KEY_IMAGE_NAME;
        e = "channelNumber";
        f = "subName";
    }

    private static synchronized int h() {
        int intValue;
        synchronized (cbd.class) {
            if (g == null) {
                int i = 0;
                try {
                    i = Class.forName("com.google.android.tv.Version").getField("GTV_SDK_INT").getInt(null);
                    getStaticLogger(cbd.class).info("gtv sdk vers: " + i);
                } catch (Throwable th) {
                }
                g = Integer.valueOf(i);
            }
            intValue = g.intValue();
        }
        return intValue;
    }

    public static boolean a() {
        return h() == 0;
    }

    public cbd(App app, IService iService) {
        this.h = app;
        this.l = iService;
    }

    public void b() {
        bpu a2;
        try {
            if (c().bU() && (a2 = c().ax().a()) != null) {
                if (a2.f() && a2.l()) {
                    k();
                } else if (c().e() && c().aW()) {
                    i();
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem refreshing channel list: ", th);
        }
    }

    private boolean i() {
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        try {
            Cursor query = c().getContentResolver().query(j(), null, null, null, null);
            if (query == null) {
                return false;
            }
            try {
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    String string = query.getString(query.getColumnIndex(c));
                    String string2 = query.getString(query.getColumnIndex(f));
                    String string3 = query.getString(query.getColumnIndex(d));
                    if (string != null) {
                        if (string.lastIndexOf(e) != -1) {
                            String queryParameter = Uri.parse(string).getQueryParameter(e);
                            try {
                                queryParameter = String.valueOf(Integer.parseInt(queryParameter));
                            } catch (Throwable th) {
                            }
                            hashtable2.put(queryParameter, string);
                        }
                        if (string2 != null) {
                            hashtable.put(string2, string);
                        } else if (string3 != null) {
                            hashtable.put(string3, string);
                        }
                    }
                    query.moveToNext();
                }
                query.close();
                if (hashtable.size() > 0) {
                    this.j.clear();
                    this.j = hashtable;
                }
                if (hashtable2.size() > 0) {
                    this.k.clear();
                    this.k = hashtable2;
                }
                getLogger().info("refreshed " + this.k.size() + " channels from gtv");
                return true;
            } catch (Throwable th2) {
                query.close();
                throw th2;
            }
        } catch (Throwable th3) {
            getLogger().log(Level.SEVERE, "problem: ", th3);
            return false;
        }
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return this.j.get(str);
    }

    private Uri j() {
        if (this.i == null) {
            this.i = Uri.parse("content://" + a).buildUpon().appendPath(b).build();
        }
        return this.i;
    }

    public App c() {
        return this.h;
    }

    public String b(String str) {
        String str2 = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            String[] split = str.split(",");
            int length = split.length;
            int i = 0;
            String str3 = null;
            while (i < length) {
                try {
                    String str4 = split[i];
                    try {
                        str4 = String.valueOf(Integer.parseInt(str4));
                    } catch (Throwable th) {
                    }
                    String str5 = this.k.get(str4);
                    if (str5 == null) {
                        i++;
                        str3 = str5;
                    } else {
                        return str5;
                    }
                } catch (Throwable th2) {
                    str2 = str3;
                    th = th2;
                    getLogger().log(Level.SEVERE, "problem getting channel: ", th);
                    return str2;
                }
            }
            return str3;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public boolean d() {
        return this.k.isEmpty();
    }

    public boolean c(String str) {
        return this.k.containsKey(str);
    }

    private void k() {
        Map<String, String> v;
        if (c().bU() && this.l != null) {
            boolean z = System.currentTimeMillis() > this.m + 14400000;
            if ((this.k.isEmpty() || z) && (v = this.l.v()) != null && v.size() > 0) {
                this.k.clear();
                this.k.putAll(v);
            }
        }
    }

    public Map<String, String> e() {
        return this.k;
    }

    public long f() {
        return this.m;
    }

    public void a(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            this.k.clear();
            this.k.putAll(map);
            this.m = System.currentTimeMillis();
        }
    }

    public void g() {
        this.k.clear();
        this.j.clear();
    }
}
