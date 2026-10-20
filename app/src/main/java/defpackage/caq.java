package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class caq {
    private List<bpy> a;
    private boolean b;

    public caq() {
        this.a = new ArrayList();
        this.b = false;
    }

    public caq(List<bpy> list, boolean z) {
        this.a = new ArrayList();
        this.b = false;
        this.a.addAll(list);
        this.b = z;
    }

    public List<bpy> a() {
        return this.a;
    }

    public void a(List<bpy> list) {
        this.a = list;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
