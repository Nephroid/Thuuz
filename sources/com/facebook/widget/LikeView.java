package com.facebook.widget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.android.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.LikeActionController;
import com.facebook.internal.LikeBoxCountView;
import com.facebook.internal.LikeButton;
import com.facebook.internal.Utility;

/* loaded from: classes.dex */
public class LikeView extends FrameLayout {
    private static final int NO_FOREGROUND_COLOR = -1;
    private AuxiliaryViewPosition auxiliaryViewPosition;
    private BroadcastReceiver broadcastReceiver;
    private LinearLayout containerView;
    private LikeActionControllerCreationCallback creationCallback;
    private int edgePadding;
    private int foregroundColor;
    private HorizontalAlignment horizontalAlignment;
    private int internalPadding;
    private LikeActionController likeActionController;
    private LikeBoxCountView likeBoxCountView;
    private LikeButton likeButton;
    private Style likeViewStyle;
    private String objectId;
    private OnErrorListener onErrorListener;
    private Fragment parentFragment;
    private TextView socialSentenceView;

    /* loaded from: classes.dex */
    public interface OnErrorListener {
        void onError(Bundle bundle);
    }

    /* loaded from: classes.dex */
    public enum Style {
        STANDARD("standard", 0),
        BUTTON("button", 1),
        BOX_COUNT("box_count", 2);

        private int intValue;
        private String stringValue;
        static Style DEFAULT = STANDARD;

        static Style fromInt(int i) {
            for (Style style : values()) {
                if (style.getValue() == i) {
                    return style;
                }
            }
            return null;
        }

        Style(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.stringValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    /* loaded from: classes.dex */
    public enum HorizontalAlignment {
        CENTER("center", 0),
        LEFT("left", 1),
        RIGHT("right", 2);

        static HorizontalAlignment DEFAULT = CENTER;
        private int intValue;
        private String stringValue;

        static HorizontalAlignment fromInt(int i) {
            for (HorizontalAlignment horizontalAlignment : values()) {
                if (horizontalAlignment.getValue() == i) {
                    return horizontalAlignment;
                }
            }
            return null;
        }

        HorizontalAlignment(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.stringValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    /* loaded from: classes.dex */
    public enum AuxiliaryViewPosition {
        BOTTOM("bottom", 0),
        INLINE("inline", 1),
        TOP("top", 2);

        static AuxiliaryViewPosition DEFAULT = BOTTOM;
        private int intValue;
        private String stringValue;

        static AuxiliaryViewPosition fromInt(int i) {
            for (AuxiliaryViewPosition auxiliaryViewPosition : values()) {
                if (auxiliaryViewPosition.getValue() == i) {
                    return auxiliaryViewPosition;
                }
            }
            return null;
        }

        AuxiliaryViewPosition(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.stringValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    public static boolean handleOnActivityResult(Context context, int i, int i2, Intent intent) {
        return LikeActionController.handleOnActivityResult(context, i, i2, intent);
    }

    public LikeView(Context context) {
        super(context);
        this.likeViewStyle = Style.DEFAULT;
        this.horizontalAlignment = HorizontalAlignment.DEFAULT;
        this.auxiliaryViewPosition = AuxiliaryViewPosition.DEFAULT;
        this.foregroundColor = -1;
        initialize(context);
    }

    public LikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.likeViewStyle = Style.DEFAULT;
        this.horizontalAlignment = HorizontalAlignment.DEFAULT;
        this.auxiliaryViewPosition = AuxiliaryViewPosition.DEFAULT;
        this.foregroundColor = -1;
        parseAttributes(attributeSet);
        initialize(context);
    }

    public void setObjectId(String str) {
        String coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(str, null);
        if (!Utility.areObjectsEqual(coerceValueIfNullOrEmpty, this.objectId)) {
            setObjectIdForced(coerceValueIfNullOrEmpty);
            updateLikeStateAndLayout();
        }
    }

    public void setLikeViewStyle(Style style) {
        if (style == null) {
            style = Style.DEFAULT;
        }
        if (this.likeViewStyle != style) {
            this.likeViewStyle = style;
            updateLayout();
        }
    }

    public void setAuxiliaryViewPosition(AuxiliaryViewPosition auxiliaryViewPosition) {
        if (auxiliaryViewPosition == null) {
            auxiliaryViewPosition = AuxiliaryViewPosition.DEFAULT;
        }
        if (this.auxiliaryViewPosition != auxiliaryViewPosition) {
            this.auxiliaryViewPosition = auxiliaryViewPosition;
            updateLayout();
        }
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        if (horizontalAlignment == null) {
            horizontalAlignment = HorizontalAlignment.DEFAULT;
        }
        if (this.horizontalAlignment != horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
            updateLayout();
        }
    }

    public void setForegroundColor(int i) {
        if (this.foregroundColor != i) {
            this.socialSentenceView.setTextColor(i);
        }
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = fragment;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        setObjectId(null);
        super.onDetachedFromWindow();
    }

    private void parseAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && getContext() != null && (obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.com_facebook_like_view)) != null) {
            this.objectId = Utility.coerceValueIfNullOrEmpty(obtainStyledAttributes.getString(R.styleable.com_facebook_like_view_object_id), null);
            this.likeViewStyle = Style.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_style, Style.DEFAULT.getValue()));
            if (this.likeViewStyle == null) {
                throw new IllegalArgumentException("Unsupported value for LikeView 'style'");
            }
            this.auxiliaryViewPosition = AuxiliaryViewPosition.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_auxiliary_view_position, AuxiliaryViewPosition.DEFAULT.getValue()));
            if (this.auxiliaryViewPosition == null) {
                throw new IllegalArgumentException("Unsupported value for LikeView 'auxiliary_view_position'");
            }
            this.horizontalAlignment = HorizontalAlignment.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_horizontal_alignment, HorizontalAlignment.DEFAULT.getValue()));
            if (this.horizontalAlignment == null) {
                throw new IllegalArgumentException("Unsupported value for LikeView 'horizontal_alignment'");
            }
            this.foregroundColor = obtainStyledAttributes.getColor(R.styleable.com_facebook_like_view_foreground_color, -1);
            obtainStyledAttributes.recycle();
        }
    }

    private void initialize(Context context) {
        this.edgePadding = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_edge_padding);
        this.internalPadding = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_internal_padding);
        if (this.foregroundColor == -1) {
            this.foregroundColor = getResources().getColor(R.color.com_facebook_likeview_text_color);
        }
        setBackgroundColor(0);
        this.containerView = new LinearLayout(context);
        this.containerView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        initializeLikeButton(context);
        initializeSocialSentenceView(context);
        initializeLikeCountView(context);
        this.containerView.addView(this.likeButton);
        this.containerView.addView(this.socialSentenceView);
        this.containerView.addView(this.likeBoxCountView);
        addView(this.containerView);
        setObjectIdForced(this.objectId);
        updateLikeStateAndLayout();
    }

    private void initializeLikeButton(Context context) {
        this.likeButton = new LikeButton(context, this.likeActionController != null ? this.likeActionController.isObjectLiked() : false);
        this.likeButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.widget.LikeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LikeView.this.toggleLike();
            }
        });
        this.likeButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    }

    private void initializeSocialSentenceView(Context context) {
        this.socialSentenceView = new TextView(context);
        this.socialSentenceView.setTextSize(0, getResources().getDimension(R.dimen.com_facebook_likeview_text_size));
        this.socialSentenceView.setMaxLines(2);
        this.socialSentenceView.setTextColor(this.foregroundColor);
        this.socialSentenceView.setGravity(17);
        this.socialSentenceView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    }

    private void initializeLikeCountView(Context context) {
        this.likeBoxCountView = new LikeBoxCountView(context);
        this.likeBoxCountView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleLike() {
        Activity activity;
        if (this.likeActionController != null) {
            if (this.parentFragment == null) {
                Context context = getContext();
                if (context instanceof Activity) {
                    activity = (Activity) context;
                } else {
                    if (context instanceof ContextWrapper) {
                        Context baseContext = ((ContextWrapper) context).getBaseContext();
                        if (baseContext instanceof Activity) {
                            activity = (Activity) baseContext;
                        }
                    }
                    activity = null;
                }
            } else {
                activity = this.parentFragment.getActivity();
            }
            this.likeActionController.toggleLike(activity, this.parentFragment, getAnalyticsParameters());
        }
    }

    private Bundle getAnalyticsParameters() {
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, this.likeViewStyle.toString());
        bundle.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_AUXILIARY_POSITION, this.auxiliaryViewPosition.toString());
        bundle.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_HORIZONTAL_ALIGNMENT, this.horizontalAlignment.toString());
        bundle.putString("object_id", Utility.coerceValueIfNullOrEmpty(this.objectId, ""));
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setObjectIdForced(String str) {
        tearDownObjectAssociations();
        this.objectId = str;
        if (!Utility.isNullOrEmpty(str)) {
            this.creationCallback = new LikeActionControllerCreationCallback();
            LikeActionController.getControllerForObjectId(getContext(), str, this.creationCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void associateWithLikeActionController(LikeActionController likeActionController) {
        this.likeActionController = likeActionController;
        this.broadcastReceiver = new LikeControllerBroadcastReceiver();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_UPDATED);
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR);
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_RESET);
        localBroadcastManager.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private void tearDownObjectAssociations() {
        if (this.broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.broadcastReceiver);
            this.broadcastReceiver = null;
        }
        if (this.creationCallback != null) {
            this.creationCallback.cancel();
            this.creationCallback = null;
        }
        this.likeActionController = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLikeStateAndLayout() {
        if (this.likeActionController == null) {
            this.likeButton.setLikeState(false);
            this.socialSentenceView.setText((CharSequence) null);
            this.likeBoxCountView.setText(null);
        } else {
            this.likeButton.setLikeState(this.likeActionController.isObjectLiked());
            this.socialSentenceView.setText(this.likeActionController.getSocialSentence());
            this.likeBoxCountView.setText(this.likeActionController.getLikeCountString());
        }
        updateLayout();
    }

    private void updateLayout() {
        int i;
        View view;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.containerView.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.likeButton.getLayoutParams();
        if (this.horizontalAlignment == HorizontalAlignment.LEFT) {
            i = 3;
        } else {
            i = this.horizontalAlignment == HorizontalAlignment.CENTER ? 1 : 5;
        }
        layoutParams.gravity = i | 48;
        layoutParams2.gravity = i;
        this.socialSentenceView.setVisibility(8);
        this.likeBoxCountView.setVisibility(8);
        if (this.likeViewStyle == Style.STANDARD && this.likeActionController != null && !Utility.isNullOrEmpty(this.likeActionController.getSocialSentence())) {
            view = this.socialSentenceView;
        } else if (this.likeViewStyle == Style.BOX_COUNT && this.likeActionController != null && !Utility.isNullOrEmpty(this.likeActionController.getLikeCountString())) {
            updateBoxCountCaretPosition();
            view = this.likeBoxCountView;
        } else {
            return;
        }
        view.setVisibility(0);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = i;
        this.containerView.setOrientation(this.auxiliaryViewPosition == AuxiliaryViewPosition.INLINE ? 0 : 1);
        if (this.auxiliaryViewPosition == AuxiliaryViewPosition.TOP || (this.auxiliaryViewPosition == AuxiliaryViewPosition.INLINE && this.horizontalAlignment == HorizontalAlignment.RIGHT)) {
            this.containerView.removeView(this.likeButton);
            this.containerView.addView(this.likeButton);
        } else {
            this.containerView.removeView(view);
            this.containerView.addView(view);
        }
        switch (this.auxiliaryViewPosition) {
            case TOP:
                view.setPadding(this.edgePadding, this.edgePadding, this.edgePadding, this.internalPadding);
                return;
            case BOTTOM:
                view.setPadding(this.edgePadding, this.internalPadding, this.edgePadding, this.edgePadding);
                return;
            case INLINE:
                if (this.horizontalAlignment == HorizontalAlignment.RIGHT) {
                    view.setPadding(this.edgePadding, this.edgePadding, this.internalPadding, this.edgePadding);
                    return;
                } else {
                    view.setPadding(this.internalPadding, this.edgePadding, this.edgePadding, this.edgePadding);
                    return;
                }
            default:
                return;
        }
    }

    private void updateBoxCountCaretPosition() {
        switch (this.auxiliaryViewPosition) {
            case TOP:
                this.likeBoxCountView.setCaretPosition(LikeBoxCountView.LikeBoxCountViewCaretPosition.BOTTOM);
                return;
            case BOTTOM:
                this.likeBoxCountView.setCaretPosition(LikeBoxCountView.LikeBoxCountViewCaretPosition.TOP);
                return;
            case INLINE:
                this.likeBoxCountView.setCaretPosition(this.horizontalAlignment == HorizontalAlignment.RIGHT ? LikeBoxCountView.LikeBoxCountViewCaretPosition.RIGHT : LikeBoxCountView.LikeBoxCountViewCaretPosition.LEFT);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LikeControllerBroadcastReceiver extends BroadcastReceiver {
        private LikeControllerBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString(LikeActionController.ACTION_OBJECT_ID_KEY);
                if (!Utility.isNullOrEmpty(string) && !Utility.areObjectsEqual(LikeView.this.objectId, string)) {
                    z = false;
                }
            }
            if (z) {
                if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_UPDATED.equals(action)) {
                    LikeView.this.updateLikeStateAndLayout();
                    return;
                }
                if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR.equals(action)) {
                    if (LikeView.this.onErrorListener != null) {
                        LikeView.this.onErrorListener.onError(extras);
                    }
                } else if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_RESET.equals(action)) {
                    LikeView.this.setObjectIdForced(LikeView.this.objectId);
                    LikeView.this.updateLikeStateAndLayout();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LikeActionControllerCreationCallback implements LikeActionController.CreationCallback {
        private boolean isCancelled;

        private LikeActionControllerCreationCallback() {
        }

        public void cancel() {
            this.isCancelled = true;
        }

        @Override // com.facebook.internal.LikeActionController.CreationCallback
        public void onComplete(LikeActionController likeActionController) {
            if (!this.isCancelled) {
                LikeView.this.associateWithLikeActionController(likeActionController);
                LikeView.this.updateLikeStateAndLayout();
                LikeView.this.creationCallback = null;
            }
        }
    }
}
