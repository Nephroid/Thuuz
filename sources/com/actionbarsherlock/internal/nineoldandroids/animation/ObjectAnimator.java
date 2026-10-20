package com.actionbarsherlock.internal.nineoldandroids.animation;

/* loaded from: classes.dex */
public final class ObjectAnimator extends ValueAnimator {
    private static final boolean DBG = false;
    private String mPropertyName;
    private Object mTarget;

    public void setPropertyName(String str) {
        if (this.mValues != null) {
            PropertyValuesHolder propertyValuesHolder = this.mValues[0];
            String propertyName = propertyValuesHolder.getPropertyName();
            propertyValuesHolder.setPropertyName(str);
            this.mValuesMap.remove(propertyName);
            this.mValuesMap.put(str, propertyValuesHolder);
        }
        this.mPropertyName = str;
        this.mInitialized = false;
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    public ObjectAnimator() {
    }

    private ObjectAnimator(Object obj, String str) {
        this.mTarget = obj;
        setPropertyName(str);
    }

    public static ObjectAnimator ofInt(Object obj, String str, int... iArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.setIntValues(iArr);
        return objectAnimator;
    }

    public static ObjectAnimator ofFloat(Object obj, String str, float... fArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.setFloatValues(fArr);
        return objectAnimator;
    }

    public static ObjectAnimator ofObject(Object obj, String str, TypeEvaluator typeEvaluator, Object... objArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.setObjectValues(objArr);
        objectAnimator.setEvaluator(typeEvaluator);
        return objectAnimator;
    }

    public static ObjectAnimator ofPropertyValuesHolder(Object obj, PropertyValuesHolder... propertyValuesHolderArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.mTarget = obj;
        objectAnimator.setValues(propertyValuesHolderArr);
        return objectAnimator;
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public void setIntValues(int... iArr) {
        if (this.mValues == null || this.mValues.length == 0) {
            setValues(PropertyValuesHolder.ofInt(this.mPropertyName, iArr));
        } else {
            super.setIntValues(iArr);
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public void setFloatValues(float... fArr) {
        if (this.mValues == null || this.mValues.length == 0) {
            setValues(PropertyValuesHolder.ofFloat(this.mPropertyName, fArr));
        } else {
            super.setFloatValues(fArr);
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public void setObjectValues(Object... objArr) {
        if (this.mValues == null || this.mValues.length == 0) {
            setValues(PropertyValuesHolder.ofObject(this.mPropertyName, (TypeEvaluator) null, objArr));
        } else {
            super.setObjectValues(objArr);
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator, com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    public void start() {
        super.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public void initAnimation() {
        if (!this.mInitialized) {
            int length = this.mValues.length;
            for (int i = 0; i < length; i++) {
                this.mValues[i].setupSetterAndGetter(this.mTarget);
            }
            super.initAnimation();
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator, com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    public ObjectAnimator setDuration(long j) {
        super.setDuration(j);
        return this;
    }

    public Object getTarget() {
        return this.mTarget;
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    public void setTarget(Object obj) {
        if (this.mTarget != obj) {
            Object obj2 = this.mTarget;
            this.mTarget = obj;
            if (obj2 == null || obj == null || obj2.getClass() != obj.getClass()) {
                this.mInitialized = false;
            }
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    public void setupStartValues() {
        initAnimation();
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].setupStartValue(this.mTarget);
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    public void setupEndValues() {
        initAnimation();
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].setupEndValue(this.mTarget);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public void animateValue(float f) {
        super.animateValue(f);
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].setAnimatedValue(this.mTarget);
        }
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator, com.actionbarsherlock.internal.nineoldandroids.animation.Animator
    /* renamed from: clone */
    public ObjectAnimator mo2clone() {
        return (ObjectAnimator) super.mo2clone();
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator
    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.mTarget;
        if (this.mValues != null) {
            for (int i = 0; i < this.mValues.length; i++) {
                str = str + "\n    " + this.mValues[i].toString();
            }
        }
        return str;
    }
}
