package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.feed.FeedFragment;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;

/* loaded from: classes.dex */
public class bgr implements View.OnLongClickListener {
    final /* synthetic */ ShareActivity.SHARE_TYPE a;
    final /* synthetic */ SettingsAccountActivity.SOURCE b;
    final /* synthetic */ FeedFragment c;

    public bgr(FeedFragment feedFragment, ShareActivity.SHARE_TYPE share_type, SettingsAccountActivity.SOURCE source) {
        this.c = feedFragment;
        this.a = share_type;
        this.b = source;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        new bgt(this.c, this.a, this.b).onClick(view);
        return true;
    }
}
