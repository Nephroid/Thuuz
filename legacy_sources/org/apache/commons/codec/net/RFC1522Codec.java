package org.apache.commons.codec.net;

@Deprecated
/* loaded from: classes.dex */
abstract class RFC1522Codec {
    protected abstract byte[] doDecoding(byte[] bArr);

    protected abstract byte[] doEncoding(byte[] bArr);

    protected abstract String getEncoding();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RFC1522Codec() {
        throw new RuntimeException("Stub!");
    }

    protected String encodeText(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    protected String decodeText(String str) {
        throw new RuntimeException("Stub!");
    }
}
