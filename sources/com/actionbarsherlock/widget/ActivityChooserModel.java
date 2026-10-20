package com.actionbarsherlock.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ActivityChooserModel extends DataSetObservable {
    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private final Context mContext;
    private final String mHistoryFileName;
    private Intent mIntent;
    private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
    private static final Object sRegistryLock = new Object();
    private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
    private static final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
    private final Object mInstanceLock = new Object();
    private final List<ActivityResolveInfo> mActivites = new ArrayList();
    private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
    private ActivitySorter mActivitySorter = new DefaultSorter();
    private int mHistoryMaxSize = 50;
    private boolean mCanReadHistoricalData = true;
    private boolean mReadShareHistoryCalled = false;
    private boolean mHistoricalRecordsChanged = true;
    private final Handler mHandler = new Handler();

    /* loaded from: classes.dex */
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    /* loaded from: classes.dex */
    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    /* loaded from: classes.dex */
    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    public static ActivityChooserModel get(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (sRegistryLock) {
            activityChooserModel = sDataModelRegistry.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                sDataModelRegistry.put(str, activityChooserModel);
            }
            activityChooserModel.readHistoricalData();
        }
        return activityChooserModel;
    }

    private ActivityChooserModel(Context context, String str) {
        this.mContext = context.getApplicationContext();
        if (!TextUtils.isEmpty(str) && !str.endsWith(HISTORY_FILE_EXTENSION)) {
            this.mHistoryFileName = str + HISTORY_FILE_EXTENSION;
        } else {
            this.mHistoryFileName = str;
        }
    }

    public void setIntent(Intent intent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent != intent) {
                this.mIntent = intent;
                loadActivitiesLocked();
            }
        }
    }

    public Intent getIntent() {
        Intent intent;
        synchronized (this.mInstanceLock) {
            intent = this.mIntent;
        }
        return intent;
    }

    public int getActivityCount() {
        int size;
        synchronized (this.mInstanceLock) {
            size = this.mActivites.size();
        }
        return size;
    }

    public ResolveInfo getActivity(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.mInstanceLock) {
            resolveInfo = this.mActivites.get(i).resolveInfo;
        }
        return resolveInfo;
    }

    public int getActivityIndex(ResolveInfo resolveInfo) {
        List<ActivityResolveInfo> list = this.mActivites;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).resolveInfo == resolveInfo) {
                return i;
            }
        }
        return -1;
    }

    public Intent chooseActivity(int i) {
        ActivityResolveInfo activityResolveInfo = this.mActivites.get(i);
        ComponentName componentName = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
        Intent intent = new Intent(this.mIntent);
        intent.setComponent(componentName);
        if (this.mActivityChoserModelPolicy != null) {
            if (this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                return null;
            }
        }
        addHisoricalRecord(new HistoricalRecord(componentName, System.currentTimeMillis(), DEFAULT_HISTORICAL_RECORD_WEIGHT));
        return intent;
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        this.mActivityChoserModelPolicy = onChooseActivityListener;
    }

    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            if (!this.mActivites.isEmpty()) {
                return this.mActivites.get(0).resolveInfo;
            }
            return null;
        }
    }

    public void setDefaultActivity(int i) {
        float f;
        ActivityResolveInfo activityResolveInfo = this.mActivites.get(i);
        ActivityResolveInfo activityResolveInfo2 = this.mActivites.get(0);
        if (activityResolveInfo2 != null) {
            f = (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f;
        } else {
            f = DEFAULT_HISTORICAL_RECORD_WEIGHT;
        }
        addHisoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
    }

    private void readHistoricalData() {
        synchronized (this.mInstanceLock) {
            if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged) {
                this.mCanReadHistoricalData = false;
                this.mReadShareHistoryCalled = true;
                if (!TextUtils.isEmpty(this.mHistoryFileName)) {
                    SERIAL_EXECUTOR.execute(new HistoryLoader());
                }
            }
        }
    }

    private void persistHistoricalData() {
        synchronized (this.mInstanceLock) {
            if (!this.mReadShareHistoryCalled) {
                throw new IllegalStateException("No preceding call to #readHistoricalData");
            }
            if (this.mHistoricalRecordsChanged) {
                this.mHistoricalRecordsChanged = false;
                this.mCanReadHistoricalData = true;
                if (!TextUtils.isEmpty(this.mHistoryFileName)) {
                    SERIAL_EXECUTOR.execute(new HistoryPersister());
                }
            }
        }
    }

    public void setActivitySorter(ActivitySorter activitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter != activitySorter) {
                this.mActivitySorter = activitySorter;
                sortActivities();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortActivities() {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter != null && !this.mActivites.isEmpty()) {
                this.mActivitySorter.sort(this.mIntent, this.mActivites, Collections.unmodifiableList(this.mHistoricalRecords));
                notifyChanged();
            }
        }
    }

    public void setHistoryMaxSize(int i) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize != i) {
                this.mHistoryMaxSize = i;
                pruneExcessiveHistoricalRecordsLocked();
                sortActivities();
            }
        }
    }

    public int getHistoryMaxSize() {
        int i;
        synchronized (this.mInstanceLock) {
            i = this.mHistoryMaxSize;
        }
        return i;
    }

    public int getHistorySize() {
        int size;
        synchronized (this.mInstanceLock) {
            size = this.mHistoricalRecords.size();
        }
        return size;
    }

    private boolean addHisoricalRecord(HistoricalRecord historicalRecord) {
        boolean add;
        synchronized (this.mInstanceLock) {
            add = this.mHistoricalRecords.add(historicalRecord);
            if (add) {
                this.mHistoricalRecordsChanged = true;
                pruneExcessiveHistoricalRecordsLocked();
                persistHistoricalData();
                sortActivities();
            }
        }
        return add;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pruneExcessiveHistoricalRecordsLocked() {
        List<HistoricalRecord> list = this.mHistoricalRecords;
        int size = list.size() - this.mHistoryMaxSize;
        if (size > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < size; i++) {
                list.remove(0);
            }
        }
    }

    private void loadActivitiesLocked() {
        this.mActivites.clear();
        if (this.mIntent != null) {
            List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
            int size = queryIntentActivities.size();
            for (int i = 0; i < size; i++) {
                this.mActivites.add(new ActivityResolveInfo(queryIntentActivities.get(i)));
            }
            sortActivities();
            return;
        }
        notifyChanged();
    }

    /* loaded from: classes.dex */
    public final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.activity = componentName;
            this.time = j;
            this.weight = f;
        }

        public int hashCode() {
            return (((((this.activity == null ? 0 : this.activity.hashCode()) + 31) * 31) + ((int) (this.time ^ (this.time >>> 32)))) * 31) + Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                HistoricalRecord historicalRecord = (HistoricalRecord) obj;
                if (this.activity == null) {
                    if (historicalRecord.activity != null) {
                        return false;
                    }
                } else if (!this.activity.equals(historicalRecord.activity)) {
                    return false;
                }
                return this.time == historicalRecord.time && Float.floatToIntBits(this.weight) == Float.floatToIntBits(historicalRecord.weight);
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("; activity:").append(this.activity);
            sb.append("; time:").append(this.time);
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo) obj).weight);
        }

        @Override // java.lang.Comparable
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("resolveInfo:").append(this.resolveInfo.toString());
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<String, ActivityResolveInfo> mPackageNameToActivityMap;

        private DefaultSorter() {
            this.mPackageNameToActivityMap = new HashMap();
        }

        @Override // com.actionbarsherlock.widget.ActivityChooserModel.ActivitySorter
        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            float f;
            Map<String, ActivityResolveInfo> map = this.mPackageNameToActivityMap;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ActivityResolveInfo activityResolveInfo = list.get(i);
                activityResolveInfo.weight = 0.0f;
                map.put(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo);
            }
            int size2 = list2.size() - 1;
            float f2 = ActivityChooserModel.DEFAULT_HISTORICAL_RECORD_WEIGHT;
            int i2 = size2;
            while (i2 >= 0) {
                HistoricalRecord historicalRecord = list2.get(i2);
                ActivityResolveInfo activityResolveInfo2 = map.get(historicalRecord.activity.getPackageName());
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight = (historicalRecord.weight * f2) + activityResolveInfo2.weight;
                    f = WEIGHT_DECAY_COEFFICIENT * f2;
                } else {
                    f = f2;
                }
                i2--;
                f2 = f;
            }
            Collections.sort(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class HistoryLoader implements Runnable {
        private HistoryLoader() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FileInputStream openFileInput;
            try {
                try {
                    openFileInput = ActivityChooserModel.this.mContext.openFileInput(ActivityChooserModel.this.mHistoryFileName);
                    try {
                        try {
                            XmlPullParser newPullParser = Xml.newPullParser();
                            newPullParser.setInput(openFileInput, null);
                            for (int i = 0; i != 1 && i != 2; i = newPullParser.next()) {
                            }
                            if (!ActivityChooserModel.TAG_HISTORICAL_RECORDS.equals(newPullParser.getName())) {
                                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                            }
                            ArrayList arrayList = new ArrayList();
                            while (true) {
                                int next = newPullParser.next();
                                if (next == 1) {
                                    synchronized (ActivityChooserModel.this.mInstanceLock) {
                                        LinkedHashSet linkedHashSet = new LinkedHashSet(arrayList);
                                        List list = ActivityChooserModel.this.mHistoricalRecords;
                                        for (int size = list.size() - 1; size >= 0; size--) {
                                            linkedHashSet.add((HistoricalRecord) list.get(size));
                                        }
                                        if (list.size() == linkedHashSet.size()) {
                                            if (openFileInput != null) {
                                                try {
                                                    openFileInput.close();
                                                    return;
                                                } catch (IOException e) {
                                                    return;
                                                }
                                            }
                                            return;
                                        }
                                        list.clear();
                                        list.addAll(linkedHashSet);
                                        ActivityChooserModel.this.mHistoricalRecordsChanged = true;
                                        ActivityChooserModel.this.mHandler.post(new Runnable() { // from class: com.actionbarsherlock.widget.ActivityChooserModel.HistoryLoader.1
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                ActivityChooserModel.this.pruneExcessiveHistoricalRecordsLocked();
                                                ActivityChooserModel.this.sortActivities();
                                            }
                                        });
                                        if (openFileInput != null) {
                                            try {
                                                openFileInput.close();
                                                return;
                                            } catch (IOException e2) {
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                }
                                if (next != 3 && next != 4) {
                                    if (!ActivityChooserModel.TAG_HISTORICAL_RECORD.equals(newPullParser.getName())) {
                                        throw new XmlPullParserException("Share records file not well-formed.");
                                    }
                                    arrayList.add(new HistoricalRecord(newPullParser.getAttributeValue(null, ActivityChooserModel.ATTRIBUTE_ACTIVITY), Long.parseLong(newPullParser.getAttributeValue(null, ActivityChooserModel.ATTRIBUTE_TIME)), Float.parseFloat(newPullParser.getAttributeValue(null, ActivityChooserModel.ATTRIBUTE_WEIGHT))));
                                }
                            }
                        } catch (XmlPullParserException e3) {
                            Log.e(ActivityChooserModel.LOG_TAG, "Error reading historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e3);
                            if (openFileInput != null) {
                                try {
                                    openFileInput.close();
                                } catch (IOException e4) {
                                }
                            }
                        }
                    } catch (IOException e5) {
                        Log.e(ActivityChooserModel.LOG_TAG, "Error reading historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e5);
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e6) {
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class HistoryPersister implements Runnable {
        private HistoryPersister() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList;
            synchronized (ActivityChooserModel.this.mInstanceLock) {
                arrayList = new ArrayList(ActivityChooserModel.this.mHistoricalRecords);
            }
            try {
                FileOutputStream openFileOutput = ActivityChooserModel.this.mContext.openFileOutput(ActivityChooserModel.this.mHistoryFileName, 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    try {
                        try {
                            newSerializer.setOutput(openFileOutput, null);
                            newSerializer.startDocument(HTTP.UTF_8, true);
                            newSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                            int size = arrayList.size();
                            for (int i = 0; i < size; i++) {
                                HistoricalRecord historicalRecord = (HistoricalRecord) arrayList.remove(0);
                                newSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                                newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_ACTIVITY, historicalRecord.activity.flattenToString());
                                newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_TIME, String.valueOf(historicalRecord.time));
                                newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_WEIGHT, String.valueOf(historicalRecord.weight));
                                newSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                            }
                            newSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                            newSerializer.endDocument();
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (IOException e) {
                                }
                            }
                        } catch (IllegalArgumentException e2) {
                            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e2);
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (IOException e3) {
                                }
                            }
                        }
                    } catch (Throwable th) {
                        if (openFileOutput != null) {
                            try {
                                openFileOutput.close();
                            } catch (IOException e4) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e5);
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (IllegalStateException e7) {
                    Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e7);
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e8) {
                        }
                    }
                }
            } catch (FileNotFoundException e9) {
                Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + ActivityChooserModel.this.mHistoryFileName, e9);
            }
        }
    }
}
