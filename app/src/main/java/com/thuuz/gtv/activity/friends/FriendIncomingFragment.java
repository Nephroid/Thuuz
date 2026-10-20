package com.thuuz.gtv.activity.friends;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bbp;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.big;
import defpackage.bqb;

/* loaded from: classes.dex */
public class FriendIncomingFragment extends AbstractFriendFragment {
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getViewId() {
        return bbr.friend_incoming_frag;
    }

    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment, defpackage.bbf
    public void doRefresh(boolean z) {
        FriendsActivity friendsActivity;
        super.doRefresh(z);
        if (!isResponded() || (friendsActivity = (FriendsActivity) getFragsActivity()) == null || friendsActivity.isDestroyed()) {
            return;
        }
        if (friendsActivity.af() < 400.0f) {
            hidePopup();
            return;
        }
        if (getFriends().isEmpty()) {
            showNoFriends();
        } else if (getFriends().size() < 2) {
            showFewFriends();
        } else {
            hidePopup();
        }
    }

    private void showFewFriends() {
        TextView orAddPopup = getOrAddPopup();
        if (orAddPopup != null) {
            if (getFriends().size() == 1) {
                orAddPopup.setText(bbt.one_follow_msg);
                return;
            }
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                orAddPopup.setText(fragsActivity.getResources().getString(bbt.few_follow_msg, Integer.valueOf(getFriends().size())));
            }
        }
    }

    private void showNoFriends() {
        TextView orAddPopup = getOrAddPopup();
        if (orAddPopup != null) {
            orAddPopup.setText(bbt.no_friends_msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupButton(Button button, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (fragsActivity.j().e()) {
                button.setVisibility(8);
                return;
            }
            button.setVisibility(0);
            button.setText("Unfollow");
            button.setOnClickListener(getButtonClickHandler());
            button.setTag(bqbVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public View.OnClickListener getButtonClickHandler() {
        return new big(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupRightText(TextView textView, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (fragsActivity.j().e()) {
                textView.setVisibility(0);
                textView.setText("Unfollow");
            } else {
                textView.setVisibility(8);
            }
        }
    }

    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getLabelResId() {
        return bbt.incoming_fol_tab;
    }

    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getSingleLabelResId() {
        return bbt.incoming_fol_tab_single;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupCheckmark(ImageView imageView, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && fragsActivity.j().e()) {
            imageView.setVisibility(0);
            imageView.setImageResource(bbp.ic_action_user_remove);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            if (marginLayoutParams != null) {
                marginLayoutParams.rightMargin = 0;
            }
        }
    }
}
