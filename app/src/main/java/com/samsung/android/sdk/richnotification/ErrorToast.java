package com.samsung.android.sdk.richnotification;

import defpackage.axf;
import defpackage.axg;
import java.util.UUID;

/* loaded from: classes.dex */
final class ErrorToast {

    @axf
    @axg(a = "message")
    private final String mMessage;

    @axf
    @axg(a = "uuid")
    private final UUID mUuid;

    public ErrorToast(UUID uuid, String str) {
        this.mUuid = uuid;
        this.mMessage = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UUID getUuid() {
        return this.mUuid;
    }
}
