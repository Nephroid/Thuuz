package defpackage;

import android.view.View;
import android.widget.Button;
// [removed legacy tracker import]
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bio implements View.OnClickListener {
    final /* synthetic */ SearchFriendsActivity a;

    public bio(SearchFriendsActivity searchFriendsActivity) {
        this.a = searchFriendsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Integer num;
        Button button;
        Integer num2;
        Button button2;
        String aM;
        String aM2;
        try {
            num = SearchFriendsActivity.f;
            button = this.a.m;
            if (num == button.getTag()) {
                this.a.aK();
            } else {
                num2 = SearchFriendsActivity.g;
                button2 = this.a.m;
                if (num2 == button2.getTag()) {
                    SearchFriendsActivity searchFriendsActivity = this.a;
                    aM = this.a.aM();
                    searchFriendsActivity.a(new String[]{aM});
                    SearchFriendsActivity searchFriendsActivity2 = this.a;
                    aM2 = this.a.aM();
                    new bis(searchFriendsActivity2, aM2).execute(new Void[0]);
                    this.a.j().a(NativeProtocol.AUDIENCE_FRIENDS, "invite", "search", 0L);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
