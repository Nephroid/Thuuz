package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class jk implements Handler.Callback {
    private static final Object a = new Object();
    private static jk b;
    private final Context c;
    private final HashMap<String, jl> d = new HashMap<>();
    private final Handler e;

    private jk(Context context) {
        this.e = new Handler(context.getMainLooper(), this);
        this.c = context.getApplicationContext();
    }

    public static jk a(Context context) {
        synchronized (a) {
            if (b == null) {
                b = new jk(context.getApplicationContext());
            }
        }
        return b;
    }

    /* JADX WARN: Incorrect inner types in method signature: (Ljava/lang/String;Liz<*>.je;)Z */
    public boolean a(String str, je jeVar) {
        boolean c;
        synchronized (this.d) {
            jl jlVar = this.d.get(str);
            if (jlVar != null) {
                this.e.removeMessages(0, jlVar);
                if (!jlVar.c(jeVar)) {
                    jlVar.a(jeVar);
                    switch (jlVar.d()) {
                        case 1:
                            jeVar.onServiceConnected(jlVar.g(), jlVar.f());
                            break;
                        case 2:
                            jlVar.a(this.c.bindService(new Intent(str).setPackage("com.google.android.gms"), jlVar.a(), 129));
                            break;
                    }
                } else {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + str);
                }
            } else {
                jlVar = new jl(this, str);
                jlVar.a(jeVar);
                jlVar.a(this.c.bindService(new Intent(str).setPackage("com.google.android.gms"), jlVar.a(), 129));
                this.d.put(str, jlVar);
            }
            c = jlVar.c();
        }
        return c;
    }

    /* JADX WARN: Incorrect inner types in method signature: (Ljava/lang/String;Liz<*>.je;)V */
    public void b(String str, je jeVar) {
        synchronized (this.d) {
            jl jlVar = this.d.get(str);
            if (jlVar == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + str);
            }
            if (!jlVar.c(jeVar)) {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + str);
            }
            jlVar.b(jeVar);
            if (jlVar.e()) {
                this.e.sendMessageDelayed(this.e.obtainMessage(0, jlVar), 5000L);
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                jl jlVar = (jl) message.obj;
                synchronized (this.d) {
                    if (jlVar.e()) {
                        this.c.unbindService(jlVar.a());
                        this.d.remove(jlVar.b());
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
