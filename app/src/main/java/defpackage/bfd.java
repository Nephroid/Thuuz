package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.thuuz.gtv.activity.fantasy.FantasyInviteActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfd implements TextView.OnEditorActionListener {
    final /* synthetic */ FantasyInviteActivity a;

    private bfd(FantasyInviteActivity fantasyInviteActivity) {
        this.a = fantasyInviteActivity;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            try {
                Context context = textView.getContext();
                if (context == null) {
                    return false;
                }
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return false;
    }
}
