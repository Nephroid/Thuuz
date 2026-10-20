package defpackage;

import android.widget.AdapterView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxs implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ TVProviderFragment a;

    private bxs(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    public /* synthetic */ bxs(TVProviderFragment tVProviderFragment, bxc bxcVar) {
        this(tVProviderFragment);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        if (r1.equals(r0.b()) == false) goto L11;
     */
    @Override // android.widget.AdapterView.OnItemSelectedListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onItemSelected(android.widget.AdapterView<?> r6, android.view.View r7, int r8, long r9) {
        /*
            r5 = this;
            com.thuuz.gtv.settings.loc.TVProviderFragment r0 = r5.a     // Catch: java.lang.Throwable -> L47
            android.widget.Spinner r0 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3300(r0)     // Catch: java.lang.Throwable -> L47
            java.lang.Object r0 = r0.getSelectedItem()     // Catch: java.lang.Throwable -> L47
            bqc r0 = (defpackage.bqc) r0     // Catch: java.lang.Throwable -> L47
            if (r0 != 0) goto Lf
        Le:
            return
        Lf:
            java.lang.String r1 = "-1"
            java.lang.String r2 = r0.b()     // Catch: java.lang.Throwable -> L47
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L47
            if (r1 != 0) goto Le
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            java.lang.String r1 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3400(r1)     // Catch: java.lang.Throwable -> L47
            if (r1 == 0) goto L33
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            java.lang.String r1 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3400(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r2 = r0.b()     // Catch: java.lang.Throwable -> L47
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L47
            if (r1 != 0) goto Le
        L33:
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            bpu r1 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$500(r1)     // Catch: java.lang.Throwable -> L47
            if (r1 != 0) goto L56
            com.thuuz.gtv.settings.loc.TVProviderFragment r0 = r5.a     // Catch: java.lang.Throwable -> L47
            java.util.logging.Logger r0 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3500(r0)     // Catch: java.lang.Throwable -> L47
            java.lang.String r1 = "selectedLocation null but device selected"
            r0.severe(r1)     // Catch: java.lang.Throwable -> L47
            goto Le
        L47:
            r0 = move-exception
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a
            java.util.logging.Logger r1 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3900(r1)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "cannot handle: "
            r1.log(r2, r3, r0)
            goto Le
        L56:
            java.lang.String r1 = "-2"
            java.lang.String r2 = r0.b()     // Catch: java.lang.Throwable -> L47
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L47
            if (r1 == 0) goto L93
            com.thuuz.gtv.settings.loc.TVProviderFragment r0 = r5.a     // Catch: java.lang.Throwable -> L47
            bpu r0 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$500(r0)     // Catch: java.lang.Throwable -> L47
            r0.n()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r0 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.activity.AbstractActivity r1 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3600(r1)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.App r1 = r1.j()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.data.Locations r1 = r1.ax()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r2 = r5.a     // Catch: java.lang.Throwable -> L47
            bpu r2 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$500(r2)     // Catch: java.lang.Throwable -> L47
            bpu r1 = r1.e(r2)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment.access$502(r0, r1)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r0 = r5.a     // Catch: java.lang.Throwable -> L47
            bwr r0 = r0.getIEditActivity()     // Catch: java.lang.Throwable -> L47
            r0.aI()     // Catch: java.lang.Throwable -> L47
            goto Le
        L93:
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            java.lang.String r2 = r0.b()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment.access$3402(r1, r2)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r2 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.activity.AbstractActivity r2 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3700(r2)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.App r2 = r2.j()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.data.Locations r2 = r2.ax()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r3 = r5.a     // Catch: java.lang.Throwable -> L47
            bpu r3 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$500(r3)     // Catch: java.lang.Throwable -> L47
            bpu r2 = r2.e(r3)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment.access$502(r1, r2)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r1 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r2 = r5.a     // Catch: java.lang.Throwable -> L47
            bpu r2 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$500(r2)     // Catch: java.lang.Throwable -> L47
            java.lang.Integer r2 = r2.a()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment.access$702(r1, r2)     // Catch: java.lang.Throwable -> L47
            bwk r1 = new bwk     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r2 = r5.a     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.activity.AbstractActivity r2 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$3800(r2)     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r3 = r5.a     // Catch: java.lang.Throwable -> L47
            java.lang.Integer r3 = com.thuuz.gtv.settings.loc.TVProviderFragment.access$700(r3)     // Catch: java.lang.Throwable -> L47
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L47
            com.thuuz.gtv.settings.loc.TVProviderFragment r4 = r5.a     // Catch: java.lang.Throwable -> L47
            r1.<init>(r2, r0, r3, r4)     // Catch: java.lang.Throwable -> L47
            r0 = 0
            java.lang.Void[] r0 = new java.lang.Void[r0]     // Catch: java.lang.Throwable -> L47
            r1.execute(r0)     // Catch: java.lang.Throwable -> L47
            goto Le
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bxs.onItemSelected(android.widget.AdapterView, android.view.View, int, long):void");
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
