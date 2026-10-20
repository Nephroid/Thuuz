package twitter4j.internal.logging;

import org.apache.log4j.Level;

/* loaded from: classes.dex */
final class Log4JLogger extends Logger {
    private final org.apache.log4j.Logger LOGGER;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Log4JLogger(org.apache.log4j.Logger logger) {
        this.LOGGER = logger;
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isDebugEnabled() {
        return this.LOGGER.isDebugEnabled();
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isInfoEnabled() {
        return this.LOGGER.isInfoEnabled();
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isWarnEnabled() {
        return this.LOGGER.isEnabledFor(Level.WARN);
    }

    @Override // twitter4j.internal.logging.Logger
    public boolean isErrorEnabled() {
        return this.LOGGER.isEnabledFor(Level.WARN);
    }

    @Override // twitter4j.internal.logging.Logger
    public void debug(String str) {
        this.LOGGER.debug(str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void debug(String str, String str2) {
        debug(str + str2);
    }

    @Override // twitter4j.internal.logging.Logger
    public void info(String str) {
        this.LOGGER.info(str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void info(String str, String str2) {
        info(str + str2);
    }

    @Override // twitter4j.internal.logging.Logger
    public void warn(String str) {
        this.LOGGER.warn(str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void warn(String str, String str2) {
        warn(str + str2);
    }

    @Override // twitter4j.internal.logging.Logger
    public void error(String str) {
        this.LOGGER.error(str);
    }

    @Override // twitter4j.internal.logging.Logger
    public void error(String str, Throwable th) {
        this.LOGGER.error(str, th);
    }
}
