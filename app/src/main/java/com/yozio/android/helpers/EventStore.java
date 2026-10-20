package com.yozio.android.helpers;

import android.content.SharedPreferences;
import com.samsung.android.sdk.richnotification.Utilities;
import com.yozio.android.Constants;
import com.yozio.android.YozioService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EventStore {
    private static EventStore _instance = new EventStore();
    private final ThreadPoolExecutor _executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    public static EventStore getInstance() {
        return _instance;
    }

    private EventStore() {
    }

    public void add(String str, String str2, HashMap<String, Object> hashMap, boolean z) {
        this._executor.submit(new AddEventTask(this, str, str2, hashMap, z));
    }

    protected void doAdd(String str, String str2, HashMap<String, Object> hashMap, boolean z) {
        if (count() >= 5000) {
            flush();
            return;
        }
        YozioService.log(Constants.LOG_LEVEL.INFO, "track event name:[" + (str != null ? str : "") + "], value:[" + (str2 != null ? str2 : "") + "], properties:[" + (hashMap != null ? hashMap.toString() : "") + "], forceFlush:[" + Boolean.toString(z) + "]");
        addEventToQueue(str, str2, hashMap);
        if (count() >= 20) {
            z = true;
        }
        if (z) {
            flush();
        }
    }

    public void flush() {
        this._executor.submit(new FlushEventsTask(this));
    }

    protected void doFlush() {
        BufferedReader bufferedReader;
        File file = new File(YozioService.getInstance().getContext().getFilesDir() + File.separator + Constants.YOZIO_BASE_PATH + File.separator + Constants.YOZIO_EVENT_QUEUE_PATH);
        YozioService.log(Constants.LOG_LEVEL.INFO, "flushing EventQueue...");
        if (!file.exists()) {
            YozioService.log(Constants.LOG_LEVEL.INFO, "no EventQueue file, start with fresh EventQueue.");
            return;
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (Exception e) {
            e = e;
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder("[");
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else {
                    sb.append(String.valueOf(readLine) + ",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            JSONArray jSONArray = new JSONArray(sb.toString());
            bufferedReader.close();
            if (!Api.getInstance().flushEvents(jSONArray)) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to flush EventQueue: " + count());
                return;
            }
            file.delete();
            reset();
            YozioService.log(Constants.LOG_LEVEL.INFO, "EventQueue flushed: " + count());
        } catch (Exception e2) {
            e = e2;
            YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to read data from EventQueue file!");
            e.printStackTrace();
            try {
                bufferedReader.close();
            } catch (Exception e3) {
            }
        }
    }

    protected void addEventToQueue(String str, String str2, HashMap<String, Object> hashMap) {
        File file = new File(YozioService.getInstance().getContext().getFilesDir() + File.separator + Constants.YOZIO_BASE_PATH + File.separator + Constants.YOZIO_EVENT_QUEUE_PATH);
        JSONObject jSONObject = new JSONObject();
        BufferedWriter bufferedWriter = null;
        if (str != null) {
            try {
                jSONObject.put(Utilities.DB_KEY_IMAGE_NAME, str);
            } catch (Exception e) {
                e = e;
                YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to add to EventQueue!");
                e.printStackTrace();
                try {
                    bufferedWriter.close();
                } catch (Exception e2) {
                    return;
                }
            }
        }
        if (str2 != null) {
            jSONObject.put("value", str2);
        }
        if (hashMap != null) {
            jSONObject.put("properties", new JSONObject(hashMap));
        }
        jSONObject.put("timestamp", Long.toString(SystemInfo.getInstance().getCurrentTimeLong()));
        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, true));
        try {
            bufferedWriter2.write(String.valueOf(jSONObject.toString()) + "\n");
            bufferedWriter2.close();
            increment();
        } catch (Exception e3) {
            e = e3;
            bufferedWriter = bufferedWriter2;
            YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to add to EventQueue!");
            e.printStackTrace();
            bufferedWriter.close();
        }
    }

    protected int count() {
        return YozioService.getInstance().getContext().getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0).getInt(Constants.YOZIO_SHARED_PREFERENCE_KEY_EVENTS_COUNT, 0);
    }

    protected void increment() {
        setCount(count() + 1);
    }

    protected void reset() {
        setCount(0);
    }

    protected void setCount(int i) {
        SharedPreferences.Editor edit = YozioService.getInstance().getContext().getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0).edit();
        edit.putInt(Constants.YOZIO_SHARED_PREFERENCE_KEY_EVENTS_COUNT, i);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AddEventTask implements Runnable {
        private final boolean _forceFlush;
        private final String _name;
        private final HashMap<String, Object> _properties;
        private final String _value;
        private final EventStore _yozioEventStore;

        AddEventTask(EventStore eventStore, String str, String str2, HashMap<String, Object> hashMap, boolean z) {
            this._yozioEventStore = eventStore;
            this._name = str;
            this._value = str2;
            this._properties = hashMap;
            this._forceFlush = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            this._yozioEventStore.doAdd(this._name, this._value, this._properties, this._forceFlush);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FlushEventsTask implements Runnable {
        private final EventStore _yozioEventStore;

        FlushEventsTask(EventStore eventStore) {
            this._yozioEventStore = eventStore;
        }

        @Override // java.lang.Runnable
        public void run() {
            this._yozioEventStore.doFlush();
        }
    }
}
