package com.samsung.android.sdk.richnotification;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction;
import defpackage.awi;
import defpackage.awp;
import defpackage.awr;
import defpackage.awu;
import defpackage.aww;
import defpackage.awx;
import defpackage.awy;
import defpackage.awz;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: classes.dex */
final class GsonHelper {
    private static final String TAG = GsonHelper.class.getSimpleName();
    private static awi gson;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        awp a = new awp().b().a();
        a.a(Enum.class, (Object) new EnumSerializer(null));
        a.a((Type) Uri.class, (Object) new StringItemSerializer(0 == true ? 1 : 0));
        a.a((Type) Bundle.class, (Object) new BundleSerializer(0 == true ? 1 : 0));
        a.a((Type) SrnImageAsset.class, (Object) new SrnImageAsset.ImageAssetSerializer());
        a.a((Type) SrnAction.CallbackIntent.class, (Object) new SrnAction.CallbackIntentSerializer());
        a.a((Type) SrnRichNotification.Templates.class, (Object) new SrnRichNotification.TemplatesSerializer());
        a.a((Type) Map.class, (Object) new MapSerializer(0 == true ? 1 : 0));
        try {
            Field declaredField = SrnRemoteInputAction.class.getDeclaredField("SERIALIZER");
            declaredField.setAccessible(true);
            a.a((Type) SrnRemoteInputAction.class, declaredField.get(null));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        gson = a.c();
    }

    private GsonHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static awi getGson() {
        return gson;
    }

    /* loaded from: classes.dex */
    class StringItemSerializer<T> implements awz<T> {
        private StringItemSerializer() {
        }

        /* synthetic */ StringItemSerializer(StringItemSerializer stringItemSerializer) {
            this();
        }

        @Override // defpackage.awz
        public awu serialize(T t, Type type, awy awyVar) {
            return new awx(t.toString());
        }
    }

    /* loaded from: classes.dex */
    class EnumSerializer implements awz<Enum<?>> {
        private EnumSerializer() {
        }

        /* synthetic */ EnumSerializer(EnumSerializer enumSerializer) {
            this();
        }

        @Override // defpackage.awz
        public awu serialize(Enum<?> r3, Type type, awy awyVar) {
            return new awx((Number) Integer.valueOf(r3.ordinal()));
        }
    }

    /* loaded from: classes.dex */
    class MapSerializer implements awz<Map<?, ?>> {
        private MapSerializer() {
        }

        /* synthetic */ MapSerializer(MapSerializer mapSerializer) {
            this();
        }

        @Override // defpackage.awz
        public awu serialize(Map<?, ?> map, Type type, awy awyVar) {
            if (map.size() == 0) {
                return null;
            }
            return awyVar.a(map);
        }
    }

    /* loaded from: classes.dex */
    class BundleSerializer implements awz<Bundle> {
        private BundleSerializer() {
        }

        /* synthetic */ BundleSerializer(BundleSerializer bundleSerializer) {
            this();
        }

        @Override // defpackage.awz
        public awu serialize(Bundle bundle, Type type, awy awyVar) {
            awr awrVar = new awr();
            for (String str : bundle.keySet()) {
                aww awwVar = new aww();
                awwVar.a("key", str);
                awwVar.a("value", bundle.get(str).toString());
                awrVar.a(awwVar);
            }
            return awrVar;
        }
    }
}
