package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AddFriendsFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import java.util.List;

/* loaded from: classes.dex */
public class bht implements View.OnClickListener {
    final /* synthetic */ AddFriendsFragment a;

    private bht(AddFriendsFragment addFriendsFragment) {
        this.a = addFriendsFragment;
    }

    public /* synthetic */ bht(AddFriendsFragment addFriendsFragment, bhq bhqVar) {
        this(addFriendsFragment);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        List list;
        List list2;
        AbstractActivity fragsActivity;
        listView = this.a.mListView;
        int positionForView = listView.getPositionForView(view);
        if (positionForView != -1 && positionForView >= 0) {
            list = this.a.mSelectionTypes;
            if (positionForView <= list.size()) {
                list2 = this.a.mSelectionTypes;
                AddFriendsFragment.ADD_FRIENDS_SELECTION_TYPE add_friends_selection_type = (AddFriendsFragment.ADD_FRIENDS_SELECTION_TYPE) list2.get(positionForView);
                fragsActivity = this.a.getFragsActivity();
                FriendsActivity friendsActivity = (FriendsActivity) fragsActivity;
                if (friendsActivity != null) {
                    switch (bhr.a[add_friends_selection_type.a().ordinal()]) {
                        case 1:
                            if (!friendsActivity.j().i()) {
                                friendsActivity.a(SettingsAccountActivity.SOURCE.SOURCE_FRIENDS);
                                return;
                            } else {
                                friendsActivity.aM();
                                return;
                            }
                        case 2:
                            if (!friendsActivity.j().i()) {
                                friendsActivity.a(SettingsAccountActivity.SOURCE.SOURCE_FRIENDS);
                                return;
                            } else {
                                friendsActivity.aL();
                                return;
                            }
                        case 3:
                            if (!friendsActivity.j().i()) {
                                friendsActivity.a(SettingsAccountActivity.SOURCE.SOURCE_FRIENDS);
                                return;
                            } else {
                                friendsActivity.aN();
                                return;
                            }
                        case 4:
                            friendsActivity.a((String[]) null);
                            return;
                        case 5:
                            friendsActivity.ai();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}
