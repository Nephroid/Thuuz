package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import com.thuuz.gtv.activity.nobar.DialogActivity;
import com.thuuz.gtv.data.OnlineResource;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.view.PlayTriangleView;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjy extends ArrayAdapter<ButtonData> {
    final /* synthetic */ GamePageTvFragment a;
    private final List<ButtonData> b;
    private final int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bjy(GamePageTvFragment gamePageTvFragment, Context context, List<ButtonData> list) {
        super(context, bbr.game_page_button, list);
        this.a = gamePageTvFragment;
        this.c = 20;
        this.b = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        View a;
        Logger logger;
        if (view == null) {
            try {
                a = a(viewGroup);
                try {
                    a.setOnClickListener(null);
                    bjw bjwVar = new bjw(null);
                    bjwVar.a = (TextView) a.findViewById(bbq.label);
                    bjwVar.b = (TextView) a.findViewById(bbq.descrip);
                    bjwVar.g = a.findViewById(bbq.symbols);
                    bjwVar.i = (RelativeLayout) a.findViewById(bbq.record_circle);
                    bjwVar.j = (PlayTriangleView) a.findViewById(bbq.play_triangle);
                    bjwVar.c = (TextView) a.findViewById(bbq.section_title);
                    bjwVar.d = a.findViewById(bbq.button_lay);
                    bjwVar.e = (TextView) a.findViewById(bbq.fineprint);
                    bjwVar.f = (NetworkListenableImageView) a.findViewById(bbq.online_image_button);
                    bjwVar.h = a.findViewById(bbq.see_all_link);
                    bjwVar.k = a.findViewById(bbq.section_header_lay);
                    a.setTag(bjwVar);
                    GamePageActivity fragsActivity = this.a.getFragsActivity();
                    if (fragsActivity != null) {
                        bjwVar.d.setId(fragsActivity.A());
                    }
                    bjwVar.d.setNextFocusRightId(bbq.list);
                } catch (Throwable th2) {
                    view2 = a;
                    th = th2;
                    logger = this.a.getLogger();
                    logger.log(Level.SEVERE, "Cannot inflate tv button view: ", th);
                    return view2;
                }
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot inflate tv button view: ", th);
                return view2;
            }
        } else {
            a = view;
        }
        if (i >= this.b.size()) {
            return a;
        }
        a(a, getItem(i));
        return a;
    }

    private View a(ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.game_page_button, viewGroup, false);
        if (inflate == null) {
            return null;
        }
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            inflate.setId(fragsActivity.A());
            return inflate;
        }
        return inflate;
    }

    private void a(View view, ButtonData buttonData) {
        View view2;
        View view3;
        bjw bjwVar = (bjw) view.getTag();
        bjwVar.g.setVisibility(8);
        bjwVar.i.setVisibility(8);
        bjwVar.j.setVisibility(8);
        bjwVar.b.setVisibility(8);
        bjwVar.e.setVisibility(8);
        if (buttonData.d()) {
            bjwVar.c.setVisibility(0);
            bjwVar.k.setVisibility(0);
        } else {
            bjwVar.c.setVisibility(8);
            bjwVar.k.setVisibility(8);
        }
        bjwVar.h.setVisibility(8);
        bjwVar.d.setNextFocusRightId(bbq.list);
        switch (buttonData.c()) {
            case SET_TV_PROVIDER:
                view3 = this.a.webLay;
                view3.setVisibility(8);
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
                e(view);
                bjwVar.c.setVisibility(8);
                return;
            case LIVE_TV:
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
                f(view);
                bjwVar.j.setVisibility(0);
                bjwVar.g.setVisibility(0);
                return;
            case TV_LISTINGS:
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
                view2 = this.a.webLay;
                view2.setVisibility(8);
                b(view);
                return;
            case ONLINE_RESOURCE:
                b(view, buttonData);
                return;
            case SLING:
                bjwVar.c.setText("Watch on Sling");
                a(view);
                return;
            case DVR:
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
                bjwVar.i.setVisibility(0);
                bjwVar.g.setVisibility(0);
                c(view);
                return;
            case FIND_LOCAL_BAR:
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
                bjwVar.c.setText("Watch Nearby");
                d(view);
                return;
            default:
                return;
        }
    }

    private void a(View view) {
        Logger logger;
        bjw bjwVar = (bjw) view.getTag();
        bjwVar.a.setText("View in SlingPlayer app");
        try {
            bjwVar.f.setVisibility(0);
            bjwVar.d.setVisibility(8);
            bjwVar.f.setDefaultImageResId(bbp.sling_logo);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
        bke bkeVar = new bke(this, null);
        bjwVar.d.setOnClickListener(bkeVar);
        bjwVar.f.setOnClickListener(bkeVar);
        view.setTag(bbt.click_handler_key, bkeVar);
    }

    private void b(View view) {
        bjw bjwVar = (bjw) view.getTag();
        bjwVar.a.setText("See full TV schedule");
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && fragsActivity.j().a((Activity) fragsActivity)) {
            bjwVar.a.setPadding(20, 0, 0, 0);
            bjwVar.a.setGravity(19);
        }
        bjwVar.d.setOnClickListener(new bjz(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<bpa> arrayList) {
        a(this.a.getResources().getString(bbt.tv_schedule), this.a.getGame().a(arrayList, -1));
        String str = this.a.getGame().k() + "/" + this.a.getGame().l();
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            fragsActivity.j().a("watch", "schedule", str, this.a.getGame().i());
        }
    }

    public void a(String str, String str2) {
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            Intent intent = new Intent(fragsActivity, (Class<?>) DialogActivity.class);
            intent.putExtra("title", str);
            intent.putExtra("msg", str2);
            intent.putExtra("html", 1);
            fragsActivity.startActivity(intent);
        }
    }

    private void c(View view) {
        boolean z;
        TextView textView;
        ArrayList<bpa> broadcastsFiltered;
        Logger logger;
        TextView textView2;
        View.OnClickListener onClickListener;
        if (view != null) {
            bjw bjwVar = (bjw) view.getTag();
            Iterator<ButtonData> it = this.a.buttons.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().c() == ButtonData.BUTTON_TYPE.LIVE_TV) {
                    z = true;
                    break;
                }
            }
            textView = this.a.webShowAllLink;
            boolean z2 = textView.getTag() != null;
            if (z && !z2) {
                bjwVar.h.setVisibility(0);
                textView2 = this.a.webShowAllLink;
                textView2.setVisibility(8);
                View view2 = bjwVar.h;
                onClickListener = this.a.seeAllBroadcastsClickListener;
                view2.setOnClickListener(onClickListener);
            }
            if (z) {
                broadcastsFiltered = this.a.getShowingNowBroadcastsFiltered();
            } else {
                broadcastsFiltered = this.a.getBroadcastsFiltered();
            }
            if (broadcastsFiltered.size() == 0) {
                logger = this.a.getLogger();
                logger.severe("got to filling dvr button but w/ NO broadcasts");
                this.a.removeAny(ButtonData.BUTTON_TYPE.DVR);
                return;
            }
            GamePageActivity fragsActivity = this.a.getFragsActivity();
            String str = "";
            Resources resources = view.getResources();
            if (broadcastsFiltered.size() == 1) {
                String str2 = "Record on " + broadcastsFiltered.get(0).g() + " ";
                if (fragsActivity.j().a((Activity) fragsActivity)) {
                    if (resources != null) {
                        str2 = resources.getString(bbt.choose_channel);
                    }
                    bjwVar.a.setPadding(20, 0, 0, 0);
                    bjwVar.a.setGravity(19);
                }
                bjwVar.a.setText(str2);
            } else {
                if (resources != null) {
                    str = resources.getString(bbt.ctv_mobile_record_channel);
                }
                if (fragsActivity.j().a((Activity) fragsActivity)) {
                    if (resources != null) {
                        str = resources.getString(bbt.choose_channel);
                    }
                    bjwVar.a.setPadding(20, 0, 0, 0);
                    bjwVar.a.setGravity(19);
                }
                bjwVar.a.setText(str);
            }
            bka bkaVar = new bka(this, broadcastsFiltered);
            bjwVar.d.setOnClickListener(bkaVar);
            view.setTag(bbt.click_handler_key, bkaVar);
        }
    }

    private void d(View view) {
        bjw bjwVar = (bjw) view.getTag();
        bjwVar.a.setText("Local sports bars");
        bja bjaVar = new bja(this.a);
        bjwVar.d.setOnClickListener(bjaVar);
        view.setTag(bbt.click_handler_key, bjaVar);
    }

    private void e(View view) {
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            bjw bjwVar = (bjw) view.getTag();
            bjwVar.a.setText(fragsActivity.getResources().getString(bbt.select_tv_provider));
            biz bizVar = new biz(this.a);
            bjwVar.d.setOnClickListener(bizVar);
            view.setTag(bbt.click_handler_key, bizVar);
        }
    }

    private void f(View view) {
        Logger logger;
        Logger logger2;
        if (view != null) {
            try {
                ArrayList<bpa> showingNowBroadcastsFiltered = this.a.getShowingNowBroadcastsFiltered();
                if (showingNowBroadcastsFiltered.size() == 0) {
                    logger2 = this.a.getLogger();
                    logger2.severe("removing live button because NO broadcasts");
                    this.a.removeAny(ButtonData.BUTTON_TYPE.LIVE_TV);
                    return;
                }
                bjw bjwVar = (bjw) view.getTag();
                GamePageActivity fragsActivity = this.a.getFragsActivity();
                Resources resources = view.getResources();
                if (resources != null) {
                    bjwVar.a.setText(resources.getString(bbt.ctv_change_channel));
                }
                if (fragsActivity.j().a((Activity) fragsActivity)) {
                    bjwVar.a.setPadding(20, 0, 0, 0);
                    bjwVar.a.setGravity(19);
                }
                bkb bkbVar = new bkb(this, showingNowBroadcastsFiltered);
                bjwVar.d.setOnClickListener(bkbVar);
                view.setTag(bbt.click_handler_key, bkbVar);
            } catch (Throwable th) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    private void b(View view, ButtonData buttonData) {
        Logger logger;
        Logger logger2;
        try {
            bjw bjwVar = (bjw) view.getTag();
            OnlineResource onlineResource = (OnlineResource) buttonData.b();
            OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE onlineResType = this.a.getOnlineResType(buttonData);
            if (buttonData.d()) {
                bjwVar.c.setVisibility(0);
            } else {
                bjwVar.c.setVisibility(8);
            }
            switch (onlineResType) {
                case WATCH_ONLINE:
                    bjwVar.c.setText("Watch Online");
                    break;
                case WATCH_HIGHLIGHTS:
                    bjwVar.c.setText("Watch Highlights Online");
                    break;
                case LISTEN:
                    bjwVar.c.setText("Listen Online");
                    break;
                case GAME_TRACKER:
                    bjwVar.c.setText("Game Tracker");
                    break;
            }
            bjwVar.a.setText(onlineResource.i());
            String l = onlineResource.l();
            if (l.length() > 0) {
                bjwVar.e.setText("(" + l + ")");
                bjwVar.e.setVisibility(0);
            } else {
                bjwVar.e.setVisibility(8);
            }
            String h = onlineResource.h();
            if (h.length() > 0) {
                bjwVar.b.setText(h);
                bjwVar.b.setVisibility(0);
            } else {
                bjwVar.b.setVisibility(8);
            }
            String j = onlineResource.j();
            View.OnClickListener createOnlineViewerClickListener = this.a.createOnlineViewerClickListener(onlineResource);
            if (j.length() > 0) {
                try {
                    bjwVar.f.setErrorListener(new bkc(this, null));
                    bjwVar.f.setSuccessListener(new bkd(this, null));
                    GamePageActivity fragsActivity = this.a.getFragsActivity();
                    if (fragsActivity != null && fragsActivity.aE() != null) {
                        bjwVar.f.setImageUrl(this.a.getUpdatedOnlineLinkImageUrl(j), fragsActivity.aE());
                    }
                    bjwVar.f.setOnClickListener(createOnlineViewerClickListener);
                } catch (Throwable th) {
                    logger2 = this.a.getLogger();
                    logger2.log(Level.SEVERE, "Cannot handle: ", th);
                }
            } else {
                bjwVar.f.setVisibility(8);
                bjwVar.d.setVisibility(0);
            }
            bjwVar.d.setOnClickListener(createOnlineViewerClickListener);
            view.setTag(bbt.click_handler_key, createOnlineViewerClickListener);
        } catch (Throwable th2) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th2);
        }
    }
}
