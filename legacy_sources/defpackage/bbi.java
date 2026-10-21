package defpackage;

import com.thuuz.gtv.App;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* loaded from: classes.dex */
public class bbi extends Handler {
    final /* synthetic */ App a;

    private bbi(App app) {
        this.a = app;
    }

    public /* synthetic */ bbi(App app, bbg bbgVar) {
        this(app);
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord logRecord) {
        Map map;
        Map map2;
        if ((a() || logRecord.getLevel().intValue() >= Level.SEVERE.intValue()) && ((logRecord.getLoggerName() != null && logRecord.getLoggerName().endsWith("thuuz")) || (logRecord.getMessage() != null && logRecord.getMessage().contains("com.thuuz")))) {
            map = this.a.v;
            map.put(logRecord, "");
        }
        if (a()) {
            map2 = this.a.v;
            if (map2.size() > 10) {
                this.a.au();
            }
        }
    }

    private boolean a() {
        return false;
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    @Override // java.util.logging.Handler
    public void close() {
    }
}
