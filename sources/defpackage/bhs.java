package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AddFriendsFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bhs extends ArrayAdapter<AddFriendsFragment.ADD_FRIENDS_SELECTION_TYPE> {
    final /* synthetic */ AddFriendsFragment a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bhs(com.thuuz.gtv.activity.friends.AddFriendsFragment r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.add_friend_select_list_item
            java.util.List r1 = com.thuuz.gtv.activity.friends.AddFriendsFragment.access$300(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bhs.<init>(com.thuuz.gtv.activity.friends.AddFriendsFragment, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        Logger logger;
        AbstractActivity fragsActivity;
        AbstractActivity fragsActivity2;
        boolean z;
        bht bhtVar;
        try {
            fragsActivity2 = this.a.getFragsActivity();
        } catch (OutOfMemoryError e) {
            outOfMemoryError = e;
            view2 = view;
        } catch (Throwable th2) {
            th = th2;
            view2 = view;
        }
        if (fragsActivity2 != null) {
            View inflate = view == null ? fragsActivity2.getLayoutInflater().inflate(bbr.add_friend_select_list_item, (ViewGroup) null) : view;
            if (inflate == null) {
                return null;
            }
            try {
                AddFriendsFragment.ADD_FRIENDS_SELECTION_TYPE item = getItem(i);
                inflate.setTag(item);
                ((TextView) inflate.findViewById(bbq.text)).setText(item.b());
                TextView textView = (TextView) inflate.findViewById(bbq.separator);
                ImageView imageView = (ImageView) inflate.findViewById(bbq.icon);
                switch (item.a()) {
                    case ADD_FRIENDS_SELECTION_TYPE_SEARCH:
                        textView.setText("Find friends to follow");
                        imageView.setImageDrawable(this.a.getResources().getDrawable(bbp.search_icon));
                        z = true;
                        break;
                    case ADD_FRIENDS_SELECTION_TYPE_FACEBOOK:
                        imageView.setImageDrawable(this.a.getResources().getDrawable(bbp.facebook_icon));
                        z = false;
                        break;
                    case ADD_FRIENDS_SELECTION_TYPE_EXPERTS:
                        imageView.setImageDrawable(this.a.getResources().getDrawable(bbp.expert_icon));
                        z = false;
                        break;
                    case ADD_FRIENDS_SELECTION_TYPE_EMAIL:
                        imageView.setImageDrawable(this.a.getResources().getDrawable(bbp.mail_icon));
                        textView.setText("Invite friends to join Thuuz");
                        z = true;
                        break;
                    case ADD_FRIENDS_SELECTION_TYPE_SMS:
                        imageView.setImageDrawable(this.a.getResources().getDrawable(bbp.sms_icon));
                        z = false;
                        break;
                    default:
                        z = false;
                        break;
                }
                if (z) {
                    textView.setVisibility(0);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
                    if (marginLayoutParams != null) {
                        if (i == 0) {
                            marginLayoutParams.topMargin = 0;
                        } else {
                            marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                        }
                    }
                } else {
                    textView.setVisibility(8);
                }
                RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(bbq.selectable_item);
                bhtVar = this.a.clickListener;
                relativeLayout.setOnClickListener(bhtVar);
                view2 = inflate;
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view2 = inflate;
                System.gc();
                System.gc();
                fragsActivity = this.a.getFragsActivity();
                if (fragsActivity != null) {
                    fragsActivity.a(outOfMemoryError);
                }
                return view2;
            } catch (Throwable th3) {
                th = th3;
                view2 = inflate;
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
            return view2;
        }
        return view;
    }
}
