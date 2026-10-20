package com.yozio.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.yozio.android.Constants;
import com.yozio.android.async.GetMetaDataTask;
import com.yozio.android.async.GetUrlTask;
import com.yozio.android.helpers.Configs;
import com.yozio.android.helpers.EventStore;
import com.yozio.android.helpers.SystemInfo;
import com.yozio.android.interfaces.GetMetaDataCallback;
import com.yozio.android.interfaces.GetUrlCallback;
import com.yozio.android.invites.ContactsListActivity;
import com.yozio.android.invites.InviteSplashPageActivity;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes.dex */
public class YozioService {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$yozio$android$Constants$LOG_LEVEL;
    private static YozioService _instance = new YozioService();
    private String _appKey;
    private Context _context;
    private String _deviceId;
    private GetMetaDataCallback _metadataCallback;
    private String _secretKey;

    static /* synthetic */ int[] $SWITCH_TABLE$com$yozio$android$Constants$LOG_LEVEL() {
        int[] iArr = $SWITCH_TABLE$com$yozio$android$Constants$LOG_LEVEL;
        if (iArr == null) {
            iArr = new int[Constants.LOG_LEVEL.valuesCustom().length];
            try {
                iArr[Constants.LOG_LEVEL.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Constants.LOG_LEVEL.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Constants.LOG_LEVEL.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Constants.LOG_LEVEL.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Constants.LOG_LEVEL.WARN.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            $SWITCH_TABLE$com$yozio$android$Constants$LOG_LEVEL = iArr;
        }
        return iArr;
    }

    public static YozioService getInstance() {
        return _instance;
    }

    private YozioService() {
    }

    public static void log(Constants.LOG_LEVEL log_level, String str) {
        getInstance().logToConsole(log_level, str);
    }

    public void setAppKeyAndSecretKey(Context context, String str, String str2, GetMetaDataCallback getMetaDataCallback) {
        this._context = context;
        this._appKey = str;
        this._secretKey = str2;
        this._metadataCallback = getMetaDataCallback;
        validateKeys();
        initYozioDirectory();
        trackInstall();
        trackEvent(Constants.YOZIO_EVENT_NAME_APP_ON_CREATE, null, null, false);
        Configs.getInstance().initConfigs();
    }

    public Class<?> getInviteActivityClass() {
        String config = Configs.getInstance().getConfig(Configs.KEY_INVITE_SPLASH_PAGE_BODY);
        return (config == null || config.trim().equalsIgnoreCase("")) ? ContactsListActivity.class : InviteSplashPageActivity.class;
    }

    public void getUrl(GetUrlCallback getUrlCallback) {
        new GetUrlTask(getUrlCallback).execute(new Void[0]);
    }

    public void trackInviteActivityShown() {
        trackEvent(Constants.YOZIO_EVENT_NAME_USER_START_INVITE, null, null, false);
    }

    public void trackInvites(int i, String str, boolean z) {
        String num = Integer.toString(i);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("channel", str);
        hashMap.put("count", Integer.valueOf(i));
        trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SEND, num, hashMap, z);
    }

    protected void logToConsole(Constants.LOG_LEVEL log_level, String str) {
        if (Yozio.YOZIO_ENABLE_LOGGING) {
            switch ($SWITCH_TABLE$com$yozio$android$Constants$LOG_LEVEL()[log_level.ordinal()]) {
                case 1:
                    Log.v("YOZIO", str);
                    return;
                case 2:
                    Log.d("YOZIO", str);
                    return;
                case 3:
                    Log.i("YOZIO", str);
                    return;
                case 4:
                    Log.w("YOZIO", str);
                    return;
                case 5:
                    Log.e("YOZIO", str);
                    return;
                default:
                    return;
            }
        }
    }

    protected void validateKeys() {
        if (this._appKey == null) {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!! Yozio APP_KEY cannot be nil. You can find your APP_KEY on the app overview page (Your App > Overview) on Yozio website.");
        }
        if (this._appKey.contains("APP_KEY")) {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!! Please replace the APP_KEY with your own APP_KEY. You can find your APP_KEY on the app overview page (Your App > Overview) on Yozio website.");
        }
        if (this._secretKey == null) {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!! Yozio SECRET_KEY cannot be nil. You can find your SECRET_KEY on the app overview page (Your App > Overview) on Yozio website.");
        }
        if (this._secretKey.contains("SECRET_KEY")) {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!! Please replace the SECRET_KEY with your own SECRET_KEY. You can find your SECRET_KEY on the app overview page (Your App > Overview) on Yozio website.");
        }
    }

    protected void initYozioDirectory() {
        File file = new File(this._context.getFilesDir() + File.separator + Constants.YOZIO_BASE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    protected void trackInstall() {
        SharedPreferences sharedPreferences = this._context.getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0);
        if (sharedPreferences.getBoolean(Constants.YOZIO_SHARED_PREFERENCE_KEY_INSTALL_TRACKED, false)) {
            log(Constants.LOG_LEVEL.INFO, "installTracked already exists.");
            if (this._metadataCallback != null) {
                this._metadataCallback.handleMetaData(new HashMap<>());
                return;
            }
            return;
        }
        if (this._metadataCallback != null) {
            new GetMetaDataTask(this._metadataCallback).execute(new Void[0]);
        }
        log(Constants.LOG_LEVEL.INFO, "tracking new app install.");
        trackEvent(Constants.YOZIO_EVENT_NAME_APP_INSTALL, null, null, true);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(Constants.YOZIO_SHARED_PREFERENCE_KEY_INSTALL_TRACKED, true);
        edit.commit();
    }

    public void trackEvent(String str, String str2, HashMap<String, Object> hashMap, boolean z) {
        EventStore.getInstance().add(str, str2, hashMap, z);
    }

    public Context getContext() {
        return this._context;
    }

    public String getAppKey() {
        return this._appKey;
    }

    public String getDeviceId() {
        if (this._deviceId != null) {
            return this._deviceId;
        }
        this._deviceId = SystemInfo.getInstance().getYozioUDID();
        return this._deviceId;
    }

    public void toast(Activity activity, String str) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.yozio_toast, (ViewGroup) activity.findViewById(R.id.toast_layout_root));
        ((TextView) inflate.findViewById(R.id.text)).setText(str);
        Toast toast = new Toast(this._context);
        toast.setGravity(16, 0, 0);
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }
}
