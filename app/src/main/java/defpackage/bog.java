package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class bog implements View.OnClickListener {
    final /* synthetic */ ShareActivity a;

    public bog(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String aP;
        EditText editText;
        String aQ;
        int i;
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            aP = this.a.aP();
            intent.putExtra("android.intent.extra.SUBJECT", aP);
            StringBuilder sb = new StringBuilder();
            editText = this.a.s;
            Editable text = editText.getText();
            if (text != null) {
                String trim = text.toString().trim();
                if (trim.length() > 0) {
                    sb.append(trim);
                    sb.append(" ");
                }
            }
            aQ = this.a.aQ();
            sb.append(aQ);
            intent.putExtra("android.intent.extra.TEXT", sb.toString());
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            this.a.k().startActivity(Intent.createChooser(intent, "Share:"));
            App j = this.a.j();
            i = this.a.p;
            j.a("share_other", "game_share", String.valueOf(i), 0L);
        } catch (ActivityNotFoundException e) {
            this.a.e().warning("text/plain activity not found");
            this.a.c("", "Please install an app for email or other text-based communication and try again.");
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
