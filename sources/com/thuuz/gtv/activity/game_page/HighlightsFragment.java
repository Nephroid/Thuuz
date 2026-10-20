package com.thuuz.gtv.activity.game_page;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.data.GameData;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bkm;
import defpackage.bkn;
import defpackage.bkp;
import defpackage.bms;
import defpackage.bpp;
import defpackage.cak;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class HighlightsFragment extends AbstractTvFragment {
    private ListView buttonListView;
    private bkp button_adaptor;
    private boolean isRefreshed;
    private View parent;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Throwable th;
        if (viewGroup == null) {
            return null;
        }
        try {
            view = layoutInflater.inflate(bbr.game_page_highlights_frag, viewGroup, false);
            if (view == null) {
                return null;
            }
            try {
                GamePageActivity fragsActivity = getFragsActivity();
                if (fragsActivity == null) {
                    return null;
                }
                view.setId(fragsActivity.A());
                setupComponents(view, fragsActivity);
                return view;
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "cannot handle: ", th);
                return view;
            }
        } catch (Throwable th3) {
            view = null;
            th = th3;
        }
    }

    protected void setupComponents(View view, AbstractActivity abstractActivity) {
        ViewTreeObserver viewTreeObserver;
        this.parent = view;
        this.buttonListView = (ListView) view.findViewById(bbq.highlights_list);
        if (abstractActivity.j().e()) {
            this.buttonListView.setNextFocusRightId(bbq.list);
        }
        this.button_adaptor = new bkp(this, getFragsActivity(), this.buttons);
        this.buttonListView.setAdapter((ListAdapter) this.button_adaptor);
        this.buttonListView.setOnItemClickListener(new bkm(this));
        if (Build.VERSION.SDK_INT >= 11 && (viewTreeObserver = this.buttonListView.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new bkn(this));
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        getLogger().info("refreshing from resume");
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        if (getFragsActivity() != null && getService() != null) {
            refreshFrag();
        }
    }

    protected void refreshFrag() {
        try {
            renderWatchHighlightsOnline();
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                cak.a(fragsActivity, this.buttonListView);
                this.button_adaptor.notifyDataSetChanged();
                if (!this.isRefreshed) {
                    this.isRefreshed = true;
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirmListSize() {
        ViewGroup.LayoutParams layoutParams = this.buttonListView.getLayoutParams();
        if (layoutParams != null) {
            int i = layoutParams.height;
            int calcListHeight = calcListHeight(this.buttons);
            layoutParams.height = calcListHeight;
            if (i != calcListHeight) {
                this.buttonListView.requestLayout();
                this.parent.requestLayout();
            }
        }
    }

    public void renderWatchHighlightsOnline() {
        try {
            if (getFragsActivity() != null) {
                this.buttons.clear();
                if (getGame() != null) {
                    Iterator<bpp> it = getGame().an().iterator();
                    while (it.hasNext()) {
                        putButton(ButtonData.BUTTON_TYPE.HIGHLIGHT, it.next());
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCellHeight(int i) {
        Integer num;
        GamePageActivity fragsActivity;
        String str;
        Integer valueOf = Integer.valueOf(getAutoCellHeight());
        String b = ((bpp) this.buttons.get(i).b()).b();
        if (b != null && (fragsActivity = getFragsActivity()) != null) {
            Iterator<String> it = fragsActivity.aI().snapshot().keySet().iterator();
            while (true) {
                str = b;
                if (!it.hasNext()) {
                    break;
                }
                b = it.next();
                String[] split = bms.a.split(b);
                if (split.length <= 1 || !str.equals(split[1])) {
                    b = str;
                }
            }
            Bitmap bitmap = fragsActivity.aI().get(str);
            if (bitmap != null) {
                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                if (height != 0 && width != 0) {
                    num = Integer.valueOf((int) ((height * getCellWidth()) / width));
                    if (num.intValue() == 0) {
                        num = Integer.valueOf(getAutoCellHeight());
                    }
                    return num.intValue();
                }
            }
        }
        num = valueOf;
        return num.intValue();
    }

    private int getAutoCellHeight() {
        int cellWidth = (int) (getCellWidth() * 0.75f);
        if (cellWidth == 0) {
            return 150;
        }
        return cellWidth;
    }

    private int getCellWidth() {
        int measuredWidth = this.buttonListView.getMeasuredWidth();
        if (measuredWidth == 0) {
            return 200;
        }
        return measuredWidth;
    }

    @Override // defpackage.bbf
    public GamePageActivity getFragsActivity() {
        return (GamePageActivity) getActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.game_page.AbstractTvFragment
    public GameData getGame() {
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null) {
            return null;
        }
        return fragsActivity.aQ();
    }

    public int calcListHeight(List<ButtonData> list) {
        int i;
        Throwable th;
        try {
            int dividerHeight = this.buttonListView.getDividerHeight();
            i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                try {
                    i = i + getCellHeight(i2) + dividerHeight;
                } catch (Throwable th2) {
                    th = th2;
                    getLogger().log(Level.SEVERE, "problem: ", th);
                    return i;
                }
            }
            if (i > 0) {
                return i - dividerHeight;
            }
            return i;
        } catch (Throwable th3) {
            i = 0;
            th = th3;
        }
    }

    @Override // defpackage.bkq
    public void setNextFocusDownId(int i) {
        if (this.parent == null) {
            getLogger().severe("cannot set focus id because of null view");
        } else {
            this.parent.setNextFocusDownId(i);
        }
    }

    @Override // defpackage.bkq
    public void setNextFocusUpId(int i) {
        if (this.buttonListView == null) {
            getLogger().severe("cannot set focus id because of null view");
        } else {
            this.buttonListView.setNextFocusUpId(i);
        }
    }

    @Override // defpackage.bkq
    public int getFirstViewId() {
        if (this.buttonListView != null) {
            return this.buttonListView.getId();
        }
        getLogger().severe("cannot set focus id because of null view");
        return -1;
    }

    @Override // defpackage.bkq
    public int getLastViewId() {
        if (this.buttonListView != null) {
            return this.buttonListView.getId();
        }
        getLogger().severe("cannot set focus id because of null view");
        return -1;
    }

    @Override // defpackage.bkq
    public boolean isRendered() {
        return this.buttons.size() == this.buttonListView.getChildCount();
    }
}
