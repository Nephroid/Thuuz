package com.thuuz.gtv.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.thuuz.gtv.activity.AbstractSettings;
import com.thuuz.gtv.view.FakeSpinDropView;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bqx;
import defpackage.bus;
import defpackage.but;
import defpackage.buu;
import defpackage.buv;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsSoundsActivity extends AbstractSettings {
    private Spinner f;
    private Spinner g;

    /* loaded from: classes.dex */
    public enum VIB_TYPE {
        USE_DEFAULT(0, null),
        NO_VIB(1, new long[0]),
        ONE_SHORT(2, new long[]{0, 100}),
        ONE_LONG(3, new long[]{0, 300}),
        ONE_SHORT_ONE_LONG(4, new long[]{0, 100, 200, 300}),
        ONE_LONG_ONE_SHORT(5, new long[]{0, 300, 200, 100}),
        TWO_SHORT(6, new long[]{0, 100, 200, 100}),
        TWO_LONG(7, new long[]{0, 300, 200, 300});

        private final int i;
        private final long[] vib;

        VIB_TYPE(int i, long[] jArr) {
            this.i = i;
            this.vib = jArr;
        }

        public int a() {
            return this.i;
        }

        public long[] b() {
            return this.vib;
        }

        public static VIB_TYPE a(int i) {
            for (VIB_TYPE vib_type : values()) {
                if (i == vib_type.a()) {
                    return vib_type;
                }
            }
            return USE_DEFAULT;
        }
    }

    /* loaded from: classes.dex */
    public enum TOAST_TYPE {
        NONE(0),
        SHORT(1),
        LONG(2),
        DOUBLE_LONG(3),
        TRIPLE_LONG(4);

        private final int id;

        TOAST_TYPE(int i) {
            this.id = i;
        }

        public int a() {
            return this == SHORT ? 0 : 1;
        }

        public static TOAST_TYPE a(int i) {
            for (TOAST_TYPE toast_type : values()) {
                if (i == toast_type.id) {
                    return toast_type;
                }
            }
            return LONG;
        }

        public int b() {
            return this.id;
        }
    }

    public SettingsSoundsActivity() {
        super("Alert Sounds");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_sounds_layout);
        if (j().e()) {
            a("Alert Text Duration");
        }
        Button button = (Button) findViewById(bbq.change_sound_and_vibration_settings_button);
        LinearLayout linearLayout = (LinearLayout) findViewById(bbq.sound_and_vibration_container);
        if (Build.VERSION.SDK_INT >= 26) {
            button.setVisibility(0);
            linearLayout.setVisibility(8);
            button.setOnClickListener(new bus(this));
        } else {
            button.setVisibility(8);
            linearLayout.setVisibility(0);
            aF();
            aG();
        }
        aK();
    }

    private void aF() {
        this.f = (FakeSpinDropView) findViewById(bbq.select_sound);
        if (j().e()) {
            this.f.setVisibility(8);
            findViewById(bbq.sound_label).setVisibility(8);
        } else {
            aL();
            this.f.setOnClickListener(new but(this, null));
            expandWidthByDevice(this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (j().e() && getCurrentFocus() == null) {
            this.g.requestFocus();
        }
        j().k("/prefs/sounds");
    }

    private void aG() {
        Spinner spinner = (Spinner) findViewById(bbq.select_vib);
        if (j().e()) {
            spinner.setVisibility(8);
            findViewById(bbq.vib_label).setVisibility(8);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(getResources().getString(bbt.system_vib));
        arrayList.add(getResources().getString(bbt.no_vib));
        arrayList.add(getResources().getString(bbt.one_short));
        arrayList.add(getResources().getString(bbt.one_long));
        arrayList.add(getResources().getString(bbt.one_short_one_long));
        arrayList.add(getResources().getString(bbt.one_long_one_short));
        arrayList.add(getResources().getString(bbt.two_short));
        arrayList.add(getResources().getString(bbt.two_long));
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, bbr.simple_list_item, bbq.text, arrayList));
        VIB_TYPE H = j().H();
        if (j().e()) {
            arrayList.remove(0);
            spinner.setEnabled(false);
            spinner.setFocusable(false);
        } else {
            spinner.setOnItemSelectedListener(new bqx(new buv(this, null), H.a()));
            spinner.setSelection(H.a());
        }
        expandWidthByDevice(spinner);
    }

    private void aK() {
        this.g = (Spinner) findViewById(bbq.select_toast);
        ArrayList arrayList = new ArrayList();
        arrayList.add("None");
        arrayList.add("Short (2 seconds)");
        arrayList.add("Long (3.5 seconds)");
        arrayList.add("Double Long");
        arrayList.add("Triple Long");
        this.g.setAdapter((SpinnerAdapter) new ArrayAdapter(this, bbr.simple_list_item, bbq.text, arrayList));
        TOAST_TYPE I = j().I();
        this.g.setOnItemSelectedListener(new bqx(new buu(this, null), I.b()));
        this.g.setSelection(I.b());
        expandWidthByDevice(this.g);
        if (j().e()) {
            this.g.requestFocus();
        }
    }

    private void aL() {
        try {
            ArrayList arrayList = new ArrayList();
            if (j().e()) {
                arrayList.add("No Sound");
                this.f.setEnabled(false);
                this.f.setFocusable(false);
            } else {
                arrayList.add(a(j().G(), getContentResolver()));
            }
            this.f.setAdapter((SpinnerAdapter) new ArrayAdapter(this, bbr.simple_list_item, bbq.text, arrayList));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem setting sound label: ", th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ca, code lost:
    
        if (r0.length() <= 0) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[Catch: SecurityException -> 0x0099, Throwable -> 0x00ce, TRY_ENTER, TRY_LEAVE, TryCatch #3 {SecurityException -> 0x0099, Throwable -> 0x00ce, blocks: (B:6:0x0005, B:8:0x0010, B:12:0x001b, B:14:0x0025, B:30:0x0094), top: B:5:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r8, android.content.ContentResolver r9) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.settings.SettingsSoundsActivity.a(java.lang.String, android.content.ContentResolver):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 352) {
            Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            if (uri == null) {
                j().d("");
            } else {
                e().info("selected sound: " + uri.toString());
                j().d(uri.toString());
            }
            aL();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#014";
    }
}
