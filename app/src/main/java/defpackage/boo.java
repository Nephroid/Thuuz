package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import com.thuuz.gtv.activity.sports.ShareActivity;

/* loaded from: classes.dex */
public class boo implements TextView.OnEditorActionListener {
    final /* synthetic */ ShareActivity a;

    private boo(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public /* synthetic */ boo(ShareActivity shareActivity, bof bofVar) {
        this(shareActivity);
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        this.a.f(false);
        return true;
    }
}
