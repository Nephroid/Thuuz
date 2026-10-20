package com.yozio.android.helpers;

import android.content.SharedPreferences;
import com.yozio.android.Constants;
import com.yozio.android.R;
import com.yozio.android.YozioService;
import com.yozio.android.async.GetConfigsTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Configs {
    public static final String KEY_CONTACTS_VIEW_SEND_BUTTON_COUNT_MULTIPLIER = "contacts_view.send_button.count_multiplier";
    public static final String KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL = "contacts_view.send_button.label";
    public static final String KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL_WITH_COUNT = "contacts_view.send_button.label_with_count";
    public static final String KEY_INVITE_SMS_BODY = "invite_sms.body";
    public static final String KEY_INVITE_SPLASH_PAGE_BODY = "invite_splash_page.body";
    public static final String KEY_URL = "app.url";
    private static Configs _instance = new Configs();
    private HashMap<String, Object> _configs;

    public static Configs getInstance() {
        return _instance;
    }

    private Configs() {
    }

    public String getConfig(String str) {
        String str2 = (String) getConfigs().get(str);
        if (str2 == null) {
            if (str.equalsIgnoreCase(KEY_URL)) {
                return getDefaultUrl();
            }
            if (str.equalsIgnoreCase(KEY_INVITE_SMS_BODY)) {
                return getDefaultInviteSms();
            }
            if (str.equalsIgnoreCase(KEY_INVITE_SPLASH_PAGE_BODY)) {
                return YozioService.getInstance().getContext().getString(R.string.yozio_invite_splash_page_body);
            }
            if (str.equalsIgnoreCase(KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL)) {
                return YozioService.getInstance().getContext().getString(R.string.yozio_send_button_label);
            }
            if (str.equalsIgnoreCase(KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL_WITH_COUNT)) {
                return YozioService.getInstance().getContext().getString(R.string.yozio_send_button_label_with_count);
            }
            if (str.equalsIgnoreCase(KEY_CONTACTS_VIEW_SEND_BUTTON_COUNT_MULTIPLIER)) {
                return YozioService.getInstance().getContext().getString(R.string.yozio_send_button_label_count_multiplier);
            }
            return null;
        }
        return str2;
    }

    public HashMap<String, Object> getConfigs() {
        if (this._configs != null) {
            return this._configs;
        }
        File file = new File(YozioService.getInstance().getContext().getFilesDir() + File.separator + Constants.YOZIO_BASE_PATH + File.separator + Constants.YOZIO_CONFIGS_PATH);
        if (!file.exists()) {
            YozioService.log(Constants.LOG_LEVEL.INFO, "Configs - no configs file, start with empty configs.");
            return new HashMap<>();
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            this._configs = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            return this._configs;
        } catch (Exception e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "Configs - failed to read local configs.");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public boolean persistConfigs(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return false;
        }
        this._configs = hashMap;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(YozioService.getInstance().getContext().getFilesDir() + File.separator + Constants.YOZIO_BASE_PATH + File.separator + Constants.YOZIO_CONFIGS_PATH)));
            objectOutputStream.writeObject(hashMap);
            objectOutputStream.close();
            return true;
        } catch (Exception e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "Configs - failed to persist configs locally.");
            e.printStackTrace();
            return false;
        }
    }

    public void initConfigs() {
        long currentTimeLong = SystemInfo.getInstance().getCurrentTimeLong();
        long lastFetchedConfigs = getLastFetchedConfigs();
        if (lastFetchedConfigs > 0 && currentTimeLong - lastFetchedConfigs < 21600) {
            YozioService.log(Constants.LOG_LEVEL.INFO, "Configs - configs still fresh, skip updating");
        } else {
            new GetConfigsTask().execute(new Void[0]);
        }
    }

    protected long getLastFetchedConfigs() {
        return YozioService.getInstance().getContext().getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0).getLong(Constants.YOZIO_SHARED_PREFERENCE_KEY_LAST_FETCHED_CONFIGS, 0L);
    }

    protected void setLastFetchedConfigs(Long l) {
        SharedPreferences.Editor edit = YozioService.getInstance().getContext().getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0).edit();
        edit.putLong(Constants.YOZIO_SHARED_PREFERENCE_KEY_LAST_FETCHED_CONFIGS, l.longValue());
        edit.commit();
    }

    public void handleRemoteConfigs(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            YozioService.log(Constants.LOG_LEVEL.WARN, "Configs - remote config was NULL.");
            return;
        }
        YozioService.log(Constants.LOG_LEVEL.INFO, "Configs - updating values from remote: " + hashMap.toString());
        if (persistConfigs(hashMap)) {
            setLastFetchedConfigs(Long.valueOf(SystemInfo.getInstance().getCurrentTimeLong()));
        }
    }

    public String getDefaultUrl() {
        String string = YozioService.getInstance().getContext().getString(R.string.default_url);
        if (string == null || string.contains("default-url.com")) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "!!!!!!!!!!!!!!!!!! Please make sure you configure the default URL from res/values/yozio.xml !!!!!!!!!!!!!!!!!!");
            YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_ERROR_DEFAULT_URL_NULL, null, null, false);
        }
        return string;
    }

    public String getDefaultInviteSms() {
        String string = YozioService.getInstance().getContext().getString(R.string.default_invite_sms);
        if (string == null || string.contains("Default Invite SMS")) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "!!!!!!!!!!!!!!!!!! Please make sure you configure the default Invite SMS from res/values/yozio.xml !!!!!!!!!!!!!!!!!!");
            YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_ERROR_DEFAULT_SMS_NULL, null, null, false);
        }
        return string;
    }
}
