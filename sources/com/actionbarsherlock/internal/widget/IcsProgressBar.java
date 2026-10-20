package com.actionbarsherlock.internal.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RemoteViews;

@RemoteViews.RemoteView
/* loaded from: classes.dex */
public class IcsProgressBar extends View {
    private static final int ANIMATION_RESOLUTION = 200;
    private static final boolean IS_HONEYCOMB;
    private static final int MAX_LEVEL = 10000;
    private static final int[] ProgressBar;
    private static final int ProgressBar_animationResolution = 14;
    private static final int ProgressBar_indeterminate = 5;
    private static final int ProgressBar_indeterminateBehavior = 10;
    private static final int ProgressBar_indeterminateDrawable = 7;
    private static final int ProgressBar_indeterminateDuration = 9;
    private static final int ProgressBar_indeterminateOnly = 6;
    private static final int ProgressBar_interpolator = 13;
    private static final int ProgressBar_max = 2;
    private static final int ProgressBar_maxHeight = 1;
    private static final int ProgressBar_maxWidth = 0;
    private static final int ProgressBar_minHeight = 12;
    private static final int ProgressBar_minWidth = 11;
    private static final int ProgressBar_progress = 3;
    private static final int ProgressBar_progressDrawable = 8;
    private static final int ProgressBar_secondaryProgress = 4;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    private AccessibilityEventSender mAccessibilityEventSender;
    private AccessibilityManager mAccessibilityManager;
    private AlphaAnimation mAnimation;
    private int mAnimationResolution;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private int mDuration;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private int mIndeterminateRealLeft;
    private int mIndeterminateRealTop;
    private Interpolator mInterpolator;
    private long mLastDrawTime;
    private int mMax;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private int mProgress;
    private Drawable mProgressDrawable;
    private RefreshProgressRunnable mRefreshProgressRunnable;
    Bitmap mSampleTile;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;

    static {
        IS_HONEYCOMB = Build.VERSION.SDK_INT >= 11;
        ProgressBar = new int[]{R.attr.maxWidth, R.attr.maxHeight, R.attr.max, R.attr.progress, R.attr.secondaryProgress, R.attr.indeterminate, R.attr.indeterminateOnly, R.attr.indeterminateDrawable, R.attr.progressDrawable, R.attr.indeterminateDuration, R.attr.indeterminateBehavior, R.attr.minWidth, R.attr.minHeight, R.attr.interpolator, R.attr.animationResolution};
    }

    public IcsProgressBar(Context context) {
        this(context, null);
    }

    public IcsProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.progressBarStyle);
    }

    public IcsProgressBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public IcsProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mUiThreadId = Thread.currentThread().getId();
        initProgressBar();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ProgressBar, i, i2);
        this.mNoInvalidate = true;
        Drawable drawable = obtainStyledAttributes.getDrawable(8);
        if (drawable != null) {
            setProgressDrawable(tileify(drawable, false));
        }
        this.mDuration = obtainStyledAttributes.getInt(9, this.mDuration);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(11, this.mMinWidth);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(0, this.mMaxWidth);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(12, this.mMinHeight);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(1, this.mMaxHeight);
        this.mBehavior = obtainStyledAttributes.getInt(10, this.mBehavior);
        int resourceId = obtainStyledAttributes.getResourceId(13, R.anim.linear_interpolator);
        if (resourceId > 0) {
            setInterpolator(context, resourceId);
        }
        setMax(obtainStyledAttributes.getInt(2, this.mMax));
        setProgress(obtainStyledAttributes.getInt(3, this.mProgress));
        setSecondaryProgress(obtainStyledAttributes.getInt(4, this.mSecondaryProgress));
        Drawable drawable2 = obtainStyledAttributes.getDrawable(7);
        if (drawable2 != null) {
            setIndeterminateDrawable(tileifyIndeterminate(drawable2));
        }
        this.mOnlyIndeterminate = obtainStyledAttributes.getBoolean(6, this.mOnlyIndeterminate);
        this.mNoInvalidate = false;
        setIndeterminate(this.mOnlyIndeterminate || obtainStyledAttributes.getBoolean(5, this.mIndeterminate));
        this.mAnimationResolution = obtainStyledAttributes.getInteger(ProgressBar_animationResolution, 200);
        obtainStyledAttributes.recycle();
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    private Drawable tileify(Drawable drawable, boolean z) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                drawableArr[i] = tileify(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
            }
            return layerDrawable2;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (this.mSampleTile == null) {
            this.mSampleTile = bitmap;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
        shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
        return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
    }

    Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            AnimationDrawable animationDrawable2 = new AnimationDrawable();
            animationDrawable2.setOneShot(animationDrawable.isOneShot());
            for (int i = 0; i < numberOfFrames; i++) {
                Drawable tileify = tileify(animationDrawable.getFrame(i), true);
                tileify.setLevel(MAX_LEVEL);
                animationDrawable2.addFrame(tileify, animationDrawable.getDuration(i));
            }
            animationDrawable2.setLevel(MAX_LEVEL);
            return animationDrawable2;
        }
        return drawable;
    }

    private void initProgressBar() {
        this.mMax = 100;
        this.mProgress = 0;
        this.mSecondaryProgress = 0;
        this.mIndeterminate = false;
        this.mOnlyIndeterminate = false;
        this.mDuration = 4000;
        this.mBehavior = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized boolean isIndeterminate() {
        return this.mIndeterminate;
    }

    public synchronized void setIndeterminate(boolean z) {
        if ((!this.mOnlyIndeterminate || !this.mIndeterminate) && z != this.mIndeterminate) {
            this.mIndeterminate = z;
            if (z) {
                this.mCurrentDrawable = this.mIndeterminateDrawable;
                startAnimation();
            } else {
                this.mCurrentDrawable = this.mProgressDrawable;
                stopAnimation();
            }
        }
    }

    public Drawable getIndeterminateDrawable() {
        return this.mIndeterminateDrawable;
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.mIndeterminateDrawable = drawable;
        if (this.mIndeterminate) {
            this.mCurrentDrawable = drawable;
            postInvalidate();
        }
    }

    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public void setProgressDrawable(Drawable drawable) {
        boolean z;
        if (this.mProgressDrawable == null || drawable == this.mProgressDrawable) {
            z = false;
        } else {
            this.mProgressDrawable.setCallback(null);
            z = true;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            int minimumHeight = drawable.getMinimumHeight();
            if (this.mMaxHeight < minimumHeight) {
                this.mMaxHeight = minimumHeight;
                requestLayout();
            }
        }
        this.mProgressDrawable = drawable;
        if (!this.mIndeterminate) {
            this.mCurrentDrawable = drawable;
            postInvalidate();
        }
        if (z) {
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(R.id.progress, this.mProgress, false, false);
            doRefreshProgress(R.id.secondaryProgress, this.mSecondaryProgress, false, false);
        }
    }

    Drawable getCurrentDrawable() {
        return this.mCurrentDrawable;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mProgressDrawable || drawable == this.mIndeterminateDrawable || super.verifyDrawable(drawable);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.jumpToCurrentState();
        }
        if (this.mIndeterminateDrawable != null) {
            this.mIndeterminateDrawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void postInvalidate() {
        if (!this.mNoInvalidate) {
            super.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class RefreshProgressRunnable implements Runnable {
        private boolean mFromUser;
        private int mId;
        private int mProgress;

        RefreshProgressRunnable(int i, int i2, boolean z) {
            this.mId = i;
            this.mProgress = i2;
            this.mFromUser = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            IcsProgressBar.this.doRefreshProgress(this.mId, this.mProgress, this.mFromUser, true);
            IcsProgressBar.this.mRefreshProgressRunnable = this;
        }

        public void setup(int i, int i2, boolean z) {
            this.mId = i;
            this.mProgress = i2;
            this.mFromUser = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void doRefreshProgress(int i, int i2, boolean z, boolean z2) {
        float f = this.mMax > 0 ? i2 / this.mMax : 0.0f;
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != null) {
            Drawable drawable2 = null;
            if (drawable instanceof LayerDrawable) {
                drawable2 = ((LayerDrawable) drawable).findDrawableByLayerId(i);
            }
            int i3 = (int) (10000.0f * f);
            if (drawable2 != null) {
                drawable = drawable2;
            }
            drawable.setLevel(i3);
        } else {
            invalidate();
        }
        if (z2 && i == 16908301) {
            onProgressRefresh(f, z);
        }
    }

    void onProgressRefresh(float f, boolean z) {
        if (this.mAccessibilityManager.isEnabled()) {
            scheduleAccessibilityEventSender();
        }
    }

    private synchronized void refreshProgress(int i, int i2, boolean z) {
        RefreshProgressRunnable refreshProgressRunnable;
        if (this.mUiThreadId == Thread.currentThread().getId()) {
            doRefreshProgress(i, i2, z, true);
        } else {
            if (this.mRefreshProgressRunnable != null) {
                refreshProgressRunnable = this.mRefreshProgressRunnable;
                this.mRefreshProgressRunnable = null;
                refreshProgressRunnable.setup(i, i2, z);
            } else {
                refreshProgressRunnable = new RefreshProgressRunnable(i, i2, z);
            }
            post(refreshProgressRunnable);
        }
    }

    public synchronized void setProgress(int i) {
        setProgress(i, false);
    }

    synchronized void setProgress(int i, boolean z) {
        if (!this.mIndeterminate) {
            int i2 = i < 0 ? 0 : i;
            if (i2 > this.mMax) {
                i2 = this.mMax;
            }
            if (i2 != this.mProgress) {
                this.mProgress = i2;
                refreshProgress(R.id.progress, this.mProgress, z);
            }
        }
    }

    public synchronized void setSecondaryProgress(int i) {
        synchronized (this) {
            if (!this.mIndeterminate) {
                int i2 = i >= 0 ? i : 0;
                if (i2 > this.mMax) {
                    i2 = this.mMax;
                }
                if (i2 != this.mSecondaryProgress) {
                    this.mSecondaryProgress = i2;
                    refreshProgress(R.id.secondaryProgress, this.mSecondaryProgress, false);
                }
            }
        }
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getProgress() {
        return this.mIndeterminate ? 0 : this.mProgress;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getSecondaryProgress() {
        return this.mIndeterminate ? 0 : this.mSecondaryProgress;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMax() {
        return this.mMax;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i != this.mMax) {
            this.mMax = i;
            postInvalidate();
            if (this.mProgress > i) {
                this.mProgress = i;
            }
            refreshProgress(R.id.progress, this.mProgress, false);
        }
    }

    public final synchronized void incrementProgressBy(int i) {
        setProgress(this.mProgress + i);
    }

    public final synchronized void incrementSecondaryProgressBy(int i) {
        setSecondaryProgress(this.mSecondaryProgress + i);
    }

    void startAnimation() {
        if (getVisibility() == 0) {
            if (this.mIndeterminateDrawable instanceof Animatable) {
                this.mShouldStartAnimationDrawable = true;
                this.mAnimation = null;
            } else {
                if (this.mInterpolator == null) {
                    this.mInterpolator = new LinearInterpolator();
                }
                this.mTransformation = new Transformation();
                this.mAnimation = new AlphaAnimation(0.0f, 1.0f);
                this.mAnimation.setRepeatMode(this.mBehavior);
                this.mAnimation.setRepeatCount(-1);
                this.mAnimation.setDuration(this.mDuration);
                this.mAnimation.setInterpolator(this.mInterpolator);
                this.mAnimation.setStartTime(-1L);
            }
            postInvalidate();
        }
    }

    void stopAnimation() {
        this.mAnimation = null;
        this.mTransformation = null;
        if (this.mIndeterminateDrawable instanceof Animatable) {
            ((Animatable) this.mIndeterminateDrawable).stop();
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    public void setInterpolator(Context context, int i) {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (this.mIndeterminate) {
                if (i == 8 || i == 4) {
                    stopAnimation();
                } else {
                    startAnimation();
                }
            }
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.mIndeterminate) {
            if (i == 8 || i == 4) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (!this.mInDrawing) {
            if (verifyDrawable(drawable)) {
                Rect bounds = drawable.getBounds();
                int scrollX = getScrollX() + getPaddingLeft();
                int scrollY = getScrollY() + getPaddingTop();
                invalidate(bounds.left + scrollX, bounds.top + scrollY, scrollX + bounds.right, bounds.bottom + scrollY);
                return;
            }
            super.invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        updateDrawableBounds(i, i2);
    }

    private void updateDrawableBounds(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int paddingRight = (i - getPaddingRight()) - getPaddingLeft();
        int paddingBottom = (i2 - getPaddingBottom()) - getPaddingTop();
        if (this.mIndeterminateDrawable != null) {
            if (this.mOnlyIndeterminate && !(this.mIndeterminateDrawable instanceof AnimationDrawable)) {
                float intrinsicWidth = this.mIndeterminateDrawable.getIntrinsicWidth() / this.mIndeterminateDrawable.getIntrinsicHeight();
                float f = i / i2;
                if (intrinsicWidth != f) {
                    if (f > intrinsicWidth) {
                        int i7 = (int) (intrinsicWidth * i2);
                        i5 = (i - i7) / 2;
                        i3 = i7 + i5;
                        i4 = paddingBottom;
                        i6 = 0;
                    } else {
                        int i8 = (int) ((1.0f / intrinsicWidth) * i);
                        int i9 = (i2 - i8) / 2;
                        i3 = paddingRight;
                        i4 = i8 + i9;
                        i6 = i9;
                        i5 = 0;
                    }
                    this.mIndeterminateDrawable.setBounds(0, 0, i3 - i5, i4 - i6);
                    this.mIndeterminateRealLeft = i5;
                    this.mIndeterminateRealTop = i6;
                }
            }
            i5 = 0;
            i3 = paddingRight;
            i4 = paddingBottom;
            i6 = 0;
            this.mIndeterminateDrawable.setBounds(0, 0, i3 - i5, i4 - i6);
            this.mIndeterminateRealLeft = i5;
            this.mIndeterminateRealTop = i6;
        } else {
            i3 = paddingRight;
            i4 = paddingBottom;
        }
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.setBounds(0, 0, i3, i4);
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != 0) {
            canvas.save();
            canvas.translate(getPaddingLeft() + this.mIndeterminateRealLeft, getPaddingTop() + this.mIndeterminateRealTop);
            long drawingTime = getDrawingTime();
            if (this.mAnimation != null) {
                this.mAnimation.getTransformation(drawingTime, this.mTransformation);
                float alpha = this.mTransformation.getAlpha();
                try {
                    this.mInDrawing = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.mInDrawing = false;
                    if (SystemClock.uptimeMillis() - this.mLastDrawTime >= this.mAnimationResolution) {
                        this.mLastDrawTime = SystemClock.uptimeMillis();
                        postInvalidateDelayed(this.mAnimationResolution);
                    }
                } catch (Throwable th) {
                    this.mInDrawing = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restore();
            if (this.mShouldStartAnimationDrawable && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    @Override // android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        synchronized (this) {
            Drawable drawable = this.mCurrentDrawable;
            if (drawable != null) {
                i3 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, drawable.getIntrinsicWidth()));
                i4 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, drawable.getIntrinsicHeight()));
            } else {
                i3 = 0;
            }
            updateDrawableState();
            int paddingLeft = i3 + getPaddingLeft() + getPaddingRight();
            int paddingTop = i4 + getPaddingTop() + getPaddingBottom();
            if (IS_HONEYCOMB) {
                setMeasuredDimension(View.resolveSizeAndState(paddingLeft, i, 0), View.resolveSizeAndState(paddingTop, i2, 0));
            } else {
                setMeasuredDimension(View.resolveSize(paddingLeft, i), View.resolveSize(paddingTop, i2));
            }
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    private void updateDrawableState() {
        int[] drawableState = getDrawableState();
        if (this.mProgressDrawable != null && this.mProgressDrawable.isStateful()) {
            this.mProgressDrawable.setState(drawableState);
        }
        if (this.mIndeterminateDrawable != null && this.mIndeterminateDrawable.isStateful()) {
            this.mIndeterminateDrawable.setState(drawableState);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.actionbarsherlock.internal.widget.IcsProgressBar.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int progress;
        int secondaryProgress;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.secondaryProgress = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.secondaryProgress);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.progress = this.mProgress;
        savedState.secondaryProgress = this.mSecondaryProgress;
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.progress);
        setSecondaryProgress(savedState.secondaryProgress);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIndeterminate) {
            startAnimation();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.mIndeterminate) {
            stopAnimation();
        }
        if (this.mRefreshProgressRunnable != null) {
            removeCallbacks(this.mRefreshProgressRunnable);
        }
        if (this.mAccessibilityEventSender != null) {
            removeCallbacks(this.mAccessibilityEventSender);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setItemCount(this.mMax);
        accessibilityEvent.setCurrentItemIndex(this.mProgress);
    }

    private void scheduleAccessibilityEventSender() {
        if (this.mAccessibilityEventSender == null) {
            this.mAccessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(this.mAccessibilityEventSender);
        }
        postDelayed(this.mAccessibilityEventSender, 200L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AccessibilityEventSender implements Runnable {
        private AccessibilityEventSender() {
        }

        @Override // java.lang.Runnable
        public void run() {
            IcsProgressBar.this.sendAccessibilityEvent(4);
        }
    }
}
