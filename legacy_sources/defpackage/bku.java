package defpackage;

import android.media.MediaPlayer;
import android.view.View;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bku implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ MP4VideoActivity a;

    public bku(MP4VideoActivity mP4VideoActivity) {
        this.a = mP4VideoActivity;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        View view;
        boolean z;
        try {
            view = this.a.g;
            view.setVisibility(8);
            this.a.h = true;
            z = this.a.l;
            if (z) {
                this.a.aI();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }
}
