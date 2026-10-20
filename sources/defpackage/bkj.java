package defpackage;

import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import com.thuuz.gtv.activity.game_page.GamePageWebViewFragment;

/* loaded from: classes.dex */
public class bkj implements View.OnFocusChangeListener {
    final /* synthetic */ GamePageWebViewFragment a;

    public bkj(GamePageWebViewFragment gamePageWebViewFragment) {
        this.a = gamePageWebViewFragment;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        View view2;
        WebView webView;
        View view3;
        WebView webView2;
        View view4;
        if (z) {
            view4 = this.a.focus_rect_lay;
            view4.setBackgroundResource(bbn.dan_blue);
        } else if (Build.VERSION.SDK_INT >= 16) {
            view3 = this.a.focus_rect_lay;
            webView2 = this.a.webView;
            view3.setBackground(webView2.getBackground());
        } else {
            view2 = this.a.focus_rect_lay;
            webView = this.a.webView;
            view2.setBackgroundDrawable(webView.getBackground());
        }
    }
}
