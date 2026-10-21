package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.thuuz.gtv.view.FocusingWebView;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbk implements View.OnTouchListener {
    final /* synthetic */ FocusingWebView a;

    public cbk(FocusingWebView focusingWebView) {
        this.a = focusingWebView;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view != null) {
            try {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
            } catch (Throwable th) {
                bzy.getStaticLogger(FocusingWebView.class).log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return false;
    }
}
