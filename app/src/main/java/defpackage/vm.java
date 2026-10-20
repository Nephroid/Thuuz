package defpackage;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.internal.Cdo;
import com.google.android.gms.internal.dk;
import com.google.android.gms.internal.gv;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public final class vm implements vg {
    private static int a(DisplayMetrics displayMetrics, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return adm.a(displayMetrics, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            adn.e("Could not parse " + str + " in a video GMSG: " + str2);
            return i;
        }
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        String str = map.get("action");
        if (str == null) {
            adn.e("Action missing from video GMSG.");
            return;
        }
        dk d = gvVar.d();
        if (d == null) {
            adn.e("Could not get ad overlay for a video GMSG.");
            return;
        }
        boolean equalsIgnoreCase = "new".equalsIgnoreCase(str);
        boolean equalsIgnoreCase2 = "position".equalsIgnoreCase(str);
        if (equalsIgnoreCase || equalsIgnoreCase2) {
            DisplayMetrics displayMetrics = gvVar.getContext().getResources().getDisplayMetrics();
            int a = a(displayMetrics, map, "x", 0);
            int a2 = a(displayMetrics, map, "y", 0);
            int a3 = a(displayMetrics, map, "w", -1);
            int a4 = a(displayMetrics, map, "h", -1);
            if (equalsIgnoreCase && d.b() == null) {
                d.b(a, a2, a3, a4);
                return;
            } else {
                d.a(a, a2, a3, a4);
                return;
            }
        }
        Cdo b = d.b();
        if (b == null) {
            Cdo.a(gvVar, "no_video_view", (String) null);
            return;
        }
        if ("click".equalsIgnoreCase(str)) {
            DisplayMetrics displayMetrics2 = gvVar.getContext().getResources().getDisplayMetrics();
            int a5 = a(displayMetrics2, map, "x", 0);
            int a6 = a(displayMetrics2, map, "y", 0);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, a5, a6, 0);
            b.a(obtain);
            obtain.recycle();
            return;
        }
        if ("controls".equalsIgnoreCase(str)) {
            String str2 = map.get("enabled");
            if (str2 == null) {
                adn.e("Enabled parameter missing from controls video GMSG.");
                return;
            } else {
                b.a(Boolean.parseBoolean(str2));
                return;
            }
        }
        if ("currentTime".equalsIgnoreCase(str)) {
            String str3 = map.get("time");
            if (str3 == null) {
                adn.e("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                b.a((int) (Float.parseFloat(str3) * 1000.0f));
                return;
            } catch (NumberFormatException e) {
                adn.e("Could not parse time parameter from currentTime video GMSG: " + str3);
                return;
            }
        }
        if ("hide".equalsIgnoreCase(str)) {
            b.setVisibility(4);
            return;
        }
        if ("load".equalsIgnoreCase(str)) {
            b.b();
            return;
        }
        if ("pause".equalsIgnoreCase(str)) {
            b.c();
            return;
        }
        if ("play".equalsIgnoreCase(str)) {
            b.d();
            return;
        }
        if ("show".equalsIgnoreCase(str)) {
            b.setVisibility(0);
        } else if ("src".equalsIgnoreCase(str)) {
            b.a(map.get("src"));
        } else {
            adn.e("Unknown video action: " + str);
        }
    }
}
