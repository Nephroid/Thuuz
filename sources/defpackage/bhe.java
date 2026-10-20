package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhe implements Runnable {
    final /* synthetic */ AddFbFriendsActivity a;

    public bhe(AddFbFriendsActivity addFbFriendsActivity) {
        this.a = addFbFriendsActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        ListView listView;
        boolean z;
        ListView listView2;
        bhj bhjVar;
        ListView listView3;
        boolean z2;
        bhj bhjVar2;
        bhj bhjVar3;
        try {
            listView = this.a.f;
            int childCount = listView.getChildCount();
            int i = 0;
            boolean z3 = false;
            while (i < childCount) {
                listView3 = this.a.f;
                View childAt = listView3.getChildAt(i);
                if (childAt == null) {
                    z2 = z3;
                } else {
                    bhp bhpVar = (bhp) childAt.getTag();
                    if (bhpVar != null) {
                        if (bhpVar.d == null) {
                            z2 = z3;
                        } else {
                            int measuredWidth = bhpVar.d.getMeasuredWidth();
                            if (measuredWidth > 0) {
                                bhjVar2 = this.a.i;
                                if (measuredWidth > bhjVar2.a) {
                                    z2 = true;
                                    bhjVar3 = this.a.i;
                                    bhjVar3.a = measuredWidth;
                                }
                            }
                        }
                    }
                    z2 = z3;
                }
                i++;
                z3 = z2;
            }
            if (z3) {
                bhjVar = this.a.i;
                bhjVar.notifyDataSetChanged();
            }
            if (childCount > 0) {
                z = this.a.x;
                if (z && this.a.j().e()) {
                    this.a.x = false;
                    listView2 = this.a.f;
                    View childAt2 = listView2.getChildAt(0);
                    if (childAt2 != null) {
                        childAt2.requestFocus();
                    }
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
