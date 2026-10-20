package com.actionbarsherlock.internal;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.actionbarsherlock.R;

/* loaded from: classes.dex */
public final class ResourcesCompat {
    private ResourcesCompat() {
    }

    public static boolean getResources_getBoolean(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 13) {
            return context.getResources().getBoolean(i);
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = displayMetrics.widthPixels / displayMetrics.density;
        float f2 = displayMetrics.heightPixels / displayMetrics.density;
        if (f < f2) {
            f2 = f;
        }
        if (i == R.bool.abs__action_bar_embed_tabs) {
            return f >= 480.0f;
        }
        if (i == R.bool.abs__split_action_bar_is_narrow) {
            return f < 480.0f;
        }
        if (i == R.bool.abs__action_bar_expanded_action_views_exclusive) {
            return f2 < 600.0f;
        }
        if (i == R.bool.abs__config_allowActionMenuItemTextWithIcon) {
            return f >= 480.0f;
        }
        throw new IllegalArgumentException("Unknown boolean resource ID " + i);
    }

    public static int getResources_getInteger(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 13) {
            return context.getResources().getInteger(i);
        }
        float f = r0.widthPixels / context.getResources().getDisplayMetrics().density;
        if (i != R.integer.abs__max_action_buttons) {
            throw new IllegalArgumentException("Unknown integer resource ID " + i);
        }
        if (f >= 600.0f) {
            return 5;
        }
        if (f >= 500.0f) {
            return 4;
        }
        if (f >= 360.0f) {
            return 3;
        }
        return 2;
    }
}
