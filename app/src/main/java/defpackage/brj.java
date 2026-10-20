package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;

/* loaded from: classes.dex */
public final class brj extends bqq {
    final /* synthetic */ bqg a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public brj(String str, AbstractActivity abstractActivity, boolean z, bqg bqgVar) {
        super(str, abstractActivity, z);
        this.a = bqgVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqq, android.os.AsyncTask
    public void onPreExecute() {
        super.onPreExecute();
        this.a.a(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqq, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        this.a.a(true);
    }
}
