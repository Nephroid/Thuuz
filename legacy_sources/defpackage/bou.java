package defpackage;

import android.os.AsyncTask;
import android.text.Editable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.data.FantasySite;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import twitter4j.conf.PropertyConfiguration;

/* loaded from: classes.dex */
public class bou extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ ShareActivity a;
    private final Integer b;

    private bou(ShareActivity shareActivity) {
        this.a = shareActivity;
        this.b = 9999;
    }

    public /* synthetic */ bou(ShareActivity shareActivity, bof bofVar) {
        this(shareActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        EditText editText;
        EditText editText2;
        try {
            this.a.e(true);
            String str = "";
            editText = this.a.s;
            Editable text = editText.getText();
            if (text != null) {
                str = text.toString().trim();
            }
            editText2 = this.a.s;
            editText2.setText(str.replaceAll("\n", " "));
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        CheckBox checkBox;
        EditText editText;
        int i;
        ShareActivity.SHARE_TYPE share_type;
        ShareActivity.SHARE_TYPE share_type2;
        ShareActivity.SHARE_TYPE share_type3;
        String str;
        int i2;
        ShareActivity.SHARE_TYPE share_type4;
        bot botVar;
        Map map;
        Spinner spinner;
        Spinner spinner2;
        List list;
        List list2;
        boolean z = false;
        try {
            checkBox = this.a.u;
            boolean isChecked = checkBox.isChecked();
            String str2 = "";
            editText = this.a.s;
            Editable text = editText.getText();
            if (text != null) {
                str2 = text.toString().trim();
            }
            String replaceAll = str2.replaceAll("\n", " ");
            String str3 = "game";
            i = this.a.p;
            String num = Integer.toString(i);
            share_type = this.a.o;
            if (share_type != ShareActivity.SHARE_TYPE.SHARE_TYPE_USERFEED) {
                share_type2 = this.a.o;
                if (share_type2 != ShareActivity.SHARE_TYPE.SHARE_TYPE_FANTASY) {
                    share_type3 = this.a.o;
                    if (share_type3 == ShareActivity.SHARE_TYPE.SHARE_TYPE_GAME) {
                        z = true;
                        str = num;
                    } else {
                        str = num;
                    }
                } else {
                    str3 = "fsleague";
                    IService F = this.a.F();
                    if (F != null) {
                        i2 = this.a.p;
                        bpf n = F.n(i2);
                        if (n == null) {
                            new bop(this.a, F, true).execute(new Void[0]);
                            return this.b;
                        }
                        num = String.valueOf(n.a());
                    } else {
                        this.a.e().warning("cannot get service");
                    }
                    str = num;
                    isChecked = false;
                }
            } else {
                str3 = PropertyConfiguration.USER;
                str = new bqd(this.a.j().bj()).a();
            }
            bzp bzpVar = new bzp(this.a.j(), str3, str, z, replaceAll, isChecked);
            share_type4 = this.a.o;
            if (share_type4 == ShareActivity.SHARE_TYPE.SHARE_TYPE_FANTASY) {
                spinner = this.a.t;
                spinner2 = this.a.t;
                int positionForView = spinner.getPositionForView(spinner2.getSelectedView());
                if (positionForView > 0) {
                    list = this.a.B;
                    if (positionForView < list.size()) {
                        list2 = this.a.B;
                        bzpVar.a(String.valueOf(list2.get(positionForView)));
                    }
                }
            }
            bzpVar.l();
            if (bzpVar.y()) {
                botVar = new bot(this.a, true, null);
            } else {
                botVar = new bot(this.a, false, "FAILED notifying followers");
            }
            map = this.a.y;
            map.put(ShareActivity.SHARE_TO.SHARE_TO_FOLLOWERS, botVar);
            return Integer.valueOf(bzpVar.v());
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        ShareActivity.SHARE_TYPE share_type;
        IService F;
        int i;
        bpg l;
        FantasySite b;
        try {
            if (this.a.d()) {
                if (this.b.equals(num)) {
                    return;
                }
                share_type = this.a.o;
                if (share_type == ShareActivity.SHARE_TYPE.SHARE_TYPE_FANTASY && num.intValue() == -85 && (F = this.a.F()) != null) {
                    i = this.a.p;
                    bpf n = F.n(i);
                    if (n != null && (l = F.l(n.c())) != null && (b = F.b(l.c(), l.b())) != null) {
                        this.a.c("Login Expired for " + b.c(), "Please login again");
                        this.a.a(l.b(), b.a());
                    }
                }
                this.a.aK();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.e(false);
        }
    }
}
