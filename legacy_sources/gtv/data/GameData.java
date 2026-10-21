package com.thuuz.gtv.data;

import android.graphics.Point;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.OnlineResource;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbn;
import defpackage.bbp;
import defpackage.bbt;
import defpackage.bml;
import defpackage.bmn;
import defpackage.bmz;
import defpackage.boc;
import defpackage.bod;
import defpackage.box;
import defpackage.bpa;
import defpackage.bpm;
import defpackage.bpp;
import defpackage.bpq;
import defpackage.bpr;
import defpackage.bpx;
import defpackage.bzy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GameData extends bpq implements Comparable<GameData> {
    public static final int a = "yyyy-MM-dd hh:mm:ss".length();
    private static Logger e;
    private App c;
    private long d;
    private Date f;

    /* loaded from: classes.dex */
    public enum DateRequestType {
        DATE,
        TIME,
        DATETIME,
        DAY_DATETIME,
        DATE_SUPER_SHORT,
        WEEKDAY,
        SHORT_DATETIME
    }

    public void a(TableLayout tableLayout, TableLayout tableLayout2) {
        try {
            b(tableLayout, tableLayout2);
            if (ar().has("linescores")) {
                JSONArray jSONArray = ar().getJSONArray("linescores");
                if (jSONArray.length() < 2) {
                    b(tableLayout, tableLayout2);
                } else {
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    JSONObject jSONObject2 = jSONArray.getJSONObject(1);
                    List<List<String>> a2 = a(jSONObject);
                    List<List<String>> a3 = a(jSONObject2);
                    if (Build.VERSION.SDK_INT > 11) {
                        b(a2, a3, tableLayout);
                        b(a3, a2, tableLayout2);
                    } else {
                        a(a2, a3, tableLayout);
                        a(a3, a2, tableLayout2);
                    }
                }
            }
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
        } finally {
            b(tableLayout, tableLayout2);
        }
    }

    private void b(TableLayout tableLayout, TableLayout tableLayout2) {
        try {
            a(tableLayout);
            a(tableLayout2);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void a(TableLayout tableLayout) {
        int i = 0;
        try {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(0);
            while (true) {
                int i2 = i;
                if (i2 < tableRow.getChildCount()) {
                    TextView textView = (TextView) tableRow.getChildAt(i2);
                    textView.setTextColor(this.c.getResources().getColor(bbn.white_ff));
                    textView.setText("");
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private List<List<String>> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("score")) {
            JSONArray jSONArray = jSONObject.getJSONArray("score");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (!jSONArray.isNull(i)) {
                    arrayList.add(String.valueOf(jSONArray.getInt(i)));
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (jSONObject.has("tiebreaker")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("tiebreaker");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                if (jSONArray2.isNull(i2)) {
                    arrayList2.add("");
                } else {
                    arrayList2.add(String.valueOf(jSONArray2.getInt(i2)));
                }
            }
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(arrayList);
        arrayList3.add(arrayList2);
        return arrayList3;
    }

    private void a(List<List<String>> list, List<List<String>> list2, TableLayout tableLayout) {
        try {
            if (list != null) {
                if (list.size() >= 2 && list2 != null && list2.size() >= 2) {
                    List<String> list3 = list.get(0);
                    List<String> list4 = list2.get(0);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(0);
                    if (tableRow == null) {
                        return;
                    }
                    int size = 5 - list3.size();
                    if (size < 0) {
                        size = 0;
                    }
                    int i = 0;
                    int i2 = size;
                    for (String str : list3) {
                        if (i > 4) {
                            getLogger().warning("number tennis scores > 5, cannot display: " + this);
                            i++;
                        } else {
                            TextView textView = (TextView) tableRow.getChildAt(i2);
                            if (textView != null) {
                                if (" ".equals(str)) {
                                    textView.setText("");
                                } else {
                                    if (list4.size() > i ? Integer.parseInt(str) < Integer.parseInt(list4.get(i)) : false) {
                                        textView.setTypeface(null, 0);
                                        textView.setTextSize(1, 13.0f);
                                        textView.setTextColor(D().getResources().getColor(bbn.white_f1));
                                    } else {
                                        textView.setTextSize(1, 14.0f);
                                        textView.setTypeface(null, 1);
                                        textView.setTextColor(D().getResources().getColor(bbn.white_f8));
                                    }
                                    textView.setText(str);
                                }
                                i++;
                                i2++;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        } finally {
            a(tableLayout);
        }
    }

    private void b(List<List<String>> list, List<List<String>> list2, TableLayout tableLayout) {
        try {
            if (list != null) {
                if (list.size() >= 2 && list2 != null && list2.size() >= 2) {
                    List<String> list3 = list.get(0);
                    List<String> list4 = list.get(1);
                    List<String> list5 = list2.get(0);
                    List<String> list6 = list2.get(1);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(0);
                    if (tableRow == null) {
                        return;
                    }
                    int size = 5 - list3.size();
                    if (size < 0) {
                        size = 0;
                    }
                    int i = 0;
                    int i2 = size;
                    for (String str : list3) {
                        if (i > 4) {
                            getLogger().warning("number tennis scores > 5, cannot display: " + this);
                            i++;
                        } else {
                            ArrayList<Point> arrayList = new ArrayList();
                            ArrayList<Point> arrayList2 = new ArrayList();
                            StringBuilder sb = new StringBuilder();
                            TextView textView = (TextView) tableRow.getChildAt(i2);
                            if (textView != null) {
                                if (" ".equals(str)) {
                                    textView.setText("");
                                } else {
                                    boolean z = list5.size() > i ? Integer.parseInt(str) < Integer.parseInt(list5.get(i)) : false;
                                    sb.append(str);
                                    if (z) {
                                        arrayList2.add(new Point(sb.length() - str.length(), sb.length()));
                                    }
                                    if (list4.size() > i) {
                                        String str2 = list4.get(i);
                                        if (list6.size() > i) {
                                            String str3 = list6.get(i);
                                            if (str2.length() > 0 && str3.length() > 0) {
                                                sb.append(str2);
                                                arrayList.add(new Point(sb.length() - str2.length(), sb.length()));
                                            } else if (str2.length() != str3.length()) {
                                                getLogger().info("skipping missing tiebreaker: " + this);
                                            }
                                        } else {
                                            getLogger().info("skipping missing tiebreaker: " + this);
                                        }
                                    }
                                    SpannableString spannableString = new SpannableString(sb.toString());
                                    for (Point point : arrayList2) {
                                        spannableString.setSpan(new ForegroundColorSpan(D().getResources().getColor(bbn.losing_team_text)), point.x, point.y, 0);
                                    }
                                    for (Point point2 : arrayList) {
                                        spannableString.setSpan(new SuperscriptSpan(), point2.x, point2.y, 0);
                                        spannableString.setSpan(new ForegroundColorSpan(D().getResources().getColor(bbn.light_a_gray)), point2.x, point2.y, 0);
                                        spannableString.setSpan(new RelativeSizeSpan(0.8f), point2.x, point2.y, 0);
                                    }
                                    textView.setText(spannableString, TextView.BufferType.SPANNABLE);
                                }
                                i++;
                                i2++;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        } finally {
            a(tableLayout);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0049 A[Catch: JSONException -> 0x00d3, TryCatch #0 {JSONException -> 0x00d3, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x0022, B:9:0x0030, B:11:0x003c, B:12:0x0041, B:14:0x0049, B:16:0x0055, B:17:0x005a, B:19:0x0062, B:21:0x006e, B:22:0x0073, B:25:0x007c, B:27:0x0082, B:28:0x009a, B:31:0x00c7, B:33:0x00e4, B:35:0x00ea, B:37:0x00fd, B:39:0x009e, B:41:0x00a4, B:43:0x00ae, B:44:0x00b8), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062 A[Catch: JSONException -> 0x00d3, TryCatch #0 {JSONException -> 0x00d3, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x0022, B:9:0x0030, B:11:0x003c, B:12:0x0041, B:14:0x0049, B:16:0x0055, B:17:0x005a, B:19:0x0062, B:21:0x006e, B:22:0x0073, B:25:0x007c, B:27:0x0082, B:28:0x009a, B:31:0x00c7, B:33:0x00e4, B:35:0x00ea, B:37:0x00fd, B:39:0x009e, B:41:0x00a4, B:43:0x00ae, B:44:0x00b8), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fd A[Catch: JSONException -> 0x00d3, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00d3, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x0022, B:9:0x0030, B:11:0x003c, B:12:0x0041, B:14:0x0049, B:16:0x0055, B:17:0x005a, B:19:0x0062, B:21:0x006e, B:22:0x0073, B:25:0x007c, B:27:0x0082, B:28:0x009a, B:31:0x00c7, B:33:0x00e4, B:35:0x00ea, B:37:0x00fd, B:39:0x009e, B:41:0x00a4, B:43:0x00ae, B:44:0x00b8), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(int r7) {
        /*
            r6 = this;
            r1 = 0
            java.lang.String r0 = ""
            java.lang.String r2 = "linescores"
            org.json.JSONObject r2 = r6.ar()     // Catch: org.json.JSONException -> Ld3
            java.lang.String r3 = "linescores"
            boolean r2 = r2.has(r3)     // Catch: org.json.JSONException -> Ld3
            if (r2 == 0) goto Ldf
            org.json.JSONObject r2 = r6.ar()     // Catch: org.json.JSONException -> Ld3
            java.lang.String r3 = "linescores"
            org.json.JSONArray r2 = r2.getJSONArray(r3)     // Catch: org.json.JSONException -> Ld3
            int r3 = r2.length()     // Catch: org.json.JSONException -> Ld3
            r4 = 2
            if (r3 < r4) goto L9e
            java.lang.String r0 = ""
            org.json.JSONObject r3 = r2.getJSONObject(r7)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r2 = "score"
            boolean r2 = r3.has(r2)     // Catch: org.json.JSONException -> Ld3
            if (r2 == 0) goto L101
            java.lang.String r2 = "score"
            org.json.JSONArray r2 = r3.getJSONArray(r2)     // Catch: org.json.JSONException -> Ld3
            int r4 = r2.length()     // Catch: org.json.JSONException -> Ld3
            if (r4 <= 0) goto L101
            r4 = 0
            int r2 = r2.getInt(r4)     // Catch: org.json.JSONException -> Ld3
        L41:
            java.lang.String r4 = "wickets"
            boolean r4 = r3.has(r4)     // Catch: org.json.JSONException -> Ld3
            if (r4 == 0) goto L5a
            java.lang.String r4 = "wickets"
            org.json.JSONArray r4 = r3.getJSONArray(r4)     // Catch: org.json.JSONException -> Ld3
            int r5 = r4.length()     // Catch: org.json.JSONException -> Ld3
            if (r5 <= 0) goto L5a
            r1 = 0
            int r1 = r4.getInt(r1)     // Catch: org.json.JSONException -> Ld3
        L5a:
            java.lang.String r4 = "overs"
            boolean r4 = r3.has(r4)     // Catch: org.json.JSONException -> Ld3
            if (r4 == 0) goto L73
            java.lang.String r4 = "overs"
            org.json.JSONArray r3 = r3.getJSONArray(r4)     // Catch: org.json.JSONException -> Ld3
            int r4 = r3.length()     // Catch: org.json.JSONException -> Ld3
            if (r4 <= 0) goto L73
            r0 = 0
            java.lang.String r0 = r3.getString(r0)     // Catch: org.json.JSONException -> Ld3
        L73:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Ld3
            r3.<init>()     // Catch: org.json.JSONException -> Ld3
            if (r2 <= 0) goto Lc3
            if (r1 <= 0) goto Lc3
            int r4 = r0.length()     // Catch: org.json.JSONException -> Ld3
            if (r4 <= 0) goto Lc3
            r3.append(r2)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r2 = "/"
            r3.append(r2)     // Catch: org.json.JSONException -> Ld3
            r3.append(r1)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r1 = " ("
            r3.append(r1)     // Catch: org.json.JSONException -> Ld3
            r3.append(r0)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r0 = ")"
            r3.append(r0)     // Catch: org.json.JSONException -> Ld3
        L9a:
            java.lang.String r0 = r3.toString()     // Catch: org.json.JSONException -> Ld3
        L9e:
            int r1 = r0.length()     // Catch: org.json.JSONException -> Ld3
            if (r1 != 0) goto Lb8
            java.util.ArrayList r1 = r6.R()     // Catch: org.json.JSONException -> Ld3
            int r2 = r1.size()     // Catch: org.json.JSONException -> Ld3
            if (r7 >= r2) goto Lb8
            java.lang.Object r0 = r1.get(r7)     // Catch: org.json.JSONException -> Ld3
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: org.json.JSONException -> Ld3
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: org.json.JSONException -> Ld3
        Lb8:
            java.lang.String r1 = "0"
            int r1 = r0.compareToIgnoreCase(r1)     // Catch: org.json.JSONException -> Ld3
            if (r1 != 0) goto Lc2
            java.lang.String r0 = ""
        Lc2:
            return r0
        Lc3:
            if (r2 <= 0) goto Le2
            if (r1 <= 0) goto Le2
            r3.append(r2)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r0 = "/"
            r3.append(r0)     // Catch: org.json.JSONException -> Ld3
            r3.append(r1)     // Catch: org.json.JSONException -> Ld3
            goto L9a
        Ld3:
            r0 = move-exception
            java.util.logging.Logger r1 = r6.getLogger()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "Cannot handle: "
            r1.log(r2, r3, r0)
        Ldf:
            java.lang.String r0 = ""
            goto Lc2
        Le2:
            if (r2 <= 0) goto Lfb
            int r1 = r0.length()     // Catch: org.json.JSONException -> Ld3
            if (r1 <= 0) goto Lfb
            r3.append(r2)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r1 = "/0 ("
            r3.append(r1)     // Catch: org.json.JSONException -> Ld3
            r3.append(r0)     // Catch: org.json.JSONException -> Ld3
            java.lang.String r0 = ")"
            r3.append(r0)     // Catch: org.json.JSONException -> Ld3
            goto L9a
        Lfb:
            if (r2 <= 0) goto L9a
            r3.append(r2)     // Catch: org.json.JSONException -> Ld3
            goto L9a
        L101:
            r2 = r1
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.data.GameData.a(int):java.lang.String");
    }

    public boolean a(Set<Integer> set) {
        if (!b() || D().aK()) {
            return c(set) || d(set);
        }
        return false;
    }

    public boolean a() {
        if (an().size() > 0) {
            return true;
        }
        return D().cb() && a(D(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_HIGHLIGHTS).size() > 0;
    }

    public boolean b() {
        return "golf".equalsIgnoreCase(k());
    }

    public Integer b(int i) {
        try {
            if (!ar().has("linescores")) {
                return null;
            }
            JSONArray jSONArray = ar().getJSONArray("linescores");
            if (i < 0 || jSONArray.length() <= i) {
                return null;
            }
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.has("pos")) {
                return Integer.valueOf(jSONObject.getInt("pos"));
            }
            return null;
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
            return null;
        }
    }

    public bmn a(AbstractActivity abstractActivity) {
        String k = k();
        if ("tennis".equalsIgnoreCase(k)) {
            return new box(this, abstractActivity);
        }
        if ("golf".equalsIgnoreCase(k)) {
            return new bmz(this, abstractActivity);
        }
        if ("cricket".equalsIgnoreCase(k)) {
            return new bml(this, abstractActivity);
        }
        if (b()) {
            return new bod(this, abstractActivity);
        }
        if (D().f(k)) {
            return new boc(this, abstractActivity);
        }
        return new bmn(this, abstractActivity);
    }

    public String c(int i) {
        try {
            if (!ar().has("linescores")) {
                return "";
            }
            JSONArray jSONArray = ar().getJSONArray("linescores");
            if (i < 0 || jSONArray.length() <= i) {
                return "";
            }
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (!jSONObject.has("thru")) {
                return "";
            }
            return jSONObject.getString("thru");
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
            return "";
        }
    }

    public String c() {
        try {
            if (!ar().has("linescores")) {
                return "";
            }
            JSONArray jSONArray = ar().getJSONArray("linescores");
            if (jSONArray.length() == 0) {
                return "";
            }
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            if (!jSONObject.has("venue")) {
                return "";
            }
            return jSONObject.getString("venue");
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
            return "";
        }
    }

    public String d() {
        String e2 = e("short_title");
        return (e2 == null || e2.length() <= 0) ? e() : e2;
    }

    public String e() {
        return e("title");
    }

    public String d(int i) {
        StringBuilder sb = new StringBuilder();
        try {
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        if (!ar().has("linescores")) {
            return "";
        }
        JSONArray jSONArray = ar().getJSONArray("linescores");
        if (jSONArray.length() < 2) {
            return "";
        }
        List<List<String>> a2 = a(jSONArray.getJSONObject(i));
        if (a2 == null || a2.size() < 2) {
            return "";
        }
        for (String str : a2.get(0)) {
            if (str.length() == 1) {
                sb.append(" ");
            }
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }

    public void a(Date date) {
        this.f = date;
    }

    public Date f() {
        return this.f;
    }

    /* loaded from: classes.dex */
    public enum GAME_RATING {
        RATING_DUD(1),
        RATING_OK(2),
        RATING_GOOD(3),
        RATING_GREAT(4);

        private int rating;

        GAME_RATING(int i) {
            this.rating = i;
        }

        public int a() {
            return this.rating;
        }
    }

    /* loaded from: classes.dex */
    public enum GAME_STATUS {
        UPCOMING(0),
        IN_PROGRESS(1),
        COMPLETED(2);

        private int status_int;

        GAME_STATUS(int i) {
            this.status_int = i;
        }

        public int a() {
            return this.status_int;
        }
    }

    public GameData(JSONObject jSONObject, App app) {
        super(jSONObject);
        this.c = app;
        this.d = System.currentTimeMillis();
    }

    public static Date a(DateTime dateTime) {
        try {
            return dateTime.withZone(DateTimeZone.getDefault()).toDate();
        } catch (Throwable th) {
            h().log(Level.SEVERE, "time calc problem: ", th);
            return new Date();
        }
    }

    public static Date a(String str) {
        try {
            if (str.length() > a) {
                str = str.substring(0, a);
            }
            return a(g().parseDateTime(str));
        } catch (Throwable th) {
            h().log(Level.SEVERE, "Cannot convert date: ", th);
            return new Date();
        }
    }

    public static Date b(String str) {
        try {
            if (str.length() > a) {
                str = str.substring(0, a);
            }
            return g().parseDateTime(str).toDate();
        } catch (Throwable th) {
            h().log(Level.SEVERE, "Cannot convert date: " + str, th);
            return new Date();
        }
    }

    public static DateTimeFormatter g() {
        return DateTimeFormat.forPattern("yyyy-MM-dd' 'HH:mm:ss").withZone(DateTimeZone.UTC);
    }

    public static Logger h() {
        if (e == null) {
            e = Logger.getLogger(GameData.class.getSimpleName() + ":thuuz");
            if (e.getLevel() != App.aV()) {
                e.setLevel(App.aV());
            }
        }
        return e;
    }

    public static int a(Calendar calendar, Calendar calendar2) {
        DateTime dateTime = new DateTime(calendar);
        DateTime dateTime2 = new DateTime(calendar2);
        int dayOfYear = dateTime2.getDayOfYear() - dateTime.getDayOfYear();
        if (dateTime2.isAfter(dateTime) && dayOfYear < 0) {
            return dayOfYear + dateTime.dayOfYear().getMaximumValue();
        }
        if (dateTime2.isBefore(dateTime) && dayOfYear > 0) {
            return dayOfYear + (-dateTime2.dayOfYear().getMaximumValue());
        }
        return dayOfYear;
    }

    public int i() {
        try {
            return this.b.getInt("id");
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return -1;
        }
    }

    public GAME_RATING e(int i) {
        com.thuuz.gtv.game.GameRating rating = new com.thuuz.gtv.game.GameExcitementCalculator().getGameRating(i);
        switch (rating) {
            case GREAT: return GAME_RATING.RATING_GREAT;
            case GOOD: return GAME_RATING.RATING_GOOD;
            case OK: return GAME_RATING.RATING_OK;
            case DUD:
            default: return GAME_RATING.RATING_DUD;
        }
    }

    public int b(Set<Integer> set) {
        try {
            return e(e(set)).a();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return 0;
        }
    }

    public GAME_STATUS j() {
        int i = 0;
        try {
            if (l("status")) {
                i = this.b.getInt("status");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getGameStatus() Cannot handle: ", th);
        }
        GAME_STATUS game_status = GAME_STATUS.UPCOMING;
        if (i == 1) {
            return GAME_STATUS.IN_PROGRESS;
        }
        return i == 2 ? GAME_STATUS.COMPLETED : game_status;
    }

    public String k() {
        return e("sport");
    }

    public String l() {
        return e("league");
    }

    public String[] m() {
        return new String[]{k(), l()};
    }

    public String n() {
        return e("date");
    }

    public Date c(String str) {
        try {
            return a(this.b.getString(str));
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getLocalDate() cannot handle: ", th);
            return new Date();
        }
    }

    public Date o() {
        try {
            return c("date");
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getLocalGameDate() cannot handle: ", th);
            return new Date();
        }
    }

    public Date p() {
        Date date = new Date();
        try {
            if (l("schedule_end")) {
                return c("schedule_end");
            }
            return null;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return date;
        }
    }

    public String q() {
        String r = r();
        String w = w();
        String x = x();
        if (x.length() > 0 && w.length() > 0) {
            r = r + w + " at " + x;
        }
        try {
            return r + (" (" + this.b.getString("gex") + "/100)");
        } catch (Throwable th) {
            return r;
        }
    }

    public String r() {
        try {
            if (!l("rating")) {
                return "Thuuz Alert";
            }
            switch (this.b.getInt("rating")) {
                case 3:
                    return "Good Game: ";
                case 4:
                    return "Great Game: ";
                default:
                    return "";
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle content: " + this.b.toString(), th);
            return "Thuuz Alert";
        }
    }

    public Integer s() {
        return Integer.valueOf(f("away_team_id"));
    }

    public Integer t() {
        return Integer.valueOf(f("home_team_id"));
    }

    public String u() {
        return e("away_team");
    }

    public String v() {
        return e("home_team");
    }

    public String w() {
        return e("away_team_short_name");
    }

    public String x() {
        return e("home_team_short_name");
    }

    public Integer y() {
        Integer num = 0;
        if (l("gex_away_fan_adjust")) {
            num = Integer.valueOf(f("gex_away_fan_adjust"));
        }
        return Integer.valueOf(Math.max(0, num.intValue()));
    }

    public Integer z() {
        Integer num = 0;
        if (l("gex_home_fan_adjust") && !k("gex_home_fan_adjust")) {
            num = Integer.valueOf(f("gex_home_fan_adjust"));
        }
        return Integer.valueOf(Math.max(0, num.intValue()));
    }

    public boolean c(Set<Integer> set) {
        return set != null && set.contains(t());
    }

    public boolean d(Set<Integer> set) {
        return set != null && set.contains(s());
    }

    public int e(Set<Integer> set) {
        try {
            boolean isFanOfHomeTeam = c(set);
            boolean isFanOfAwayTeam = d(set);
            int gexPredict = ar().has("gex_predict") ? ar().getInt("gex_predict") : 0;
            int gex = l("gex") ? this.b.getInt("gex") : 0;
            
            return new com.thuuz.gtv.game.GameExcitementCalculator().calculateGameExcitement(
                ak(),
                ar().has("gex_predict"),
                gexPredict,
                l("gex"),
                gex,
                isFanOfHomeTeam,
                isFanOfAwayTeam,
                l("gex_home_fan_adjust") && !k("gex_home_fan_adjust") ? f("gex_home_fan_adjust") : 0,
                l("gex_away_fan_adjust") ? f("gex_away_fan_adjust") : 0
            );
        } catch (Throwable th) {
            getLogger().log(java.util.logging.Level.SEVERE, "getGec() cannot handle: ", th);
            return 0;
        }
    }

    public String f(Set<Integer> set) {
        return String.valueOf(Integer.valueOf(e(set)));
    }

    public String g(Set<Integer> set) {
        return f(set);
    }

    public int h(Set<Integer> set) {
        Integer num = 0;
        String g = g(set);
        if (g != null && g.length() > 0) {
            try {
                num = Integer.valueOf(Integer.parseInt(g));
            } catch (Throwable th) {
                try {
                    num = Integer.valueOf(Integer.parseInt(g.substring(1)));
                    if (g.startsWith("-")) {
                        num = Integer.valueOf(-num.intValue());
                    }
                } catch (Throwable th2) {
                    getLogger().info("getGecInt() cannot parse GEC: " + g);
                }
            }
        }
        return num.intValue();
    }

    public boolean i(Set<bpr> set) {
        String[] m;
        boolean z;
        if (set == null || set.size() == 0 || (m = m()) == null || m.length < 2) {
            return false;
        }
        Iterator<bpr> it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            bpr next = it.next();
            try {
                String b = next.c().b();
                String e2 = next.e();
                if (b != null && e2 != null && b.equals(k()) && e2.equals(l())) {
                    z = true;
                    break;
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "cannot handle: ", th);
            }
        }
        return z;
    }

    public int a(boolean z, Set<Integer> set) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = bbp.gray_circle_small;
        int i5 = bbp.gray_dot_small;
        if (z) {
            i4 = bbp.gray_circle_large;
            i5 = bbp.gray_dot_large;
        }
        try {
            i3 = f("status");
            if (l("rating")) {
                switch (bpm.a[e(e(set)).ordinal()]) {
                    case 1:
                        i4 = bbp.yellow_circle_small;
                        i5 = bbp.yellow_dot_small;
                        if (z) {
                            i4 = bbp.yellow_circle_large;
                            i5 = bbp.yellow_dot_large;
                            break;
                        }
                        break;
                    case 2:
                        i4 = bbp.orange_circle_small;
                        i5 = bbp.orange_dot_small;
                        if (z) {
                            i4 = bbp.orange_circle_large;
                            i5 = bbp.orange_dot_large;
                            break;
                        }
                        break;
                    case 3:
                        i4 = bbp.red_circle_small;
                        i5 = bbp.red_dot_small;
                        if (z) {
                            i4 = bbp.red_circle_large;
                            i5 = bbp.red_dot_large;
                            break;
                        }
                        break;
                }
            }
            int i6 = i5;
            i2 = i4;
            i = i6;
        } catch (Throwable th) {
            int i7 = i4;
            i = i5;
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            i2 = i7;
        }
        return i3 == 0 ? i2 : i;
    }

    public int j(Set<Integer> set) {
        int i = 0;
        try {
            switch (b(set)) {
                case 0:
                case 1:
                    i = bbp.tiny_fan_gray;
                    break;
                case 2:
                    i = bbp.tiny_fan_yellow;
                    break;
                case 3:
                    i = bbp.tiny_fan_orange;
                    break;
                case 4:
                    i = bbp.tiny_fan_red;
                    break;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        return i;
    }

    public int k(Set<Integer> set) {
        int i = bbn.ok_color;
        try {
            GAME_RATING e2 = e(e(set));
            if (e2 == GAME_RATING.RATING_GREAT) {
                i = bbn.great_color;
            } else if (e2 == GAME_RATING.RATING_GOOD) {
                i = bbn.good_color;
            } else if (e2 == GAME_RATING.RATING_DUD) {
                i = bbn.dull_color;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getGecTextColor() cannot handle: ", th);
        }
        return i;
    }

    public int l(Set<Integer> set) {
        int i = bbn.black;
        try {
            if (ak()) {
                return k(set);
            }
            return i;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getGecTextColor() cannot handle: ", th);
            return i;
        }
    }

    public String A() {
        if (!l("game_clock")) {
            return "";
        }
        return e("game_clock");
    }

    public String B() {
        String str = "";
        try {
            GAME_STATUS j = j();
            switch (bpm.b[j.ordinal()]) {
                case 1:
                    str = a(DateRequestType.DAY_DATETIME, true);
                    break;
                case 2:
                    str = "In progress";
                    String A = A();
                    if (A.length() > 0) {
                        str = "In progress, " + A;
                        break;
                    }
                    break;
                case 3:
                    str = "Final - " + a(DateRequestType.DATE_SUPER_SHORT, false);
                    break;
                default:
                    getLogger().severe("unexpected game status: " + j);
                    break;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        return str;
    }

    public String C() {
        return e("pre_game_teaser");
    }

    public String m(Set<Integer> set) {
        boolean d = d(set);
        boolean c = c(set);
        if ((d && c) || (!d && !c)) {
            return e("gex_teaser");
        }
        if (d) {
            String e2 = e("gex_teaser_away");
            if (e2.length() == 0) {
                return e("gex_teaser");
            }
            return e2;
        }
        String e3 = e("gex_teaser_home");
        if (e3.length() == 0) {
            return e("gex_teaser");
        }
        return e3;
    }

    public String n(Set<Integer> set) {
        String str = " ";
        try {
            switch (bpm.b[j().ordinal()]) {
                case 1:
                    str = C();
                    break;
                case 2:
                case 3:
                    if (D().aK()) {
                        str = m(set);
                        break;
                    }
                    break;
                default:
                    getLogger().severe("unexpected case");
                    break;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getTeaserText() cannot handle: ", th);
        }
        if (str == null || str.length() == 0) {
            return " ";
        }
        return str;
    }

    public App D() {
        return this.c;
    }

    public String a(DateRequestType dateRequestType, boolean z) {
        SimpleDateFormat simpleDateFormat;
        String str = "";
        try {
            Date c = c("date");
            Calendar bh = D().bh();
            bh.setTime(c);
            switch (bpm.c[dateRequestType.ordinal()]) {
                case 1:
                    simpleDateFormat = new SimpleDateFormat("E");
                    break;
                case 2:
                    simpleDateFormat = new SimpleDateFormat("M/d");
                    break;
                case 3:
                    simpleDateFormat = new SimpleDateFormat("EEE, MMM d");
                    break;
                case 4:
                    if (z) {
                        simpleDateFormat = new SimpleDateFormat("h:mm a");
                        break;
                    } else {
                        simpleDateFormat = new SimpleDateFormat("h:mm a, z");
                        break;
                    }
                case 5:
                    simpleDateFormat = new SimpleDateFormat("M/d '@' h:mm a");
                    break;
                case 6:
                    if (z) {
                        if (D().bh().get(6) == bh.get(6)) {
                            simpleDateFormat = new SimpleDateFormat("'Today @' h:mm a");
                            break;
                        } else {
                            simpleDateFormat = new SimpleDateFormat("EEE '@' h:mm a");
                            break;
                        }
                    } else {
                        simpleDateFormat = new SimpleDateFormat("EEE '@' h:mm a");
                        break;
                    }
                default:
                    if (z) {
                        if (D().bh().get(6) == bh.get(6)) {
                            simpleDateFormat = new SimpleDateFormat("h:mm a, z");
                            break;
                        } else {
                            simpleDateFormat = new SimpleDateFormat("EEE, MMM d 'at' h:mm a, z");
                            break;
                        }
                    } else {
                        simpleDateFormat = new SimpleDateFormat("EEE, MMM d 'at' h:mm a, z");
                        break;
                    }
            }
            str = simpleDateFormat.format(c);
            return d(str);
        } catch (Throwable th) {
            String str2 = str;
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return str2;
        }
    }

    public String E() {
        String str = "";
        try {
            switch (bpm.b[j().ordinal()]) {
                case 1:
                    str = new SimpleDateFormat("h:mm").format(c("date"));
                    break;
                case 2:
                    str = Z();
                    break;
                case 3:
                    str = "final";
                    break;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        return str;
    }

    public String F() {
        String str = "";
        try {
            switch (bpm.b[j().ordinal()]) {
                case 1:
                    str = new SimpleDateFormat("a").format(c("date"));
                    break;
                case 2:
                    str = aa();
                    break;
                case 3:
                    str = "";
                    break;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        return str;
    }

    public static String d(String str) {
        return str.replace(" AM", "a").replace(" PM", "p");
    }

    public String G() {
        try {
            switch (bpm.b[j().ordinal()]) {
                case 3:
                    if (!l("watch_from")) {
                        return "";
                    }
                    String trim = this.b.getString("watch_from").trim();
                    if (trim.length() <= 0) {
                        return "";
                    }
                    return "Watch from: " + trim;
                default:
                    return "";
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return "";
        }
        getLogger().log(Level.SEVERE, "cannot handle: ", th);
        return "";
    }

    public int a(App app) {
        String str = "icon_" + k();
        int identifier = app.getResources().getIdentifier(str, "drawable", app.getPackageName());
        if (identifier == 0) {
            getLogger().warning("cannot get splg icon for: " + str);
        }
        return identifier;
    }

    public String H() {
        return k() + "_" + l();
    }

    public boolean I() {
        if (!l("schedule")) {
            return false;
        }
        try {
            if (as().length() <= 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return false;
        }
    }

    public ArrayList<bpa> b(Date date) {
        ArrayList<bpa> arrayList = new ArrayList<>();
        try {
            Iterator<JSONObject> it = a(date, !al()).iterator();
            while (it.hasNext()) {
                arrayList.add(new bpa(it.next()));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    public ArrayList<bpa> c(Date date) {
        return b(date);
    }

    public void J() {
        JSONArray jSONArray = new JSONArray();
        try {
            if (l("schedule")) {
                this.b.getJSONObject("schedule").put("broadcasts", jSONArray);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public ArrayList<bpa> d(Date date) {
        boolean before;
        bpa bpaVar;
        ArrayList<bpa> arrayList = new ArrayList<>();
        try {
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        if (!I()) {
            return arrayList;
        }
        JSONArray as = as();
        for (int i = 0; i < as.length(); i++) {
            JSONArray jSONArray = as.getJSONArray(i);
            D().bh().setTime(a(jSONArray.getString(0)));
            JSONArray jSONArray2 = jSONArray.getJSONArray(1);
            Calendar bh = D().bh();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                bpa bpaVar2 = new bpa(jSONArray2.getJSONObject(i2));
                bh.setTime(date);
                Date d = bpaVar2.d();
                if (am()) {
                    before = d.before(bh.getTime());
                    if (before) {
                        before = bh.before(bpaVar2.k());
                    }
                } else {
                    bh.add(12, 60);
                    before = d.before(bh.getTime());
                }
                if (before) {
                    Iterator<bpa> it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            bpaVar = null;
                            break;
                        }
                        bpaVar = it.next();
                        if (bpaVar.b().equals(bpaVar2.b())) {
                            break;
                        }
                    }
                    if (bpaVar != null) {
                        if (!bpaVar.ar().equals(bpaVar2.ar())) {
                            bh.setTime(date);
                            if (bpaVar.k().before(bh.getTime())) {
                                arrayList.remove(bpaVar);
                            } else if (bpaVar2.k().before(bh.getTime())) {
                            }
                        }
                    }
                    arrayList.add(bpaVar2);
                }
            }
        }
        HashSet hashSet = new HashSet();
        Iterator it2 = new ArrayList(arrayList).iterator();
        while (it2.hasNext()) {
            bpa bpaVar3 = (bpa) it2.next();
            String b = bpaVar3.b();
            if (hashSet.contains(b)) {
                arrayList.remove(bpaVar3);
            } else {
                hashSet.add(b);
            }
        }
        return arrayList;
    }

    private ArrayList<JSONObject> a(Date date, boolean z) {
        int i;
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        try {
            if (l("schedule")) {
                JSONArray as = as();
                D().bh().setTime(date);
                Calendar bh = D().bh();
                for (int i2 = 0; i2 < as.length(); i2++) {
                    JSONArray jSONArray = as.getJSONArray(i2).getJSONArray(1);
                    for (i = 0; i <  jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Date a2 = a(a(jSONObject, "start"));
                        if (am() && z) {
                            bh.setTime(a2);
                            int i3 = 300;
                            if (jSONObject.has("duration_min")) {
                                i3 = jSONObject.getInt("duration_min");
                            }
                            bh.add(12, i3);
                            i = date.after(bh.getTime()) ? i + 1 : 0;
                        }
                        arrayList.add(jSONObject);
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        return arrayList;
    }

    private JSONArray as() {
        return this.b.getJSONObject("schedule").getJSONArray("broadcasts");
    }

    public String a(JSONObject jSONObject, String str) {
        try {
            if (!jSONObject.has(str)) {
                return "";
            }
            return jSONObject.getString(str);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return "";
        }
    }

    public String a(IService iService) {
        String str = "<html><head><style>body { background-color: #" + Integer.toHexString(Integer.valueOf(D().getResources().getColor(bbn.transparent)).intValue()) + "; } .text { color: #f1f1f1; } </style></head><body>";
        try {
            String str2 = str + "<div class='text'>";
            Date o = o();
            Date p = p();
            str = ((p == null || !o.after(p)) ? str2 + D().getResources().getString(bbt.no_viewing_info, e(iService)) : str2 + D().getResources().getString(bbt.check_back_closer)) + "</div>";
            return str + "</body></html>";
        } catch (Throwable th) {
            String str3 = str;
            getLogger().log(Level.SEVERE, "problem: ", th);
            return str3;
        }
    }

    private String e(IService iService) {
        bpr a2;
        bpx c;
        return (iService == null || (a2 = iService.a(k(), l())) == null || (c = a2.c()) == null) ? "game" : c.e().toLowerCase();
    }

    public String a(ArrayList<bpa> arrayList, int i) {
        String str;
        String str2;
        String str3;
        int i2 = bbn.transparent;
        if (D().e()) {
            i2 = bbn.guide_bg;
        }
        String hexString = Integer.toHexString(Integer.valueOf(D().getResources().getColor(i2)).intValue());
        if (hexString.length() >= 8) {
            hexString = hexString.substring(2);
        }
        String str4 = "<html><head><style>body { background-color: #" + hexString + "; padding: 0; margin: 0; } table { margin-left: 0; margin-bottom: 0; } .text, td { color: #f1f1f1; } .date_text { text-decoration: underline; color: #999999; } .time { text-align: right; white-space: nowrap; padding-right: 2; padding-left: 10; } .time_text { font-weight: bold; color: #f1f1f1; } .on_text { font-weight: normal; color: #999999; } .callsigns { padding-bottom: 4; } .callsign_text { font-weight: bold; color: #f1f1f1; } .channel_text { font-weight: normal; color: #999999; } td { vertical-align: top; padding: 0 } </style></head><body>";
        try {
            String str5 = "";
            if (l("schedule")) {
                JSONObject jSONObject = ar().getJSONObject("schedule");
                if (jSONObject.has("header")) {
                    str5 = jSONObject.getString("header").trim();
                }
            }
            if (str5.length() > 0) {
                str4 = str4 + "<p class='time_text'>" + str5 + "</p>";
            }
            String str6 = "";
            String str7 = "";
            String str8 = str4 + "<table>";
            Iterator<bpa> it = arrayList.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                bpa next = it.next();
                if (i != -1 && i3 == i) {
                    break;
                }
                Date d = next.d();
                D().bh().setTime(d);
                String a2 = next.a();
                String b = next.b();
                String format = new SimpleDateFormat("EEE, MMM d").format(d);
                String d2 = d(new SimpleDateFormat("h:mm a").format(d));
                if (str6.length() == 0 || !format.equals(str6)) {
                    if (str6.length() > 0) {
                        str8 = str8 + "</td></tr>";
                    }
                    str8 = str8 + "<tr><td colspan='2' class='date_text'>" + format + "</td></tr>";
                    str = "";
                    str2 = format;
                } else {
                    str = str7;
                    str2 = str6;
                }
                if (str.length() == 0 || !d2.equals(str)) {
                    if (str.length() > 0) {
                        str8 = str8 + "</td></tr>";
                    }
                    str8 = str8 + "<tr><td class='time'><span class='time_text'>" + d2 + "</span><span class='on_text'> on </span></td><td class='callsigns'>";
                    str3 = d2;
                } else {
                    str3 = str;
                }
                String str9 = str8 + "<div><span class='callsign_text'>" + a2 + "</span>";
                if (b.length() > 0) {
                    str9 = str9 + "<span class='channel_text'> (" + b + ")</span>";
                }
                str8 = str9 + "</div>";
                i3++;
                String str10 = str3;
                str6 = str2;
                str7 = str10;
            }
            str4 = str8 + "</td></tr></table>";
            return str4 + "</body></html>";
        } catch (Throwable th) {
            String str11 = str4;
            getLogger().log(Level.SEVERE, "problem: ", th);
            return str11;
        }
    }

    public String o(Set<Integer> set) {
        String str = "";
        try {
            StringBuilder sb = new StringBuilder(ServerChoice.q);
            sb.append("gec-plots/");
            sb.append(k());
            sb.append("/");
            sb.append(l());
            sb.append("/");
            if (al()) {
                sb.append("live-");
            }
            if (a(set)) {
                if (c(set) && d(set)) {
                    sb.append("both-");
                } else if (c(set)) {
                    sb.append("home-");
                } else {
                    sb.append("away-");
                }
            }
            sb.append(e("id"));
            sb.append(".png");
            if (al()) {
                sb.append("?dummy=").append(Long.toString(System.currentTimeMillis()));
            }
            str = sb.toString();
            return str;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return str;
        }
    }

    public String K() {
        return a((AbstractActivity) null).g();
    }

    public String L() {
        return a((AbstractActivity) null).e();
    }

    public List<OnlineResource> M() {
        ArrayList arrayList = new ArrayList();
        if (l("online")) {
            try {
                JSONArray jSONArray = (JSONArray) this.b.get("online");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(new OnlineResource(jSONArray.getJSONObject(i)));
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return arrayList;
    }

    public List<OnlineResource> a(App app, OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE online_resource_category_type) {
        ArrayList arrayList = new ArrayList();
        for (OnlineResource onlineResource : M()) {
            Integer a2 = onlineResource.a();
            if (a2 != null && a2.equals(Integer.valueOf(online_resource_category_type.a())) && (!onlineResource.c().equals(OnlineResource.LAUNCH_TYPE.LAUNCH_TYPE_APP) || onlineResource.a(app))) {
                arrayList.add(onlineResource);
            }
        }
        return arrayList;
    }

    public boolean p(Set<Integer> set) {
        if (!l("league_group_ids") || set == null || set.size() == 0) {
            return false;
        }
        try {
            JSONArray jSONArray = ar().getJSONArray("league_group_ids");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (set.contains(Integer.valueOf(jSONArray.getInt(i)))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return false;
        }
    }

    public int[] N() {
        int[] iArr = null;
        if (l("league_group_ids")) {
            try {
                JSONArray jSONArray = ar().getJSONArray("league_group_ids");
                iArr = new int[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    iArr[i] = jSONArray.getInt(i);
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "cannot handle: ", th);
            }
        }
        return iArr;
    }

    public void O() {
        try {
            ar().put("status", GAME_STATUS.COMPLETED.a());
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
        }
    }

    private ArrayList<Integer> at() {
        Integer num;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (ar().has("rankings")) {
            try {
                JSONArray jSONArray = ar().getJSONArray("rankings");
                if (jSONArray.length() >= 2) {
                    if (jSONArray.isNull(0)) {
                        num = 0;
                    } else {
                        num = Integer.valueOf(jSONArray.getInt(0));
                    }
                    int i = 0;
                    if (!jSONArray.isNull(1)) {
                        i = Integer.valueOf(jSONArray.getInt(1));
                    }
                    arrayList.add(num);
                    arrayList.add(i);
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle json: " + ar(), th);
            }
        }
        return arrayList;
    }

    public Integer P() {
        Integer num;
        ArrayList<Integer> at = at();
        if (at.size() < 2 || (num = at.get(0)) == null || num.intValue() <= 0) {
            return null;
        }
        return num;
    }

    public Integer Q() {
        Integer num;
        ArrayList<Integer> at = at();
        if (at.size() < 2 || (num = at.get(1)) == null || num.intValue() <= 0) {
            return null;
        }
        return num;
    }

    public ArrayList<Integer> R() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = ar().getJSONArray("scores");
            if (jSONArray.length() > 0) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(0);
                if (jSONArray2.length() >= 2 && !jSONArray2.isNull(0) && !jSONArray2.isNull(1)) {
                    Integer valueOf = Integer.valueOf(jSONArray2.getInt(0));
                    Integer valueOf2 = Integer.valueOf(jSONArray2.getInt(1));
                    arrayList.add(valueOf);
                    arrayList.add(valueOf2);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return arrayList;
    }

    public boolean S() {
        return l("scores") && R().size() > 1;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(GameData gameData) {
        try {
            String n = n();
            String n2 = gameData.n();
            if (n2 == null) {
                return -1;
            }
            return n2.compareTo(n);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
            return 0;
        }
    }

    public String T() {
        StringBuilder sb = new StringBuilder();
        try {
            Integer P = P();
            if (P != null) {
                sb.append("#");
                sb.append(P.toString());
                sb.append(" ");
            }
            sb.append(w());
            sb.append(" at ");
            Integer Q = Q();
            if (Q != null) {
                sb.append("#");
                sb.append(Q.toString());
                sb.append(" ");
            }
            sb.append(x());
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot create notification text: ", th);
        }
        return sb.toString();
    }

    public String U() {
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<Integer> R = R();
            boolean z = S() && D().aK();
            String A = A();
            if (A.length() > 0) {
                if (z) {
                    sb.append(String.valueOf(R.get(0)));
                    sb.append("-");
                    sb.append(String.valueOf(R.get(1)));
                    sb.append(" : ");
                }
                sb.append(A);
            } else {
                sb.append("In progress");
                if (z) {
                    sb.append(": ");
                    sb.append(String.valueOf(R.get(0)));
                    sb.append("-");
                    sb.append(String.valueOf(R.get(1)));
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot create notification text: ", th);
        }
        return sb.toString();
    }

    public String q(Set<Integer> set) {
        String m;
        try {
            m = m(set);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "getNotificationLine3() cannot handle: ", th);
        }
        if (m.length() <= 0) {
            GAME_RATING e2 = e(e(set));
            if (e2 == GAME_RATING.RATING_GREAT) {
                return "GREAT game";
            }
            if (e2 == GAME_RATING.RATING_GOOD) {
                return "GOOD game";
            }
            if (e2 == GAME_RATING.RATING_OK) {
                return "OK game";
            }
            if (e2 == GAME_RATING.RATING_DUD) {
                return "DULL game";
            }
            return "";
        }
        return m;
    }

    public boolean V() {
        return l("home_team_id") && l("away_team_id") && t().intValue() > 0 && s().intValue() > 0;
    }

    public String W() {
        String k = k();
        if (k == null) {
            k = "none";
        }
        String l = l();
        if (l == null) {
            l = "none";
        }
        return String.format("%s%s/%s/game/%d/", ServerChoice.q, k, l, Integer.valueOf(i()));
    }

    public String X() {
        if (!l("shares")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject jSONObject = ar().getJSONObject("shares");
            if (jSONObject.getInt("count") == 0) {
                getLogger().warning("zero shares; ignoring");
                return null;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("users");
            for (int i = 0; i < Math.min(2, jSONArray.length()); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(jSONArray.getJSONObject(i).getString(Utilities.DB_KEY_IMAGE_NAME).trim());
            }
            if (jSONArray.length() > 2) {
                sb.append(" & ");
                int length = jSONArray.length() - 1;
                sb.append(length);
                sb.append(" other");
                if (length > 1) {
                    sb.append("s");
                }
            }
            return sb.toString();
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
            return null;
        }
    }

    public boolean Y() {
        return g("has_tv_schedule");
    }

    private String m(String str) {
        JSONObject jSONObject;
        try {
            if (!ar().has("short_game_clock") || (jSONObject = ar().getJSONObject("short_game_clock")) == null || !jSONObject.has(str)) {
                return "";
            }
            return jSONObject.getString(str);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return "";
        }
    }

    public String Z() {
        return m("l1");
    }

    public String aa() {
        return m("l2");
    }

    public int ab() {
        if (!ar().has("seconds_remaining")) {
            return 0;
        }
        try {
            return ar().getInt("seconds_remaining");
        } catch (JSONException e2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
            return 0;
        }
    }

    private String n(String str) {
        String str2 = null;
        try {
            JSONObject ar = ar();
            if (!ar.has("hashtags")) {
                return null;
            }
            JSONObject jSONObject = ar.getJSONObject("hashtags");
            if (!jSONObject.has(str)) {
                return null;
            }
            String string = jSONObject.getString(str);
            try {
                if (string.length() == 0) {
                    return null;
                }
                return string;
            } catch (Throwable th) {
                str2 = string;
                th = th;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return str2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String ac() {
        return n("comp");
    }

    public static int e(Date date) {
        try {
            int days = Days.daysBetween(new DateTime().withTimeAtStartOfDay(), new DateTime(date).withTimeAtStartOfDay()).getDays();
            int i = days < 0 ? -1 : 1;
            if (days != 0) {
                return days + i;
            }
            return days;
        } catch (Throwable th) {
            bzy.getStaticLogger(GameData.class).log(Level.SEVERE, "Cannot handle 0", th);
            return 0;
        }
    }

    public int r(Set<Integer> set) {
        try {
            boolean isFanOfHomeTeam = c(set);
            boolean isFanOfAwayTeam = d(set);
            int gexPredict = ar().has("gex_predict") ? ar().getInt("gex_predict") : 0;
            
            // Reusing calculateGameExcitement by isolating the predict part (treating as upcoming)
            return new com.thuuz.gtv.game.GameExcitementCalculator().calculateGameExcitement(
                true,
                ar().has("gex_predict"),
                gexPredict,
                false,
                0,
                isFanOfHomeTeam,
                isFanOfAwayTeam,
                0,
                0
            );
        } catch (Throwable th) {
            getLogger().log(java.util.logging.Level.SEVERE, "problem: ", th);
            return 0;
        }
    }

    public String ad() {
        return n("league");
    }

    public int ae() {
        return f("team_in_possession");
    }

    public boolean af() {
        return g("in_scoring_position");
    }

    public String ag() {
        String ah = ah();
        return ah.length() > 0 ? d() + " - " + ah : d();
    }

    public String ah() {
        return e("subtitle");
    }

    public String b(IService iService) {
        bpr a2;
        String str = "";
        if (iService != null && (a2 = iService.a(k(), l())) != null) {
            str = a2.h();
        }
        if (str == null || str.length() == 0) {
            str = l();
        }
        if (str == null) {
            return "";
        }
        return str.toUpperCase();
    }

    public String ai() {
        String E;
        String F = F();
        if (F.length() > 0) {
            E = E() + " " + F;
        } else {
            E = E();
        }
        String d = d(E);
        if (ak() && aj()) {
            return "est. " + d;
        }
        return d;
    }

    public boolean aj() {
        return l("date_type") && f("date_type") == 1;
    }

    public boolean ak() {
        return GAME_STATUS.UPCOMING == j();
    }

    public boolean al() {
        return GAME_STATUS.IN_PROGRESS == j();
    }

    public boolean am() {
        return GAME_STATUS.COMPLETED == j();
    }

    public List<bpp> an() {
        ArrayList arrayList = new ArrayList();
        if (l("highlights")) {
            try {
                JSONArray jSONArray = (JSONArray) this.b.get("highlights");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(new bpp(jSONArray.getJSONObject(i)));
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return arrayList;
    }

    public boolean ao() {
        return g("has_friend_thuuzes");
    }

    public boolean ap() {
        return g("has_fantasy_players");
    }

    public boolean c(IService iService) {
        bpr a2;
        if (iService == null || (a2 = iService.a(k(), l())) == null) {
            return false;
        }
        return a2.m();
    }

    public boolean d(IService iService) {
        bpr a2;
        bpx c;
        return iService == null || (a2 = iService.a(k(), l())) == null || (c = a2.c()) == null || c.f() == 0;
    }

    public String aq() {
        String k = k();
        if ("soccer".equals(k)) {
            return "vs";
        }
        for (bpx bpxVar : D().V()) {
            if (bpxVar.b().equals(k)) {
                return bpxVar.k();
            }
        }
        return "at";
    }
}
