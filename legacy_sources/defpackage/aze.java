package defpackage;

import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class aze extends azg {
    final axd<?> a;
    final /* synthetic */ awi b;
    final /* synthetic */ bau c;
    final /* synthetic */ Field d;
    final /* synthetic */ boolean e;
    final /* synthetic */ azd f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aze(azd azdVar, String str, boolean z, boolean z2, awi awiVar, bau bauVar, Field field, boolean z3) {
        super(str, z, z2);
        this.f = azdVar;
        this.b = awiVar;
        this.c = bauVar;
        this.d = field;
        this.e = z3;
        this.a = this.b.a(this.c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.azg
    public void a(bav bavVar, Object obj) {
        new azl(this.b, this.a, this.c.b()).a(bavVar, (bav) this.d.get(obj));
    }
}
