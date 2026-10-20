package defpackage;

import android.media.MediaPlayer;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bkt implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ MP4VideoActivity a;

    public bkt(MP4VideoActivity mP4VideoActivity) {
        this.a = mP4VideoActivity;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        try {
            this.a.d("Video Finished", "");
            this.a.aH();
            if (!this.a.isFinishing() && !this.a.isDestroyed()) {
                this.a.finish();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }
}
