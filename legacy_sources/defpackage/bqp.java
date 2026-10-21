package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public class bqp extends RequestAsyncTask {
    private final WeakReference<AbstractActivity> a;

    public bqp(Request request, AbstractActivity abstractActivity) {
        super(request);
        this.a = new WeakReference<>(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.RequestAsyncTask, android.os.AsyncTask
    public void onPreExecute() {
        super.onPreExecute();
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.RequestAsyncTask, android.os.AsyncTask
    public void onPostExecute(List<Response> list) {
        super.onPostExecute(list);
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null) {
            abstractActivity.e(false);
        }
    }
}
