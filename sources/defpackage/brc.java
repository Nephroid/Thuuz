package defpackage;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.settings.FeedbackActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class brc implements View.OnClickListener {
    final /* synthetic */ FeedbackActivity a;

    public brc(FeedbackActivity feedbackActivity) {
        this.a = feedbackActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean ag;
        EditText editText;
        Button button;
        try {
            ag = this.a.ag();
            if (!ag) {
                editText = this.a.h;
                Editable text = editText.getText();
                if (text == null || text.toString().length() != 0) {
                    button = this.a.i;
                    button.setEnabled(false);
                    new brd(this.a).execute((Void[]) null);
                } else {
                    this.a.c("No Message", "Please first type in your message.");
                }
            } else {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{"android@thuuz.com"});
                intent.putExtra("android.intent.extra.SUBJECT", "Thuuz Feedback");
                intent.putExtra("android.intent.extra.TEXT", this.a.j().aj() + this.a.j().al());
                this.a.c(intent);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
