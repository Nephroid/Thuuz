package defpackage;

import com.android.volley.VolleyError;
import com.thuuz.gtv.view.PlayThumbnail;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class cbt implements ccc {
    final /* synthetic */ PlayThumbnail a;

    public cbt(PlayThumbnail playThumbnail) {
        this.a = playThumbnail;
    }

    @Override // defpackage.ccc
    public void a(NetworkListenableImageView networkListenableImageView, VolleyError volleyError) {
        String str;
        if (this.a.getLogger().isLoggable(Level.INFO)) {
            Logger logger = this.a.getLogger();
            StringBuilder append = new StringBuilder().append("cannot get thumbnail image for: ");
            str = this.a.g;
            logger.info(append.append(str).append(" because: ").append(volleyError).toString());
        }
    }
}
