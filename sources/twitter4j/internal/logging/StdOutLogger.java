package twitter4j.internal.logging;

import java.util.Date;
import twitter4j.conf.ConfigurationContext;

/* loaded from: classes.dex */
final class StdOutLogger extends Logger {
    private static final boolean DEBUG = ConfigurationContext.getInstance().isDebugEnabled();

    @Override // twitter4j.internal.logging.Logger
    public boolean isDebugEnabled() {
        return DEBUG;
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // twitter4j.internal.logging.Logger
    public void debug(String str) {
        if (DEBUG) {
            System.out.println("[" + new Date() + "]" + str);
        }
    }

    @Override // twitter4j.internal.logging.Logger
    public void debug(String str, String str2) {
        if (DEBUG) {
            debug(str + str2);
        }
    }

    @Override // twitter4j.internal.logging.Logger
    public void info(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void info(String str, String str2) {
        info(str + str2);
    }

    @Override // twitter4j.internal.logging.Logger
    public void warn(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void warn(String str, String str2) {
        warn(str + str2);
    }

    @Override // twitter4j.internal.logging.Logger
    public void error(String str) {
        System.out.println("[" + new Date() + "]" + str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void error(String str, Throwable th) {
        System.out.println(str);
        th.printStackTrace(System.err);
    }
}
