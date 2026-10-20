package com.thuuz.gtv.activity.friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.facebook.UiLifecycleHelper;
import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bhq;
import defpackage.bhs;
import defpackage.bht;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class AddFriendsFragment extends bbf {
    private ListView mListView;
    private UiLifecycleHelper uiHelper;
    private bht clickListener = new bht(this, null);
    private List<ADD_FRIENDS_SELECTION_TYPE> mSelectionTypes = new ArrayList();

    /* loaded from: classes.dex */
    public enum ADD_FRIENDS_SELECTION_TYPE {
        ADD_FRIENDS_SELECTION_TYPE_SEARCH(0, "Search all Thuuzers"),
        ADD_FRIENDS_SELECTION_TYPE_FACEBOOK(1, "Facebook"),
        ADD_FRIENDS_SELECTION_TYPE_EMAIL(2, "Email Invite"),
        ADD_FRIENDS_SELECTION_TYPE_SMS(3, "SMS (text) Invite"),
        ADD_FRIENDS_SELECTION_TYPE_EXPERTS(4, "Expert Thuuzers");

        private int mType;
        private String my_name;

        ADD_FRIENDS_SELECTION_TYPE(int i, String str) {
            this.mType = i;
            this.my_name = str;
        }

        public static ADD_FRIENDS_SELECTION_TYPE a(int i) {
            for (ADD_FRIENDS_SELECTION_TYPE add_friends_selection_type : values()) {
                if (add_friends_selection_type.mType == i) {
                    return add_friends_selection_type;
                }
            }
            return ADD_FRIENDS_SELECTION_TYPE_SEARCH;
        }

        public ADD_FRIENDS_SELECTION_TYPE a() {
            return a(this.mType);
        }

        public String b() {
            return this.my_name;
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = null;
        if (viewGroup == null) {
            return null;
        }
        try {
            this.mSelectionTypes.clear();
            this.mSelectionTypes.add(ADD_FRIENDS_SELECTION_TYPE.ADD_FRIENDS_SELECTION_TYPE_SEARCH);
            this.mSelectionTypes.add(ADD_FRIENDS_SELECTION_TYPE.ADD_FRIENDS_SELECTION_TYPE_EXPERTS);
            this.mSelectionTypes.add(ADD_FRIENDS_SELECTION_TYPE.ADD_FRIENDS_SELECTION_TYPE_EMAIL);
            View inflate = layoutInflater.inflate(bbr.add_friends_frag, viewGroup, false);
            if (inflate == null) {
                return null;
            }
            try {
                this.mListView = (ListView) inflate.findViewById(bbq.selection_list);
                this.mListView.setItemsCanFocus(true);
                AbstractActivity fragsActivity = getFragsActivity();
                if (fragsActivity != null) {
                    if (fragsActivity.j().ca()) {
                        this.mSelectionTypes.add(ADD_FRIENDS_SELECTION_TYPE.ADD_FRIENDS_SELECTION_TYPE_SMS);
                    }
                    fragsActivity.a(this.mListView);
                    this.mListView.setAdapter((ListAdapter) new bhs(this, fragsActivity));
                }
                this.uiHelper = new UiLifecycleHelper(fragsActivity, new bhq(this));
                this.uiHelper.onCreate(bundle);
                return inflate;
            } catch (Throwable th) {
                view = inflate;
                th = th;
                getLogger().log(Level.SEVERE, "problem creating add friends frag: ", th);
                return view;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        try {
            super.onResume();
            this.uiHelper.onResume();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        try {
            this.uiHelper.onDestroy();
            super.onDestroy();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        try {
            this.uiHelper.onPause();
            super.onPause();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            this.uiHelper.onSaveInstanceState(bundle);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.uiHelper.onActivityResult(i, i2, intent);
            super.onActivityResult(i, i2, intent);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
    }
}
