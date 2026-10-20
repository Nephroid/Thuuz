package com.yozio.android;

/* loaded from: classes.dex */
public interface Constants {
    public static final String YOZIO_API_BASE_URL = "http://api.yoz.io/";
    public static final String YOZIO_BASE_PATH = "Yozio";
    public static final String YOZIO_CONFIGS_PATH = "Configs.data";
    public static final int YOZIO_CONFIGS_UPDATE_FREQUENCY = 21600;
    public static final String YOZIO_EVENT_BASE_URL = "http://e.yoz.io/";
    public static final String YOZIO_EVENT_NAME_APP_INSTALL = "app_install";
    public static final String YOZIO_EVENT_NAME_APP_ON_CREATE = "app_on_create";
    public static final String YOZIO_EVENT_NAME_ERROR_DEFAULT_SMS_NULL = "default_sms_null";
    public static final String YOZIO_EVENT_NAME_ERROR_DEFAULT_URL_NULL = "default_url_null";
    public static final String YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_CANCELED = "sms_deliver_result_canceled";
    public static final String YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_OK = "sms_deliver_result_ok";
    public static final String YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_UNKNOWN_ERROR = "sms_deliver_result_unknown_error";
    public static final String YOZIO_EVENT_NAME_SMS_INVALID_NUMBER = "sms_invalid_number";
    public static final String YOZIO_EVENT_NAME_SMS_OVER_LIMIT = "sms_over_limit";
    public static final String YOZIO_EVENT_NAME_SMS_SEND_EXCEPTION = "sms_send_exception";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_GENERIC_FAILURE = "sms_sent_result_generic_failure";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_NO_SERVICE = "sms_sent_result_no_service";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_NULL_PDU = "sms_sent_result_error_null_pdu";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_RADIO_OFF = "sms_sent_result_radio_off";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_OK = "sms_sent_result_ok";
    public static final String YOZIO_EVENT_NAME_SMS_SENT_RESULT_UNKNOWN_ERROR = "sms_sent_result_unknown_error";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_DESELECT_ALL = "user_contacts_list_deselect_all";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_INVITE_DONE = "user_contacts_list_invite_done";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SCROLL = "user_contacts_list_scroll";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SEARCH = "user_contacts_list_search";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SEARCH_TERM = "user_contacts_list_search_term";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_ALL = "user_contacts_list_select_all";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_INDIVIDUAL_EMAIL = "user_contacts_list_select_individual_email";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_INDIVIDUAL_PHONE = "user_contacts_list_select_individual_sms";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SEND = "user_contacts_list_send";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SMS_SENT = "user_contacts_list_invite_sms_sent";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_UNSELECT_INDIVIDUAL_EMAIL = "user_contacts_list_unselect_individual_email";
    public static final String YOZIO_EVENT_NAME_USER_CONTACTS_LIST_UNSELECT_INDIVIDUAL_PHONE = "user_contacts_list_unselect_individual_sms";
    public static final String YOZIO_EVENT_NAME_USER_SAW_INVITE_SPLASH_PAGE = "user_saw_invite_splash_page";
    public static final String YOZIO_EVENT_NAME_USER_START_INVITE = "user_start_invite";
    public static final int YOZIO_EVENT_QUEUE_AUTOFLUSH = 20;
    public static final int YOZIO_EVENT_QUEUE_MAX_SIZE = 5000;
    public static final String YOZIO_EVENT_QUEUE_PATH = "EventQueue.data";
    public static final String YOZIO_INSTALL_TRACKED_PATH = "InstallTracked";
    public static final boolean YOZIO_LOG_IMAGE_CACHE = false;
    public static final boolean YOZIO_LOG_IMAGE_LOADER = false;
    public static final String YOZIO_SCHEMA = "yozio";
    public static final String YOZIO_SDK_VERSION = "yozio-android-1.0.8";
    public static final String YOZIO_SERVICE_HANDLER = "YozioServiceHandler";
    public static final String YOZIO_SHARED_PREFERENCE_KEY_EVENTS_COUNT = "eventsCount";
    public static final String YOZIO_SHARED_PREFERENCE_KEY_INSTALL_TRACKED = "installTracked";
    public static final String YOZIO_SHARED_PREFERENCE_KEY_LAST_FETCHED_CONFIGS = "lastFetchedConfigs";
    public static final String YOZIO_SHARED_PREFERENCE_KEY_YOZIO_UDID = "yozioudid";
    public static final String YOZIO_SHARED_PREFERENCE_NAME = "come.yozio.android.PREFERENCES";
    public static final int YOZIO_SMS_SEND_LIMIT = 3000;

    /* loaded from: classes.dex */
    public enum LOG_LEVEL {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static LOG_LEVEL[] valuesCustom() {
            LOG_LEVEL[] valuesCustom = values();
            int length = valuesCustom.length;
            LOG_LEVEL[] log_levelArr = new LOG_LEVEL[length];
            System.arraycopy(valuesCustom, 0, log_levelArr, 0, length);
            return log_levelArr;
        }
    }
}
