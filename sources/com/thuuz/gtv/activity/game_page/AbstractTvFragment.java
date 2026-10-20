package com.thuuz.gtv.activity.game_page;

import android.location.Location;
import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.OnlineResource;
import com.thuuz.gtv.data.OnlineResourceCategory;
import defpackage.bbf;
import defpackage.bix;
import defpackage.bkq;
import defpackage.bpa;
import defpackage.bpu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public abstract class AbstractTvFragment extends bbf implements bkq {
    public final List<ButtonData> buttons = Collections.synchronizedList(new ArrayList());
    protected final List<ButtonData> tv_buttons = Collections.synchronizedList(new ArrayList());

    public static /* synthetic */ AbstractActivity access$800(AbstractTvFragment abstractTvFragment) {
        return abstractTvFragment.getFragsActivity();
    }

    public void showSportsBarDeprecated() {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                String str = "";
                bpu a = fragsActivity.j().ax().a();
                if (a != null) {
                    str = a.c();
                }
                String str2 = "http://maps.google.com/?q=sports+bar+" + str;
                fragsActivity.e().info("attempting sports bar deprecated url: " + str2);
                fragsActivity.e(str2, "Sports Bar");
                GameData game = getGame();
                if (game != null) {
                    fragsActivity.j().a("miscellaneous", "find_sports_bar", String.valueOf(game.i()), 0L);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem handling sportBarDeprecated: ", th);
        }
    }

    public void showSportsBarLocations(Location location) {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                String format = String.format("http://maps.google.com/?q=sports+bar&near=%f,%f", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
                getLogger().info("attempting sports bar url: " + format);
                fragsActivity.e(format, "Sports Bar");
                GameData game = getGame();
                if (game != null) {
                    fragsActivity.j().a("miscellaneous", "find_sports_bar", String.valueOf(game.i()), 0L);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public GameData getGame() {
        GamePageActivity gamePageActivity = (GamePageActivity) getFragsActivity();
        if (gamePageActivity == null) {
            return null;
        }
        return gamePageActivity.aQ();
    }

    public void trackLinkClick(OnlineResource onlineResource) {
        if (onlineResource != null) {
            try {
                String str = "follow";
                switch (OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.a(onlineResource.a().intValue())) {
                    case WATCH_ONLINE:
                        str = "watch_online";
                        break;
                    case WATCH_HIGHLIGHTS:
                        str = "watch_highlight";
                        break;
                    case LISTEN:
                        str = "listen";
                        break;
                    case GAME_TRACKER:
                        str = "game_tracker";
                        break;
                }
                AbstractActivity fragsActivity = getFragsActivity();
                if (fragsActivity != null) {
                    if (getGame() != null) {
                        fragsActivity.j().a("onlineresource", str, onlineResource.k(), r4.i());
                    }
                    getLogger().info("launched: " + str + " " + onlineResource.g());
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem tracking link: " + onlineResource, th);
            }
        }
    }

    public View.OnClickListener createOnlineViewerClickListener(OnlineResource onlineResource) {
        return new bix(this, onlineResource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0030 A[Catch: Throwable -> 0x0068, TryCatch #0 {Throwable -> 0x0068, blocks: (B:3:0x0007, B:5:0x000f, B:6:0x0019, B:8:0x001d, B:10:0x0030, B:11:0x0034, B:13:0x003a, B:15:0x005f, B:18:0x0046), top: B:2:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getUpdatedOnlineLinkImageUrl(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = com.thuuz.gtv.util.ServerChoice.q
            r2.<init>(r0)
            java.lang.String r0 = "hdpi"
            com.thuuz.gtv.activity.AbstractActivity r1 = r6.getFragsActivity()     // Catch: java.lang.Throwable -> L68
            if (r1 == 0) goto L1c
            android.content.res.Resources r1 = r1.getResources()     // Catch: java.lang.Throwable -> L68
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()     // Catch: java.lang.Throwable -> L68
            int r1 = r1.densityDpi     // Catch: java.lang.Throwable -> L68
            switch(r1) {
                case 320: goto L5b;
                default: goto L1c;
            }     // Catch: java.lang.Throwable -> L68
        L1c:
            r1 = r0
        L1d:
            java.lang.String r0 = "static"
            r2.append(r0)     // Catch: java.lang.Throwable -> L68
            android.net.Uri r0 = android.net.Uri.parse(r7)     // Catch: java.lang.Throwable -> L68
            java.lang.String r3 = r0.getLastPathSegment()     // Catch: java.lang.Throwable -> L68
            java.util.List r0 = r0.getPathSegments()     // Catch: java.lang.Throwable -> L68
            if (r0 == 0) goto L46
            java.util.Iterator r4 = r0.iterator()     // Catch: java.lang.Throwable -> L68
        L34:
            boolean r0 = r4.hasNext()     // Catch: java.lang.Throwable -> L68
            if (r0 == 0) goto L46
            java.lang.Object r0 = r4.next()     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L68
            int r5 = r0.compareTo(r3)     // Catch: java.lang.Throwable -> L68
            if (r5 != 0) goto L5f
        L46:
            java.lang.String r0 = "/android/"
            r2.append(r0)     // Catch: java.lang.Throwable -> L68
            r2.append(r1)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = "/"
            r2.append(r0)     // Catch: java.lang.Throwable -> L68
            r2.append(r3)     // Catch: java.lang.Throwable -> L68
        L56:
            java.lang.String r0 = r2.toString()
            return r0
        L5b:
            java.lang.String r0 = "xhdpi"
            r1 = r0
            goto L1d
        L5f:
            java.lang.String r5 = "/"
            r2.append(r5)     // Catch: java.lang.Throwable -> L68
            r2.append(r0)     // Catch: java.lang.Throwable -> L68
            goto L34
        L68:
            r0 = move-exception
            java.util.logging.Logger r1 = r6.getLogger()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem: "
            r1.log(r3, r4, r0)
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.game_page.AbstractTvFragment.getUpdatedOnlineLinkImageUrl(java.lang.String):java.lang.String");
    }

    public ArrayList<bpa> getShowingNowBroadcastsFiltered() {
        GameData game = getGame();
        return game == null ? new ArrayList<>() : getBroadcastsFiltered(game.d(getCorrectedNow()));
    }

    protected ArrayList<bpa> getBroadcastsFiltered(ArrayList<bpa> arrayList) {
        ArrayList<bpa> arrayList2 = new ArrayList<>(arrayList);
        GamePageActivity gamePageActivity = (GamePageActivity) getFragsActivity();
        if (gamePageActivity == null) {
            return arrayList2;
        }
        if (gamePageActivity.aP()) {
            Iterator<bpa> it = arrayList.iterator();
            while (it.hasNext()) {
                bpa next = it.next();
                if (!gamePageActivity.a(next)) {
                    arrayList2.remove(next);
                }
            }
        }
        return arrayList2;
    }

    public ArrayList<bpa> getBroadcastsFiltered() {
        GameData game = getGame();
        return game == null ? new ArrayList<>() : getBroadcastsFiltered(game.c(getCorrectedNow()));
    }

    public boolean hasProviderPrefs() {
        bpu a;
        AbstractActivity fragsActivity = getFragsActivity();
        return (fragsActivity == null || (a = fragsActivity.j().ax().a()) == null || a.g()) ? false : true;
    }

    public ButtonData putButton(ButtonData.BUTTON_TYPE button_type) {
        return putButton(button_type, null);
    }

    public ButtonData putTvButton(ButtonData.BUTTON_TYPE button_type) {
        return putTvButton(button_type, null);
    }

    public ButtonData putButton(ButtonData.BUTTON_TYPE button_type, Object obj) {
        ButtonData buttonData = new ButtonData(button_type, obj);
        if (this.buttons.contains(buttonData)) {
            return null;
        }
        this.buttons.add(buttonData);
        return buttonData;
    }

    public ButtonData putTvButton(ButtonData.BUTTON_TYPE button_type, Object obj) {
        ButtonData buttonData = new ButtonData(button_type, obj);
        if (this.tv_buttons.contains(buttonData)) {
            return null;
        }
        this.tv_buttons.add(buttonData);
        return buttonData;
    }

    public void removeAny(ButtonData.BUTTON_TYPE button_type) {
        Iterator it = new ArrayList(this.buttons).iterator();
        while (it.hasNext()) {
            ButtonData buttonData = (ButtonData) it.next();
            if (buttonData.c().equals(button_type)) {
                this.buttons.remove(buttonData);
            }
        }
        Iterator it2 = new ArrayList(this.tv_buttons).iterator();
        while (it2.hasNext()) {
            ButtonData buttonData2 = (ButtonData) it2.next();
            if (buttonData2.c().equals(button_type)) {
                this.tv_buttons.remove(buttonData2);
            }
        }
    }

    public OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE getOnlineResType(ButtonData buttonData) {
        OnlineResource onlineResource;
        try {
            if (buttonData.c() == ButtonData.BUTTON_TYPE.ONLINE_RESOURCE && (onlineResource = (OnlineResource) buttonData.b()) != null) {
                return OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.a(onlineResource.a().intValue());
            }
            return null;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    public void clearButtons() {
        this.buttons.clear();
    }
}
