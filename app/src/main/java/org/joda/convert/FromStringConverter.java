package org.joda.convert;

/* loaded from: classes.dex */
public interface FromStringConverter<T> {
    T convertFromString(Class<? extends T> cls, String str);
}
