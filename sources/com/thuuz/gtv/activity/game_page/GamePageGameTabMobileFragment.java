package com.thuuz.gtv.activity.game_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.bbq;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class GamePageGameTabMobileFragment extends GamePageGameTabFragment {
    private GamePageTvFragment delegateTvFrag;

    public GamePageGameTabMobileFragment() {
    }

    public GamePageGameTabMobileFragment(int i, int i2) {
        super(i, i2);
    }

    @Override // com.thuuz.gtv.activity.game_page.GamePageGameTabFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (onCreateView != null) {
            View findViewById = onCreateView.findViewById(bbq.mid_layout);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            try {
                this.delegateTvFrag = new GamePageTvFragment() { // from class: com.thuuz.gtv.activity.game_page.GamePageGameTabMobileFragment.1
                    @Override // com.thuuz.gtv.activity.game_page.GamePageTvFragment, defpackage.bbf
                    public GamePageActivity getFragsActivity() {
                        return GamePageGameTabMobileFragment.this.getFragsActivity();
                    }
                };
                GamePageActivity fragsActivity = getFragsActivity();
                this.delegateTvFrag.onAttach(fragsActivity);
                this.delegateTvFrag.setupComponents(onCreateView, fragsActivity);
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
        return onCreateView;
    }

    @Override // com.thuuz.gtv.activity.game_page.GamePageGameTabFragment
    protected void addTabletFrags() {
    }

    @Override // com.thuuz.gtv.activity.game_page.GamePageGameTabFragment
    protected int getTeaserTextSize() {
        return 14;
    }

    @Override // com.thuuz.gtv.activity.game_page.AbstractGamePageFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (this.delegateTvFrag != null) {
                this.delegateTvFrag.onCreate(bundle);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.game_page.GamePageGameTabFragment, defpackage.bbf
    public void doRefresh(boolean z) {
        super.doRefresh(z);
        if (this.delegateTvFrag != null) {
            this.delegateTvFrag.doRefresh(z);
        }
    }

    @Override // com.thuuz.gtv.activity.game_page.GamePageGameTabFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        try {
            if (this.delegateTvFrag != null) {
                this.delegateTvFrag.onResume();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
