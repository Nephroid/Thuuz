package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import com.actionbarsherlock.R;

/* loaded from: classes.dex */
public class IcsSpinner extends IcsAbsSpinner implements DialogInterface.OnClickListener {
    private static final int MAX_ITEMS_MEASURED = 15;
    public static final int MODE_DROPDOWN = 1;
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownWidth;
    private int mGravity;
    private SpinnerPopup mPopup;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface SpinnerPopup {
        void dismiss();

        CharSequence getHintText();

        boolean isShowing();

        void setAdapter(ListAdapter listAdapter);

        void setPromptText(CharSequence charSequence);

        void show();
    }

    public IcsSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionDropDownStyle);
    }

    public IcsSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempRect = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SherlockSpinner, i, 0);
        DropdownPopup dropdownPopup = new DropdownPopup(context, attributeSet, i);
        this.mDropDownWidth = obtainStyledAttributes.getLayoutDimension(R.styleable.SherlockSpinner_android_dropDownWidth, -2);
        dropdownPopup.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.SherlockSpinner_android_popupBackground));
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SherlockSpinner_android_dropDownVerticalOffset, 0);
        if (dimensionPixelOffset != 0) {
            dropdownPopup.setVerticalOffset(dimensionPixelOffset);
        }
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SherlockSpinner_android_dropDownHorizontalOffset, 0);
        if (dimensionPixelOffset2 != 0) {
            dropdownPopup.setHorizontalOffset(dimensionPixelOffset2);
        }
        this.mPopup = dropdownPopup;
        this.mGravity = obtainStyledAttributes.getInt(R.styleable.SherlockSpinner_android_gravity, 17);
        this.mPopup.setPromptText(obtainStyledAttributes.getString(R.styleable.SherlockSpinner_android_prompt));
        this.mDisableChildrenWhenDisabled = true;
        obtainStyledAttributes.recycle();
        if (this.mTempAdapter != null) {
            this.mPopup.setAdapter(this.mTempAdapter);
            this.mTempAdapter = null;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.mDisableChildrenWhenDisabled) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setEnabled(z);
            }
        }
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((i & 7) == 0) {
                i |= 3;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    @Override // com.actionbarsherlock.internal.widget.IcsAbsSpinner, com.actionbarsherlock.internal.widget.IcsAdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        super.setAdapter(spinnerAdapter);
        if (this.mPopup != null) {
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter));
        } else {
            this.mTempAdapter = new DropDownAdapter(spinnerAdapter);
        }
    }

    @Override // android.view.View
    public int getBaseline() {
        int baseline;
        View view = null;
        if (getChildCount() > 0) {
            view = getChildAt(0);
        } else if (this.mAdapter != null && this.mAdapter.getCount() > 0) {
            view = makeAndAddView(0);
            this.mRecycler.put(0, view);
            removeAllViewsInLayout();
        }
        if (view == null || (baseline = view.getBaseline()) < 0) {
            return -1;
        }
        return view.getTop() + baseline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.internal.widget.IcsAdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override // com.actionbarsherlock.internal.widget.IcsAdapterView
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.internal.widget.IcsAbsSpinner, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mPopup != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), measureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.internal.widget.IcsAdapterView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mInLayout = true;
        layout(0, false);
        this.mInLayout = false;
    }

    @Override // com.actionbarsherlock.internal.widget.IcsAbsSpinner
    void layout(int i, boolean z) {
        int i2 = this.mSpinnerPadding.left;
        int right = ((getRight() - getLeft()) - this.mSpinnerPadding.left) - this.mSpinnerPadding.right;
        if (this.mDataChanged) {
            handleDataChanged();
        }
        if (this.mItemCount == 0) {
            resetList();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            setSelectedPositionInt(this.mNextSelectedPosition);
        }
        recycleAllViews();
        removeAllViewsInLayout();
        this.mFirstPosition = this.mSelectedPosition;
        View makeAndAddView = makeAndAddView(this.mSelectedPosition);
        int measuredWidth = makeAndAddView.getMeasuredWidth();
        switch (this.mGravity & 7) {
            case 1:
                i2 = (i2 + (right / 2)) - (measuredWidth / 2);
                break;
            case 5:
                i2 = (i2 + right) - measuredWidth;
                break;
        }
        makeAndAddView.offsetLeftAndRight(i2);
        this.mRecycler.clear();
        invalidate();
        checkSelectionChanged();
        this.mDataChanged = false;
        this.mNeedSync = false;
        setNextSelectedPositionInt(this.mSelectedPosition);
    }

    private View makeAndAddView(int i) {
        View view;
        if (!this.mDataChanged && (view = this.mRecycler.get(i)) != null) {
            setUpChild(view);
            return view;
        }
        View view2 = this.mAdapter.getView(i, null, this);
        setUpChild(view2);
        return view2;
    }

    private void setUpChild(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, 0, layoutParams);
        view.setSelected(hasFocus());
        if (this.mDisableChildrenWhenDisabled) {
            view.setEnabled(isEnabled());
        }
        view.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mSpinnerPadding.left + this.mSpinnerPadding.right, layoutParams.width), ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.mSpinnerPadding.top + this.mSpinnerPadding.bottom, layoutParams.height));
        int measuredHeight = this.mSpinnerPadding.top + ((((getMeasuredHeight() - this.mSpinnerPadding.bottom) - this.mSpinnerPadding.top) - view.getMeasuredHeight()) / 2);
        view.layout(0, measuredHeight, view.getMeasuredWidth() + 0, view.getMeasuredHeight() + measuredHeight);
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            performClick = true;
            if (!this.mPopup.isShowing()) {
                this.mPopup.show();
            }
        }
        return performClick;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        setSelection(i);
        dialogInterface.dismiss();
    }

    public void setPrompt(CharSequence charSequence) {
        this.mPopup.setPromptText(charSequence);
    }

    public void setPromptId(int i) {
        setPrompt(getContext().getText(i));
    }

    public CharSequence getPrompt() {
        return this.mPopup.getHintText();
    }

    int measureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        View view;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view2 = null;
        int i = 0;
        int i2 = 0;
        while (max2 < min) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
            } else {
                itemViewType = i2;
                view = view2;
            }
            view2 = spinnerAdapter.getView(max2, view, this);
            if (view2.getLayoutParams() == null) {
                view2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view2.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view2.getMeasuredWidth());
            max2++;
            i2 = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.mTempRect);
        return this.mTempRect.left + this.mTempRect.right + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.mAdapter == null) {
                return 0;
            }
            return this.mAdapter.getCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            if (this.mAdapter == null) {
                return -1L;
            }
            return this.mAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    /* loaded from: classes.dex */
    class DropdownPopup extends IcsListPopupWindow implements SpinnerPopup {
        private ListAdapter mAdapter;
        private CharSequence mHintText;

        public DropdownPopup(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, 0, i);
            setAnchorView(IcsSpinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.actionbarsherlock.internal.widget.IcsSpinner.DropdownPopup.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                    IcsSpinner.this.setSelection(i2);
                    DropdownPopup.this.dismiss();
                }
            });
        }

        @Override // com.actionbarsherlock.internal.widget.IcsListPopupWindow, com.actionbarsherlock.internal.widget.IcsSpinner.SpinnerPopup
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        @Override // com.actionbarsherlock.internal.widget.IcsSpinner.SpinnerPopup
        public CharSequence getHintText() {
            return this.mHintText;
        }

        @Override // com.actionbarsherlock.internal.widget.IcsSpinner.SpinnerPopup
        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        @Override // com.actionbarsherlock.internal.widget.IcsListPopupWindow, com.actionbarsherlock.internal.widget.IcsSpinner.SpinnerPopup
        public void show() {
            int paddingLeft = IcsSpinner.this.getPaddingLeft();
            if (IcsSpinner.this.mDropDownWidth == -2) {
                int width = IcsSpinner.this.getWidth();
                setContentWidth(Math.max(IcsSpinner.this.measureContentWidth((SpinnerAdapter) this.mAdapter, IcsSpinner.this.getBackground()), (width - paddingLeft) - IcsSpinner.this.getPaddingRight()));
            } else if (IcsSpinner.this.mDropDownWidth == -1) {
                setContentWidth((IcsSpinner.this.getWidth() - paddingLeft) - IcsSpinner.this.getPaddingRight());
            } else {
                setContentWidth(IcsSpinner.this.mDropDownWidth);
            }
            Drawable background = IcsSpinner.this.getBackground();
            int i = 0;
            if (background != null) {
                background.getPadding(IcsSpinner.this.mTempRect);
                i = -IcsSpinner.this.mTempRect.left;
            }
            setHorizontalOffset(i + paddingLeft);
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            IcsSpinner.this.setSelection(IcsSpinner.this.getSelectedItemPosition());
        }
    }
}
