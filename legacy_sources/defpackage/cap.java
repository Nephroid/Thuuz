package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes.dex */
public class cap implements View.OnKeyListener {
    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        View.OnClickListener onClickListener;
        if (keyEvent.getAction() != 0 && (i == 23 || i == 66)) {
            try {
                View selectedView = ((ListView) view).getSelectedView();
                if (selectedView != null && selectedView.getTag(bbt.click_handler_key) != null && (onClickListener = (View.OnClickListener) selectedView.getTag(bbt.click_handler_key)) != null) {
                    onClickListener.onClick(view);
                }
            } catch (Throwable th) {
            }
        }
        return false;
    }
}
