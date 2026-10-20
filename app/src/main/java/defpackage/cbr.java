package defpackage;

import android.view.View;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbr implements View.OnFocusChangeListener {
    final /* synthetic */ HorizColumnScroll a;

    private cbr(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    public /* synthetic */ cbr(HorizColumnScroll horizColumnScroll, cbl cblVar) {
        this(horizColumnScroll);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        cbn cbnVar;
        int i;
        int i2;
        int i3;
        bqu bquVar;
        bqu bquVar2;
        bqu bquVar3;
        bqu bquVar4;
        try {
            Object tag = view.getTag();
            if (tag != null) {
                if (tag instanceof bmm) {
                    this.a.getLogger().info("got focus: " + z + " for cell: " + ((bmm) tag).i());
                    View view2 = (View) view.getParent();
                    if (view2 != null) {
                        cbnVar = (cbn) view2.getTag();
                    }
                    cbnVar = null;
                } else {
                    if (tag instanceof Integer) {
                        this.a.getLogger().info("got focus: " + z + " for dummy: " + tag);
                        View view3 = (View) view.getParent();
                        if (view3 != null) {
                            cbnVar = (cbn) view3.getTag();
                        }
                    }
                    cbnVar = null;
                }
                if (cbnVar == null) {
                    if (tag instanceof cbn) {
                        cbnVar = (cbn) view.getTag();
                    } else {
                        return;
                    }
                }
                if (cbnVar == null) {
                    this.a.getLogger().info("no column holder in focus listener");
                    return;
                }
                if (!z) {
                    this.a.getLogger().info("losing focus " + cbnVar.a);
                    return;
                }
                this.a.getLogger().info("gaining focus " + cbnVar.a);
                int focusedColumnIndex = this.a.getFocusedColumnIndex();
                if (focusedColumnIndex != -500) {
                    i = this.a.i;
                    if (i == -500) {
                        this.a.i = focusedColumnIndex;
                        return;
                    }
                    i2 = this.a.i;
                    if (i2 != focusedColumnIndex) {
                        HashSet hashSet = new HashSet(Arrays.asList(this.a.getVisibleColumnCodes()));
                        i3 = this.a.i;
                        if (focusedColumnIndex > i3) {
                            int i4 = this.a.e;
                            this.a.i = focusedColumnIndex;
                            if (focusedColumnIndex > (this.a.e + this.a.c) + (-1)) {
                                this.a.e = this.a.c((focusedColumnIndex - this.a.c) + 1);
                                this.a.getLogger().info("scrolling right, new leftColumn = " + this.a.e);
                                this.a.b();
                                this.a.a((HashSet<Integer>) hashSet);
                                bquVar3 = this.a.f;
                                if (bquVar3 != null) {
                                    bquVar4 = this.a.f;
                                    bquVar4.b(i4, this.a.e);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        int i5 = this.a.e;
                        this.a.i = focusedColumnIndex;
                        if (focusedColumnIndex < this.a.e) {
                            this.a.e = this.a.c(focusedColumnIndex);
                            this.a.getLogger().info("scrolling left, new leftColumn = " + this.a.e);
                            this.a.b();
                            this.a.a((HashSet<Integer>) hashSet);
                            bquVar = this.a.f;
                            if (bquVar != null) {
                                bquVar2 = this.a.f;
                                bquVar2.a(i5, this.a.e);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
