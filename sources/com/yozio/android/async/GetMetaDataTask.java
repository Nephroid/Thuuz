package com.yozio.android.async;

import android.os.AsyncTask;
import com.yozio.android.helpers.Api;
import com.yozio.android.interfaces.GetMetaDataCallback;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetMetaDataTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
    private final GetMetaDataCallback _callback;

    public GetMetaDataTask(GetMetaDataCallback getMetaDataCallback) {
        this._callback = getMetaDataCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public HashMap<String, Object> doInBackground(Void... voidArr) {
        return Api.getInstance().getMetaData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(HashMap<String, Object> hashMap) {
        if (this._callback != null) {
            this._callback.handleMetaData(hashMap);
        }
    }
}
