package defpackage;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class j implements Executor {
    private ThreadLocal<Integer> a;

    private j() {
        this.a = new ThreadLocal<>();
    }

    private int a() {
        Integer num = this.a.get();
        if (num == null) {
            num = 0;
        }
        int intValue = num.intValue() + 1;
        this.a.set(Integer.valueOf(intValue));
        return intValue;
    }

    private int b() {
        Integer num = this.a.get();
        if (num == null) {
            num = 0;
        }
        int intValue = num.intValue() - 1;
        if (intValue == 0) {
            this.a.remove();
        } else {
            this.a.set(Integer.valueOf(intValue));
        }
        return intValue;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        try {
            if (a() <= 15) {
                runnable.run();
            } else {
                h.a().execute(runnable);
            }
        } finally {
            b();
        }
    }
}
