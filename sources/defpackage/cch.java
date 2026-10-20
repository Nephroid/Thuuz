package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.widget.ScreenOnService;
import com.thuuz.gtv.widget.WidgetProvider;
import com.thuuz.gtv.widget.WidgetRemoteViewService;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class cch implements RemoteViewsService.RemoteViewsFactory {
    final /* synthetic */ WidgetRemoteViewService a;
    private Context b;

    public cch(WidgetRemoteViewService widgetRemoteViewService, Context context, Intent intent) {
        this.a = widgetRemoteViewService;
        try {
            this.b = context;
            if (widgetRemoteViewService.a().isLoggable(Level.INFO)) {
                widgetRemoteViewService.a().info("widget factory created for: " + intent);
            }
        } catch (Throwable th) {
            widgetRemoteViewService.a().log(Level.SEVERE, "Cannot instantiate widget remove views: ", th);
        }
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onCreate() {
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDestroy() {
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getCount() {
        List list;
        list = WidgetRemoteViewService.b;
        return list.size();
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public RemoteViews getViewAt(int i) {
        List list;
        List list2;
        GameData gameData;
        SparseArray sparseArray;
        RemoteViews remoteViews = new RemoteViews(this.b.getPackageName(), bbr.game_layout_widget_item);
        list = WidgetRemoteViewService.b;
        if (list.size() > i) {
            list2 = WidgetRemoteViewService.b;
            Integer num = (Integer) list2.get(i);
            if (num != null) {
                sparseArray = WidgetRemoteViewService.a;
                gameData = (GameData) sparseArray.get(num.intValue());
            } else {
                gameData = null;
            }
            if (gameData == null) {
                this.a.a().warning("no widget item for pos: " + i);
            } else {
                gameData.a((AbstractActivity) null).a(this.a.b().x(), (AbstractActivity) null, new boy(remoteViews));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("id", gameData.i());
                intent.putExtras(bundle);
                remoteViews.setOnClickFillInIntent(bbq.widget_item, intent);
            }
            return remoteViews;
        }
        this.a.a().info("cannot supply widget game for position: " + i);
        return remoteViews;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDataSetChanged() {
        boolean z;
        boolean z2;
        List list;
        z = WidgetRemoteViewService.c;
        if (!z) {
            try {
                boolean unused = WidgetRemoteViewService.c = true;
                if (a()) {
                    list = WidgetRemoteViewService.b;
                    if (list.isEmpty()) {
                        this.a.a().warning("widget lastFetch was recent, but gameList is empty; refreshing");
                        z2 = false;
                    } else {
                        this.a.a().info("found recent cache of games");
                        z2 = true;
                    }
                } else {
                    z2 = false;
                }
                byc a = a(z2);
                if (a.F()) {
                    Date unused2 = WidgetRemoteViewService.d = new Date();
                    boolean unused3 = WidgetRemoteViewService.e = a.H().size() > 0;
                }
                boolean unused4 = WidgetRemoteViewService.c = false;
                if (a.y()) {
                    a(a);
                    return;
                } else {
                    a(a, a != null ? a.G() : false);
                    return;
                }
            } catch (Throwable th) {
                this.a.a().log(Level.SEVERE, "Cannot handle widget dataSetChanged: ", th);
                boolean unused5 = WidgetRemoteViewService.c = false;
                a((byc) null, false);
                this.a.j();
                return;
            }
        }
        this.a.a().info("skipping fetch because ongoing.");
    }

    private boolean a() {
        Date date;
        Date date2;
        date = WidgetRemoteViewService.d;
        if (date != null) {
            long currentTimeMillis = System.currentTimeMillis();
            date2 = WidgetRemoteViewService.d;
            if (currentTimeMillis - date2.getTime() < 60000) {
                return true;
            }
        }
        return false;
    }

    private byc a(boolean z) {
        boolean i;
        boolean z2;
        String str;
        i = this.a.i();
        if (i) {
            ScreenOnService.a(this.a.getCacheDir());
            z = false;
        }
        File a = WidgetRemoteViewService.a(this.a.getCacheDir());
        if (z) {
            String a2 = cak.a(a);
            z2 = a2.length() > 0;
            str = a2;
        } else if (i) {
            z2 = false;
            str = null;
        } else {
            long currentTimeMillis = System.currentTimeMillis() - 60000;
            if (a == null || !a.exists() || a.lastModified() <= currentTimeMillis) {
                z2 = false;
                str = null;
            } else {
                String a3 = cak.a(a);
                z2 = a3.length() > 0;
                str = a3;
            }
        }
        byc a4 = a(z2, str);
        a4.e(i);
        return a4;
    }

    private byc a(boolean z, String str) {
        FetchRequest fetchRequest = new FetchRequest(FetchRequest.REQ_TYPE.WIDGET, (bbl) null, this.a.b().as());
        fetchRequest.a(15);
        byc bycVar = new byc(fetchRequest, this.a.b());
        if (z) {
            if (str == null) {
                this.a.a().severe("null cache result--programming error");
            }
            this.a.a().info("update widget skips fetch in favor of cache");
            bzu b = b(bycVar);
            if (b.y()) {
                bycVar.a(false);
                bycVar.d(new JSONObject(str));
                bycVar.a(0);
            } else {
                this.a.a().info("update widget failed to fetch sportInfo: " + b.v());
                bycVar.a(-1);
            }
        } else if (b(bycVar).y()) {
            bycVar.l();
        } else {
            bycVar.a(-1);
        }
        return bycVar;
    }

    private void a(byc bycVar, boolean z) {
        boolean z2;
        int i = -1;
        if (bycVar != null) {
            try {
                i = bycVar.v();
            } catch (Throwable th) {
                this.a.a().log(Level.SEVERE, "Cannot handle: ", th);
                this.a.j();
                return;
            }
        }
        this.a.a().info("update widget failed to fetch games: " + i);
        Intent intent = new Intent(this.a.b(), (Class<?>) WidgetProvider.class);
        intent.setAction("com.thuuz.widget.Fail");
        this.a.b().sendBroadcast(intent);
        if (z) {
            this.a.a("Failed to download Thuuz info. Will try again automatically.");
        }
        WidgetRemoteViewService widgetRemoteViewService = this.a;
        z2 = WidgetRemoteViewService.e;
        widgetRemoteViewService.c(z2);
    }

    private void a(byc bycVar) {
        SparseArray sparseArray;
        List<Integer> list;
        SparseArray sparseArray2;
        try {
            this.a.a().info("update widget fetched games: " + bycVar.E().size());
            ArrayList arrayList = new ArrayList(bycVar.E().keySet());
            Collections.sort(arrayList, new bmr(this.a.b(), bycVar.E()));
            if (arrayList.size() > 15) {
                List unused = WidgetRemoteViewService.b = arrayList.subList(0, 15);
            } else {
                List unused2 = WidgetRemoteViewService.b = arrayList;
            }
            sparseArray = WidgetRemoteViewService.a;
            sparseArray.clear();
            list = WidgetRemoteViewService.b;
            for (Integer num : list) {
                sparseArray2 = WidgetRemoteViewService.a;
                sparseArray2.put(num.intValue(), bycVar.E().get(num));
            }
            String I = bycVar.I();
            a(I == null ? "" : I, bycVar.H().size() > 0);
        } catch (Throwable th) {
            this.a.a().log(Level.SEVERE, "Cannot handle: ", th);
            this.a.j();
        }
    }

    private void a(String str, boolean z) {
        a(str);
        this.a.c(z);
    }

    private void a(String str) {
        Intent intent = new Intent(this.a.b(), (Class<?>) WidgetProvider.class);
        intent.setAction("com.thuuz.widget.Title");
        intent.putExtra("com.thuuz.widget.Title", str);
        this.a.b().sendBroadcast(intent);
    }

    private bzu b(byc bycVar) {
        String a = cak.a(this.a.b().br());
        bzu bzuVar = new bzu(this.a.b());
        if (a.length() == 0) {
            this.a.a().info("update widget fetching sportinfo...");
            bzuVar.l();
            if (bzuVar.y()) {
                this.a.a().info("update widget fetched sportinfo");
                bycVar.a(bzuVar.c().keySet());
            } else {
                this.a.a().warning("failed widget sportinfo fetch: " + bzuVar.v());
            }
        } else {
            bzuVar.a(a);
            bzuVar.a(0);
            bycVar.a(bzuVar.c().keySet());
        }
        return bzuVar;
    }
}
