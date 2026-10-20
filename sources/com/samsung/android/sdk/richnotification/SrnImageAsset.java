package com.samsung.android.sdk.richnotification;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import defpackage.awu;
import defpackage.aww;
import defpackage.awy;
import defpackage.awz;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class SrnImageAsset {
    private static final String SERIALIZED_NAME_TITLE = "title";
    private static final String SERIALIZED_NAME_UPDATE = "update";
    private static final String SERIALIZED_NAME_URI = "uri";
    private static final String TAG = SrnImageAsset.class.getSimpleName();
    private ImageConverter mImageConverter;
    private final String mName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ImageConverter {
        private final boolean mCache;
        private Bitmap.CompressFormat mCompressFormat;
        private final Context mContext;
        private final Bitmap mImage;
        private boolean mIsUpdated;
        private final String mName;

        private ImageConverter(Context context, String str, Bitmap bitmap, boolean z) {
            this.mCompressFormat = Bitmap.CompressFormat.PNG;
            this.mContext = context;
            this.mName = str;
            this.mImage = bitmap;
            this.mCache = z;
        }

        /* synthetic */ ImageConverter(Context context, String str, Bitmap bitmap, boolean z, ImageConverter imageConverter) {
            this(context, str, bitmap, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String toUriString() {
            if (this.mImage.isRecycled()) {
                throw new IllegalStateException("Can't send recycled bitmap.");
            }
            try {
                Uri uri = Utilities.toUri(this.mContext, this.mName, this.mImage, this.mCompressFormat, this.mCache);
                if (uri != null) {
                    this.mIsUpdated = uri.getBooleanQueryParameter(Utilities.STATIC_QUERY_PARAMETER_KEY, false);
                    return uri.buildUpon().clearQuery().build().toString();
                }
            } catch (Exception e) {
                Log.e(SrnImageAsset.TAG, e.getMessage(), e);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isUpdated() {
            return this.mIsUpdated;
        }
    }

    /* loaded from: classes.dex */
    class ImageAssetSerializer implements awz<SrnImageAsset> {
        @Override // defpackage.awz
        public awu serialize(SrnImageAsset srnImageAsset, Type type, awy awyVar) {
            aww awwVar = new aww();
            if (srnImageAsset.mImageConverter != null) {
                awwVar.a(SrnImageAsset.SERIALIZED_NAME_URI, srnImageAsset.mImageConverter.toUriString());
                awwVar.a(SrnImageAsset.SERIALIZED_NAME_UPDATE, Integer.valueOf(srnImageAsset.mImageConverter.isUpdated() ? 1 : 0));
            }
            awwVar.a(SrnImageAsset.SERIALIZED_NAME_TITLE, srnImageAsset.mName);
            return awwVar;
        }
    }

    public SrnImageAsset(Context context, String str, Bitmap bitmap) {
        this(context, str, bitmap, false);
    }

    private SrnImageAsset(Context context, String str, Bitmap bitmap, boolean z) {
        ImageConverter imageConverter = null;
        this.mName = str;
        if (bitmap != null) {
            this.mImageConverter = new ImageConverter(context, str, bitmap, z, imageConverter);
        } else {
            this.mImageConverter = null;
        }
    }

    public void setCompressFormat(Bitmap.CompressFormat compressFormat) {
        if (this.mImageConverter != null) {
            this.mImageConverter.mCompressFormat = compressFormat;
        }
    }
}
