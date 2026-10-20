package com.samsung.android.sdk.richnotification.actions;

import com.actionbarsherlock.widget.ActivityChooserView;
import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnValidationException;
import defpackage.awu;
import defpackage.aww;
import defpackage.awy;
import defpackage.awz;
import defpackage.axf;
import defpackage.axg;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class SrnRemoteInputAction extends SrnAction {
    private static final String SERIALIZED_NAME_DESCRIPTION = "description";
    private static final String SERIALIZED_NAME_ICON = "icon";
    public static final awz<SrnRemoteInputAction> SERIALIZER = new awz<SrnRemoteInputAction>() { // from class: com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.1
        @Override // defpackage.awz
        public awu serialize(SrnRemoteInputAction srnRemoteInputAction, Type type, awy awyVar) {
            aww h = awyVar.a(srnRemoteInputAction, SrnAction.class).h();
            h.a(SrnRemoteInputAction.SERIALIZED_NAME_DESCRIPTION, awyVar.a(srnRemoteInputAction.mDescription));
            h.a(SrnRemoteInputAction.SERIALIZED_NAME_ICON, awyVar.a(srnRemoteInputAction.mIcon));
            for (Map.Entry<String, awu> entry : awyVar.a(srnRemoteInputAction.mInputMode).h().l()) {
                h.a(entry.getKey(), entry.getValue());
            }
            return h;
        }
    };
    private String mDescription;
    private SrnImageAsset mIcon;
    private InputMode mInputMode;

    public SrnRemoteInputAction(String str) {
        this(str, InputModeFactory.createKeyboardInputMode());
    }

    public SrnRemoteInputAction(String str, InputMode inputMode) {
        super(SrnAction.ActionType.REMOTE_INPUT, str);
        if (inputMode == null) {
            throw new NullPointerException("inputMode is null.");
        }
        this.mInputMode = inputMode;
    }

    private SrnRemoteInputAction(SrnRemoteInputAction srnRemoteInputAction) {
        super(srnRemoteInputAction);
        this.mDescription = srnRemoteInputAction.mDescription;
        this.mIcon = srnRemoteInputAction.mIcon;
        this.mInputMode = srnRemoteInputAction.mInputMode.copySelf();
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setRequestedInputMode(InputMode inputMode) {
        if (inputMode == null) {
            throw new NullPointerException("inputMode is null.");
        }
        this.mInputMode = inputMode;
    }

    public void setIcon(SrnImageAsset srnImageAsset) {
        this.mIcon = srnImageAsset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Choice {

        @axf
        @axg(a = SrnRemoteInputAction.SERIALIZED_NAME_ICON)
        private final SrnImageAsset mChoiceIcon;

        @axf
        @axg(a = "choice_id")
        private final String mChoiceId;

        @axf
        @axg(a = "label")
        private final String mChoiceLabel;

        @axf
        @axg(a = "selected")
        private final Integer mSelected;

        public Choice(String str, String str2) {
            this(str, str2, null, null);
        }

        public Choice(String str, String str2, SrnImageAsset srnImageAsset) {
            this(str, str2, srnImageAsset, null);
        }

        public Choice(String str, String str2, SrnImageAsset srnImageAsset, Integer num) {
            if (str == null) {
                throw new NullPointerException("label is null.");
            }
            if (str2 == null) {
                throw new NullPointerException("choiceID is null.");
            }
            this.mChoiceLabel = str;
            this.mChoiceId = str2;
            this.mChoiceIcon = srnImageAsset;
            this.mSelected = num;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class InputMode {

        @axf
        @axg(a = "input_type")
        private final InputType mInputType;

        /* loaded from: classes.dex */
        enum InputType {
            SINGLE_SELECT,
            MULTI_SELECT,
            VOICE,
            KEYBOARD;

            /* renamed from: values, reason: to resolve conflict with enum method */
            public static InputType[] valuesCustom() {
                InputType[] valuesCustom = values();
                int length = valuesCustom.length;
                InputType[] inputTypeArr = new InputType[length];
                System.arraycopy(valuesCustom, 0, inputTypeArr, 0, length);
                return inputTypeArr;
            }
        }

        protected abstract InputMode copySelf();

        protected abstract void validateSelf(SrnRemoteInputAction srnRemoteInputAction);

        protected InputMode(InputType inputType) {
            this.mInputType = inputType;
        }

        protected InputMode(InputMode inputMode) {
            this.mInputType = inputMode.mInputType;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class SelectInputMode extends InputMode {

        @axf
        @axg(a = "choices")
        private final List<Choice> mChoices;

        protected SelectInputMode(InputMode.InputType inputType) {
            super(inputType);
            this.mChoices = new ArrayList();
        }

        protected SelectInputMode(SelectInputMode selectInputMode) {
            super(selectInputMode);
            this.mChoices = new ArrayList(selectInputMode.mChoices);
        }

        protected SelectInputMode addChoice(String str, String str2) {
            this.mChoices.add(new Choice(str, str2));
            return this;
        }

        protected SelectInputMode addChoice(String str, String str2, SrnImageAsset srnImageAsset) {
            this.mChoices.add(new Choice(str, str2, srnImageAsset));
            return this;
        }

        protected SelectInputMode addChoice(String str, String str2, SrnImageAsset srnImageAsset, boolean z) {
            this.mChoices.add(new Choice(str, str2, srnImageAsset, Integer.valueOf(z ? 1 : 0)));
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.InputMode
        protected void validateSelf(SrnRemoteInputAction srnRemoteInputAction) {
            if (this.mChoices.isEmpty()) {
                throw new SrnValidationException("Choices of SrnRempteInputAction(" + srnRemoteInputAction.getLabel() + ") are empty.");
            }
        }
    }

    /* loaded from: classes.dex */
    public final class SingleSelectInputMode extends SelectInputMode {
        /* synthetic */ SingleSelectInputMode(SingleSelectInputMode singleSelectInputMode, SingleSelectInputMode singleSelectInputMode2) {
            this();
        }

        private SingleSelectInputMode() {
            super(InputMode.InputType.SINGLE_SELECT);
        }

        private SingleSelectInputMode(SingleSelectInputMode singleSelectInputMode) {
            super(singleSelectInputMode);
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.SelectInputMode
        public SingleSelectInputMode addChoice(String str, String str2) {
            super.addChoice(str, str2);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.SelectInputMode
        public SingleSelectInputMode addChoice(String str, String str2, SrnImageAsset srnImageAsset) {
            super.addChoice(str, str2, srnImageAsset);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.InputMode
        protected InputMode copySelf() {
            return new SingleSelectInputMode(this);
        }
    }

    /* loaded from: classes.dex */
    public final class MultiSelectInputMode extends SelectInputMode {
        /* synthetic */ MultiSelectInputMode(MultiSelectInputMode multiSelectInputMode, MultiSelectInputMode multiSelectInputMode2) {
            this();
        }

        private MultiSelectInputMode() {
            super(InputMode.InputType.MULTI_SELECT);
        }

        private MultiSelectInputMode(MultiSelectInputMode multiSelectInputMode) {
            super(multiSelectInputMode);
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.SelectInputMode
        public MultiSelectInputMode addChoice(String str, String str2) {
            super.addChoice(str, str2);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.SelectInputMode
        public MultiSelectInputMode addChoice(String str, String str2, SrnImageAsset srnImageAsset) {
            super.addChoice(str, str2, srnImageAsset);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.SelectInputMode
        public MultiSelectInputMode addChoice(String str, String str2, SrnImageAsset srnImageAsset, boolean z) {
            super.addChoice(str, str2, srnImageAsset, z);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.InputMode
        protected InputMode copySelf() {
            return new MultiSelectInputMode(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class StringInputMode extends InputMode {

        @axf
        @axg(a = "char_limit")
        private Integer mCharLimit;

        protected StringInputMode(InputMode.InputType inputType) {
            super(inputType);
        }

        protected StringInputMode(StringInputMode stringInputMode) {
            super(stringInputMode);
            this.mCharLimit = stringInputMode.mCharLimit;
        }

        public StringInputMode setCharacterLimit(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("character limit must be bigger than zero.");
            }
            this.mCharLimit = Integer.valueOf(i);
            return this;
        }

        protected int getCharLimit() {
            return this.mCharLimit == null ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : this.mCharLimit.intValue();
        }
    }

    /* loaded from: classes.dex */
    public final class KeyboardInputMode extends StringInputMode {

        @axf
        @axg(a = "keyboard_type")
        private KeyboardType mKeyboardType;

        @axf
        @axg(a = "prefill_text")
        private String mPrefillText;

        /* loaded from: classes.dex */
        public enum KeyboardType {
            NORMAL,
            NUMBER,
            EMOJI;

            /* renamed from: values, reason: to resolve conflict with enum method */
            public static KeyboardType[] valuesCustom() {
                KeyboardType[] valuesCustom = values();
                int length = valuesCustom.length;
                KeyboardType[] keyboardTypeArr = new KeyboardType[length];
                System.arraycopy(valuesCustom, 0, keyboardTypeArr, 0, length);
                return keyboardTypeArr;
            }
        }

        /* synthetic */ KeyboardInputMode(KeyboardType keyboardType, KeyboardInputMode keyboardInputMode) {
            this(keyboardType);
        }

        private KeyboardInputMode(KeyboardType keyboardType) {
            super(InputMode.InputType.KEYBOARD);
            setKeyboardType(keyboardType);
        }

        private KeyboardInputMode(KeyboardInputMode keyboardInputMode) {
            super(keyboardInputMode);
            this.mKeyboardType = keyboardInputMode.mKeyboardType;
            this.mPrefillText = keyboardInputMode.mPrefillText;
        }

        public KeyboardInputMode setPrefillString(String str) {
            if (str != null && getCharLimit() < str.length()) {
                throw new IllegalStateException("prefillString is less than the character limit.");
            }
            this.mPrefillText = str;
            return this;
        }

        public KeyboardInputMode setKeyboardType(KeyboardType keyboardType) {
            if (keyboardType == null) {
                keyboardType = KeyboardType.NORMAL;
            }
            this.mKeyboardType = keyboardType;
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.StringInputMode
        public KeyboardInputMode setCharacterLimit(int i) {
            if (this.mPrefillText != null && i < this.mPrefillText.length()) {
                throw new IllegalStateException("prefillString is less than the character limit.");
            }
            super.setCharacterLimit(i);
            return this;
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.InputMode
        protected void validateSelf(SrnRemoteInputAction srnRemoteInputAction) {
        }

        @Override // com.samsung.android.sdk.richnotification.actions.SrnRemoteInputAction.InputMode
        protected InputMode copySelf() {
            return new KeyboardInputMode(this);
        }
    }

    /* loaded from: classes.dex */
    public final class InputModeFactory {
        private InputModeFactory() {
        }

        public static SingleSelectInputMode createSingleSelectInputMode() {
            SingleSelectInputMode singleSelectInputMode = null;
            return new SingleSelectInputMode(singleSelectInputMode, singleSelectInputMode);
        }

        public static MultiSelectInputMode createMultiSelectInputMode() {
            MultiSelectInputMode multiSelectInputMode = null;
            return new MultiSelectInputMode(multiSelectInputMode, multiSelectInputMode);
        }

        public static KeyboardInputMode createKeyboardInputMode() {
            return new KeyboardInputMode(KeyboardInputMode.KeyboardType.NORMAL, null);
        }

        public static KeyboardInputMode createKeyboardInputMode(KeyboardInputMode.KeyboardType keyboardType) {
            if (keyboardType == null) {
                throw new NullPointerException("keyboard type is null.");
            }
            return new KeyboardInputMode(keyboardType, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnAction
    public void validateSelf() {
        this.mInputMode.validateSelf(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnAction
    public SrnAction cloneSelf() {
        return new SrnRemoteInputAction(this);
    }
}
