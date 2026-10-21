package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.FeedActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bed implements View.OnFocusChangeListener {
    final /* synthetic */ FeedActivity a;

    private bed(FeedActivity feedActivity) {
        this.a = feedActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bed(FeedActivity feedActivity, bdy bdyVar) {
        this(feedActivity);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        try {
            this.a.e().info("view " + view.getId() + "." + view.getClass().getSimpleName() + " hasFocus: " + z);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
