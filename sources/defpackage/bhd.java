package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;

/* loaded from: classes.dex */
public class bhd implements DialogInterface.OnClickListener {
    final /* synthetic */ AddFbFriendsActivity a;

    public bhd(AddFbFriendsActivity addFbFriendsActivity) {
        this.a = addFbFriendsActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
    }
}
