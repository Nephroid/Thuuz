package com.thuuz.gtv.util;

import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bzy;

/* loaded from: classes.dex */
public enum ServerChoice {
    SERVER_TYPE_PROD(1, "mobile.thuuz.com/", true, "186985411340320", "www.thuuz.com/", "content.thuuz.com/"),
    SERVER_TYPE_DEV(2, "dev.enthuuz.com/", false, "194308550600515", "content.thuuz.com/"),
    SERVER_TYPE_STAGE(3, "stage.thuuz.com/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_WEB1(4, "web1-test-elb.enthuuz.com/", true, "186985411340320", "web1-test-elb.enthuuz.com/", "content.thuuz.com/"),
    SERVER_TYPE_LELAND(5, "192.168.50.65:8000/", false, "181526801889987", "content-stage.thuuz.com/"),
    SERVER_TYPE_JORDAN(6, "jtoor.dev.thuuz.com:80/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_DENIS(7, "192.168.50.64:8000/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_TYLER(8, "192.168.50.230:8000/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_BEN(9, "192.168.50.67:8000/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_ESHITA(10, "192.168.50.61:8000/", false, "140145106049068", "content-stage.thuuz.com/"),
    SERVER_TYPE_JON(11, "192.168.50.66:8000/", false, "140145106049068", "content-stage.thuuz.com/");

    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    private String contentServer;
    private String fbKey;
    private int id;
    private boolean isSecure;
    private String mServer;
    private String webServer;
    public static ServerChoice l = SERVER_TYPE_PROD;
    public static final String m = "http://" + SERVER_TYPE_PROD.a();

    static {
        String str;
        if (l.c()) {
            str = "https://" + l.a();
        } else {
            str = "http://" + l.a();
        }
        n = str;
        o = n + "2.4/";
        p = "http://" + l.a() + "2.4/";
        q = "http://" + l.b();
        r = "http://" + l.d();
    }

    ServerChoice(int i, String str, boolean z, String str2, String str3, String str4) {
        this.id = i;
        this.mServer = str;
        this.isSecure = z;
        this.fbKey = str2;
        this.webServer = str3;
        this.contentServer = str4;
    }

    ServerChoice(int i, String str, boolean z, String str2, String str3) {
        this(i, str, z, str2, str, str3);
    }

    public String a() {
        return this.mServer;
    }

    public String b() {
        return this.webServer;
    }

    public boolean c() {
        return this.isSecure;
    }

    public String d() {
        return this.contentServer;
    }

    public static void a(int i, AbstractActivity abstractActivity) {
        String str;
        l = a(i);
        if (l.c()) {
            str = "https://" + l.a();
        } else {
            str = "http://" + l.a();
        }
        n = str;
        o = n + "2.4/";
        p = "http://" + l.a() + "2.4/";
        r = "http://" + l.d();
        q = "http://" + l.b();
        bzy.getStaticLogger(ServerChoice.class).info("setting server choice: " + l.name());
        if (abstractActivity != null) {
            abstractActivity.j().w(i);
        }
    }

    public int e() {
        return this.id;
    }

    public static ServerChoice a(int i) {
        for (ServerChoice serverChoice : values()) {
            if (i == serverChoice.e()) {
                return serverChoice;
            }
        }
        return SERVER_TYPE_PROD;
    }

    public static String f() {
        return l.b().replace("/", "");
    }
}
