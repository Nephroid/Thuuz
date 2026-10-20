package twitter4j;

import java.io.Serializable;

/* loaded from: classes.dex */
final class ExceptionDiagnosis implements Serializable {
    private static final long serialVersionUID = 453958937114285988L;
    String hexString;
    int lineNumberHash;
    int stackLineHash;
    Throwable th;

    ExceptionDiagnosis(Throwable th) {
        this(th, new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExceptionDiagnosis(Throwable th, String[] strArr) {
        this.hexString = "";
        this.th = th;
        StackTraceElement[] stackTrace = th.getStackTrace();
        this.stackLineHash = 0;
        this.lineNumberHash = 0;
        for (int length = stackTrace.length - 1; length >= 0; length--) {
            StackTraceElement stackTraceElement = stackTrace[length];
            int length2 = strArr.length;
            int i = 0;
            while (true) {
                if (i < length2) {
                    if (!stackTraceElement.getClassName().startsWith(strArr[i])) {
                        i++;
                    } else {
                        this.stackLineHash = stackTraceElement.getClassName().hashCode() + stackTraceElement.getMethodName().hashCode() + (this.stackLineHash * 31);
                        this.lineNumberHash = (this.lineNumberHash * 31) + stackTraceElement.getLineNumber();
                        break;
                    }
                }
            }
        }
        this.hexString += toHexString(this.stackLineHash) + "-" + toHexString(this.lineNumberHash);
        if (th.getCause() != null) {
            this.hexString += " " + new ExceptionDiagnosis(th.getCause(), strArr).asHexString();
        }
    }

    int getStackLineHash() {
        return this.stackLineHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getStackLineHashAsHex() {
        return toHexString(this.stackLineHash);
    }

    int getLineNumberHash() {
        return this.lineNumberHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLineNumberHashAsHex() {
        return toHexString(this.lineNumberHash);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String asHexString() {
        return this.hexString;
    }

    private String toHexString(int i) {
        String str = "0000000" + Integer.toHexString(i);
        return str.substring(str.length() - 8, str.length());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExceptionDiagnosis exceptionDiagnosis = (ExceptionDiagnosis) obj;
        return this.lineNumberHash == exceptionDiagnosis.lineNumberHash && this.stackLineHash == exceptionDiagnosis.stackLineHash;
    }

    public int hashCode() {
        return (this.stackLineHash * 31) + this.lineNumberHash;
    }

    public String toString() {
        return "ExceptionDiagnosis{stackLineHash=" + this.stackLineHash + ", lineNumberHash=" + this.lineNumberHash + '}';
    }
}
