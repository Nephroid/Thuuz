package defpackage;

import com.thuuz.gtv.view.PlayThumbnail;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class cbu implements ccd {
    final /* synthetic */ PlayThumbnail a;

    public cbu(PlayThumbnail playThumbnail) {
        this.a = playThumbnail;
    }

    @Override // defpackage.ccd
    public void a(NetworkListenableImageView networkListenableImageView) {
        String str;
        if (this.a.getLogger().isLoggable(Level.INFO)) {
            Logger logger = this.a.getLogger();
            StringBuilder append = new StringBuilder().append("got thumbnail image for: ");
            str = this.a.g;
            logger.info(append.append(str).toString());
        }
    }
}
