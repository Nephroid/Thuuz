package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.sports.ShareActivity;

/* loaded from: classes.dex */
public class bof implements View.OnFocusChangeListener {
    final /* synthetic */ ShareActivity a;

    public bof(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.a.e().info("share button focus: " + z);
    }
}
