package defpackage;

import android.media.MediaPlayer;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bkv implements MediaPlayer.OnErrorListener {
    final /* synthetic */ MP4VideoActivity a;

    public bkv(MP4VideoActivity mP4VideoActivity) {
        this.a = mP4VideoActivity;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        try {
            this.a.e().info("problem with video: " + i + " extra: " + i2);
            if (this.a.d()) {
                this.a.b("Problem with Video", bbt.pls_try_again_later);
            }
            this.a.finish();
            return true;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Problem with alert: ", th);
            return true;
        }
    }
}
