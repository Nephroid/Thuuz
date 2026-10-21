package defpackage;

import android.os.AsyncTask;
import android.widget.Button;
import com.thuuz.gtv.settings.FeedbackActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class brd extends AsyncTask<Void, Void, Boolean> {
    final WeakReference<FeedbackActivity> a;
    final /* synthetic */ FeedbackActivity b;

    public brd(FeedbackActivity feedbackActivity) {
        this.b = feedbackActivity;
        this.a = new WeakReference<>(this.b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        return a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        Button button;
        if (this.a.get() != null && this.b.d()) {
            if (bool.booleanValue()) {
                this.b.d("Thank you!", "We like to hear from you.");
                this.b.finish();
            } else if (this.b.d()) {
                this.b.b("Unable to Send", bbt.pls_try_again_later);
            }
            button = this.b.i;
            button.setEnabled(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Boolean a() {
        /*
            Method dump skipped, instructions count: 385
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.brd.a():java.lang.Boolean");
    }
}
