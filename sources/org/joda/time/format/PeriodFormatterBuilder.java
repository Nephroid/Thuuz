package org.joda.time.format;

import com.actionbarsherlock.widget.ActivityChooserView;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

/* loaded from: classes.dex */
public class PeriodFormatterBuilder {
    private static final int DAYS = 3;
    private static final int HOURS = 4;
    private static final int MAX_FIELD = 9;
    private static final int MILLIS = 7;
    private static final int MINUTES = 5;
    private static final int MONTHS = 1;
    private static final int PRINT_ZERO_ALWAYS = 4;
    private static final int PRINT_ZERO_IF_SUPPORTED = 3;
    private static final int PRINT_ZERO_NEVER = 5;
    private static final int PRINT_ZERO_RARELY_FIRST = 1;
    private static final int PRINT_ZERO_RARELY_LAST = 2;
    private static final int SECONDS = 6;
    private static final int SECONDS_MILLIS = 8;
    private static final int SECONDS_OPTIONAL_MILLIS = 9;
    private static final int WEEKS = 2;
    private static final int YEARS = 0;
    private List<Object> iElementPairs;
    private FieldFormatter[] iFieldFormatters;
    private int iMaxParsedDigits;
    private int iMinPrintedDigits;
    private boolean iNotParser;
    private boolean iNotPrinter;
    private PeriodFieldAffix iPrefix;
    private int iPrintZeroSetting;
    private boolean iRejectSignedValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface PeriodFieldAffix {
        int calculatePrintedLength(int i);

        int parse(String str, int i);

        void printTo(Writer writer, int i);

        void printTo(StringBuffer stringBuffer, int i);

        int scan(String str, int i);
    }

    public PeriodFormatterBuilder() {
        clear();
    }

    public PeriodFormatter toFormatter() {
        PeriodFormatter formatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        this.iFieldFormatters = (FieldFormatter[]) this.iFieldFormatters.clone();
        return formatter;
    }

    public PeriodPrinter toPrinter() {
        if (this.iNotPrinter) {
            return null;
        }
        return toFormatter().getPrinter();
    }

    public PeriodParser toParser() {
        if (this.iNotParser) {
            return null;
        }
        return toFormatter().getParser();
    }

    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        if (this.iElementPairs == null) {
            this.iElementPairs = new ArrayList();
        } else {
            this.iElementPairs.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }

    public PeriodFormatterBuilder append(PeriodFormatter periodFormatter) {
        if (periodFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        clearPrefix();
        append0(periodFormatter.getPrinter(), periodFormatter.getParser());
        return this;
    }

    public PeriodFormatterBuilder append(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        if (periodPrinter == null && periodParser == null) {
            throw new IllegalArgumentException("No printer or parser supplied");
        }
        clearPrefix();
        append0(periodPrinter, periodParser);
        return this;
    }

    public PeriodFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        clearPrefix();
        Literal literal = new Literal(str);
        append0(literal, literal);
        return this;
    }

    public PeriodFormatterBuilder minimumPrintedDigits(int i) {
        this.iMinPrintedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder maximumParsedDigits(int i) {
        this.iMaxParsedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder rejectSignedValues(boolean z) {
        this.iRejectSignedValues = z;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyLast() {
        this.iPrintZeroSetting = 2;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyFirst() {
        this.iPrintZeroSetting = 1;
        return this;
    }

    public PeriodFormatterBuilder printZeroIfSupported() {
        this.iPrintZeroSetting = 3;
        return this;
    }

    public PeriodFormatterBuilder printZeroAlways() {
        this.iPrintZeroSetting = 4;
        return this;
    }

    public PeriodFormatterBuilder printZeroNever() {
        this.iPrintZeroSetting = 5;
        return this;
    }

    public PeriodFormatterBuilder appendPrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        return appendPrefix(new SimpleAffix(str));
    }

    public PeriodFormatterBuilder appendPrefix(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        return appendPrefix(new PluralAffix(str, str2));
    }

    private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix periodFieldAffix) {
        if (periodFieldAffix == null) {
            throw new IllegalArgumentException();
        }
        if (this.iPrefix != null) {
            periodFieldAffix = new CompositeAffix(this.iPrefix, periodFieldAffix);
        }
        this.iPrefix = periodFieldAffix;
        return this;
    }

    public PeriodFormatterBuilder appendYears() {
        appendField(0);
        return this;
    }

    public PeriodFormatterBuilder appendMonths() {
        appendField(1);
        return this;
    }

    public PeriodFormatterBuilder appendWeeks() {
        appendField(2);
        return this;
    }

    public PeriodFormatterBuilder appendDays() {
        appendField(3);
        return this;
    }

    public PeriodFormatterBuilder appendHours() {
        appendField(4);
        return this;
    }

    public PeriodFormatterBuilder appendMinutes() {
        appendField(5);
        return this;
    }

    public PeriodFormatterBuilder appendSeconds() {
        appendField(6);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithMillis() {
        appendField(8);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(9);
        return this;
    }

    public PeriodFormatterBuilder appendMillis() {
        appendField(7);
        return this;
    }

    public PeriodFormatterBuilder appendMillis3Digit() {
        appendField(7, 3);
        return this;
    }

    private void appendField(int i) {
        appendField(i, this.iMinPrintedDigits);
    }

    private void appendField(int i, int i2) {
        FieldFormatter fieldFormatter = new FieldFormatter(i2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, i, this.iFieldFormatters, this.iPrefix, null);
        append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[i] = fieldFormatter;
        this.iPrefix = null;
    }

    public PeriodFormatterBuilder appendSuffix(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        return appendSuffix(new SimpleAffix(str));
    }

    public PeriodFormatterBuilder appendSuffix(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        return appendSuffix(new PluralAffix(str, str2));
    }

    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix periodFieldAffix) {
        Object obj;
        Object obj2 = null;
        if (this.iElementPairs.size() > 0) {
            obj2 = this.iElementPairs.get(this.iElementPairs.size() - 2);
            obj = this.iElementPairs.get(this.iElementPairs.size() - 1);
        } else {
            obj = null;
        }
        if (obj2 == null || obj == null || obj2 != obj || !(obj2 instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        clearPrefix();
        FieldFormatter fieldFormatter = new FieldFormatter((FieldFormatter) obj2, periodFieldAffix);
        this.iElementPairs.set(this.iElementPairs.size() - 2, fieldFormatter);
        this.iElementPairs.set(this.iElementPairs.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }

    public PeriodFormatterBuilder appendSeparator(String str) {
        return appendSeparator(str, str, null, true, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String str) {
        return appendSeparator(str, str, null, false, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String str) {
        return appendSeparator(str, str, null, true, false);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2) {
        return appendSeparator(str, str2, null, true, true);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr) {
        return appendSeparator(str, str2, strArr, true, true);
    }

    private PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr, boolean z, boolean z2) {
        List<Object> list;
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        clearPrefix();
        List<Object> list2 = this.iElementPairs;
        if (list2.size() == 0) {
            if (z2 && !z) {
                Separator separator = new Separator(str, str2, strArr, Literal.EMPTY, Literal.EMPTY, z, z2);
                append0(separator, separator);
            }
        } else {
            Separator separator2 = null;
            int size = list2.size();
            while (true) {
                int i = size - 1;
                if (i < 0) {
                    list = list2;
                    break;
                }
                if (list2.get(i) instanceof Separator) {
                    separator2 = (Separator) list2.get(i);
                    list = list2.subList(i + 1, list2.size());
                    break;
                }
                size = i - 1;
            }
            if (separator2 != null && list.size() == 0) {
                throw new IllegalStateException("Cannot have two adjacent separators");
            }
            Object[] createComposite = createComposite(list);
            list.clear();
            Separator separator3 = new Separator(str, str2, strArr, (PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1], z, z2);
            list.add(separator3);
            list.add(separator3);
        }
        return this;
    }

    private void clearPrefix() {
        if (this.iPrefix != null) {
            throw new IllegalStateException("Prefix not followed by field");
        }
        this.iPrefix = null;
    }

    private PeriodFormatterBuilder append0(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        this.iNotPrinter = (periodPrinter == null) | this.iNotPrinter;
        this.iNotParser |= periodParser == null;
        return this;
    }

    private static PeriodFormatter toFormatter(List<Object> list, boolean z, boolean z2) {
        if (z && z2) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        int size = list.size();
        if (size >= 2 && (list.get(0) instanceof Separator)) {
            Separator separator = (Separator) list.get(0);
            if (separator.iAfterParser == null && separator.iAfterPrinter == null) {
                PeriodFormatter formatter = toFormatter(list.subList(2, size), z, z2);
                Separator finish = separator.finish(formatter.getPrinter(), formatter.getParser());
                return new PeriodFormatter(finish, finish);
            }
        }
        Object[] createComposite = createComposite(list);
        if (z) {
            return new PeriodFormatter(null, (PeriodParser) createComposite[1]);
        }
        if (z2) {
            return new PeriodFormatter((PeriodPrinter) createComposite[0], null);
        }
        return new PeriodFormatter((PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1]);
    }

    private static Object[] createComposite(List<Object> list) {
        switch (list.size()) {
            case 0:
                return new Object[]{Literal.EMPTY, Literal.EMPTY};
            case 1:
                return new Object[]{list.get(0), list.get(1)};
            default:
                Composite composite = new Composite(list);
                return new Object[]{composite, composite};
        }
    }

    /* loaded from: classes.dex */
    class SimpleAffix implements PeriodFieldAffix {
        private final String iText;

        SimpleAffix(String str) {
            this.iText = str;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int calculatePrintedLength(int i) {
            return this.iText.length();
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iText);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(Writer writer, int i) {
            writer.write(this.iText);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int parse(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            return str.regionMatches(true, i, str2, 0, length) ? i + length : i ^ (-1);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int scan(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            int length2 = str.length();
            for (int i2 = i; i2 < length2; i2++) {
                if (!str.regionMatches(true, i2, str2, 0, length)) {
                    switch (str.charAt(i2)) {
                        case '+':
                        case ',':
                        case '-':
                        case '.':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case '/':
                        default:
                            return i ^ (-1);
                    }
                } else {
                    return i2;
                }
            }
            return i ^ (-1);
        }
    }

    /* loaded from: classes.dex */
    class PluralAffix implements PeriodFieldAffix {
        private final String iPluralText;
        private final String iSingularText;

        PluralAffix(String str, String str2) {
            this.iSingularText = str;
            this.iPluralText = str2;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int calculatePrintedLength(int i) {
            return (i == 1 ? this.iSingularText : this.iPluralText).length();
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(i == 1 ? this.iSingularText : this.iPluralText);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(Writer writer, int i) {
            writer.write(i == 1 ? this.iSingularText : this.iPluralText);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int parse(String str, int i) {
            String str2;
            String str3;
            String str4 = this.iPluralText;
            String str5 = this.iSingularText;
            if (str4.length() < str5.length()) {
                str2 = str4;
                str3 = str5;
            } else {
                str2 = str5;
                str3 = str4;
            }
            if (str.regionMatches(true, i, str3, 0, str3.length())) {
                return str3.length() + i;
            }
            if (str.regionMatches(true, i, str2, 0, str2.length())) {
                return str2.length() + i;
            }
            return i ^ (-1);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int scan(String str, int i) {
            String str2;
            String str3 = this.iPluralText;
            String str4 = this.iSingularText;
            if (str3.length() < str4.length()) {
                str2 = str4;
            } else {
                str2 = str3;
                str3 = str4;
            }
            int length = str2.length();
            int length2 = str3.length();
            int length3 = str.length();
            int i2 = i;
            while (i2 < length3) {
                if (!str.regionMatches(true, i2, str2, 0, length) && !str.regionMatches(true, i2, str3, 0, length2)) {
                    i2++;
                } else {
                    return i2;
                }
            }
            return i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class CompositeAffix implements PeriodFieldAffix {
        private final PeriodFieldAffix iLeft;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iLeft = periodFieldAffix;
            this.iRight = periodFieldAffix2;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int calculatePrintedLength(int i) {
            return this.iLeft.calculatePrintedLength(i) + this.iRight.calculatePrintedLength(i);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(StringBuffer stringBuffer, int i) {
            this.iLeft.printTo(stringBuffer, i);
            this.iRight.printTo(stringBuffer, i);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(Writer writer, int i) {
            this.iLeft.printTo(writer, i);
            this.iRight.printTo(writer, i);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int parse(String str, int i) {
            int parse = this.iLeft.parse(str, i);
            if (parse >= 0) {
                return this.iRight.parse(str, parse);
            }
            return parse;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int scan(String str, int i) {
            int scan = this.iLeft.scan(str, i);
            return scan >= 0 ? this.iRight.scan(str, scan) : i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FieldFormatter implements PeriodParser, PeriodPrinter {
        private final FieldFormatter[] iFieldFormatters;
        private final int iFieldType;
        private final int iMaxParsedDigits;
        private final int iMinPrintedDigits;
        private final PeriodFieldAffix iPrefix;
        private final int iPrintZeroSetting;
        private final boolean iRejectSignedValues;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int i, int i2, int i3, boolean z, int i4, FieldFormatter[] fieldFormatterArr, PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iMinPrintedDigits = i;
            this.iPrintZeroSetting = i2;
            this.iMaxParsedDigits = i3;
            this.iRejectSignedValues = z;
            this.iFieldType = i4;
            this.iFieldFormatters = fieldFormatterArr;
            this.iPrefix = periodFieldAffix;
            this.iSuffix = periodFieldAffix2;
        }

        FieldFormatter(FieldFormatter fieldFormatter, PeriodFieldAffix periodFieldAffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            this.iSuffix = fieldFormatter.iSuffix != null ? new CompositeAffix(fieldFormatter.iSuffix, periodFieldAffix) : periodFieldAffix;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            if (i <= 0) {
                return 0;
            }
            return (this.iPrintZeroSetting == 4 || getFieldValue(readablePeriod) != Long.MAX_VALUE) ? 1 : 0;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int max = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                max = (fieldValue < 0 ? Math.max(max, 5) : Math.max(max, 4)) + 1;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000 == 0) {
                    max -= 4;
                }
                fieldValue /= 1000;
            }
            int i = (int) fieldValue;
            if (this.iPrefix != null) {
                max += this.iPrefix.calculatePrintedLength(i);
            }
            if (this.iSuffix != null) {
                return max + this.iSuffix.calculatePrintedLength(i);
            }
            return max;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                if (this.iPrefix != null) {
                    this.iPrefix.printTo(stringBuffer, i);
                }
                int length = stringBuffer.length();
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.appendUnpaddedInteger(stringBuffer, i);
                } else {
                    FormatUtils.appendPaddedInteger(stringBuffer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    int abs = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || abs > 0) {
                        if (fieldValue < 0 && fieldValue > -1000) {
                            stringBuffer.insert(length, '-');
                        }
                        stringBuffer.append('.');
                        FormatUtils.appendPaddedInteger(stringBuffer, abs, 3);
                    }
                }
                if (this.iSuffix != null) {
                    this.iSuffix.printTo(stringBuffer, i);
                }
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                if (this.iPrefix != null) {
                    this.iPrefix.printTo(writer, i);
                }
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.writeUnpaddedInteger(writer, i);
                } else {
                    FormatUtils.writePaddedInteger(writer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    int abs = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || abs > 0) {
                        writer.write(46);
                        FormatUtils.writePaddedInteger(writer, abs, 3);
                    }
                }
                if (this.iSuffix != null) {
                    this.iSuffix.printTo(writer, i);
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
        
            return r1 ^ (-1);
         */
        @Override // org.joda.time.format.PeriodParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parseInto(org.joda.time.ReadWritablePeriod r10, java.lang.String r11, int r12, java.util.Locale r13) {
            /*
                Method dump skipped, instructions count: 339
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.PeriodFormatterBuilder.FieldFormatter.parseInto(org.joda.time.ReadWritablePeriod, java.lang.String, int, java.util.Locale):int");
        }

        private int parseInt(String str, int i, int i2) {
            int i3;
            boolean z = false;
            if (i2 >= 10) {
                return Integer.parseInt(str.substring(i, i + i2));
            }
            if (i2 <= 0) {
                return 0;
            }
            int i4 = i + 1;
            char charAt = str.charAt(i);
            int i5 = i2 - 1;
            if (charAt == '-') {
                i5--;
                if (i5 < 0) {
                    return 0;
                }
                z = true;
                i3 = i4 + 1;
                charAt = str.charAt(i4);
            } else {
                i3 = i4;
            }
            int i6 = charAt - '0';
            int i7 = i3;
            while (true) {
                int i8 = i5 - 1;
                if (i5 <= 0) {
                    break;
                }
                i6 = (((i6 << 1) + (i6 << 3)) + str.charAt(i7)) - 48;
                i7++;
                i5 = i8;
            }
            return z ? -i6 : i6;
        }

        long getFieldValue(ReadablePeriod readablePeriod) {
            PeriodType periodType;
            long j;
            if (this.iPrintZeroSetting == 4) {
                periodType = null;
            } else {
                periodType = readablePeriod.getPeriodType();
            }
            if (periodType != null && !isSupported(periodType, this.iFieldType)) {
                return Long.MAX_VALUE;
            }
            switch (this.iFieldType) {
                case 0:
                    j = readablePeriod.get(DurationFieldType.years());
                    break;
                case 1:
                    j = readablePeriod.get(DurationFieldType.months());
                    break;
                case 2:
                    j = readablePeriod.get(DurationFieldType.weeks());
                    break;
                case 3:
                    j = readablePeriod.get(DurationFieldType.days());
                    break;
                case 4:
                    j = readablePeriod.get(DurationFieldType.hours());
                    break;
                case 5:
                    j = readablePeriod.get(DurationFieldType.minutes());
                    break;
                case 6:
                    j = readablePeriod.get(DurationFieldType.seconds());
                    break;
                case 7:
                    j = readablePeriod.get(DurationFieldType.millis());
                    break;
                case 8:
                case 9:
                    j = readablePeriod.get(DurationFieldType.millis()) + (readablePeriod.get(DurationFieldType.seconds()) * 1000);
                    break;
                default:
                    return Long.MAX_VALUE;
            }
            if (j == 0) {
                switch (this.iPrintZeroSetting) {
                    case 1:
                        if (!isZero(readablePeriod) || this.iFieldFormatters[this.iFieldType] != this) {
                            return Long.MAX_VALUE;
                        }
                        for (int min = Math.min(this.iFieldType, 8) - 1; min >= 0 && min <= 9; min--) {
                            if (isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                return Long.MAX_VALUE;
                            }
                        }
                        break;
                    case 2:
                        if (!isZero(readablePeriod) || this.iFieldFormatters[this.iFieldType] != this) {
                            return Long.MAX_VALUE;
                        }
                        for (int i = this.iFieldType + 1; i <= 9; i++) {
                            if (isSupported(periodType, i) && this.iFieldFormatters[i] != null) {
                                return Long.MAX_VALUE;
                            }
                        }
                        break;
                        break;
                    case 5:
                        return Long.MAX_VALUE;
                }
            }
            return j;
        }

        boolean isZero(ReadablePeriod readablePeriod) {
            int size = readablePeriod.size();
            for (int i = 0; i < size; i++) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isSupported(PeriodType periodType, int i) {
            switch (i) {
                case 0:
                    return periodType.isSupported(DurationFieldType.years());
                case 1:
                    return periodType.isSupported(DurationFieldType.months());
                case 2:
                    return periodType.isSupported(DurationFieldType.weeks());
                case 3:
                    return periodType.isSupported(DurationFieldType.days());
                case 4:
                    return periodType.isSupported(DurationFieldType.hours());
                case 5:
                    return periodType.isSupported(DurationFieldType.minutes());
                case 6:
                    return periodType.isSupported(DurationFieldType.seconds());
                case 7:
                    return periodType.isSupported(DurationFieldType.millis());
                case 8:
                case 9:
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                default:
                    return false;
            }
        }

        void setFieldValue(ReadWritablePeriod readWritablePeriod, int i, int i2) {
            switch (i) {
                case 0:
                    readWritablePeriod.setYears(i2);
                    return;
                case 1:
                    readWritablePeriod.setMonths(i2);
                    return;
                case 2:
                    readWritablePeriod.setWeeks(i2);
                    return;
                case 3:
                    readWritablePeriod.setDays(i2);
                    return;
                case 4:
                    readWritablePeriod.setHours(i2);
                    return;
                case 5:
                    readWritablePeriod.setMinutes(i2);
                    return;
                case 6:
                    readWritablePeriod.setSeconds(i2);
                    return;
                case 7:
                    readWritablePeriod.setMillis(i2);
                    return;
                default:
                    return;
            }
        }

        int getFieldType() {
            return this.iFieldType;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Literal implements PeriodParser, PeriodPrinter {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        Literal(String str) {
            this.iText = str;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return 0;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return this.iText.length();
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            stringBuffer.append(this.iText);
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) {
            writer.write(this.iText);
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            return str.regionMatches(true, i, this.iText, 0, this.iText.length()) ? this.iText.length() + i : i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Separator implements PeriodParser, PeriodPrinter {
        private volatile PeriodParser iAfterParser;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private final PeriodPrinter iBeforePrinter;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final String iText;
        private final boolean iUseAfter;
        private final boolean iUseBefore;

        Separator(String str, String str2, String[] strArr, PeriodPrinter periodPrinter, PeriodParser periodParser, boolean z, boolean z2) {
            this.iText = str;
            this.iFinalText = str2;
            if ((str2 == null || str.equals(str2)) && (strArr == null || strArr.length == 0)) {
                this.iParsedForms = new String[]{str};
            } else {
                TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        } else {
                            treeSet.add(strArr[length]);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.iParsedForms = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.iBeforePrinter = periodPrinter;
            this.iBeforeParser = periodParser;
            this.iUseBefore = z;
            this.iUseAfter = z2;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int countFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, i, locale);
            if (countFieldsToPrint < i) {
                return countFieldsToPrint + this.iAfterPrinter.countFieldsToPrint(readablePeriod, i, locale);
            }
            return countFieldsToPrint;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int i;
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            int calculatePrintedLength = periodPrinter.calculatePrintedLength(readablePeriod, locale) + periodPrinter2.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            i = (countFieldsToPrint > 1 ? this.iText : this.iFinalText).length() + calculatePrintedLength;
                        } else {
                            i = calculatePrintedLength;
                        }
                        return i;
                    }
                    return calculatePrintedLength + this.iText.length();
                }
                return calculatePrintedLength;
            }
            if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                return calculatePrintedLength + this.iText.length();
            }
            return calculatePrintedLength;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            stringBuffer.append(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        stringBuffer.append(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                stringBuffer.append(this.iText);
            }
            periodPrinter2.printTo(stringBuffer, readablePeriod, locale);
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(writer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            writer.write(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        writer.write(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                writer.write(this.iText);
            }
            periodPrinter2.printTo(writer, readablePeriod, locale);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
        @Override // org.joda.time.format.PeriodParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parseInto(org.joda.time.ReadWritablePeriod r11, java.lang.String r12, int r13, java.util.Locale r14) {
            /*
                r10 = this;
                r1 = 1
                r4 = 0
                org.joda.time.format.PeriodParser r0 = r10.iBeforeParser
                int r2 = r0.parseInto(r11, r12, r13, r14)
                if (r2 >= 0) goto Lb
            La:
                return r2
            Lb:
                r6 = -1
                if (r2 <= r13) goto L55
                java.lang.String[] r8 = r10.iParsedForms
                int r9 = r8.length
                r7 = r4
            L12:
                if (r7 >= r9) goto L55
                r3 = r8[r7]
                if (r3 == 0) goto L29
                int r0 = r3.length()
                if (r0 == 0) goto L29
                int r5 = r3.length()
                r0 = r12
                boolean r0 = r0.regionMatches(r1, r2, r3, r4, r5)
                if (r0 == 0) goto L3b
            L29:
                if (r3 != 0) goto L36
            L2b:
                int r2 = r2 + r4
            L2c:
                org.joda.time.format.PeriodParser r0 = r10.iAfterParser
                int r0 = r0.parseInto(r11, r12, r2, r14)
                if (r0 >= 0) goto L3f
                r2 = r0
                goto La
            L36:
                int r4 = r3.length()
                goto L2b
            L3b:
                int r0 = r7 + 1
                r7 = r0
                goto L12
            L3f:
                if (r1 == 0) goto L48
                if (r0 != r2) goto L48
                if (r4 <= 0) goto L48
                r2 = r2 ^ (-1)
                goto La
            L48:
                if (r0 <= r2) goto L53
                if (r1 != 0) goto L53
                boolean r1 = r10.iUseBefore
                if (r1 != 0) goto L53
                r2 = r2 ^ (-1)
                goto La
            L53:
                r2 = r0
                goto La
            L55:
                r1 = r4
                r4 = r6
                goto L2c
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.PeriodFormatterBuilder.Separator.parseInto(org.joda.time.ReadWritablePeriod, java.lang.String, int, java.util.Locale):int");
        }

        Separator finish(PeriodPrinter periodPrinter, PeriodParser periodParser) {
            this.iAfterPrinter = periodPrinter;
            this.iAfterParser = periodParser;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Composite implements PeriodParser, PeriodPrinter {
        private final PeriodParser[] iParsers;
        private final PeriodPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.size() <= 0) {
                this.iPrinters = null;
            } else {
                this.iPrinters = (PeriodPrinter[]) arrayList.toArray(new PeriodPrinter[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.iParsers = null;
            } else {
                this.iParsers = (PeriodParser[]) arrayList2.toArray(new PeriodParser[arrayList2.size()]);
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int i2 = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (i2 < i) {
                length--;
                if (length < 0) {
                    break;
                }
                i2 += periodPrinterArr[length].countFieldsToPrint(readablePeriod, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, locale);
            }
            return i2;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int i = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (true) {
                length--;
                if (length >= 0) {
                    i += periodPrinterArr[length].calculatePrintedLength(readablePeriod, locale);
                } else {
                    return i;
                }
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter periodPrinter : this.iPrinters) {
                periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter periodPrinter : this.iPrinters) {
                periodPrinter.printTo(writer, readablePeriod, locale);
            }
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            PeriodParser[] periodParserArr = this.iParsers;
            if (periodParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = periodParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = periodParserArr[i2].parseInto(readWritablePeriod, str, i, locale);
            }
            return i;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof PeriodPrinter) {
                    if (obj instanceof Composite) {
                        addArrayToList(list2, ((Composite) obj).iPrinters);
                    } else {
                        list2.add(obj);
                    }
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof PeriodParser) {
                    if (obj2 instanceof Composite) {
                        addArrayToList(list3, ((Composite) obj2).iParsers);
                    } else {
                        list3.add(obj2);
                    }
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object obj : objArr) {
                    list.add(obj);
                }
            }
        }
    }
}
