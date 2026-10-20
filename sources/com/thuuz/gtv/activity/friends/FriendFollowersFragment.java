package com.thuuz.gtv.activity.friends;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bid;
import defpackage.bqb;

/* loaded from: classes.dex */
public class FriendFollowersFragment extends AbstractFriendFragment {
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getViewId() {
        return bbr.friend_followers_frag;
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
        } else if (getFriends().isEmpty()) {
            showNoFriends();
        } else {
            hidePopup();
        }
    }

    private void showNoFriends() {
        TextView orAddPopup = getOrAddPopup();
        if (orAddPopup != null) {
            orAddPopup.setText(bbt.no_friends_following_msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public TextView getOrAddPopup() {
        TextView orAddPopup = super.getOrAddPopup();
        if (orAddPopup == null) {
            return null;
        }
        View view = (View) orAddPopup.getParent();
        if (view == null) {
            return orAddPopup;
        }
        Button button = (Button) view.findViewById(bbq.add_friends_button);
        if (button != null) {
            button.setVisibility(8);
        }
        return orAddPopup;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupButton(Button button, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (bqbVar.c() || (fragsActivity.j() != null && fragsActivity.j().e())) {
                button.setVisibility(8);
                return;
            }
            button.setVisibility(0);
            button.setText("Follow");
            button.setOnClickListener(getButtonClickHandler());
            button.setTag(bqbVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public View.OnClickListener getButtonClickHandler() {
        return new bid(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupRightText(TextView textView, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (bqbVar.c()) {
                textView.setVisibility(8);
            } else if (fragsActivity.j().e()) {
                textView.setVisibility(0);
                textView.setText("Follow");
            }
        }
    }

    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getLabelResId() {
        return bbt.followers_tab;
    }

    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    protected int getSingleLabelResId() {
        return bbt.followers_tab_single;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.friends.AbstractFriendFragment
    public void setupCheckmark(ImageView imageView, bqb bqbVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (bqbVar.c()) {
                imageView.setVisibility(0);
                imageView.setImageResource(bbp.bluecheck);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                if (marginLayoutParams != null) {
                    marginLayoutParams.rightMargin = fragsActivity.j().q(15);
                }
            } else {
                imageView.setVisibility(8);
            }
            if (fragsActivity.j().e()) {
                imageView.setVisibility(0);
                if (!bqbVar.c()) {
                    imageView.setImageResource(bbp.ic_action_user_add);
                    ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).rightMargin = 0;
                }
            }
        }
    }
}
