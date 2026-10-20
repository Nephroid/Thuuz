package com.google.android.gms.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import defpackage.xr;
import defpackage.zy;
import java.util.HashMap;
import java.util.Map;

@zy
/* renamed from: com.google.android.gms.internal.do, reason: invalid class name */
/* loaded from: classes.dex */
public final class Cdo extends FrameLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private final gv a;
    private final MediaController b;
    private final xr c;
    private final VideoView d;
    private long e;
    private String f;

    public Cdo(Context context, gv gvVar) {
        super(context);
        this.a = gvVar;
        this.d = new VideoView(context);
        addView(this.d, new FrameLayout.LayoutParams(-1, -1, 17));
        this.b = new MediaController(context);
        this.c = new xr(this);
        this.c.b();
        this.d.setOnCompletionListener(this);
        this.d.setOnPreparedListener(this);
        this.d.setOnErrorListener(this);
    }

    private static void a(gv gvVar, String str) {
        a(gvVar, str, new HashMap(1));
    }

    public static void a(gv gvVar, String str, String str2) {
        boolean z = str2 == null;
        HashMap hashMap = new HashMap(z ? 2 : 3);
        hashMap.put("what", str);
        if (!z) {
            hashMap.put("extra", str2);
        }
        a(gvVar, "error", hashMap);
    }

    private static void a(gv gvVar, String str, String str2, String str3) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(str2, str3);
        a(gvVar, str, hashMap);
    }

    private static void a(gv gvVar, String str, Map<String, String> map) {
        map.put("event", str);
        gvVar.a("onVideoEvent", map);
    }

    public void a() {
        this.c.a();
        this.d.stopPlayback();
    }

    public void a(int i) {
        this.d.seekTo(i);
    }

    public void a(MotionEvent motionEvent) {
        this.d.dispatchTouchEvent(motionEvent);
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(boolean z) {
        if (z) {
            this.d.setMediaController(this.b);
        } else {
            this.b.hide();
            this.d.setMediaController(null);
        }
    }

    public void b() {
        if (TextUtils.isEmpty(this.f)) {
            a(this.a, "no_src", (String) null);
        } else {
            this.d.setVideoPath(this.f);
        }
    }

    public void c() {
        this.d.pause();
    }

    public void d() {
        this.d.start();
    }

    public void e() {
        long currentPosition = this.d.getCurrentPosition();
        if (this.e != currentPosition) {
            a(this.a, "timeupdate", "time", String.valueOf(((float) currentPosition) / 1000.0f));
            this.e = currentPosition;
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        a(this.a, "ended");
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        a(this.a, String.valueOf(i), String.valueOf(i2));
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        a(this.a, "canplaythrough", "duration", String.valueOf(this.d.getDuration() / 1000.0f));
    }
}
