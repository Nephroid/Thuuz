package defpackage;

import android.media.AudioManager;
import android.widget.MediaController;
import android.widget.VideoView;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bkx implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ MP4VideoActivity a;

    private bkx(MP4VideoActivity mP4VideoActivity) {
        this.a = mP4VideoActivity;
    }

    public /* synthetic */ bkx(MP4VideoActivity mP4VideoActivity, bkt bktVar) {
        this(mP4VideoActivity);
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        VideoView videoView;
        VideoView videoView2;
        boolean z;
        boolean z2;
        VideoView videoView3;
        VideoView videoView4;
        VideoView videoView5;
        boolean z3;
        boolean z4;
        MediaController mediaController;
        try {
            switch (i) {
                case -3:
                case -2:
                    videoView = this.a.i;
                    if (videoView != null) {
                        this.a.e().info("AudioFocus Transient Loss, pausing");
                        videoView2 = this.a.i;
                        videoView2.pause();
                        this.a.o = true;
                        return;
                    }
                    this.a.e().info("AudioFocus Transient Loss");
                    return;
                case -1:
                    z = this.a.l;
                    if (z) {
                        z2 = this.a.j;
                        if (z2) {
                            this.a.e().info("AudioFocus Loss, quitting");
                            videoView3 = this.a.i;
                            if (videoView3 != null) {
                                videoView4 = this.a.i;
                                videoView4.stopPlayback();
                            }
                            this.a.aH();
                            this.a.e().info("lost audio focus in highlight, finishing");
                            if (!this.a.isFinishing() && !this.a.isDestroyed()) {
                                this.a.finish();
                                return;
                            }
                            return;
                        }
                    }
                    this.a.e().info("AudioFocus Loss, considered transient before wasStarted");
                    return;
                case 0:
                default:
                    return;
                case 1:
                    this.a.e().info("AudioFocus Gain");
                    videoView5 = this.a.i;
                    if (videoView5 != null) {
                        z4 = this.a.o;
                        if (z4) {
                            mediaController = this.a.q;
                            mediaController.requestFocus();
                        }
                    }
                    z3 = this.a.n;
                    if (z3) {
                        this.a.e().info("have already abandoned; calling to abandon audio focus again");
                        this.a.aH();
                        return;
                    }
                    return;
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
