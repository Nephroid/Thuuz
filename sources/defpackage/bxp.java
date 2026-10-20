package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxp extends ArrayAdapter<bwt> {
    final /* synthetic */ TVProviderFragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bxp(TVProviderFragment tVProviderFragment, Context context, int i, List list) {
        super(context, i, list);
        this.a = tVProviderFragment;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        Logger logger;
        List list;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.provider_item_layout, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
            } catch (Throwable th) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "problem getting list provider view: ", th);
                return null;
            }
        } else {
            inflate = view;
        }
        TextView textView = (TextView) inflate.findViewById(bbq.text);
        list = this.a.providerListData;
        bwt bwtVar = (bwt) list.get(i);
        if (bwtVar != null) {
            textView.setText(bwtVar.toString());
        }
        return inflate;
    }
}
