package defpackage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.thuuz.gtv.activity.friends.AbstractFriendFragment;
import com.thuuz.gtv.activity.friends.AddFriendsFragment;
import com.thuuz.gtv.activity.friends.FriendFollowersFragment;
import com.thuuz.gtv.activity.friends.FriendIncomingFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bim extends FragmentPagerAdapter {
    final /* synthetic */ FriendsActivity a;
    private AddFriendsFragment b;
    private FriendFollowersFragment c;
    private FriendIncomingFragment d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bim(FriendsActivity friendsActivity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.a = friendsActivity;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int i2;
        int i3;
        int i4;
        Object instantiateItem = super.instantiateItem(viewGroup, i);
        if (FriendsActivity.FRAGS.ADD.a() == i) {
            r2 = this.b == null;
            this.b = (AddFriendsFragment) instantiateItem;
        } else if (FriendsActivity.FRAGS.FOLLOWERS.a() == i) {
            r2 = this.c == null;
            this.c = (FriendFollowersFragment) instantiateItem;
        } else if (FriendsActivity.FRAGS.INCOMING.a() == i) {
            r2 = this.d == null;
            this.d = (FriendIncomingFragment) instantiateItem;
        } else {
            this.a.e().severe("unexpected friends tab for pos: " + i);
        }
        if (r2) {
            int count = getCount();
            i2 = this.a.o;
            if (count == i2) {
                Logger e = this.a.e();
                StringBuilder append = new StringBuilder().append("setting initial to: ");
                i3 = this.a.o;
                e.info(append.append(i3).toString());
                FriendsActivity friendsActivity = this.a;
                i4 = this.a.o;
                friendsActivity.f(i4);
            }
        }
        return instantiateItem;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i == FriendsActivity.FRAGS.ADD.a()) {
            return new AddFriendsFragment();
        }
        if (i == FriendsActivity.FRAGS.FOLLOWERS.a()) {
            return new FriendFollowersFragment();
        }
        if (i == FriendsActivity.FRAGS.INCOMING.a()) {
            return new FriendIncomingFragment();
        }
        this.a.e().severe("unknown item requested");
        return null;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        int aQ;
        aQ = this.a.aQ();
        return aQ;
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        if (i == FriendsActivity.FRAGS.ADD.a()) {
            return "ADD FRIENDS";
        }
        if (i == FriendsActivity.FRAGS.FOLLOWERS.a()) {
            return "FOLLOWERS";
        }
        if (i == FriendsActivity.FRAGS.INCOMING.a()) {
            return "FOLLOWING";
        }
        return "???";
    }

    private void a(AbstractFriendFragment abstractFriendFragment, Collection<bqb> collection) {
        boolean z;
        try {
            abstractFriendFragment.getFriends().clear();
            abstractFriendFragment.getFriends().addAll(collection);
            Collections.sort(abstractFriendFragment.getFriends());
            z = this.a.k;
            abstractFriendFragment.setGotResponse(z);
            abstractFriendFragment.setLabel(collection.size());
            abstractFriendFragment.doRefresh(false);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void a() {
        List list;
        List list2;
        try {
            if (this.c != null) {
                FriendFollowersFragment friendFollowersFragment = this.c;
                list2 = this.a.g;
                a(friendFollowersFragment, list2);
            }
            if (this.d != null) {
                FriendIncomingFragment friendIncomingFragment = this.d;
                list = this.a.f;
                a(friendIncomingFragment, list);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
