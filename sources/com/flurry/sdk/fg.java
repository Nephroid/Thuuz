package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class fg {
    @SuppressLint({"NewApi"})
    public static Point a() {
        Display defaultDisplay = ((WindowManager) eg.a().b().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        return point;
    }

    public static DisplayMetrics b() {
        Display defaultDisplay = ((WindowManager) eg.a().b().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static float c() {
        return b().density;
    }

    public static int a(int i) {
        return Math.round(i / b().density);
    }

    public static int b(int i) {
        return Math.round(b().density * i);
    }

    public static int d() {
        return a().x;
    }

    public static int e() {
        return a().y;
    }

    public static int f() {
        return a(d());
    }

    public static int g() {
        return a(e());
    }

    public static int h() {
        Point a = a();
        if (a.x == a.y) {
            return 3;
        }
        if (a.x < a.y) {
            return 1;
        }
        return 2;
    }

    public static Pair<Integer, Integer> i() {
        return Pair.create(Integer.valueOf(f()), Integer.valueOf(g()));
    }

    public static Pair<Integer, Integer> c(int i) {
        int f = f();
        int g = g();
        switch (i) {
            case 2:
                return Pair.create(Integer.valueOf(g), Integer.valueOf(f));
            default:
                return Pair.create(Integer.valueOf(f), Integer.valueOf(g));
        }
    }
}
