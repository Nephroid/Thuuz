package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AbstractFriendFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bha extends ArrayAdapter<bqb> {
    final /* synthetic */ AbstractFriendFragment a;
    private bhb b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bha(AbstractFriendFragment abstractFriendFragment, Context context) {
        super(context, bbr.friend_list_item, abstractFriendFragment.friends);
        this.a = abstractFriendFragment;
        this.b = new bhb(this, null);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        Logger logger;
        AbstractActivity fragsActivity;
        AbstractActivity fragsActivity2;
        FriendsActivity friendsActivity;
        View view3;
        try {
            fragsActivity2 = this.a.getFragsActivity();
            friendsActivity = (FriendsActivity) fragsActivity2;
        } catch (OutOfMemoryError e) {
            outOfMemoryError = e;
            view2 = view;
        } catch (Throwable th2) {
            th = th2;
            view2 = view;
        }
        if (friendsActivity != null) {
            if (view == null) {
                view3 = friendsActivity.getLayoutInflater().inflate(bbr.friend_list_item, (ViewGroup) null);
                if (view3 == null) {
                    return null;
                }
            } else {
                view3 = view;
            }
            try {
                bqb item = getItem(i);
                ((TextView) view3.findViewById(bbq.friend_name)).setText(item.e());
                this.a.setupButton((Button) view3.findViewById(bbq.friend_button), item);
                this.a.setupRightText((TextView) view3.findViewById(bbq.right_text), item);
                this.a.setupCheckmark((ImageView) view3.findViewById(bbq.checkmark), item);
                this.a.setupBadge((ImageView) view3.findViewById(bbq.friend_expert_badge), item);
                Bitmap a = friendsActivity.aK().a(String.valueOf(item.a()), friendsActivity.j().n(String.valueOf(item.a())));
                ImageView imageView = (ImageView) view3.findViewById(bbq.friend_pic);
                if (a != null) {
                    imageView.setImageBitmap(a);
                } else {
                    imageView.setImageBitmap(null);
                }
                View findViewById = view3.findViewById(bbq.selectable_item);
                findViewById.setTag(item);
                findViewById.setOnClickListener(this.b);
                view2 = view3;
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view2 = view3;
                System.gc();
                System.gc();
                fragsActivity = this.a.getFragsActivity();
                if (fragsActivity != null) {
                    fragsActivity.a(outOfMemoryError);
                }
                return view2;
            } catch (Throwable th3) {
                th = th3;
                view2 = view3;
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
            return view2;
        }
        return view;
    }
}
