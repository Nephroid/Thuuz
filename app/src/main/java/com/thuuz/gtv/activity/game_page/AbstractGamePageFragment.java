package com.thuuz.gtv.activity.game_page;

import android.os.Bundle;
import android.view.View;
import defpackage.bbf;
import defpackage.bjl;

/* loaded from: classes.dex */
public abstract class AbstractGamePageFragment extends bbf implements bjl {
    private String charset;
    private int dynamicResNum = -1;
    private int fragType;
    public int gameId;
    protected View game_layout;
    private boolean isPrefetch;
    private String mime;
    private String refreshUrl;
    private String state;

    public AbstractGamePageFragment() {
    }

    public AbstractGamePageFragment(int i, int i2) {
        this.fragType = i;
        this.gameId = i2;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.fragType = bundle.getInt("fragType");
            this.gameId = bundle.getInt("game_id");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("fragType", this.fragType);
        bundle.putInt("game_id", this.gameId);
        String refreshState = getRefreshState();
        if (refreshState != null) {
            bundle.putString("state", refreshState);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getRefreshState() {
        return this.state;
    }

    public String getRefreshUrl() {
        return this.refreshUrl;
    }

    public String getMime() {
        return this.mime;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setRefreshState(String str, String str2, String str3, String str4) {
        this.state = str;
        this.mime = str2;
        this.charset = str3;
        this.refreshUrl = str4;
    }

    @Override // defpackage.bjl
    public int getFragTypeNum() {
        return this.fragType;
    }

    @Override // defpackage.bbf
    public GamePageActivity getFragsActivity() {
        return (GamePageActivity) super.getFragsActivity();
    }

    public void setDynResNum(int i) {
        this.dynamicResNum = i;
    }

    /* loaded from: classes.dex */
    public enum FragType {
        GAME_MOBILE(1, "GAME", "tunein", "tunein-mob"),
        GAME_TABLET(2, "GAME", "tunein"),
        FEED(3, "FEED", "feed"),
        PREVIEW(4, "PREVIEW", "preview"),
        RECAP(5, "RECAP", "recap"),
        STATS(6, "STATS", "stats"),
        GRAPH(7, "GRAPH", "graph"),
        FANTASY(8, "FANTASY", "fantasy"),
        DYNAMIC(9, "LEADERS", "dyn_leader");

        private final String name;
        private final String title;
        private final String track;
        private final int type;

        FragType(int i, String str, String str2, String str3) {
            this.type = i;
            this.title = str;
            this.track = str2;
            this.name = str3;
        }

        FragType(int i, String str, String str2) {
            this(i, str, str2, str2);
        }

        public static FragType a(int i) {
            for (FragType fragType : values()) {
                if (fragType.type == i) {
                    return fragType;
                }
            }
            return null;
        }

        public static int a() {
            return values().length - 2;
        }

        public static int b() {
            return a() - 1;
        }

        public int c() {
            return this.type;
        }

        public String d() {
            return this.title;
        }

        public String e() {
            return this.name;
        }

        public String f() {
            return this.track;
        }
    }

    @Override // android.support.v4.app.Fragment
    public String toString() {
        FragType a = FragType.a(this.fragType);
        return a == null ? super.toString() : "frag: " + a.e();
    }

    @Override // defpackage.bjl
    public void isPrefetch(boolean z) {
        this.isPrefetch = z;
    }

    public boolean isPrefetch() {
        return this.isPrefetch;
    }

    @Override // defpackage.bjl
    public String getFragName() {
        FragType a = FragType.a(this.fragType);
        if (a == null) {
            return "";
        }
        if (a == FragType.DYNAMIC) {
            return a.e() + this.dynamicResNum;
        }
        return a.e();
    }

    @Override // defpackage.bjl
    public int getDynamicResNum() {
        return this.dynamicResNum;
    }
}
