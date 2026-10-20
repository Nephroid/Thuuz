package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;

/* loaded from: classes.dex */
public class ib extends Fragment implements DialogInterface.OnCancelListener, LoaderManager.LoaderCallbacks<gw> {
    private boolean a;
    private gw c;
    private int b = -1;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final SparseArray<ie> e = new SparseArray<>();

    public static ib a(FragmentActivity fragmentActivity) {
        kb.b("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            ib ibVar = (ib) supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (ibVar != null && !ibVar.isRemoving()) {
                return ibVar;
            }
            ib ibVar2 = new ib();
            supportFragmentManager.beginTransaction().add(ibVar2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return ibVar2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.a = false;
        this.b = -1;
        this.c = null;
        LoaderManager loaderManager = getLoaderManager();
        for (int i = 0; i < this.e.size(); i++) {
            int keyAt = this.e.keyAt(i);
            id c = c(keyAt);
            if (c != null) {
                c.a();
            }
            loaderManager.initLoader(keyAt, null, this);
        }
    }

    private void a(int i, gw gwVar) {
        if (this.a) {
            return;
        }
        this.a = true;
        this.b = i;
        this.c = gwVar;
        this.d.post(new Cif(this, i, gwVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, gw gwVar) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        ie ieVar = this.e.get(i);
        if (ieVar != null) {
            b(i);
            hp hpVar = ieVar.b;
            if (hpVar != null) {
                hpVar.a(gwVar);
            }
        }
        a();
    }

    private void d(int i) {
        if (i == this.b) {
            a();
        }
    }

    public hm a(int i) {
        id c;
        if (getActivity() == null || (c = c(i)) == null) {
            return null;
        }
        return c.a;
    }

    public void a(int i, hm hmVar, hp hpVar) {
        kb.a(hmVar, "GoogleApiClient instance cannot be null");
        kb.a(this.e.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        this.e.put(i, new ie(hmVar, hpVar));
        if (getActivity() != null) {
            getLoaderManager().initLoader(i, null, this);
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<gw> loader, gw gwVar) {
        if (gwVar.b()) {
            d(loader.getId());
        } else {
            a(loader.getId(), gwVar);
        }
    }

    public void b(int i) {
        getLoaderManager().destroyLoader(i);
        this.e.remove(i);
    }

    id c(int i) {
        try {
            return (id) getLoaderManager().getLoader(i);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0014, code lost:
    
        if (defpackage.hb.a(getActivity()) == 0) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0018, code lost:
    
        if (r5 == (-1)) goto L5;
     */
    @Override // android.support.v4.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            switch(r4) {
                case 1: goto L17;
                case 2: goto Lc;
                default: goto L5;
            }
        L5:
            r0 = r1
        L6:
            if (r0 == 0) goto L1b
            r3.a()
        Lb:
            return
        Lc:
            android.support.v4.app.FragmentActivity r2 = r3.getActivity()
            int r2 = defpackage.hb.a(r2)
            if (r2 != 0) goto L5
            goto L6
        L17:
            r2 = -1
            if (r5 != r2) goto L5
            goto L6
        L1b:
            int r0 = r3.b
            gw r1 = r3.c
            r3.b(r0, r1)
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ib.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.e.size()) {
                return;
            }
            int keyAt = this.e.keyAt(i2);
            id c = c(keyAt);
            if (c == null || this.e.valueAt(i2).a == c.a) {
                getLoaderManager().initLoader(keyAt, null, this);
            } else {
                getLoaderManager().restartLoader(keyAt, null, this);
            }
            i = i2 + 1;
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        b(this.b, this.c);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.a = bundle.getBoolean("resolving_error", false);
            this.b = bundle.getInt("failed_client_id", -1);
            if (this.b >= 0) {
                this.c = new gw(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public Loader<gw> onCreateLoader(int i, Bundle bundle) {
        return new id(getActivity(), this.e.get(i).a);
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<gw> loader) {
        if (loader.getId() == this.b) {
            a();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.a);
        if (this.b >= 0) {
            bundle.putInt("failed_client_id", this.b);
            bundle.putInt("failed_status", this.c.c());
            bundle.putParcelable("failed_resolution", this.c.d());
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.a) {
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            getLoaderManager().initLoader(this.e.keyAt(i), null, this);
        }
    }
}
