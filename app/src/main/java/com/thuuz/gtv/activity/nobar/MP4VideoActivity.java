package com.thuuz.gtv.activity.nobar;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
// [removed legacy tracker import]
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bkt;
import defpackage.bku;
import defpackage.bkv;
import defpackage.bkw;
import defpackage.bkx;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class MP4VideoActivity extends AbstractNoActionBarActivity {
    private String f;
    private View g;
    private boolean h;
    private VideoView i;
    private boolean j;
    private int k;
    private boolean l;
    private AudioManager.OnAudioFocusChangeListener m = new bkx(this, null);
    private boolean n;
    private boolean o;
    private int p;
    private MediaController q;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        if (B() && !b()) {
            setRequestedOrientation(0);
            e().info("requesting landscape orientation");
        } else {
            e().info("unchanged orientation");
        }
        setContentView(bbr.mp4_layout);
        this.g = findViewById(bbq.progress_down);
        if (getIntent() != null && getIntent().getExtras() != null) {
            this.f = getIntent().getExtras().getString(NativeProtocol.IMAGE_URL_KEY);
        }
        if (this.f == null && bundle != null) {
            this.f = bundle.getString(NativeProtocol.IMAGE_URL_KEY);
        }
        if (this.f == null || this.f.length() == 0) {
            e().warning("no video url. bailing back to game page!");
            finish();
            return;
        }
        if (aF()) {
            this.i = (VideoView) findViewById(bbq.surface);
            this.i.setKeepScreenOn(true);
            this.i.setOnCompletionListener(new bkt(this));
            this.i.setOnPreparedListener(new bku(this));
            this.i.setOnErrorListener(new bkv(this));
            this.q = new MediaController(this);
            this.q.setMediaPlayer(this.i);
            this.i.setMediaController(this.q);
            this.i.setVideoURI(Uri.parse(this.f));
        }
    }

    private boolean aF() {
        try {
            if (((AudioManager) getSystemService("audio")).requestAudioFocus(this.m, 3, 2) != 1) {
                aG();
                finish();
                return false;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return true;
    }

    private void aG() {
        c("Cannot get audio channel", "Please turn off other audio playback");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aH() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            e().info("abandoning audio focus");
            audioManager.abandonAudioFocus(this.m);
            this.n = true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            bundle.putString(NativeProtocol.IMAGE_URL_KEY, this.f);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            boolean aC = aC();
            super.onResume();
            this.l = true;
            if (this.j && this.o && this.i != null) {
                this.q.requestFocus();
                this.q.show();
                return;
            }
            if (this.h) {
                aI();
            }
            if (aC && aF() && this.i != null) {
                this.i.seekTo(this.p);
                this.i.start();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        try {
            if (this.i != null && this.i.isPlaying()) {
                this.i.pause();
                this.p = this.i.getCurrentPosition();
                aH();
                this.o = true;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aI() {
        try {
            if (!this.j) {
                if (!this.h || !this.l) {
                    int i = this.k;
                    this.k = i + 1;
                    e(i);
                    return;
                } else {
                    this.i.requestFocus();
                    this.j = true;
                    if (aF()) {
                        this.i.start();
                    } else {
                        return;
                    }
                }
            }
            if (this.g != null) {
                if (!this.h) {
                    this.g.setVisibility(0);
                } else {
                    this.g.setVisibility(8);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void e(int i) {
        if (i > 40) {
            b("Cannot start video", bbt.pls_try_again_later);
            finish();
        } else {
            aa().postDelayed(new bkw(this), 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        aH();
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        aH();
        super.onBackPressed();
    }
}
