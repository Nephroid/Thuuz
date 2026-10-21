package defpackage;
// [removed legacy tracker import]
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpj extends bpl {
    public bpj(GraphUser graphUser) {
        super(new JSONObject());
        try {
            b("sid", graphUser.getId());
            String name = graphUser.getName();
            a(name == null ? "" : name);
            i();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void i() {
        String[] split;
        String e = e();
        if (e.length() > 0 && (split = e.split(" ")) != null) {
            if (split.length > 0) {
                b(split[0]);
            }
            String str = "";
            int i = 1;
            while (i < split.length) {
                if (str.length() > 0) {
                    str = str + " ";
                }
                String str2 = str + split[i];
                i++;
                str = str2;
            }
            c(str);
        }
    }
}
