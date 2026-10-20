package com.yozio.android.invites;

import android.database.Cursor;
import com.samsung.android.sdk.richnotification.Utilities;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ContactsHelper {
    private static HashMap<String, String> _emailTypes;
    private static HashMap<String, String> _phoneTypes;

    public static HashMap<String, String> getContact(Cursor cursor) {
        String string = cursor.getString(4);
        String string2 = cursor.getString(2);
        String string3 = cursor.getString(5);
        String string4 = cursor.getString(6);
        String string5 = cursor.getString(7);
        String string6 = cursor.getString(8);
        String string7 = cursor.getString(9);
        String string8 = cursor.getString(10);
        HashMap<String, String> hashMap = new HashMap<>();
        String str = string.equalsIgnoreCase("vnd.android.cursor.item/email_v2") ? "email" : "phone";
        hashMap.put("type", str);
        hashMap.put(Utilities.DB_KEY_IMAGE_NAME, string2);
        if (!str.equalsIgnoreCase("email")) {
            string3 = string6;
        }
        hashMap.put("value", string3);
        hashMap.put("label", str.equalsIgnoreCase("email") ? getEmailType(string4, string5) : getPhoneType(string7, string8));
        return hashMap;
    }

    public static String getEmailType(String str, String str2) {
        if (_emailTypes == null) {
            _emailTypes = new HashMap<>();
            _emailTypes.put(Integer.toString(1), "Home");
            _emailTypes.put(Integer.toString(2), "Work");
            _emailTypes.put(Integer.toString(3), "Other");
            _emailTypes.put(Integer.toString(4), "Mobile");
        }
        String str3 = _emailTypes.get(str);
        if (str3 != null) {
            return str3;
        }
        if (str2 == null) {
            str2 = "Other";
        }
        return str2;
    }

    public static String getPhoneType(String str, String str2) {
        if (_phoneTypes == null) {
            _phoneTypes = new HashMap<>();
            _phoneTypes.put(Integer.toString(1), "Home");
            _phoneTypes.put(Integer.toString(2), "Mobile");
            _phoneTypes.put(Integer.toString(3), "Work");
            _phoneTypes.put(Integer.toString(4), "Work Fax");
            _phoneTypes.put(Integer.toString(5), "Home Fax");
            _phoneTypes.put(Integer.toString(6), "Pager");
            _phoneTypes.put(Integer.toString(7), "Other");
            _phoneTypes.put(Integer.toString(8), "Callback");
            _phoneTypes.put(Integer.toString(9), "Car");
            _phoneTypes.put(Integer.toString(10), "Company Main");
            _phoneTypes.put(Integer.toString(11), "ISDN");
            _phoneTypes.put(Integer.toString(12), "Main");
            _phoneTypes.put(Integer.toString(13), "Other Fax");
            _phoneTypes.put(Integer.toString(14), "Radio");
            _phoneTypes.put(Integer.toString(15), "Telex");
            _phoneTypes.put(Integer.toString(16), "TTY TDD");
            _phoneTypes.put(Integer.toString(17), "Work Mobile");
            _phoneTypes.put(Integer.toString(18), "Pager");
            _phoneTypes.put(Integer.toString(19), "Assistant");
            _phoneTypes.put(Integer.toString(20), "MMS");
        }
        String str3 = _phoneTypes.get(str);
        if (str3 != null) {
            return str3;
        }
        if (str2 == null) {
            str2 = "Other";
        }
        return str2;
    }
}
