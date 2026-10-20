package org.joda.convert;

import com.facebook.internal.ServerProtocol;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
enum JDKStringConverter implements StringConverter<Object> {
    STRING(String.class) { // from class: org.joda.convert.JDKStringConverter.1
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return str;
        }
    },
    CHAR_SEQUENCE(CharSequence.class) { // from class: org.joda.convert.JDKStringConverter.2
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return str;
        }
    },
    STRING_BUFFER(StringBuffer.class) { // from class: org.joda.convert.JDKStringConverter.3
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new StringBuffer(str);
        }
    },
    STRING_BUILDER(StringBuilder.class) { // from class: org.joda.convert.JDKStringConverter.4
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new StringBuilder(str);
        }
    },
    LONG(Long.class) { // from class: org.joda.convert.JDKStringConverter.5
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Long(str);
        }
    },
    INTEGER(Integer.class) { // from class: org.joda.convert.JDKStringConverter.6
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Integer(str);
        }
    },
    SHORT(Short.class) { // from class: org.joda.convert.JDKStringConverter.7
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Short(str);
        }
    },
    BYTE(Byte.class) { // from class: org.joda.convert.JDKStringConverter.8
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Byte(str);
        }
    },
    CHARACTER(Character.class) { // from class: org.joda.convert.JDKStringConverter.9
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            if (str.length() != 1) {
                throw new IllegalArgumentException("Character value must be a string length 1");
            }
            return new Character(str.charAt(0));
        }
    },
    BOOLEAN(Boolean.class) { // from class: org.joda.convert.JDKStringConverter.10
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(str)) {
                return Boolean.FALSE;
            }
            throw new IllegalArgumentException("Boolean value must be 'true' or 'false', case insensitive");
        }
    },
    DOUBLE(Double.class) { // from class: org.joda.convert.JDKStringConverter.11
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Double(str);
        }
    },
    FLOAT(Float.class) { // from class: org.joda.convert.JDKStringConverter.12
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new Float(str);
        }
    },
    BIG_INTEGER(BigInteger.class) { // from class: org.joda.convert.JDKStringConverter.13
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new BigInteger(str);
        }
    },
    BIG_DECIMAL(BigDecimal.class) { // from class: org.joda.convert.JDKStringConverter.14
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new BigDecimal(str);
        }
    },
    ATOMIC_LONG(AtomicLong.class) { // from class: org.joda.convert.JDKStringConverter.15
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new AtomicLong(Long.parseLong(str));
        }
    },
    ATOMIC_INTEGER(AtomicInteger.class) { // from class: org.joda.convert.JDKStringConverter.16
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new AtomicInteger(Integer.parseInt(str));
        }
    },
    ATOMIC_BOOLEAN(AtomicBoolean.class) { // from class: org.joda.convert.JDKStringConverter.17
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str)) {
                return new AtomicBoolean(true);
            }
            if ("false".equalsIgnoreCase(str)) {
                return new AtomicBoolean(false);
            }
            throw new IllegalArgumentException("Boolean value must be 'true' or 'false', case insensitive");
        }
    },
    LOCALE(Locale.class) { // from class: org.joda.convert.JDKStringConverter.18
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            String[] split = str.split("_", 3);
            switch (split.length) {
                case 1:
                    return new Locale(split[0]);
                case 2:
                    return new Locale(split[0], split[1]);
                case 3:
                    return new Locale(split[0], split[1], split[2]);
                default:
                    throw new IllegalArgumentException("Unable to parse Locale: " + str);
            }
        }
    },
    CLASS(Class.class) { // from class: org.joda.convert.JDKStringConverter.19
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            return ((Class) obj).getName();
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            try {
                return getClass().getClassLoader().loadClass(str);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to create class: " + str, e);
            }
        }
    },
    PACKAGE(Package.class) { // from class: org.joda.convert.JDKStringConverter.20
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            return ((Package) obj).getName();
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return Package.getPackage(str);
        }
    },
    CURRENCY(Currency.class) { // from class: org.joda.convert.JDKStringConverter.21
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return Currency.getInstance(str);
        }
    },
    TIME_ZONE(TimeZone.class) { // from class: org.joda.convert.JDKStringConverter.22
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            return ((TimeZone) obj).getID();
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return TimeZone.getTimeZone(str);
        }
    },
    UUID(UUID.class) { // from class: org.joda.convert.JDKStringConverter.23
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return UUID.fromString(str);
        }
    },
    URL(URL.class) { // from class: org.joda.convert.JDKStringConverter.24
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            try {
                return new URL(str);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    },
    URI(URI.class) { // from class: org.joda.convert.JDKStringConverter.25
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return URI.create(str);
        }
    },
    INET_ADDRESS(InetAddress.class) { // from class: org.joda.convert.JDKStringConverter.26
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            return ((InetAddress) obj).getHostAddress();
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            try {
                return InetAddress.getByName(str);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
    },
    FILE(File.class) { // from class: org.joda.convert.JDKStringConverter.27
        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            return new File(str);
        }
    },
    DATE(Date.class) { // from class: org.joda.convert.JDKStringConverter.28
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(obj);
            return format.substring(0, 26) + ":" + format.substring(26);
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            if (str.length() != 29) {
                throw new IllegalArgumentException("Unable to parse date: " + str);
            }
            try {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parseObject(str.substring(0, 26) + str.substring(27));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    },
    CALENDAR(Calendar.class) { // from class: org.joda.convert.JDKStringConverter.29
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            if (!(obj instanceof GregorianCalendar)) {
                throw new RuntimeException("Unable to convert calendar as it is not a GregorianCalendar");
            }
            GregorianCalendar gregorianCalendar = (GregorianCalendar) obj;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            simpleDateFormat.setCalendar(gregorianCalendar);
            String format = simpleDateFormat.format(gregorianCalendar.getTime());
            return format.substring(0, 26) + ":" + format.substring(26) + "[" + gregorianCalendar.getTimeZone().getID() + "]";
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class<?> cls, String str) {
            if (str.length() < 31 || str.charAt(26) != ':' || str.charAt(29) != '[' || str.charAt(str.length() - 1) != ']') {
                throw new IllegalArgumentException("Unable to parse date: " + str);
            }
            TimeZone timeZone = TimeZone.getTimeZone(str.substring(30, str.length() - 1));
            String str2 = str.substring(0, 26) + str.substring(27, 29);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
            gregorianCalendar.setTimeInMillis(0L);
            simpleDateFormat.setCalendar(gregorianCalendar);
            try {
                simpleDateFormat.parseObject(str2);
                return simpleDateFormat.getCalendar();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    },
    ENUM(Enum.class) { // from class: org.joda.convert.JDKStringConverter.30
        @Override // org.joda.convert.JDKStringConverter, org.joda.convert.ToStringConverter
        public String convertToString(Object obj) {
            return ((Enum) obj).name();
        }

        @Override // org.joda.convert.FromStringConverter
        public Object convertFromString(Class cls, String str) {
            return Enum.valueOf(cls, str);
        }
    };

    private Class<?> type;

    JDKStringConverter(Class cls) {
        this.type = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<?> getType() {
        return this.type;
    }

    @Override // org.joda.convert.ToStringConverter
    public String convertToString(Object obj) {
        return obj.toString();
    }
}
