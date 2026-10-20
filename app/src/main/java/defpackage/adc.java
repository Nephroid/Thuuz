package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;
import org.apache.http.protocol.HTTP;

@zy
/* loaded from: classes.dex */
public final class adc {
    private final Context a;
    private String b;
    private final float c;
    private float d;
    private float e;
    private float f;
    private int g;

    public adc(Context context) {
        this.g = 0;
        this.a = context;
        this.c = context.getResources().getDisplayMetrics().density;
    }

    public adc(Context context, String str) {
        this(context);
        this.b = str;
    }

    private void a() {
        String str;
        if (TextUtils.isEmpty(this.b)) {
            str = "No debug information";
        } else {
            Uri build = new Uri.Builder().encodedQuery(this.b).build();
            StringBuilder sb = new StringBuilder();
            Map<String, String> a = acx.a(build);
            for (String str2 : a.keySet()) {
                sb.append(str2).append(" = ").append(a.get(str2)).append("\n\n");
            }
            str = sb.toString().trim();
            if (TextUtils.isEmpty(str)) {
                str = "No debug information";
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.a);
        builder.setMessage(str);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new add(this, str));
        builder.setNegativeButton(HTTP.CONN_CLOSE, new ade(this));
        builder.create().show();
    }

    private void a(int i, float f, float f2) {
        if (i == 0) {
            this.g = 0;
            this.d = f;
            this.e = f2;
            this.f = f2;
            return;
        }
        if (this.g != -1) {
            if (i != 2) {
                if (i == 1 && this.g == 4) {
                    a();
                    return;
                }
                return;
            }
            if (f2 > this.e) {
                this.e = f2;
            } else if (f2 < this.f) {
                this.f = f2;
            }
            if (this.e - this.f > 30.0f * this.c) {
                this.g = -1;
                return;
            }
            if (this.g == 0 || this.g == 2) {
                if (f - this.d >= 50.0f * this.c) {
                    this.d = f;
                    this.g++;
                }
            } else if ((this.g == 1 || this.g == 3) && f - this.d <= (-50.0f) * this.c) {
                this.d = f;
                this.g++;
            }
            if (this.g == 1 || this.g == 3) {
                if (f > this.d) {
                    this.d = f;
                }
            } else {
                if (this.g != 2 || f >= this.d) {
                    return;
                }
                this.d = f;
            }
        }
    }

    public void a(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    public void a(String str) {
        this.b = str;
    }
}
