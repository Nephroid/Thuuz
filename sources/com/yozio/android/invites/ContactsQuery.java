package com.yozio.android.invites;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.provider.ContactsContract;
import com.yozio.android.helpers.SystemInfo;
import com.yozio.android.helpers.Utils;

/* loaded from: classes.dex */
public interface ContactsQuery {
    public static final int DISPLAY_NAME = 2;
    public static final int EMAIL_ADDRESS = 5;
    public static final int EMAIL_LABEL = 7;
    public static final int EMAIL_TYPE = 6;
    public static final int ID = 0;
    public static final int LOOKUP_KEY = 1;
    public static final int MIME_TYPE = 4;
    public static final int PHONE_LABEL = 10;
    public static final int PHONE_NUMBER = 8;
    public static final int PHONE_TYPE = 9;
    public static final int PHOTO_THUMBNAIL_DATA = 3;

    @SuppressLint({"InlinedApi"})
    public static final String[] PROJECTION;
    public static final int QUERY_ID = 1;

    @SuppressLint({"InlinedApi"})
    public static final String SELECTION;
    public static final int SORT_KEY = 11;

    @SuppressLint({"InlinedApi"})
    public static final String SORT_ORDER = "display_name_alt";
    public static final Uri CONTENT_URI = ContactsContract.Data.CONTENT_URI;
    public static final Uri FILTER_URI = ContactsContract.Data.CONTENT_URI;

    static {
        String str;
        if (SystemInfo.getInstance().canSendSMS()) {
            str = String.valueOf(Utils.hasHoneycomb() ? "display_name" : "display_name") + "<>'' AND in_visible_group=1 AND ((mimetype='vnd.android.cursor.item/phone_v2' AND data1<>'') OR (mimetype='vnd.android.cursor.item/email_v2' AND data1<>''))";
        } else {
            str = String.valueOf(Utils.hasHoneycomb() ? "display_name" : "display_name") + "<>'' AND in_visible_group=1 AND ((mimetype='vnd.android.cursor.item/email_v2' AND data1<>''))";
        }
        SELECTION = str;
        String[] strArr = new String[12];
        strArr[0] = "_id";
        strArr[1] = "lookup";
        strArr[2] = Utils.hasHoneycomb() ? "display_name" : "display_name";
        strArr[3] = Utils.hasHoneycomb() ? "photo_thumb_uri" : "_id";
        strArr[4] = "mimetype";
        strArr[5] = "data1";
        strArr[6] = "data2";
        strArr[7] = "data3";
        strArr[8] = "data1";
        strArr[9] = "data2";
        strArr[10] = "data3";
        strArr[11] = SORT_ORDER;
        PROJECTION = strArr;
    }
}
