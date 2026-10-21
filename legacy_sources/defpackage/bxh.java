package defpackage;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxh implements View.OnClickListener {
    final /* synthetic */ Button a;
    final /* synthetic */ EditText b;
    final /* synthetic */ TVProviderFragment c;

    public bxh(TVProviderFragment tVProviderFragment, Button button, EditText editText) {
        this.c = tVProviderFragment;
        this.a = button;
        this.b = editText;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        bqc bqcVar;
        AbstractActivity fragsActivity;
        bqc bqcVar2;
        AbstractActivity fragsActivity2;
        Integer num;
        Logger logger2;
        AbstractActivity fragsActivity3;
        try {
            bqcVar = this.c.startPairDevice;
            if (bqcVar == null) {
                logger2 = this.c.getLogger();
                logger2.severe("cannot find startPairDevice");
                fragsActivity3 = this.c.getFragsActivity();
                fragsActivity3.c("Cannot Pair", "Previous context was lost; please start again");
            } else {
                this.a.setEnabled(false);
                Editable text = this.b.getText();
                if (text != null) {
                    fragsActivity = this.c.getFragsActivity();
                    bqcVar2 = this.c.startPairDevice;
                    fragsActivity2 = this.c.getFragsActivity();
                    Locations ax = fragsActivity2.j().ax();
                    String obj = text.toString();
                    num = this.c.location_id;
                    new bws(fragsActivity, bqcVar2, ax, obj, num.intValue()).execute(new Void[0]);
                }
            }
        } catch (Throwable th) {
            logger = this.c.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
