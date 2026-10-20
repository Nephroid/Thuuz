package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import org.apache.http.protocol.HTTP;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class add implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ adc b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public add(adc adcVar, String str) {
        this.b = adcVar;
        this.a = str;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Context context;
        context = this.b.a;
        context.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType(HTTP.PLAIN_TEXT_TYPE).putExtra("android.intent.extra.TEXT", this.a), "Share via"));
    }
}
