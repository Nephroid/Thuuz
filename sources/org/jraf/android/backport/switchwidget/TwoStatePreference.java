package org.jraf.android.backport.switchwidget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import defpackage.cdq;

/* loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {
    boolean a;
    private CharSequence b;
    private CharSequence c;
    private boolean d;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.preference.Preference
    protected void onClick() {
        super.onClick();
        boolean z = !a();
        if (callChangeListener(Boolean.valueOf(z))) {
            a(z);
        }
    }

    public void a(boolean z) {
        if (this.a != z) {
            this.a = z;
            persistBoolean(z);
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public boolean a() {
        return this.a;
    }

    @Override // android.preference.Preference
    public boolean shouldDisableDependents() {
        boolean z;
        if (this.d) {
            z = this.a;
        } else {
            z = !this.a;
        }
        return z || super.shouldDisableDependents();
    }

    public void c(CharSequence charSequence) {
        this.b = charSequence;
        if (a()) {
            notifyChanged();
        }
    }

    public void d(CharSequence charSequence) {
        this.c = charSequence;
        if (!a()) {
            notifyChanged();
        }
    }

    public void b(boolean z) {
        this.d = z;
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        a(z ? getPersistedBoolean(this.a) : ((Boolean) obj).booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view) {
        boolean z;
        CharSequence summary;
        TextView textView = (TextView) view.findViewById(R.id.summary);
        if (textView != null) {
            boolean z2 = true;
            if (this.a && this.b != null) {
                textView.setText(this.b);
                z2 = false;
            } else if (!this.a && this.c != null) {
                textView.setText(this.c);
                z2 = false;
            }
            if (!z2 || (summary = getSummary()) == null) {
                z = z2;
            } else {
                textView.setText(summary);
                z = false;
            }
            int i = z ? 8 : 0;
            if (i != textView.getVisibility()) {
                textView.setVisibility(i);
            }
        }
    }

    @Override // android.preference.Preference
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (!isPersistent()) {
            SavedState savedState = new SavedState(onSaveInstanceState);
            savedState.a = a();
            return savedState;
        }
        return onSaveInstanceState;
    }

    @Override // android.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        a(savedState.a);
    }

    /* loaded from: classes.dex */
    public class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new cdq();
        boolean a;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
