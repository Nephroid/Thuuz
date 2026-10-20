package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.HighlightsFragment;
import com.thuuz.gtv.view.PlayThumbnail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bkp extends ArrayAdapter<ButtonData> {
    final /* synthetic */ HighlightsFragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bkp(HighlightsFragment highlightsFragment, Context context, List<ButtonData> list) {
        super(context, bbr.game_page_button, list);
        this.a = highlightsFragment;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        Logger logger;
        int cellHeight;
        if (view == null) {
            try {
                view2 = a(viewGroup);
            } catch (Throwable th) {
                th = th;
                view2 = view;
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot get view: ", th);
                return view2;
            }
        } else {
            view2 = view;
        }
        try {
            PlayThumbnail playThumbnail = (PlayThumbnail) view2;
            ViewGroup.LayoutParams layoutParams = playThumbnail.getLayoutParams();
            if (layoutParams != null) {
                cellHeight = this.a.getCellHeight(i);
                layoutParams.height = cellHeight;
            }
            String b = ((bpp) this.a.buttons.get(i).b()).b();
            if (b != null) {
                playThumbnail.setImageUrl(b, this.a.getFragsActivity().aE());
            }
        } catch (Throwable th2) {
            th = th2;
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot get view: ", th);
            return view2;
        }
        return view2;
    }

    private View a(ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.highlight_button, viewGroup, false);
        if (inflate == null) {
            return null;
        }
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            inflate.setId(fragsActivity.A());
            return inflate;
        }
        return inflate;
    }
}
