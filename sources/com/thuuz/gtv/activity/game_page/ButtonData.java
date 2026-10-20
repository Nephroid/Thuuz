package com.thuuz.gtv.activity.game_page;

/* loaded from: classes.dex */
public class ButtonData {
    private boolean a;
    private BUTTON_TYPE b;
    private Object c;

    /* loaded from: classes.dex */
    public enum BUTTON_TYPE {
        SET_TV_PROVIDER,
        LIVE_TV,
        DVR,
        TV_LISTINGS,
        ONLINE_RESOURCE,
        FIND_LOCAL_BAR,
        HIGHLIGHT,
        SLING
    }

    public boolean a() {
        return BUTTON_TYPE.TV_LISTINGS == this.b || BUTTON_TYPE.ONLINE_RESOURCE == this.b || BUTTON_TYPE.FIND_LOCAL_BAR == this.b || BUTTON_TYPE.HIGHLIGHT == this.b || BUTTON_TYPE.SLING == this.b;
    }

    public ButtonData(BUTTON_TYPE button_type, Object obj) {
        this.b = button_type;
        this.c = obj;
    }

    public Object b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ButtonData buttonData = (ButtonData) obj;
        if (this.b == buttonData.b) {
            if (this.c == null && buttonData.c == null) {
                return true;
            }
            if (this.c != null && this.c.equals(buttonData.c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.b != null ? this.b.hashCode() : 0) * 31) + (this.c != null ? this.c.hashCode() : 0);
    }

    public BUTTON_TYPE c() {
        return this.b;
    }

    public boolean d() {
        return this.a && a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.a = a() && z;
    }
}
