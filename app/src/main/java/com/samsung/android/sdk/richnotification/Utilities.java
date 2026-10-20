package com.samsung.android.sdk.richnotification;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
final class Utilities {
    private static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Bitmap$CompressFormat = null;
    public static final String DB_KEY_EXPIRY_TIME = "expiry_time";
    public static final String DB_KEY_HASH = "hash";
    public static final String DB_KEY_IMAGE_NAME = "name";
    public static final String DB_KEY_IMAGE_TYPE = "image_type";
    public static final String DB_KEY_PACKAGE_NAME = "package_name";
    public static final String IMAGE_TYPE_JPEG = "jpg";
    public static final String IMAGE_TYPE_PNG = "png";
    public static final String STATIC_QUERY_PARAMETER_KEY = "updated";
    private static final String TAG = "Utilities";
    private static final Uri CONTENT_URI = Uri.parse("content://com.samsung.accessory.goproviders.sanotificationservice.rich_notification");
    public static final String DB_KEY_IMAGE = "image";
    private static final Uri IMAGE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, DB_KEY_IMAGE);
    private static final Uri STATIC_IMAGE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, "static_image");

    static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Bitmap$CompressFormat() {
        int[] iArr = $SWITCH_TABLE$android$graphics$Bitmap$CompressFormat;
        if (iArr == null) {
            iArr = new int[Bitmap.CompressFormat.values().length];
            try {
                iArr[Bitmap.CompressFormat.JPEG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Bitmap.CompressFormat.PNG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Bitmap.CompressFormat.WEBP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$android$graphics$Bitmap$CompressFormat = iArr;
        }
        return iArr;
    }

    private Utilities() {
    }

    public static Uri toUri(Context context, String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) {
        Uri uri;
        byte[] convertResizedBinary = convertResizedBinary(bitmap, compressFormat);
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(DB_KEY_IMAGE_NAME, str);
            uri = STATIC_IMAGE_CONTENT_URI;
        } else {
            contentValues.put(DB_KEY_HASH, makeHash(convertResizedBinary));
            uri = IMAGE_CONTENT_URI;
        }
        contentValues.put(DB_KEY_PACKAGE_NAME, context.getPackageName());
        contentValues.put(DB_KEY_IMAGE, convertResizedBinary);
        switch ($SWITCH_TABLE$android$graphics$Bitmap$CompressFormat()[compressFormat.ordinal()]) {
            case 1:
                contentValues.put(DB_KEY_IMAGE_TYPE, IMAGE_TYPE_JPEG);
                break;
            default:
                contentValues.put(DB_KEY_IMAGE_TYPE, IMAGE_TYPE_PNG);
                break;
        }
        return context.getContentResolver().insert(uri, contentValues);
    }

    public static String makeHash(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException();
        }
    }

    private static byte[] convertResizedBinary(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        Bitmap createScaledBitmap;
        if (bitmap.getWidth() > 360 || bitmap.getHeight() > 480) {
            float min = Math.min(360.0f / bitmap.getWidth(), 480.0f / bitmap.getHeight());
            createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * min), (int) (min * bitmap.getHeight()), false);
        } else {
            createScaledBitmap = bitmap;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            createScaledBitmap.compress(compressFormat, 90, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (createScaledBitmap != bitmap) {
                createScaledBitmap.recycle();
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return Bitmap.createBitmap(((BitmapDrawable) drawable).getBitmap());
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}
