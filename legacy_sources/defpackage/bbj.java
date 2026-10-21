package defpackage;

import com.thuuz.gtv.App;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/* loaded from: classes.dex */
public class bbj extends SimpleFormatter {
    final /* synthetic */ App a;

    private bbj(App app) {
        this.a = app;
    }

    public /* synthetic */ bbj(App app, bbg bbgVar) {
        this(app);
    }

    @Override // java.util.logging.SimpleFormatter, java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(formatMessage(logRecord));
        str = App.p;
        sb.append(str);
        Throwable thrown = logRecord.getThrown();
        if (thrown != null) {
            str2 = App.p;
            sb.append(str2);
            try {
                sb.append("thuuz ");
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                thrown.printStackTrace(printWriter);
                printWriter.close();
                sb.append(stringWriter.toString());
            } catch (Throwable th) {
            }
            str3 = App.p;
            sb.append(str3);
        }
        return sb.toString();
    }
}
