package defpackage;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes.dex */
public class bqx implements AdapterView.OnItemSelectedListener {
    private int a;
    private AdapterView.OnItemSelectedListener b;

    public bqx(AdapterView.OnItemSelectedListener onItemSelectedListener, int i) {
        this.a = i;
        this.b = onItemSelectedListener;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a == i) {
            Log.d(getClass().getName(), "Ignoring onItemSelected for same position: " + i);
        } else {
            Log.d(getClass().getName(), "Passing on onItemSelected for different position: " + i);
            this.b.onItemSelected(adapterView, view, i, j);
        }
        this.a = i;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        this.b.onNothingSelected(adapterView);
    }
}
