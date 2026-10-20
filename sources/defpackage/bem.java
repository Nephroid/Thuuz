package defpackage;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fantasy.FantasyCompListFrag;
import com.thuuz.gtv.data.FantasySite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bem extends ArrayAdapter<bep> {
    final /* synthetic */ FantasyCompListFrag a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bem(com.thuuz.gtv.activity.fantasy.FantasyCompListFrag r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.fantasy_list_item
            java.util.List r1 = com.thuuz.gtv.activity.fantasy.FantasyCompListFrag.access$200(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bem.<init>(com.thuuz.gtv.activity.fantasy.FantasyCompListFrag, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        AbstractActivity fragsActivity;
        Logger logger;
        View inflate;
        List list;
        IService service;
        bpg bpgVar;
        boolean isNeedReconnect;
        boolean isNeedReconnect2;
        bpg bpgVar2;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.fantasy_list_item, (ViewGroup) null);
                try {
                    try {
                        a(inflate);
                    } catch (Throwable th2) {
                        th = th2;
                        view2 = inflate;
                        logger = this.a.getLogger();
                        logger.log(Level.SEVERE, "Cannot handle: ", th);
                        return view2;
                    }
                } catch (OutOfMemoryError e) {
                    outOfMemoryError = e;
                    view2 = inflate;
                    System.gc();
                    System.gc();
                    fragsActivity = this.a.getFragsActivity();
                    if (fragsActivity != null) {
                        fragsActivity.a(outOfMemoryError);
                    }
                    return view2;
                }
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view2 = view;
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
            }
        } else {
            inflate = view;
        }
        if (inflate == null) {
            return null;
        }
        try {
            list = this.a.mTeamItems;
            bep bepVar = (bep) list.get(i);
            beq beqVar = (beq) inflate.getTag();
            service = this.a.getService();
            if (service != null) {
                bpg l = service.l(bepVar.c);
                if (l == null && bepVar.a()) {
                    bpgVar2 = bepVar.e;
                    bpgVar = bpgVar2;
                } else {
                    bpgVar = l;
                }
                if (bpgVar != null) {
                    String a = a(bpgVar);
                    if (a.length() == 0) {
                        a = "(unnamed)";
                    }
                    beqVar.b.setText(a);
                    beqVar.e.setText(b(bpgVar), TextView.BufferType.SPANNABLE);
                    isNeedReconnect = this.a.isNeedReconnect(bpgVar);
                    if (isNeedReconnect) {
                        beqVar.b.setGravity(5);
                    } else {
                        beqVar.b.setGravity(3);
                    }
                    beqVar.b.setTextColor(this.a.getResources().getColorStateList(bbp.text_selector));
                    if (bepVar.a) {
                        beqVar.a.setVisibility(0);
                        beqVar.c.setVisibility(8);
                        FantasySite b = service.b(bpgVar.c(), bpgVar.b());
                        if (b != null) {
                            beqVar.d.setText(b.c());
                        }
                        isNeedReconnect2 = this.a.isNeedReconnect(bpgVar);
                        if (isNeedReconnect2) {
                            beqVar.b.setTextColor(this.a.getResources().getColor(bbn.red));
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) beqVar.a.getLayoutParams();
                        if (marginLayoutParams != null) {
                            if (i == 0) {
                                marginLayoutParams.topMargin = 0;
                            } else {
                                marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                            }
                        }
                    } else {
                        beqVar.a.setVisibility(8);
                    }
                    view2 = inflate;
                    return view2;
                }
                return inflate;
            }
            return inflate;
        } catch (Throwable th4) {
            return inflate;
        }
    }

    private void a(View view) {
        ben benVar;
        beo beoVar;
        beq beqVar = new beq();
        beqVar.a = view.findViewById(bbq.separator);
        beqVar.b = (TextView) view.findViewById(bbq.title);
        beqVar.e = (TextView) view.findViewById(bbq.subtitle);
        beqVar.f = (RelativeLayout) view.findViewById(bbq.selectable_item);
        beqVar.f.setClickable(true);
        beqVar.f.setFocusable(true);
        RelativeLayout relativeLayout = beqVar.f;
        benVar = this.a.mClickListener;
        relativeLayout.setOnClickListener(benVar);
        RelativeLayout relativeLayout2 = beqVar.f;
        beoVar = this.a.mKeyListener;
        relativeLayout2.setOnKeyListener(beoVar);
        beqVar.c = (TextView) view.findViewById(bbq.comp_title);
        beqVar.d = (TextView) view.findViewById(bbq.site_title);
        view.setTag(beqVar);
        beqVar.c.setVisibility(8);
    }

    private String a(bpg bpgVar) {
        if (bpgVar.a() == -1 || bpgVar.a() == -2) {
        }
        return bpgVar.e();
    }

    private SpannableString b(bpg bpgVar) {
        StringBuilder sb = new StringBuilder(bpgVar.d());
        if (!bpgVar.f()) {
            return new SpannableString(sb.toString());
        }
        sb.append(" ");
        int length = sb.length();
        SpannableString spannableString = new SpannableString(sb.toString() + "   Season Over");
        spannableString.setSpan(new ForegroundColorSpan(-65536), length, spannableString.length(), 0);
        return spannableString;
    }
}
