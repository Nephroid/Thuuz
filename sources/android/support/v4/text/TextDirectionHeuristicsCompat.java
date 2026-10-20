package android.support.v4.text;

import android.support.v4.widget.ViewDragHelper;
import java.nio.CharBuffer;
import java.util.Locale;
import org.apache.http.util.LangUtils;

/* loaded from: classes.dex */
public class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface TextDirectionAlgorithm {
        int checkRtl(CharSequence charSequence, int i, int i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        boolean z = true;
        boolean z2 = false;
        LTR = new TextDirectionHeuristicInternal(null, z2);
        RTL = new TextDirectionHeuristicInternal(0 == true ? 1 : 0, z);
        FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, z2);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, z);
        ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int isRtlText(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
            case 2:
                return 0;
            default:
                return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int isRtlTextOrFormat(int i) {
        switch (i) {
            case 0:
            case 14:
            case ViewDragHelper.EDGE_ALL /* 15 */:
                return 1;
            case 1:
            case 2:
            case 16:
            case LangUtils.HASH_SEED /* 17 */:
                return 0;
            default:
                return 2;
        }
    }

    /* loaded from: classes.dex */
    abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        protected abstract boolean defaultIsRtl();

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionAlgorithm) {
            this.mAlgorithm = textDirectionAlgorithm;
        }

        @Override // android.support.v4.text.TextDirectionHeuristicCompat
        public boolean isRtl(char[] cArr, int i, int i2) {
            return isRtl(CharBuffer.wrap(cArr), i, i2);
        }

        @Override // android.support.v4.text.TextDirectionHeuristicCompat
        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            }
            return this.mAlgorithm == null ? defaultIsRtl() : doCheck(charSequence, i, i2);
        }

        private boolean doCheck(CharSequence charSequence, int i, int i2) {
            switch (this.mAlgorithm.checkRtl(charSequence, i, i2)) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    return defaultIsRtl();
            }
        }
    }

    /* loaded from: classes.dex */
    class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        private TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionAlgorithm, boolean z) {
            super(textDirectionAlgorithm);
            this.mDefaultIsRtl = z;
        }

        @Override // android.support.v4.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
        protected boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    /* loaded from: classes.dex */
    class FirstStrong implements TextDirectionAlgorithm {
        public static final FirstStrong INSTANCE = new FirstStrong();

        @Override // android.support.v4.text.TextDirectionHeuristicsCompat.TextDirectionAlgorithm
        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i + i2;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }

        private FirstStrong() {
        }
    }

    /* loaded from: classes.dex */
    class AnyStrong implements TextDirectionAlgorithm {
        private final boolean mLookForRtl;
        public static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
        public static final AnyStrong INSTANCE_LTR = new AnyStrong(false);

        @Override // android.support.v4.text.TextDirectionHeuristicsCompat.TextDirectionAlgorithm
        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i + i2;
            boolean z = false;
            while (i < i3) {
                switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charSequence.charAt(i)))) {
                    case 0:
                        if (!this.mLookForRtl) {
                            z = true;
                            break;
                        } else {
                            return 0;
                        }
                    case 1:
                        if (!this.mLookForRtl) {
                            return 1;
                        }
                        z = true;
                        break;
                }
                i++;
            }
            if (z) {
                return !this.mLookForRtl ? 0 : 1;
            }
            return 2;
        }

        private AnyStrong(boolean z) {
            this.mLookForRtl = z;
        }
    }

    /* loaded from: classes.dex */
    class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        public static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();

        public TextDirectionHeuristicLocale() {
            super(null);
        }

        @Override // android.support.v4.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
        protected boolean defaultIsRtl() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }
}
