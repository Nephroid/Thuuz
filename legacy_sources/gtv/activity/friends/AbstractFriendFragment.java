package com.thuuz.gtv.activity.friends;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bgy;
import defpackage.bgz;
import defpackage.bha;
import defpackage.bhc;
import defpackage.bqb;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public abstract class AbstractFriendFragment extends bbf {
    protected bha adapter;
    public List<bqb> friends = new ArrayList();
    private boolean gotResponse;
    private TextView label;
    private ListView listView;
    protected ViewGroup parent;

    public abstract View.OnClickListener getButtonClickHandler();

    protected abstract int getLabelResId();

    protected abstract int getSingleLabelResId();

    protected abstract int getViewId();

    public abstract void setupButton(Button button, bqb bqbVar);

    public abstract void setupRightText(TextView textView, bqb bqbVar);

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (viewGroup == null) {
            return null;
        }
        this.parent = null;
        try {
            this.parent = (ViewGroup) layoutInflater.inflate(getViewId(), viewGroup, false);
            setupAdaptor(this.parent);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem creating friend frag: ", th);
        }
        return this.parent;
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        refreshLabel();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.friends.size() > 0) {
            this.gotResponse = true;
        }
        refreshLabel();
    }

    private void refreshLabel() {
        if (this.label != null) {
            if (!this.gotResponse) {
                this.label.setText("");
            } else {
                setLabel(this.friends.size());
            }
        }
    }

    public void hidePopup() {
        View findViewById;
        if (this.parent != null && (findViewById = this.parent.findViewById(bbq.popup)) != null) {
            this.parent.removeView(findViewById);
        }
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
        refreshLabel();
    }

    protected void setupAdaptor(View view) {
        this.listView = (ListView) view.findViewById(bbq.friend_list);
        FriendsActivity friendsActivity = (FriendsActivity) getFragsActivity();
        if (friendsActivity != null) {
            this.adapter = new bha(this, friendsActivity);
            this.listView.setAdapter((ListAdapter) this.adapter);
            friendsActivity.aK().a(this.adapter);
            this.label = (TextView) view.findViewById(bbq.tab_descrip);
            this.listView.setOnKeyListener(new bhc(this));
            refreshLabel();
            this.listView.setItemsCanFocus(true);
        }
    }

    public List<bqb> getFriends() {
        return this.friends;
    }

    public void setLabel(int i) {
        if (getFragsActivity() != null && this.label != null) {
            if (this.friends.size() == 1) {
                this.label.setText(getResources().getString(getSingleLabelResId(), String.valueOf(i)));
            } else if (this.friends.size() > 1) {
                this.label.setText(getResources().getString(getLabelResId(), String.valueOf(i)));
            } else {
                this.label.setText("");
            }
        }
    }

    public void setGotResponse(boolean z) {
        this.gotResponse = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TextView getOrAddPopup() {
        TextView textView;
        Throwable th;
        try {
        } catch (Throwable th2) {
            textView = null;
            th = th2;
        }
        if (this.parent == null) {
            return null;
        }
        View findViewById = this.parent.findViewById(bbq.popup);
        if (findViewById != null) {
            textView = null;
        } else {
            if (getFragsActivity() == null) {
                return null;
            }
            FrameLayout frameLayout = (FrameLayout) this.parent.findViewById(bbq.frame);
            if (frameLayout != null) {
                findViewById = getFragsActivity().getLayoutInflater().inflate(bbr.popup, (ViewGroup) null);
                if (findViewById != null) {
                    frameLayout.addView(findViewById);
                }
            } else {
                getLogger().severe("cannot get frame from frag: " + getClass().getSimpleName());
            }
            if (findViewById == null) {
                return null;
            }
            textView = (TextView) findViewById.findViewById(bbq.popup_text);
            try {
                textView.setClickable(true);
                textView.setOnClickListener(new bgy(this));
                Button button = (Button) findViewById.findViewById(bbq.add_friends_button);
                if (this.friends.size() > 2) {
                    button.setVisibility(8);
                } else {
                    button.setVisibility(0);
                    Drawable background = button.getBackground();
                    if (background != null) {
                        background.setAlpha(MotionEventCompat.ACTION_MASK);
                    }
                }
                button.setOnClickListener(new bgz(this));
            } catch (Throwable th3) {
                th = th3;
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
                return textView;
            }
        }
        return textView;
    }

    public void setupCheckmark(ImageView imageView, bqb bqbVar) {
    }

    public void setupBadge(ImageView imageView, bqb bqbVar) {
        int i = 0;
        if (!bqbVar.i()) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    public boolean isResponded() {
        return this.gotResponse;
    }
}
