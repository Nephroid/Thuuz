package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.CustomProperty;

/* loaded from: classes.dex */
public class ml implements Parcelable.Creator<CustomProperty> {
    public static void a(CustomProperty customProperty, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, customProperty.a);
        ke.a(parcel, 2, (Parcelable) customProperty.b, i, false);
        ke.a(parcel, 3, customProperty.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CustomProperty createFromParcel(Parcel parcel) {
        String o;
        CustomPropertyKey customPropertyKey;
        int i;
        String str = null;
        int b = a.b(parcel);
        int i2 = 0;
        CustomPropertyKey customPropertyKey2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    String str2 = str;
                    customPropertyKey = customPropertyKey2;
                    i = a.g(parcel, a);
                    o = str2;
                    break;
                case 2:
                    CustomPropertyKey customPropertyKey3 = (CustomPropertyKey) a.a(parcel, a, CustomPropertyKey.CREATOR);
                    i = i2;
                    o = str;
                    customPropertyKey = customPropertyKey3;
                    break;
                case 3:
                    o = a.o(parcel, a);
                    customPropertyKey = customPropertyKey2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    o = str;
                    customPropertyKey = customPropertyKey2;
                    i = i2;
                    break;
            }
            i2 = i;
            customPropertyKey2 = customPropertyKey;
            str = o;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CustomProperty(i2, customPropertyKey2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CustomProperty[] newArray(int i) {
        return new CustomProperty[i];
    }
}
