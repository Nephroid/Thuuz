package org.joda.time.format;

import java.io.Writer;

/* loaded from: classes.dex */
public class FormatUtils {
    private static final double LOG_10 = Math.log(10.0d);

    private FormatUtils() {
    }

    public static void appendPaddedInteger(StringBuffer stringBuffer, int i, int i2) {
        int log;
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                while (i2 > 10) {
                    stringBuffer.append('0');
                    i2--;
                }
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            while (i2 > 1) {
                stringBuffer.append('0');
                i2--;
            }
            stringBuffer.append((char) (i + 48));
            return;
        }
        if (i < 100) {
            while (i2 > 2) {
                stringBuffer.append('0');
                i2--;
            }
            int i3 = ((i + 1) * 13421772) >> 27;
            stringBuffer.append((char) (i3 + 48));
            stringBuffer.append((char) (((i - (i3 << 3)) - (i3 << 1)) + 48));
            return;
        }
        if (i < 1000) {
            log = 3;
        } else if (i < 10000) {
            log = 4;
        } else {
            log = ((int) (Math.log(i) / LOG_10)) + 1;
        }
        while (i2 > log) {
            stringBuffer.append('0');
            i2--;
        }
        stringBuffer.append(Integer.toString(i));
    }

    public static void appendPaddedInteger(StringBuffer stringBuffer, long j, int i) {
        int i2 = (int) j;
        if (i2 == j) {
            appendPaddedInteger(stringBuffer, i2, i);
            return;
        }
        if (i <= 19) {
            stringBuffer.append(Long.toString(j));
            return;
        }
        if (j < 0) {
            stringBuffer.append('-');
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                while (i > 19) {
                    stringBuffer.append('0');
                    i--;
                }
                stringBuffer.append("9223372036854775808");
                return;
            }
        }
        int log = ((int) (Math.log(j) / LOG_10)) + 1;
        while (i > log) {
            stringBuffer.append('0');
            i--;
        }
        stringBuffer.append(Long.toString(j));
    }

    public static void writePaddedInteger(Writer writer, int i, int i2) {
        int log;
        if (i < 0) {
            writer.write(45);
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                while (i2 > 10) {
                    writer.write(48);
                    i2--;
                }
                writer.write("2147483648");
                return;
            }
        }
        if (i < 10) {
            while (i2 > 1) {
                writer.write(48);
                i2--;
            }
            writer.write(i + 48);
            return;
        }
        if (i < 100) {
            while (i2 > 2) {
                writer.write(48);
                i2--;
            }
            int i3 = ((i + 1) * 13421772) >> 27;
            writer.write(i3 + 48);
            writer.write(((i - (i3 << 3)) - (i3 << 1)) + 48);
            return;
        }
        if (i < 1000) {
            log = 3;
        } else if (i < 10000) {
            log = 4;
        } else {
            log = ((int) (Math.log(i) / LOG_10)) + 1;
        }
        while (i2 > log) {
            writer.write(48);
            i2--;
        }
        writer.write(Integer.toString(i));
    }

    public static void writePaddedInteger(Writer writer, long j, int i) {
        int i2 = (int) j;
        if (i2 == j) {
            writePaddedInteger(writer, i2, i);
            return;
        }
        if (i <= 19) {
            writer.write(Long.toString(j));
            return;
        }
        if (j < 0) {
            writer.write(45);
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                while (i > 19) {
                    writer.write(48);
                    i--;
                }
                writer.write("9223372036854775808");
                return;
            }
        }
        int log = ((int) (Math.log(j) / LOG_10)) + 1;
        while (i > log) {
            writer.write(48);
            i--;
        }
        writer.write(Long.toString(j));
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, int i) {
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            stringBuffer.append((char) (i + 48));
        } else {
            if (i < 100) {
                int i2 = ((i + 1) * 13421772) >> 27;
                stringBuffer.append((char) (i2 + 48));
                stringBuffer.append((char) (((i - (i2 << 3)) - (i2 << 1)) + 48));
                return;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, long j) {
        int i = (int) j;
        if (i == j) {
            appendUnpaddedInteger(stringBuffer, i);
        } else {
            stringBuffer.append(Long.toString(j));
        }
    }

    public static void writeUnpaddedInteger(Writer writer, int i) {
        if (i < 0) {
            writer.write(45);
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                writer.write("2147483648");
                return;
            }
        }
        if (i < 10) {
            writer.write(i + 48);
        } else {
            if (i < 100) {
                int i2 = ((i + 1) * 13421772) >> 27;
                writer.write(i2 + 48);
                writer.write(((i - (i2 << 3)) - (i2 << 1)) + 48);
                return;
            }
            writer.write(Integer.toString(i));
        }
    }

    public static void writeUnpaddedInteger(Writer writer, long j) {
        int i = (int) j;
        if (i == j) {
            writeUnpaddedInteger(writer, i);
        } else {
            writer.write(Long.toString(j));
        }
    }

    public static int calculateDigitCount(long j) {
        if (j < 0) {
            if (j != Long.MIN_VALUE) {
                return calculateDigitCount(-j) + 1;
            }
            return 20;
        }
        if (j < 10) {
            return 1;
        }
        if (j < 100) {
            return 2;
        }
        if (j < 1000) {
            return 3;
        }
        if (j < 10000) {
            return 4;
        }
        return ((int) (Math.log(j) / LOG_10)) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseTwoDigits(String str, int i) {
        int charAt = str.charAt(i) - '0';
        return (((charAt << 1) + (charAt << 3)) + str.charAt(i + 1)) - 48;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String createErrorMessage(String str, int i) {
        int i2 = i + 32;
        String concat = str.length() <= i2 + 3 ? str : str.substring(0, i2).concat("...");
        if (i <= 0) {
            return "Invalid format: \"" + concat + '\"';
        }
        if (i >= str.length()) {
            return "Invalid format: \"" + concat + "\" is too short";
        }
        return "Invalid format: \"" + concat + "\" is malformed at \"" + concat.substring(i) + '\"';
    }
}
