package defpackage;

import android.os.AsyncTask;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bit extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ SearchFriendsActivity a;
    private final String b;
    private bzo c;

    public bit(SearchFriendsActivity searchFriendsActivity, String str) {
        this.a = searchFriendsActivity;
        this.b = str;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.a.e(true);
        this.a.f(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i = -1;
        try {
            this.c = new bzo(this.a.j(), this.b);
            this.c.l();
            i = this.c.v();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        EditText editText;
        TextView textView;
        Button button;
        Button button2;
        Integer num2;
        Button button3;
        TextView textView2;
        Button button4;
        Integer num3;
        Button button5;
        Button button6;
        Button button7;
        TextView textView3;
        try {
            if (this.a.d()) {
                if (num.intValue() == 0) {
                    this.a.h(true);
                    editText = this.a.i;
                    Editable text = editText.getText();
                    if (text != null) {
                        textView3 = this.a.l;
                        textView3.setText(text.toString());
                    }
                    List<bqb> b = this.c.b();
                    if (b.isEmpty()) {
                        textView2 = this.a.k;
                        textView2.setText("We couldn't find:");
                        if (text != null) {
                            button7 = this.a.m;
                            button7.setText("Send an invite to: " + text.toString());
                        }
                        button4 = this.a.m;
                        num3 = SearchFriendsActivity.g;
                        button4.setTag(num3);
                        button5 = this.a.m;
                        button5.requestFocus();
                        button6 = this.a.n;
                        button6.setText("Search again");
                    } else {
                        textView = this.a.k;
                        textView.setText("We found:");
                        button = this.a.m;
                        button.setText("Follow");
                        button2 = this.a.m;
                        num2 = SearchFriendsActivity.f;
                        button2.setTag(num2);
                        button3 = this.a.n;
                        button3.setText("Cancel");
                        this.a.p = b.get(0);
                    }
                } else if (this.a.d()) {
                    this.a.b("Unable to Search", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.o = null;
            this.a.e(false);
        }
    }
}
