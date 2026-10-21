package com.samsung.android.sdk.richnotification;

import android.content.Context;
import android.content.pm.PackageManager;
import com.samsung.android.sdk.richnotification.templates.SrnPrimaryTemplate;
import com.samsung.android.sdk.richnotification.templates.SrnSecondaryTemplate;
import defpackage.awr;
import defpackage.awu;
import defpackage.awy;
import defpackage.awz;
import defpackage.axf;
import defpackage.axg;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class SrnRichNotification {
    private static SrnImageAsset sAppIcon;

    @axf
    @axg(a = "actions")
    private List<SrnAction> mActionList;

    @axf
    @axg(a = "alert_type")
    private AlertType mAlertType;
    private Integer mAndroidNotificationID;
    private String mAndroidNotificationTag;

    @axf
    @axg(a = "icon")
    private SrnImageAsset mIcon;

    @axf
    @axg(a = "popup_type")
    private PopupType mPopupType;

    @axf
    @axg(a = "readout_message")
    private String mReadoutMessage;

    @axf
    @axg(a = "readout_title")
    private String mReadoutTitle;

    @axf
    @axg(a = "templates")
    private final Templates mTemplates;

    @axf
    @axg(a = "title")
    private String mTitle;

    @axf
    @axg(a = "uuid")
    private final UUID mUuid;

    /* loaded from: classes.dex */
    public enum AlertType {
        SILENCE,
        VIBRATION,
        SOUND,
        SOUND_AND_VIBRATION;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static AlertType[] valuesCustom() {
            AlertType[] valuesCustom = values();
            int length = valuesCustom.length;
            AlertType[] alertTypeArr = new AlertType[length];
            System.arraycopy(valuesCustom, 0, alertTypeArr, 0, length);
            return alertTypeArr;
        }
    }

    /* loaded from: classes.dex */
    public enum PopupType {
        NORMAL,
        NONE;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static PopupType[] valuesCustom() {
            PopupType[] valuesCustom = values();
            int length = valuesCustom.length;
            PopupType[] popupTypeArr = new PopupType[length];
            System.arraycopy(valuesCustom, 0, popupTypeArr, 0, length);
            return popupTypeArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Templates {
        private SrnPrimaryTemplate mPrimaryTemplate;
        private SrnSecondaryTemplate mSecondaryTemplate;

        private Templates() {
        }

        /* synthetic */ Templates(Templates templates, Templates templates2) {
            this();
        }

        private Templates(Templates templates) {
            if (templates.mPrimaryTemplate != null) {
                this.mPrimaryTemplate = (SrnPrimaryTemplate) templates.mPrimaryTemplate.cloneSelf();
            }
            if (templates.mSecondaryTemplate != null) {
                this.mSecondaryTemplate = (SrnSecondaryTemplate) templates.mSecondaryTemplate.cloneSelf();
            }
        }

        /* synthetic */ Templates(Templates templates, Templates templates2, Templates templates3) {
            this(templates);
        }
    }

    public SrnRichNotification(Context context) {
        this(context, UUID.randomUUID());
    }

    public SrnRichNotification(Context context, UUID uuid) {
        Templates templates = null;
        if (context == null) {
            throw new NullPointerException("context is null.");
        }
        if (uuid == null) {
            throw new NullPointerException("uuid is null.");
        }
        this.mIcon = loadAppIcon(context);
        this.mUuid = uuid;
        this.mAlertType = AlertType.SOUND;
        this.mTemplates = new Templates(templates, templates);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SrnRichNotification(SrnRichNotification srnRichNotification) {
        Templates templates = null;
        this.mAndroidNotificationID = srnRichNotification.mAndroidNotificationID;
        this.mAndroidNotificationTag = srnRichNotification.mAndroidNotificationTag;
        this.mUuid = srnRichNotification.mUuid;
        this.mAlertType = srnRichNotification.mAlertType;
        this.mPopupType = srnRichNotification.mPopupType;
        this.mReadoutTitle = srnRichNotification.mReadoutTitle;
        this.mReadoutMessage = srnRichNotification.mReadoutMessage;
        this.mTitle = srnRichNotification.mTitle;
        this.mIcon = srnRichNotification.mIcon;
        this.mTemplates = new Templates(srnRichNotification.mTemplates, templates, templates);
        if (srnRichNotification.mActionList != null) {
            this.mActionList = new ArrayList(srnRichNotification.mActionList.size());
            Iterator<SrnAction> it = srnRichNotification.mActionList.iterator();
            while (it.hasNext()) {
                this.mActionList.add(it.next().cloneSelf());
            }
        }
    }

    private static SrnImageAsset loadAppIcon(Context context) {
        if (sAppIcon == null) {
            try {
                sAppIcon = new SrnImageAsset(context, "appIcon", Utilities.drawableToBitmap(context.getPackageManager().getApplicationIcon(context.getPackageName())));
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sAppIcon;
    }

    public UUID getUuid() {
        return this.mUuid;
    }

    public void setIcon(SrnImageAsset srnImageAsset) {
        this.mIcon = srnImageAsset;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setPrimaryTemplate(SrnPrimaryTemplate srnPrimaryTemplate) {
        this.mTemplates.mPrimaryTemplate = srnPrimaryTemplate;
    }

    public void setSecondaryTemplate(SrnSecondaryTemplate srnSecondaryTemplate) {
        this.mTemplates.mSecondaryTemplate = srnSecondaryTemplate;
    }

    public void setAssociatedAndroidNotification(int i) {
        this.mAndroidNotificationID = Integer.valueOf(i);
    }

    public void setAssociatedAndroidNotification(int i, String str) {
        this.mAndroidNotificationID = Integer.valueOf(i);
        this.mAndroidNotificationTag = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer getAssociatedAndroidNotification() {
        return this.mAndroidNotificationID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAssociatedAndroidNotificationTag() {
        return this.mAndroidNotificationTag;
    }

    public void addAction(SrnAction srnAction) {
        if (srnAction == null) {
            throw new NullPointerException("action is null.");
        }
        if (this.mActionList == null) {
            this.mActionList = new ArrayList();
        }
        this.mActionList.add(srnAction);
    }

    public void addActions(List<SrnAction> list) {
        if (list == null) {
            throw new NullPointerException("actions is null.");
        }
        if (this.mActionList == null) {
            this.mActionList = new ArrayList();
        }
        this.mActionList.addAll(list);
    }

    public void setReadout(String str, String str2) {
        this.mReadoutTitle = str;
        this.mReadoutMessage = str2;
    }

    public void setAlertType(AlertType alertType) {
        if (alertType == null) {
            throw new NullPointerException("alertType is null,");
        }
        this.mAlertType = alertType;
        this.mPopupType = PopupType.NORMAL;
    }

    public void setAlertType(AlertType alertType, PopupType popupType) {
        if (alertType == null) {
            throw new NullPointerException("alertType is null,");
        }
        if (popupType == null) {
            throw new NullPointerException("popupType is null,");
        }
        this.mAlertType = alertType;
        this.mPopupType = popupType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateSelf() {
        if (this.mTemplates.mPrimaryTemplate == null) {
            throw new SrnValidationException("Primary template is null.");
        }
        if (this.mActionList != null) {
            Iterator<SrnAction> it = this.mActionList.iterator();
            while (it.hasNext()) {
                it.next().validateSelf();
            }
        }
    }

    /* loaded from: classes.dex */
    class TemplatesSerializer implements awz<Templates> {
        @Override // defpackage.awz
        public awu serialize(Templates templates, Type type, awy awyVar) {
            awr awrVar = new awr();
            awrVar.a(awyVar.a(templates.mPrimaryTemplate));
            if (templates.mSecondaryTemplate != null) {
                awrVar.a(awyVar.a(templates.mSecondaryTemplate));
            }
            return awrVar;
        }
    }
}
