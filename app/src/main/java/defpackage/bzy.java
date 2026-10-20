package defpackage;

import android.content.Intent;
import android.os.Bundle;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bzy {
    public static String logAllStringExtras(Intent intent) {
        StringBuilder sb = new StringBuilder();
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str : extras.keySet()) {
                    sb.append(str);
                    sb.append(": ");
                    sb.append(intent.getStringExtra(str));
                    sb.append(", ");
                }
            }
        } catch (Throwable th) {
            getStaticLogger(bzy.class).log(Level.INFO, "problem: ", th);
        }
        return sb.toString();
    }

    public Logger getLogger() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public static Logger getStaticLogger(Class cls) {
        Logger logger = Logger.getLogger(cls.getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }
}
