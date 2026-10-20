package com.actionbarsherlock.internal.nineoldandroids.animation;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class PropertyValuesHolder implements Cloneable {
    private Object mAnimatedValue;
    private TypeEvaluator mEvaluator;
    private Method mGetter;
    KeyframeSet mKeyframeSet;
    final ReentrantReadWriteLock mPropertyMapLock;
    String mPropertyName;
    Method mSetter;
    final Object[] mTmpValueArray;
    Class mValueType;
    private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
    private static final TypeEvaluator sFloatEvaluator = new FloatEvaluator();
    private static Class[] FLOAT_VARIANTS = {Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
    private static Class[] INTEGER_VARIANTS = {Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
    private static Class[] DOUBLE_VARIANTS = {Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
    private static final HashMap<Class, HashMap<String, Method>> sSetterPropertyMap = new HashMap<>();
    private static final HashMap<Class, HashMap<String, Method>> sGetterPropertyMap = new HashMap<>();

    private PropertyValuesHolder(String str) {
        this.mSetter = null;
        this.mGetter = null;
        this.mKeyframeSet = null;
        this.mPropertyMapLock = new ReentrantReadWriteLock();
        this.mTmpValueArray = new Object[1];
        this.mPropertyName = str;
    }

    public static PropertyValuesHolder ofInt(String str, int... iArr) {
        return new IntPropertyValuesHolder(str, iArr);
    }

    public static PropertyValuesHolder ofFloat(String str, float... fArr) {
        return new FloatPropertyValuesHolder(str, fArr);
    }

    public static PropertyValuesHolder ofObject(String str, TypeEvaluator typeEvaluator, Object... objArr) {
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(str);
        propertyValuesHolder.setObjectValues(objArr);
        propertyValuesHolder.setEvaluator(typeEvaluator);
        return propertyValuesHolder;
    }

    public static PropertyValuesHolder ofKeyframe(String str, Keyframe... keyframeArr) {
        KeyframeSet ofKeyframe = KeyframeSet.ofKeyframe(keyframeArr);
        if (ofKeyframe instanceof IntKeyframeSet) {
            return new IntPropertyValuesHolder(str, (IntKeyframeSet) ofKeyframe);
        }
        if (ofKeyframe instanceof FloatKeyframeSet) {
            return new FloatPropertyValuesHolder(str, (FloatKeyframeSet) ofKeyframe);
        }
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(str);
        propertyValuesHolder.mKeyframeSet = ofKeyframe;
        propertyValuesHolder.mValueType = keyframeArr[0].getType();
        return propertyValuesHolder;
    }

    public void setIntValues(int... iArr) {
        this.mValueType = Integer.TYPE;
        this.mKeyframeSet = KeyframeSet.ofInt(iArr);
    }

    public void setFloatValues(float... fArr) {
        this.mValueType = Float.TYPE;
        this.mKeyframeSet = KeyframeSet.ofFloat(fArr);
    }

    public void setKeyframes(Keyframe... keyframeArr) {
        int length = keyframeArr.length;
        Keyframe[] keyframeArr2 = new Keyframe[Math.max(length, 2)];
        this.mValueType = keyframeArr[0].getType();
        for (int i = 0; i < length; i++) {
            keyframeArr2[i] = keyframeArr[i];
        }
        this.mKeyframeSet = new KeyframeSet(keyframeArr2);
    }

    public void setObjectValues(Object... objArr) {
        this.mValueType = objArr[0].getClass();
        this.mKeyframeSet = KeyframeSet.ofObject(objArr);
    }

    private Method getPropertyFunction(Class cls, String str, Class cls2) {
        Class<?>[] clsArr;
        Method method = null;
        String methodName = getMethodName(str, this.mPropertyName);
        if (cls2 == null) {
            try {
                return cls.getMethod(methodName, null);
            } catch (NoSuchMethodException e) {
                Log.e("PropertyValuesHolder", cls.getSimpleName() + " - Couldn't find no-arg method for property " + this.mPropertyName + ": " + e);
                return null;
            }
        }
        Class<?>[] clsArr2 = new Class[1];
        if (this.mValueType.equals(Float.class)) {
            clsArr = FLOAT_VARIANTS;
        } else if (this.mValueType.equals(Integer.class)) {
            clsArr = INTEGER_VARIANTS;
        } else if (this.mValueType.equals(Double.class)) {
            clsArr = DOUBLE_VARIANTS;
        } else {
            clsArr = new Class[]{this.mValueType};
        }
        for (Class<?> cls3 : clsArr) {
            clsArr2[0] = cls3;
            try {
                method = cls.getMethod(methodName, clsArr2);
                this.mValueType = cls3;
                return method;
            } catch (NoSuchMethodException e2) {
            }
        }
        Log.e("PropertyValuesHolder", "Couldn't find " + str + "ter property " + this.mPropertyName + " for " + cls.getSimpleName() + " with value type " + this.mValueType);
        return method;
    }

    private Method setupSetterOrGetter(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        Method method = null;
        try {
            this.mPropertyMapLock.writeLock().lock();
            HashMap<String, Method> hashMap2 = hashMap.get(cls);
            if (hashMap2 != null) {
                method = hashMap2.get(this.mPropertyName);
            }
            if (method == null) {
                method = getPropertyFunction(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.mPropertyName, method);
            }
            Method method2 = method;
            return method2;
        } finally {
            this.mPropertyMapLock.writeLock().unlock();
        }
    }

    void setupSetter(Class cls) {
        this.mSetter = setupSetterOrGetter(cls, sSetterPropertyMap, "set", this.mValueType);
    }

    private void setupGetter(Class cls) {
        this.mGetter = setupSetterOrGetter(cls, sGetterPropertyMap, "get", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupSetterAndGetter(Object obj) {
        Class<?> cls = obj.getClass();
        if (this.mSetter == null) {
            setupSetter(cls);
        }
        Iterator<Keyframe> it = this.mKeyframeSet.mKeyframes.iterator();
        while (it.hasNext()) {
            Keyframe next = it.next();
            if (!next.hasValue()) {
                if (this.mGetter == null) {
                    setupGetter(cls);
                }
                try {
                    next.setValue(this.mGetter.invoke(obj, new Object[0]));
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }
    }

    private void setupValue(Object obj, Keyframe keyframe) {
        try {
            if (this.mGetter == null) {
                setupGetter(obj.getClass());
            }
            keyframe.setValue(this.mGetter.invoke(obj, new Object[0]));
        } catch (IllegalAccessException e) {
            Log.e("PropertyValuesHolder", e.toString());
        } catch (InvocationTargetException e2) {
            Log.e("PropertyValuesHolder", e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupStartValue(Object obj) {
        setupValue(obj, this.mKeyframeSet.mKeyframes.get(0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupEndValue(Object obj) {
        setupValue(obj, this.mKeyframeSet.mKeyframes.get(this.mKeyframeSet.mKeyframes.size() - 1));
    }

    @Override // 
    /* renamed from: clone */
    public PropertyValuesHolder mo6clone() {
        try {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) super.clone();
            propertyValuesHolder.mPropertyName = this.mPropertyName;
            propertyValuesHolder.mKeyframeSet = this.mKeyframeSet.mo4clone();
            propertyValuesHolder.mEvaluator = this.mEvaluator;
            return propertyValuesHolder;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAnimatedValue(Object obj) {
        if (this.mSetter != null) {
            try {
                this.mTmpValueArray[0] = getAnimatedValue();
                this.mSetter.invoke(obj, this.mTmpValueArray);
            } catch (IllegalAccessException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (InvocationTargetException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init() {
        TypeEvaluator typeEvaluator;
        if (this.mEvaluator == null) {
            if (this.mValueType == Integer.class) {
                typeEvaluator = sIntEvaluator;
            } else {
                typeEvaluator = this.mValueType == Float.class ? sFloatEvaluator : null;
            }
            this.mEvaluator = typeEvaluator;
        }
        if (this.mEvaluator != null) {
            this.mKeyframeSet.setEvaluator(this.mEvaluator);
        }
    }

    public void setEvaluator(TypeEvaluator typeEvaluator) {
        this.mEvaluator = typeEvaluator;
        this.mKeyframeSet.setEvaluator(typeEvaluator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateValue(float f) {
        this.mAnimatedValue = this.mKeyframeSet.getValue(f);
    }

    public void setPropertyName(String str) {
        this.mPropertyName = str;
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getAnimatedValue() {
        return this.mAnimatedValue;
    }

    public String toString() {
        return this.mPropertyName + ": " + this.mKeyframeSet.toString();
    }

    static String getMethodName(String str, String str2) {
        if (str2 != null && str2.length() != 0) {
            return str + Character.toUpperCase(str2.charAt(0)) + str2.substring(1);
        }
        return str;
    }

    /* loaded from: classes.dex */
    class IntPropertyValuesHolder extends PropertyValuesHolder {
        int mIntAnimatedValue;
        IntKeyframeSet mIntKeyframeSet;

        public IntPropertyValuesHolder(String str, IntKeyframeSet intKeyframeSet) {
            super(str);
            this.mValueType = Integer.TYPE;
            this.mKeyframeSet = intKeyframeSet;
            this.mIntKeyframeSet = (IntKeyframeSet) this.mKeyframeSet;
        }

        public IntPropertyValuesHolder(String str, int... iArr) {
            super(str);
            setIntValues(iArr);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        public void setIntValues(int... iArr) {
            super.setIntValues(iArr);
            this.mIntKeyframeSet = (IntKeyframeSet) this.mKeyframeSet;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void calculateValue(float f) {
            this.mIntAnimatedValue = this.mIntKeyframeSet.getIntValue(f);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        Object getAnimatedValue() {
            return Integer.valueOf(this.mIntAnimatedValue);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        /* renamed from: clone */
        public IntPropertyValuesHolder mo6clone() {
            IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder) super.mo6clone();
            intPropertyValuesHolder.mIntKeyframeSet = (IntKeyframeSet) intPropertyValuesHolder.mKeyframeSet;
            return intPropertyValuesHolder;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void setAnimatedValue(Object obj) {
            if (this.mSetter != null) {
                try {
                    this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
                    this.mSetter.invoke(obj, this.mTmpValueArray);
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void setupSetter(Class cls) {
            super.setupSetter(cls);
        }
    }

    /* loaded from: classes.dex */
    class FloatPropertyValuesHolder extends PropertyValuesHolder {
        float mFloatAnimatedValue;
        FloatKeyframeSet mFloatKeyframeSet;

        public FloatPropertyValuesHolder(String str, FloatKeyframeSet floatKeyframeSet) {
            super(str);
            this.mValueType = Float.TYPE;
            this.mKeyframeSet = floatKeyframeSet;
            this.mFloatKeyframeSet = (FloatKeyframeSet) this.mKeyframeSet;
        }

        public FloatPropertyValuesHolder(String str, float... fArr) {
            super(str);
            setFloatValues(fArr);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        public void setFloatValues(float... fArr) {
            super.setFloatValues(fArr);
            this.mFloatKeyframeSet = (FloatKeyframeSet) this.mKeyframeSet;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void calculateValue(float f) {
            this.mFloatAnimatedValue = this.mFloatKeyframeSet.getFloatValue(f);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        Object getAnimatedValue() {
            return Float.valueOf(this.mFloatAnimatedValue);
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public FloatPropertyValuesHolder mo6clone() {
            FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder) super.mo6clone();
            floatPropertyValuesHolder.mFloatKeyframeSet = (FloatKeyframeSet) floatPropertyValuesHolder.mKeyframeSet;
            return floatPropertyValuesHolder;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void setAnimatedValue(Object obj) {
            if (this.mSetter != null) {
                try {
                    this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
                    this.mSetter.invoke(obj, this.mTmpValueArray);
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.PropertyValuesHolder
        void setupSetter(Class cls) {
            super.setupSetter(cls);
        }
    }
}
