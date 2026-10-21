package com.thuuz.gtv.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.thuuz.gtv.App;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bms;
import defpackage.bqu;
import defpackage.cbj;
import defpackage.cbl;
import defpackage.cbm;
import defpackage.cbn;
import defpackage.cbo;
import defpackage.cbp;
import defpackage.cbq;
import defpackage.cbr;
import defpackage.cbs;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class HorizColumnScroll extends HorizontalScrollView {
    private static LinearGradient l;
    private static int m;
    private static LinearGradient n;
    private static int o;
    private GestureDetector a;
    private LinearLayout b;
    private int c;
    private int d;
    private int e;
    private bqu f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private cbs p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private Object u;
    private int v;
    private ObjectAnimator w;
    private float x;
    private cbr y;
    private String z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizColumnScroll(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cbl cblVar = null;
        this.c = 1;
        this.e = 0;
        this.i = -500;
        this.j = 570923;
        this.p = new cbs(this, cblVar);
        this.x = 1.0f;
        this.y = new cbr(this, cblVar);
        this.z = getClass().getSimpleName() + ":thuuz";
        a(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizColumnScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cbl cblVar = null;
        this.c = 1;
        this.e = 0;
        this.i = -500;
        this.j = 570923;
        this.p = new cbs(this, cblVar);
        this.x = 1.0f;
        this.y = new cbr(this, cblVar);
        this.z = getClass().getSimpleName() + ":thuuz";
        a(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizColumnScroll(Context context) {
        super(context);
        cbl cblVar = null;
        this.c = 1;
        this.e = 0;
        this.i = -500;
        this.j = 570923;
        this.p = new cbs(this, cblVar);
        this.x = 1.0f;
        this.y = new cbr(this, cblVar);
        this.z = getClass().getSimpleName() + ":thuuz";
        a(context);
    }

    public boolean a() {
        try {
            return getApp().e();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    private void a(Context context) {
        int i = 0;
        try {
            setHorizontalFadingEdgeEnabled(false);
            if (a()) {
                setSmoothScrollingEnabled(false);
            } else {
                this.a = new GestureDetector(getContext(), new cbp(this, null));
                setOnTouchListener(new cbq(this, null));
            }
            this.b = new LinearLayout(getContext());
            this.b.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.b.setOrientation(0);
            addView(this.b);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.r = viewConfiguration.getScaledMinimumFlingVelocity();
            this.s = viewConfiguration.getScaledPagingTouchSlop();
            this.t = viewConfiguration.getScaledMaximumFlingVelocity();
            if (this.t == 0) {
                this.t = 10000;
            }
            if (Build.VERSION.SDK_INT >= 9) {
                try {
                    Class<?> cls = getClass();
                    do {
                        cls = cls.getSuperclass();
                        i++;
                        if (i >= 10) {
                            break;
                        }
                    } while (!cls.getName().equals("android.widget.HorizontalScrollView"));
                    if (i > 1) {
                        getLogger().severe("cannot find HorizontalScrollView in superclass hierarchy");
                    }
                    Field declaredField = cls.getDeclaredField("mScroller");
                    declaredField.setAccessible(true);
                    this.u = declaredField.get(this);
                } catch (Throwable th) {
                    getLogger().log(Level.SEVERE, "cannot reflect scroller: ", th);
                }
            }
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "problem: ", th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        for (int i = 0; i < getSize(); i++) {
            cbn d = d(i);
            d.c.setVisibility(4);
            d.d.setVisibility(4);
        }
    }

    public View a(Integer num) {
        boolean z;
        if (b(num) != null) {
            throw new Exception("ignoring attempt to add dup column for code: " + num);
        }
        Context context = getContext();
        if (context == null) {
            return null;
        }
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(bbr.hscroll_col_layout, (ViewGroup) this.b, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(getColumnWidth(), -1);
        int columnCount = getColumnCount();
        int i = 0;
        while (true) {
            if (i >= columnCount) {
                z = false;
                break;
            }
            View childAt = getContainer().getChildAt(i);
            if (childAt != null) {
                cbn cbnVar = (cbn) childAt.getTag();
                if (cbnVar == null) {
                    getLogger().severe("no holder tag for column index: " + i);
                } else if (num.intValue() < cbnVar.a.intValue()) {
                    a(num, inflate, i, layoutParams);
                    z = true;
                    break;
                }
            }
            i++;
        }
        if (!z) {
            a(num, inflate, columnCount, layoutParams);
        }
        return inflate;
    }

    @SuppressLint({"InlinedApi"})
    private void a(Integer num, View view, int i, ViewGroup.LayoutParams layoutParams) {
        try {
            cbn cbnVar = new cbn(num, view, a(), getContext(), null);
            cbnVar.a(this.p);
            cbnVar.b(new cbo(this, null));
            view.setTag(cbnVar);
            cbnVar.e.setId(getNextColumnId());
            boolean isFocusable = cbnVar.e.isFocusable();
            cbnVar.e.setFocusable(false);
            this.b.addView(view, i, layoutParams);
            cbnVar.e.setFocusable(isFocusable);
            if (getApp().e() && Build.VERSION.SDK_INT >= 11) {
                cbnVar.e.setOverScrollMode(2);
            } else {
                TextView textView = new TextView(getContext());
                textView.setHeight(0);
                textView.setFocusable(false);
                cbnVar.e.addHeaderView(textView, null, false);
                cbnVar.e.addFooterView(textView, null, false);
            }
            this.k = ((ViewGroup.MarginLayoutParams) cbnVar.e.getLayoutParams()).leftMargin;
            i();
            cbnVar.e.setOnFocusChangeListener(this.y);
            if (getColumnCount() == 1) {
                this.e = 0;
                if (a()) {
                    cbnVar.e.requestFocus();
                }
            } else if (i <= this.e) {
                int c = c(this.e + 1);
                scrollTo(getColumnWidth() * c, 0);
                this.e = c;
            }
            d();
            cbnVar.e.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private App getApp() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return (App) context.getApplicationContext();
    }

    private void i() {
        cbn cbnVar;
        View childAt;
        int columnCount = getColumnCount();
        int i = 0;
        cbn cbnVar2 = null;
        while (i < columnCount) {
            View childAt2 = getContainer().getChildAt(i);
            if (childAt2 != null && (cbnVar = (cbn) childAt2.getTag()) != null) {
                if (cbnVar2 != null) {
                    cbnVar.e.setNextFocusLeftId(cbnVar2.e.getId());
                }
                if (i < columnCount - 1 && (childAt = getContainer().getChildAt(i + 1)) != null) {
                    cbnVar.e.setNextFocusRightId(((cbn) childAt.getTag()).e.getId());
                }
                i++;
                cbnVar2 = cbnVar;
            } else {
                return;
            }
        }
    }

    public void setDefaultDisplay(DisplayMetrics displayMetrics) {
        if (displayMetrics != null) {
            this.d = displayMetrics.widthPixels;
            this.x = displayMetrics.density;
            if (this.x == 0.0f) {
                this.x = 1.0f;
            }
        }
    }

    public int getLeftVisibleIndex() {
        if (this.c == 0) {
            return 0;
        }
        return c(this.e);
    }

    public ListViewDpad a(int i) {
        View childAt = this.b.getChildAt(i);
        if (childAt == null) {
            return null;
        }
        cbn cbnVar = (cbn) childAt.getTag();
        if (cbnVar != null) {
            return cbnVar.e;
        }
        return (ListViewDpad) childAt.findViewById(bbq.earlier);
    }

    public int getColumnWidth() {
        if (this.c == 0) {
            return 0;
        }
        int measuredWidth = getMeasuredWidth() / this.c;
        if (measuredWidth == 0) {
            return (this.d - ((int) (this.d * 0.02d))) / this.c;
        }
        return measuredWidth;
    }

    public void b(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        a(i, z, 0.0f);
    }

    public void a(int i, boolean z, float f) {
        cbn d;
        int i2 = 0;
        try {
            if (getSize() != 0) {
                if (i < 0) {
                    getLogger().warning("scroll target index < 0; setting to 0");
                } else {
                    i2 = i;
                }
                int maxColumnThatCanBeLeftmost = getMaxColumnThatCanBeLeftmost();
                if (i2 > maxColumnThatCanBeLeftmost) {
                    getLogger().warning("scroll target index > max; setting to: " + maxColumnThatCanBeLeftmost);
                } else {
                    maxColumnThatCanBeLeftmost = i2;
                }
                if (getContainer().getChildAt(maxColumnThatCanBeLeftmost) == null) {
                    getLogger().warning("cannot scroll to unknown child at: " + maxColumnThatCanBeLeftmost);
                    return;
                }
                int columnWidth = getColumnWidth() * maxColumnThatCanBeLeftmost;
                if (columnWidth == getScrollX()) {
                    b();
                    return;
                }
                if (getLogger().isLoggable(Level.INFO) && (d = d(maxColumnThatCanBeLeftmost)) != null) {
                    getLogger().info("scrolling to index/x/code: " + maxColumnThatCanBeLeftmost + "/" + columnWidth + "/" + d.a);
                }
                if (!z) {
                    scrollTo(columnWidth, 0);
                } else if (Math.abs(f) > 0.0f && Build.VERSION.SDK_INT >= 11 && this.t > 0) {
                    a(f, columnWidth);
                } else {
                    smoothScrollTo(columnWidth, 0);
                }
                this.e = maxColumnThatCanBeLeftmost;
                b();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @TargetApi(11)
    private void a(float f, int i) {
        try {
            if (f == 0.0f) {
                smoothScrollTo(i, 0);
                return;
            }
            try {
                ((OverScroller) this.u).abortAnimation();
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle myScroller.abort: ", th);
            }
            if (this.w != null && this.w.isRunning()) {
                getLogger().info("cancelling animation and advancing to: " + this.v);
                this.w.cancel();
                scrollTo(this.v, 0);
            }
            this.v = i;
            this.w = ObjectAnimator.ofInt(this, "scrollX", i);
            this.w.addListener(new cbj(this, this.w));
            float abs = Math.abs(Math.abs(getScrollX() - i) / f) * 1000.0f;
            if (abs < 150.0f) {
                abs = 150.0f;
            }
            if (abs > 700.0f) {
                abs = 700.0f;
            }
            this.w.setDuration((int) abs);
            getLogger().info("animation duration: " + abs + " on velocity: " + f);
            this.w.start();
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "Cannot handle customSmoothScroll; reverting to built-in version: ", th2);
            smoothScrollTo(i, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMaxColumnThatCanBeLeftmost() {
        return Math.max(0, getColumnCount() - this.c);
    }

    public int c(int i) {
        if (i < 0) {
            i = 0;
        }
        int maxColumnThatCanBeLeftmost = getMaxColumnThatCanBeLeftmost();
        return i > maxColumnThatCanBeLeftmost ? maxColumnThatCanBeLeftmost : i;
    }

    public void b() {
        int i;
        int i2;
        try {
            int columnCount = getColumnCount();
            int e = e();
            int i3 = this.e;
            int min = i3 + Math.min(e - 1, (columnCount - this.e) - 1);
            getLogger().info("possible arrows on left, right indices: " + i3 + ", " + min);
            boolean z = false;
            for (int i4 = i3; i4 <= min; i4++) {
                View childAt = getContainer().getChildAt(i4);
                if (childAt == null) {
                    getLogger().info("no column at " + i4 + " so hiding both left/right arrows");
                    z = true;
                } else {
                    cbn cbnVar = (cbn) childAt.getTag();
                    if (cbnVar == null) {
                        getLogger().severe("cannot find holder for child at index: " + i4);
                        return;
                    }
                    if (i4 <= 0 || i3 != i4 || z) {
                        i = 4;
                    } else {
                        getLogger().info("left arrow visible for " + i4 + " which has code: " + childAt.getTag());
                        i = 0;
                    }
                    cbnVar.c.setVisibility(i);
                    if (i4 >= columnCount - 1 || i4 != min || z) {
                        i2 = 4;
                    } else {
                        getLogger().info("right arrow visible for " + i4 + " which has code: " + childAt.getTag());
                        i2 = 0;
                    }
                    cbnVar.d.setVisibility(i2);
                    if (a()) {
                        a(i3, min, z, i4, cbnVar);
                    }
                }
            }
            postDelayed(new cbl(this), 100L);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with arrows: ", th);
        }
    }

    private void a(int i, int i2, boolean z, int i3, cbn cbnVar) {
        if (z) {
            cbnVar.e.setNextFocusUpId(-1);
        } else if (i != i3) {
            if (i2 == i3) {
                cbnVar.e.setNextFocusUpId(bbq.sort_option);
            } else {
                cbnVar.e.setNextFocusUpId(-1);
            }
        }
    }

    public View b(Integer num) {
        View view;
        cbn cbnVar;
        if (num == null) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getColumnCount()) {
                view = null;
                break;
            }
            View childAt = getContainer().getChildAt(i2);
            if (childAt != null && (cbnVar = (cbn) childAt.getTag()) != null && num.equals(cbnVar.a)) {
                view = childAt;
                break;
            }
            i = i2 + 1;
        }
        return view;
    }

    public void setOnNavColumnListener(bqu bquVar) {
        this.f = bquVar;
    }

    public int c(Integer num) {
        int abs;
        int i;
        int i2 = -500;
        if (num == null) {
            return 0;
        }
        int d = d(num);
        if (d == -500) {
            int columnCount = getColumnCount();
            int i3 = columnCount / 2;
            int i4 = 0;
            while (i4 < columnCount) {
                View childAt = getContainer().getChildAt(i4);
                if (childAt == null) {
                    abs = i2;
                    i = i3;
                } else {
                    cbn cbnVar = (cbn) childAt.getTag();
                    if (cbnVar == null) {
                        abs = i2;
                        i = i3;
                    } else {
                        Integer num2 = cbnVar.a;
                        if (!num.equals(num2)) {
                            abs = Math.abs(num.intValue() - num2.intValue());
                            if (abs < i2) {
                                i = i4;
                            } else if (abs != i2 || num2.intValue() <= num.intValue()) {
                                abs = i2;
                                i = i3;
                            } else {
                                abs = i2;
                                i = i4;
                            }
                        } else {
                            return i4;
                        }
                    }
                }
                i4++;
                i3 = i;
                i2 = abs;
            }
            return i3;
        }
        return d;
    }

    public cbn d(int i) {
        View e = e(i);
        if (e == null) {
            return null;
        }
        return (cbn) e.getTag();
    }

    private int getNextColumnId() {
        int i = this.j;
        this.j = i + 1;
        return i;
    }

    public int getSize() {
        return getColumnCount();
    }

    public void a(View view) {
        ListViewDpad listViewDpad;
        int i;
        boolean z;
        int i2;
        ListViewDpad a;
        int columnCount = getColumnCount();
        int i3 = 0;
        while (true) {
            if (i3 >= columnCount) {
                listViewDpad = null;
                i = -500;
                z = false;
                i2 = -500;
                break;
            }
            View e = e(i3);
            if (e == null || e != view) {
                i3++;
            } else {
                cbn cbnVar = (cbn) e.getTag();
                if (cbnVar == null) {
                    listViewDpad = null;
                    z = false;
                    i2 = i3;
                    i = -500;
                } else {
                    ListViewDpad listViewDpad2 = cbnVar.e;
                    z = listViewDpad2.hasFocus();
                    listViewDpad2.setFocusable(false);
                    i2 = i3;
                    i = cbnVar.a.intValue();
                    listViewDpad = listViewDpad2;
                }
            }
        }
        if (i2 == -500) {
            getLogger().severe("cannot remove column--not found");
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(getVisibleColumnCodes()));
        getContainer().removeView(view);
        if (i2 <= this.e) {
            int c = c(this.e - 1);
            scrollTo(getColumnWidth() * c, 0);
            this.e = c;
            getLogger().info("scrolled after remove to preserve leftColumn: " + this.e + " with resulting scrollX: " + getScrollX() + ", focus: " + getFocusedColumnIndex());
        }
        if (z && i != -500 && (a = a(c(Integer.valueOf(i)))) != null) {
            a.requestFocus();
        }
        a(hashSet);
        if (listViewDpad != null) {
            try {
                if (listViewDpad.getGameListAdapter() instanceof bms) {
                    ((bms) listViewDpad.getGameListAdapter()).g();
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    public int d(Integer num) {
        int i;
        if (num == null) {
            return -500;
        }
        for (int i2 = 0; i2 < getColumnCount(); i2++) {
            try {
                cbn cbnVar = (cbn) getContainer().getChildAt(i2).getTag();
                if (cbnVar != null && num.equals(cbnVar.a)) {
                    i = i2;
                    break;
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "error: ", th);
            }
        }
        i = -500;
        return i;
    }

    public int getVisLeftColumn() {
        int i;
        Throwable th;
        int i2 = this.e;
        try {
            if (getContainer() == null) {
                return this.e;
            }
            int columnCount = getColumnCount();
            if (columnCount == 0) {
                if (this.e == 0) {
                    return this.e;
                }
                return 0;
            }
            Rect rect = new Rect();
            int i3 = 0;
            while (true) {
                if (i3 >= columnCount) {
                    i = i2;
                    break;
                }
                View e = e(i3);
                if (((cbn) e.getTag()) == null) {
                    getLogger().info("cannot get column holder for index: " + i3);
                } else {
                    rect.set(0, 0, 0, 0);
                    if (e.getGlobalVisibleRect(rect, null)) {
                        i = i3;
                        break;
                    }
                }
                i3++;
            }
            try {
                if (i != this.e) {
                    getLogger().info("getVisLeftColumn found change: " + i + ", was: " + this.e);
                    return i;
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "error: ", th);
                return i;
            }
        } catch (Throwable th3) {
            i = i2;
            th = th3;
        }
    }

    public Integer[] getVisibleColumnCodes() {
        int columnCount;
        Integer[] numArr = new Integer[0];
        if (getContainer() != null && (columnCount = getColumnCount()) != 0) {
            try {
                int leftVisibleIndex = getLeftVisibleIndex();
                int min = Math.min(columnCount, this.c);
                int i = (leftVisibleIndex + min) - 1;
                Integer[] numArr2 = new Integer[min];
                int i2 = 0;
                for (int i3 = leftVisibleIndex; i3 <= i; i3++) {
                    try {
                        cbn d = d(i3);
                        if (d == null) {
                            getLogger().severe("cannot get column holder for index: " + i3);
                        } else {
                            int i4 = i2 + 1;
                            numArr2[i2] = d.a;
                            i2 = i4;
                        }
                    } catch (Throwable th) {
                        th = th;
                        numArr = numArr2;
                        getLogger().log(Level.SEVERE, "error: ", th);
                        return numArr;
                    }
                }
                return numArr2;
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            return numArr;
        }
    }

    public void c() {
        while (getSize() > 0) {
            getContainer().removeView(e(0));
        }
        getLogger().info("hscroll cleared, has " + getSize() + " columns");
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0038 A[Catch: Throwable -> 0x0042, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0042, blocks: (B:3:0x0002, B:6:0x0007, B:9:0x000c, B:11:0x0014, B:13:0x0028, B:15:0x002c, B:16:0x002f, B:18:0x0033, B:20:0x0038), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d() {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            int r2 = r7.c     // Catch: java.lang.Throwable -> L42
            if (r2 != r1) goto L40
            r3 = r1
        L7:
            int r2 = r7.k     // Catch: java.lang.Throwable -> L42
            if (r3 == 0) goto L51
            r4 = r1
        Lc:
            int r5 = r7.getColumnCount()     // Catch: java.lang.Throwable -> L42
            r3 = r0
            r2 = r0
        L12:
            if (r3 >= r5) goto L4e
            android.view.View r0 = r7.e(r3)     // Catch: java.lang.Throwable -> L42
            java.lang.Object r0 = r0.getTag()     // Catch: java.lang.Throwable -> L42
            cbn r0 = (defpackage.cbn) r0     // Catch: java.lang.Throwable -> L42
            com.thuuz.gtv.view.ListViewDpad r0 = r0.e     // Catch: java.lang.Throwable -> L42
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()     // Catch: java.lang.Throwable -> L42
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L4f
            int r6 = r0.leftMargin     // Catch: java.lang.Throwable -> L42
            if (r6 == r4) goto L2f
            r0.leftMargin = r4     // Catch: java.lang.Throwable -> L42
            r2 = r1
        L2f:
            int r6 = r0.rightMargin     // Catch: java.lang.Throwable -> L42
            if (r6 == r4) goto L4f
            r0.rightMargin = r4     // Catch: java.lang.Throwable -> L42
            r0 = r1
        L36:
            if (r0 == 0) goto L3b
            r7.invalidate()     // Catch: java.lang.Throwable -> L42
        L3b:
            int r2 = r3 + 1
            r3 = r2
            r2 = r0
            goto L12
        L40:
            r3 = r0
            goto L7
        L42:
            r0 = move-exception
            java.util.logging.Logger r1 = r7.getLogger()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
        L4e:
            return
        L4f:
            r0 = r2
            goto L36
        L51:
            r4 = r2
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.view.HorizColumnScroll.d():void");
    }

    public void a(boolean z) {
        this.q = z;
    }

    public void a(Animator animator) {
        if (this.w == animator) {
            this.w = null;
        }
    }

    public void setCurrentFocusColumn(int i) {
        this.i = i;
    }

    public int getFocusedColumnIndex() {
        for (int i = 0; i < getColumnCount(); i++) {
            if (a(i).hasFocus()) {
                return i;
            }
        }
        return -500;
    }

    private int getColumnCount() {
        return getContainer().getChildCount();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (this.g) {
                if (getSize() > 0) {
                    a(this.e, false);
                }
                this.g = false;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem w/ onLayout: ", th);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        try {
            HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(getVisibleColumnCodes()));
            super.onSizeChanged(i, i2, i3, i4);
            this.c = e();
            int columnWidth = getColumnWidth();
            getLogger().info("size changed, num_lists/col_width/total_width: " + this.c + "/" + columnWidth + "/" + i);
            int columnCount = getColumnCount();
            for (int i5 = 0; i5 < columnCount; i5++) {
                View childAt = getContainer().getChildAt(i5);
                if (childAt != null) {
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.width = columnWidth;
                    }
                    ListViewDpad listViewDpad = ((cbn) childAt.getTag()).e;
                    if (listViewDpad != null) {
                        listViewDpad.a();
                    } else {
                        getLogger().severe("cannot find lv for index/code: " + i5 + "/" + childAt.getTag());
                    }
                }
            }
            a(hashSet);
            d();
            postDelayed(new cbm(this), 100L);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem resizing: ", th);
        }
    }

    public LinearLayout getContainer() {
        return this.b;
    }

    public View e(int i) {
        return this.b.getChildAt(i);
    }

    public Logger getLogger() {
        Logger logger = Logger.getLogger(this.z);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public int e() {
        int i = 3;
        if (a()) {
            return 3;
        }
        Resources resources = getResources();
        if (resources != null) {
            int i2 = resources.getConfiguration().orientation;
            int i3 = resources.getConfiguration().screenLayout & 15;
            if (i3 >= 4) {
                if (i2 != 2) {
                    i = 2;
                }
            } else if (i3 >= 3) {
                i = i2 == 2 ? 2 : 1;
            } else if (i3 == 2) {
                i = i2 == 2 ? 2 : 1;
            } else {
                i = 1;
            }
        } else {
            i = 1;
        }
        if (h(i) && i > 1) {
            getLogger().info("decr num lists from: " + i);
            return i - 1;
        }
        if (i(i)) {
            getLogger().info("incr num lists from: " + i);
            return i + 1;
        }
        return i;
    }

    private boolean h(int i) {
        int j = j(i);
        boolean z = ((float) j) / this.x < 250.0f;
        if (z) {
            getLogger().info("too small width/density: " + j + "/" + this.x);
        }
        return z;
    }

    private boolean i(int i) {
        int j = j(i);
        return (((((float) j) / this.x) > 600.0f ? 1 : ((((float) j) / this.x) == 600.0f ? 0 : -1)) > 0) && (getApp().an().width() > j * 2);
    }

    private int j(int i) {
        int measuredWidth = getMeasuredWidth();
        if (measuredWidth == 0) {
            measuredWidth = this.d;
        }
        return measuredWidth / i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized Shader k(int i) {
        LinearGradient linearGradient;
        synchronized (HorizColumnScroll.class) {
            if (l == null || m != i) {
                m = i;
                l = new LinearGradient(0.0f, 0.0f, 70.0f, i, new int[]{-12303292, -3355444}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            }
            linearGradient = l;
        }
        return linearGradient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized Shader l(int i) {
        LinearGradient linearGradient;
        synchronized (HorizColumnScroll.class) {
            if (n == null || o != i) {
                o = i;
                n = new LinearGradient(0.0f, 0.0f, 70.0f, i, new int[]{-3355444, -12303292}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            }
            linearGradient = n;
        }
        return linearGradient;
    }

    public void f() {
        int visLeftColumn = getVisLeftColumn();
        if (this.e != visLeftColumn && this.f != null) {
            if (this.e < visLeftColumn) {
                this.f.a(this.e, visLeftColumn);
            } else {
                this.f.b(this.e, visLeftColumn);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashSet<Integer> hashSet) {
        if (this.f != null) {
            for (Integer num : getVisibleColumnCodes()) {
                if (!hashSet.contains(num)) {
                    this.f.a(num.intValue());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(getVisibleColumnCodes()));
        b(i2);
        if (this.f != null) {
            a(hashSet);
            if (i < i2) {
                this.f.b(i, this.e);
            } else {
                this.f.a(i, this.e);
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        try {
            super.measureChildWithMargins(view, i, i2, i3, i4);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem measuring: ", th);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            return super.dispatchKeyEvent(keyEvent);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem dispatching key: ", th);
            return true;
        }
    }

    public boolean g() {
        return this.q;
    }
}
