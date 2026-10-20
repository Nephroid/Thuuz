package com.yozio.android.async;

import android.os.AsyncTask;
import com.yozio.android.helpers.Api;
import com.yozio.android.helpers.Configs;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetConfigsTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public HashMap<String, Object> doInBackground(Void... voidArr) {
        return Api.getInstance().getConfigs();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(HashMap<String, Object> hashMap) {
        Configs.getInstance().handleRemoteConfigs(hashMap);
    }
}
