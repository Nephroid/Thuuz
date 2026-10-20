package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphObjectException;
import com.facebook.NativeAppCallAttachmentStore;
import com.facebook.NativeAppCallContentProvider;
import com.facebook.Settings;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.samsung.android.sdk.richnotification.Utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FacebookDialog {
    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_ID_KEY = "postId";
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private PendingCall appCall;
    private Fragment fragment;
    private OnPresentCallback onPresentCallback;

    /* loaded from: classes.dex */
    public interface Callback {
        void onComplete(PendingCall pendingCall, Bundle bundle);

        void onError(PendingCall pendingCall, Exception exc, Bundle bundle);
    }

    /* loaded from: classes.dex */
    public interface DialogFeature {
        String getAction();

        int getMinVersion();

        String name();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface OnPresentCallback {
        void onPresent(Context context);
    }

    static /* synthetic */ NativeAppCallAttachmentStore access$900() {
        return getAttachmentStore();
    }

    /* loaded from: classes.dex */
    public enum ShareDialogFeature implements DialogFeature {
        SHARE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140204),
        VIDEO(NativeProtocol.PROTOCOL_VERSION_20141028);

        private int minVersion;

        ShareDialogFeature(int i) {
            this.minVersion = i;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public String getAction() {
            return NativeProtocol.ACTION_FEED_DIALOG;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public int getMinVersion() {
            return this.minVersion;
        }
    }

    /* loaded from: classes.dex */
    public enum MessageDialogFeature implements DialogFeature {
        MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140324),
        VIDEO(NativeProtocol.PROTOCOL_VERSION_20141218);

        private int minVersion;

        MessageDialogFeature(int i) {
            this.minVersion = i;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public String getAction() {
            return NativeProtocol.ACTION_MESSAGE_DIALOG;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public int getMinVersion() {
            return this.minVersion;
        }
    }

    /* loaded from: classes.dex */
    public enum OpenGraphActionDialogFeature implements DialogFeature {
        OG_ACTION_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618);

        private int minVersion;

        OpenGraphActionDialogFeature(int i) {
            this.minVersion = i;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public String getAction() {
            return NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public int getMinVersion() {
            return this.minVersion;
        }
    }

    /* loaded from: classes.dex */
    public enum OpenGraphMessageDialogFeature implements DialogFeature {
        OG_MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204);

        private int minVersion;

        OpenGraphMessageDialogFeature(int i) {
            this.minVersion = i;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public String getAction() {
            return NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public static boolean getNativeDialogDidComplete(Bundle bundle) {
        return bundle.containsKey(RESULT_ARGS_DIALOG_COMPLETE_KEY) ? bundle.getBoolean(RESULT_ARGS_DIALOG_COMPLETE_KEY) : bundle.getBoolean(EXTRA_DIALOG_COMPLETE_KEY, false);
    }

    public static String getNativeDialogCompletionGesture(Bundle bundle) {
        return bundle.containsKey(RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY) ? bundle.getString(RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY) : bundle.getString(EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getNativeDialogPostId(Bundle bundle) {
        return bundle.containsKey(RESULT_ARGS_DIALOG_COMPLETION_ID_KEY) ? bundle.getString(RESULT_ARGS_DIALOG_COMPLETION_ID_KEY) : bundle.getString(EXTRA_DIALOG_COMPLETION_ID_KEY);
    }

    private FacebookDialog(Activity activity, Fragment fragment, PendingCall pendingCall, OnPresentCallback onPresentCallback) {
        this.activity = activity;
        this.fragment = fragment;
        this.appCall = pendingCall;
        this.onPresentCallback = onPresentCallback;
    }

    public PendingCall present() {
        logDialogActivity(this.activity, this.fragment, getEventName(this.appCall.getRequestIntent()), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
        if (this.onPresentCallback != null) {
            try {
                this.onPresentCallback.onPresent(this.activity);
            } catch (Exception e) {
                throw new FacebookException(e);
            }
        }
        if (this.fragment != null) {
            this.fragment.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        } else {
            this.activity.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        }
        return this.appCall;
    }

    public static boolean handleActivityResult(Context context, PendingCall pendingCall, int i, Intent intent, Callback callback) {
        if (i != pendingCall.getRequestCode()) {
            return false;
        }
        if (attachmentStore != null) {
            attachmentStore.cleanupAttachmentsForCall(context, pendingCall.getCallId());
        }
        if (callback != null) {
            if (NativeProtocol.isErrorResult(intent)) {
                Bundle errorDataFromResultIntent = NativeProtocol.getErrorDataFromResultIntent(intent);
                callback.onError(pendingCall, NativeProtocol.getExceptionFromErrorData(errorDataFromResultIntent), errorDataFromResultIntent);
            } else {
                callback.onComplete(pendingCall, NativeProtocol.getSuccessResultsFromIntent(intent));
            }
        }
        return true;
    }

    public static boolean canPresentShareDialog(Context context, ShareDialogFeature... shareDialogFeatureArr) {
        return handleCanPresent(context, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, shareDialogFeatureArr));
    }

    public static boolean canPresentMessageDialog(Context context, MessageDialogFeature... messageDialogFeatureArr) {
        return handleCanPresent(context, EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, messageDialogFeatureArr));
    }

    public static boolean canPresentOpenGraphActionDialog(Context context, OpenGraphActionDialogFeature... openGraphActionDialogFeatureArr) {
        return handleCanPresent(context, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, openGraphActionDialogFeatureArr));
    }

    public static boolean canPresentOpenGraphMessageDialog(Context context, OpenGraphMessageDialogFeature... openGraphMessageDialogFeatureArr) {
        return handleCanPresent(context, EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG, openGraphMessageDialogFeatureArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean handleCanPresent(Context context, Iterable<? extends DialogFeature> iterable) {
        String actionForFeatures = getActionForFeatures(iterable);
        String applicationId = Settings.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            applicationId = Utility.getMetadataApplicationId(context);
        }
        return getProtocolVersionForNativeDialog(context, actionForFeatures, getVersionSpecForFeatures(applicationId, actionForFeatures, iterable)) != -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getProtocolVersionForNativeDialog(Context context, String str, int[] iArr) {
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(context, str, iArr);
    }

    private static NativeAppCallAttachmentStore getAttachmentStore() {
        if (attachmentStore == null) {
            attachmentStore = new NativeAppCallAttachmentStore();
        }
        return attachmentStore;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] getVersionSpecForFeatures(String str, String str2, Iterable<? extends DialogFeature> iterable) {
        int[] iArr = null;
        Iterator<? extends DialogFeature> it = iterable.iterator();
        while (true) {
            int[] iArr2 = iArr;
            if (it.hasNext()) {
                iArr = Utility.intersectRanges(iArr2, getVersionSpecForFeature(str, str2, it.next()));
            } else {
                return iArr2;
            }
        }
    }

    private static int[] getVersionSpecForFeature(String str, String str2, DialogFeature dialogFeature) {
        Utility.DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(str, str2, dialogFeature.name());
        return dialogFeatureConfig != null ? dialogFeatureConfig.getVersionSpec() : new int[]{dialogFeature.getMinVersion()};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getActionForFeatures(Iterable<? extends DialogFeature> iterable) {
        Iterator<? extends DialogFeature> it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        return it.next().getAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logDialogActivity(Activity activity, Fragment fragment, String str, String str2) {
        if (fragment != null) {
            activity = fragment.getActivity();
        }
        AppEventsLogger newLogger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, str2);
        newLogger.logSdkEvent(str, null, bundle);
    }

    private static String getEventName(Intent intent) {
        boolean z;
        boolean z2 = true;
        String stringExtra = intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION);
        boolean hasExtra = intent.hasExtra(NativeProtocol.EXTRA_PHOTOS);
        Bundle bundleExtra = intent.getBundleExtra(NativeProtocol.EXTRA_PROTOCOL_METHOD_ARGS);
        if (bundleExtra == null) {
            z2 = false;
            z = hasExtra;
        } else {
            ArrayList<String> stringArrayList = bundleExtra.getStringArrayList(NativeProtocol.METHOD_ARGS_PHOTOS);
            String string = bundleExtra.getString(NativeProtocol.METHOD_ARGS_VIDEO);
            if (stringArrayList != null && !stringArrayList.isEmpty()) {
                hasExtra = true;
            }
            if (string == null || string.isEmpty()) {
                z2 = false;
                z = hasExtra;
            } else {
                z = hasExtra;
            }
        }
        return getEventName(stringExtra, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getEventName(String str, boolean z, boolean z2) {
        if (str.equals(NativeProtocol.ACTION_FEED_DIALOG)) {
            return z2 ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_VIDEO_SHARE : z ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_SHARE : AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_SHARE;
        }
        if (str.equals(NativeProtocol.ACTION_MESSAGE_DIALOG)) {
            return z ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_MESSAGE : AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_MESSAGE;
        }
        if (str.equals(NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG)) {
            return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_SHARE;
        }
        if (str.equals(NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG)) {
            return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_MESSAGE;
        }
        if (str.equals(NativeProtocol.ACTION_LIKE_DIALOG)) {
            return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_LIKE;
        }
        throw new FacebookException("An unspecified action was presented");
    }

    /* loaded from: classes.dex */
    public abstract class Builder<CONCRETE extends Builder<?>> {
        protected final Activity activity;
        protected final PendingCall appCall;
        protected final String applicationId;
        protected String applicationName;
        protected Fragment fragment;
        protected HashMap<String, Bitmap> imageAttachments = new HashMap<>();
        protected HashMap<String, File> mediaAttachmentFiles = new HashMap<>();

        protected abstract EnumSet<? extends DialogFeature> getDialogFeatures();

        protected abstract Bundle getMethodArguments();

        public Builder(Activity activity) {
            Validate.notNull(activity, "activity");
            this.activity = activity;
            this.applicationId = Utility.getMetadataApplicationId(activity);
            this.appCall = new PendingCall(NativeProtocol.DIALOG_REQUEST_CODE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setRequestCode(int i) {
            this.appCall.setRequestCode(i);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setApplicationName(String str) {
            this.applicationName = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setFragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public FacebookDialog build() {
            Bundle bundleExtras;
            validate();
            String actionForFeatures = FacebookDialog.getActionForFeatures(getDialogFeatures());
            int protocolVersionForNativeDialog = FacebookDialog.getProtocolVersionForNativeDialog(this.activity, actionForFeatures, FacebookDialog.getVersionSpecForFeatures(this.applicationId, actionForFeatures, getDialogFeatures()));
            if (NativeProtocol.isVersionCompatibleWithBucketedIntent(protocolVersionForNativeDialog)) {
                bundleExtras = getMethodArguments();
            } else {
                bundleExtras = setBundleExtras(new Bundle());
            }
            Intent createPlatformActivityIntent = NativeProtocol.createPlatformActivityIntent(this.activity, this.appCall.getCallId().toString(), actionForFeatures, protocolVersionForNativeDialog, this.applicationName, bundleExtras);
            if (createPlatformActivityIntent == null) {
                FacebookDialog.logDialogActivity(this.activity, this.fragment, FacebookDialog.getEventName(actionForFeatures, bundleExtras.containsKey(NativeProtocol.EXTRA_PHOTOS), false), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED);
                throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
            }
            this.appCall.setRequestIntent(createPlatformActivityIntent);
            return new FacebookDialog(this.activity, this.fragment, this.appCall, getOnPresentCallback());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String getWebFallbackUrlInternal() {
            String str;
            String str2;
            Uri fallbackUrl;
            Iterator<T> it = getDialogFeatures().iterator();
            if (it.hasNext()) {
                DialogFeature dialogFeature = (DialogFeature) it.next();
                str2 = dialogFeature.name();
                str = dialogFeature.getAction();
            } else {
                str = null;
                str2 = null;
            }
            Utility.DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(this.applicationId, str, str2);
            if (dialogFeatureConfig == null || (fallbackUrl = dialogFeatureConfig.getFallbackUrl()) == null) {
                return null;
            }
            Bundle queryParamsForPlatformActivityIntentWebFallback = ServerProtocol.getQueryParamsForPlatformActivityIntentWebFallback(this.activity, this.appCall.getCallId().toString(), NativeProtocol.getLatestKnownVersion(), this.applicationName, getMethodArguments());
            if (queryParamsForPlatformActivityIntentWebFallback == null) {
                return null;
            }
            if (fallbackUrl.isRelative()) {
                fallbackUrl = Utility.buildUri(ServerProtocol.getDialogAuthority(), fallbackUrl.toString(), queryParamsForPlatformActivityIntentWebFallback);
            }
            return fallbackUrl.toString();
        }

        public boolean canPresent() {
            return FacebookDialog.handleCanPresent(this.activity, getDialogFeatures());
        }

        void validate() {
        }

        OnPresentCallback getOnPresentCallback() {
            return new OnPresentCallback() { // from class: com.facebook.widget.FacebookDialog.Builder.1
                @Override // com.facebook.widget.FacebookDialog.OnPresentCallback
                public void onPresent(Context context) {
                    if (Builder.this.imageAttachments != null && Builder.this.imageAttachments.size() > 0) {
                        FacebookDialog.access$900().addAttachmentsForCall(context, Builder.this.appCall.getCallId(), Builder.this.imageAttachments);
                    }
                    if (Builder.this.mediaAttachmentFiles != null && Builder.this.mediaAttachmentFiles.size() > 0) {
                        FacebookDialog.access$900().addAttachmentFilesForCall(context, Builder.this.appCall.getCallId(), Builder.this.mediaAttachmentFiles);
                    }
                }
            };
        }

        protected List<String> addImageAttachments(Collection<Bitmap> collection) {
            ArrayList arrayList = new ArrayList();
            for (Bitmap bitmap : collection) {
                String uuid = UUID.randomUUID().toString();
                addImageAttachment(uuid, bitmap);
                arrayList.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), uuid));
            }
            return arrayList;
        }

        protected List<String> addImageAttachmentFiles(Collection<File> collection) {
            ArrayList arrayList = new ArrayList();
            for (File file : collection) {
                String uuid = UUID.randomUUID().toString();
                addImageAttachment(uuid, file);
                arrayList.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), uuid));
            }
            return arrayList;
        }

        protected String addVideoAttachmentFile(File file) {
            String uuid = UUID.randomUUID().toString();
            addVideoAttachment(uuid, file);
            return NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), uuid);
        }

        List<String> getImageAttachmentNames() {
            return new ArrayList(this.imageAttachments.keySet());
        }

        protected Bundle setBundleExtras(Bundle bundle) {
            return bundle;
        }

        protected void putExtra(Bundle bundle, String str, String str2) {
            if (str2 != null) {
                bundle.putString(str, str2);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected CONCRETE addImageAttachment(String str, Bitmap bitmap) {
            this.imageAttachments.put(str, bitmap);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected CONCRETE addImageAttachment(String str, File file) {
            this.mediaAttachmentFiles.put(str, file);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected CONCRETE addVideoAttachment(String str, File file) {
            this.mediaAttachmentFiles.put(str, file);
            return this;
        }
    }

    /* loaded from: classes.dex */
    abstract class ShareDialogBuilderBase<CONCRETE extends ShareDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private String caption;
        private boolean dataErrorsFatal;
        private String description;
        private ArrayList<String> friends;
        protected String link;
        private String name;
        private String picture;
        private String place;
        private String ref;

        public ShareDialogBuilderBase(Activity activity) {
            super(activity);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setName(String str) {
            this.name = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setCaption(String str) {
            this.caption = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setDescription(String str) {
            this.description = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setLink(String str) {
            this.link = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setPicture(String str) {
            this.picture = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setPlace(String str) {
            this.place = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setFriends(List<String> list) {
            this.friends = list == null ? null : new ArrayList<>(list);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setRef(String str) {
            this.ref = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setDataErrorsFatal(boolean z) {
            this.dataErrorsFatal = z;
            return this;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle setBundleExtras(Bundle bundle) {
            putExtra(bundle, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(bundle, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(bundle, NativeProtocol.EXTRA_TITLE, this.name);
            putExtra(bundle, NativeProtocol.EXTRA_SUBTITLE, this.caption);
            putExtra(bundle, NativeProtocol.EXTRA_DESCRIPTION, this.description);
            putExtra(bundle, NativeProtocol.EXTRA_LINK, this.link);
            putExtra(bundle, NativeProtocol.EXTRA_IMAGE, this.picture);
            putExtra(bundle, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            putExtra(bundle, NativeProtocol.EXTRA_REF, this.ref);
            bundle.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                bundle.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return bundle;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle getMethodArguments() {
            Bundle bundle = new Bundle();
            putExtra(bundle, NativeProtocol.METHOD_ARGS_TITLE, this.name);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_SUBTITLE, this.caption);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_DESCRIPTION, this.description);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_LINK, this.link);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_IMAGE, this.picture);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_REF, this.ref);
            bundle.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                bundle.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return bundle;
        }
    }

    /* loaded from: classes.dex */
    public class ShareDialogBuilder extends ShareDialogBuilderBase<ShareDialogBuilder> {
        public ShareDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG);
        }
    }

    /* loaded from: classes.dex */
    abstract class PhotoDialogBuilderBase<CONCRETE extends PhotoDialogBuilderBase<?>> extends Builder<CONCRETE> {
        static int MAXIMUM_PHOTO_COUNT = 6;
        private ArrayList<String> friends;
        private ArrayList<String> imageAttachmentUrls;
        private String place;

        abstract int getMaximumNumberOfPhotos();

        public PhotoDialogBuilderBase(Activity activity) {
            super(activity);
            this.imageAttachmentUrls = new ArrayList<>();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setPlace(String str) {
            this.place = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setFriends(List<String> list) {
            this.friends = list == null ? null : new ArrayList<>(list);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE addPhotos(Collection<Bitmap> collection) {
            this.imageAttachmentUrls.addAll(addImageAttachments(collection));
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE addPhotoFiles(Collection<File> collection) {
            this.imageAttachmentUrls.addAll(addImageAttachmentFiles(collection));
            return this;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        void validate() {
            super.validate();
            if (this.imageAttachmentUrls.isEmpty()) {
                throw new FacebookException("Must specify at least one photo.");
            }
            if (this.imageAttachmentUrls.size() > getMaximumNumberOfPhotos()) {
                throw new FacebookException(String.format("Cannot add more than %d photos.", Integer.valueOf(getMaximumNumberOfPhotos())));
            }
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle setBundleExtras(Bundle bundle) {
            putExtra(bundle, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(bundle, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(bundle, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            bundle.putStringArrayList(NativeProtocol.EXTRA_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                bundle.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return bundle;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle getMethodArguments() {
            Bundle bundle = new Bundle();
            putExtra(bundle, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            bundle.putStringArrayList(NativeProtocol.METHOD_ARGS_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                bundle.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return bundle;
        }
    }

    /* loaded from: classes.dex */
    public class PhotoShareDialogBuilder extends PhotoDialogBuilderBase<PhotoShareDialogBuilder> {
        public PhotoShareDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.PHOTOS);
        }

        @Override // com.facebook.widget.FacebookDialog.PhotoDialogBuilderBase
        int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }
    }

    /* loaded from: classes.dex */
    public class PhotoMessageDialogBuilder extends PhotoDialogBuilderBase<PhotoMessageDialogBuilder> {
        @Override // com.facebook.widget.FacebookDialog.PhotoDialogBuilderBase
        public /* bridge */ /* synthetic */ PhotoMessageDialogBuilder setFriends(List list) {
            return setFriends2((List<String>) list);
        }

        public PhotoMessageDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, MessageDialogFeature.PHOTOS);
        }

        @Override // com.facebook.widget.FacebookDialog.PhotoDialogBuilderBase
        int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }

        @Override // com.facebook.widget.FacebookDialog.PhotoDialogBuilderBase
        public PhotoMessageDialogBuilder setPlace(String str) {
            return this;
        }

        @Override // com.facebook.widget.FacebookDialog.PhotoDialogBuilderBase
        /* renamed from: setFriends, reason: avoid collision after fix types in other method */
        public PhotoMessageDialogBuilder setFriends2(List<String> list) {
            return this;
        }
    }

    /* loaded from: classes.dex */
    abstract class VideoDialogBuilderBase<CONCRETE extends VideoDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private String place;
        private String videoAttachmentUrl;

        public VideoDialogBuilderBase(Activity activity) {
            super(activity);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setPlace(String str) {
            this.place = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE addVideoFile(File file) {
            this.videoAttachmentUrl = addVideoAttachmentFile(file);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setVideoUrl(String str) {
            this.videoAttachmentUrl = str;
            return this;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        void validate() {
            super.validate();
            if (this.videoAttachmentUrl == null || this.videoAttachmentUrl.isEmpty()) {
                throw new FacebookException("Must specify at least one video.");
            }
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle getMethodArguments() {
            Bundle bundle = new Bundle();
            putExtra(bundle, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            bundle.putString(NativeProtocol.METHOD_ARGS_VIDEO, this.videoAttachmentUrl);
            return bundle;
        }
    }

    /* loaded from: classes.dex */
    public class VideoShareDialogBuilder extends VideoDialogBuilderBase<VideoShareDialogBuilder> {
        public VideoShareDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.VIDEO);
        }
    }

    /* loaded from: classes.dex */
    public class VideoMessageDialogBuilder extends VideoDialogBuilderBase<VideoMessageDialogBuilder> {
        public VideoMessageDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<MessageDialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, MessageDialogFeature.VIDEO);
        }

        @Override // com.facebook.widget.FacebookDialog.VideoDialogBuilderBase
        public VideoMessageDialogBuilder setPlace(String str) {
            return this;
        }
    }

    /* loaded from: classes.dex */
    public class MessageDialogBuilder extends ShareDialogBuilderBase<MessageDialogBuilder> {
        @Override // com.facebook.widget.FacebookDialog.ShareDialogBuilderBase
        public /* bridge */ /* synthetic */ MessageDialogBuilder setFriends(List list) {
            return setFriends((List<String>) list);
        }

        public MessageDialogBuilder(Activity activity) {
            super(activity);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.widget.FacebookDialog.ShareDialogBuilderBase
        public MessageDialogBuilder setPlace(String str) {
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.widget.FacebookDialog.ShareDialogBuilderBase
        public MessageDialogBuilder setFriends(List<String> list) {
            return this;
        }
    }

    /* loaded from: classes.dex */
    abstract class OpenGraphDialogBuilderBase<CONCRETE extends OpenGraphDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private OpenGraphAction action;
        private String actionType;
        private boolean dataErrorsFatal;
        private String previewPropertyName;

        @Deprecated
        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction openGraphAction, String str, String str2) {
            super(activity);
            Validate.notNull(openGraphAction, "action");
            Validate.notNullOrEmpty(str, "actionType");
            Validate.notNullOrEmpty(str2, "previewPropertyName");
            if (openGraphAction.getProperty(str2) == null) {
                throw new IllegalArgumentException("A property named \"" + str2 + "\" was not found on the action.  The name of the preview property must match the name of an action property.");
            }
            String type = openGraphAction.getType();
            if (!Utility.isNullOrEmpty(type) && !type.equals(str)) {
                throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
            }
            this.action = openGraphAction;
            this.actionType = str;
            this.previewPropertyName = str2;
        }

        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction openGraphAction, String str) {
            super(activity);
            Validate.notNull(openGraphAction, "action");
            Validate.notNullOrEmpty(openGraphAction.getType(), "action.getType()");
            Validate.notNullOrEmpty(str, "previewPropertyName");
            if (openGraphAction.getProperty(str) == null) {
                throw new IllegalArgumentException("A property named \"" + str + "\" was not found on the action.  The name of the preview property must match the name of an action property.");
            }
            this.action = openGraphAction;
            this.actionType = openGraphAction.getType();
            this.previewPropertyName = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setDataErrorsFatal(boolean z) {
            this.dataErrorsFatal = z;
            return this;
        }

        public CONCRETE setImageAttachmentsForAction(List<Bitmap> list) {
            return setImageAttachmentsForAction(list, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setImageAttachmentsForAction(List<Bitmap> list, boolean z) {
            Validate.containsNoNulls(list, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachments(list), z);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForAction(List<File> list) {
            return setImageAttachmentFilesForAction(list, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setImageAttachmentFilesForAction(List<File> list, boolean z) {
            Validate.containsNoNulls(list, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachmentFiles(list), z);
            return this;
        }

        private void updateActionAttachmentUrls(List<String> list, boolean z) {
            List<JSONObject> image = this.action.getImage();
            List<JSONObject> arrayList = image == null ? new ArrayList(list.size()) : image;
            for (String str : list) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(NativeProtocol.IMAGE_URL_KEY, str);
                    if (z) {
                        jSONObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    }
                    arrayList.add(jSONObject);
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
            this.action.setImage(arrayList);
        }

        public CONCRETE setImageAttachmentsForObject(String str, List<Bitmap> list) {
            return setImageAttachmentsForObject(str, list, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setImageAttachmentsForObject(String str, List<Bitmap> list, boolean z) {
            Validate.notNull(str, "objectProperty");
            Validate.containsNoNulls(list, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(str, addImageAttachments(list), z);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForObject(String str, List<File> list) {
            return setImageAttachmentFilesForObject(str, list, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CONCRETE setImageAttachmentFilesForObject(String str, List<File> list, boolean z) {
            Validate.notNull(str, "objectProperty");
            Validate.containsNoNulls(list, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(str, addImageAttachmentFiles(list), z);
            return this;
        }

        void updateObjectAttachmentUrls(String str, List<String> list, boolean z) {
            try {
                OpenGraphObject openGraphObject = (OpenGraphObject) this.action.getPropertyAs(str, OpenGraphObject.class);
                if (openGraphObject == null) {
                    throw new IllegalArgumentException("Action does not contain a property '" + str + "'");
                }
                if (!openGraphObject.getCreateObject()) {
                    throw new IllegalArgumentException("The Open Graph object in '" + str + "' is not marked for creation");
                }
                GraphObjectList<GraphObject> image = openGraphObject.getImage();
                GraphObjectList<GraphObject> createList = image == null ? GraphObject.Factory.createList(GraphObject.class) : image;
                for (String str2 : list) {
                    GraphObject create = GraphObject.Factory.create();
                    create.setProperty(NativeProtocol.IMAGE_URL_KEY, str2);
                    if (z) {
                        create.setProperty(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    }
                    createList.add(create);
                }
                openGraphObject.setImage(createList);
            } catch (FacebookGraphObjectException e) {
                throw new IllegalArgumentException("Property '" + str + "' is not a graph object");
            }
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle setBundleExtras(Bundle bundle) {
            putExtra(bundle, NativeProtocol.EXTRA_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(bundle, NativeProtocol.EXTRA_ACTION_TYPE, this.actionType);
            bundle.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            putExtra(bundle, NativeProtocol.EXTRA_ACTION, flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
            return bundle;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle getMethodArguments() {
            Bundle bundle = new Bundle();
            putExtra(bundle, NativeProtocol.METHOD_ARGS_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_ACTION_TYPE, this.actionType);
            bundle.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            putExtra(bundle, NativeProtocol.METHOD_ARGS_ACTION, flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
            return bundle;
        }

        private JSONObject flattenChildrenOfGraphObject(JSONObject jSONObject) {
            try {
                JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!next.equalsIgnoreCase(Utilities.DB_KEY_IMAGE)) {
                        jSONObject2.put(next, flattenObject(jSONObject2.get(next)));
                    }
                }
                return jSONObject2;
            } catch (JSONException e) {
                throw new FacebookException(e);
            }
        }

        private Object flattenObject(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                if (!jSONObject.optBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY)) {
                    if (jSONObject.has("id")) {
                        return jSONObject.getString("id");
                    }
                    if (jSONObject.has(NativeProtocol.IMAGE_URL_KEY)) {
                        return jSONObject.getString(NativeProtocol.IMAGE_URL_KEY);
                    }
                    return obj;
                }
                return obj;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                JSONArray jSONArray2 = new JSONArray();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    jSONArray2.put(flattenObject(jSONArray.get(i)));
                }
                return jSONArray2;
            }
            return obj;
        }
    }

    /* loaded from: classes.dex */
    public class OpenGraphActionDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphActionDialogBuilder> {
        @Deprecated
        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction openGraphAction, String str, String str2) {
            super(activity, openGraphAction, str, str2);
        }

        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction openGraphAction, String str) {
            super(activity, openGraphAction, str);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
        }
    }

    /* loaded from: classes.dex */
    public class OpenGraphMessageDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphMessageDialogBuilder> {
        public OpenGraphMessageDialogBuilder(Activity activity, OpenGraphAction openGraphAction, String str) {
            super(activity, openGraphAction, str);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG);
        }
    }

    /* loaded from: classes.dex */
    public class PendingCall implements Parcelable {
        public static final Parcelable.Creator<PendingCall> CREATOR = new Parcelable.Creator<PendingCall>() { // from class: com.facebook.widget.FacebookDialog.PendingCall.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PendingCall createFromParcel(Parcel parcel) {
                return new PendingCall(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PendingCall[] newArray(int i) {
                return new PendingCall[i];
            }
        };
        private UUID callId;
        private int requestCode;
        private Intent requestIntent;

        public PendingCall(int i) {
            this.callId = UUID.randomUUID();
            this.requestCode = i;
        }

        private PendingCall(Parcel parcel) {
            this.callId = UUID.fromString(parcel.readString());
            this.requestIntent = (Intent) parcel.readParcelable(null);
            this.requestCode = parcel.readInt();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestIntent(Intent intent) {
            this.requestIntent = intent;
        }

        public Intent getRequestIntent() {
            return this.requestIntent;
        }

        public UUID getCallId() {
            return this.callId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestCode(int i) {
            this.requestCode = i;
        }

        public int getRequestCode() {
            return this.requestCode;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.callId.toString());
            parcel.writeParcelable(this.requestIntent, 0);
            parcel.writeInt(this.requestCode);
        }
    }
}
