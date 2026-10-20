package com.yozio.android.async;

import android.os.AsyncTask;
import com.yozio.android.helpers.Api;
import com.yozio.android.helpers.Configs;
import com.yozio.android.interfaces.GetUrlCallback;

/* loaded from: classes.dex */
public class GetUrlTask extends AsyncTask<Void, Void, String> {
    private final GetUrlCallback _callback;

    public GetUrlTask(GetUrlCallback getUrlCallback) {
        this._callback = getUrlCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Void... voidArr) {
        String url = Api.getInstance().getUrl();
        return url == null ? Configs.getInstance().getDefaultUrl() : url;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        if (this._callback != null) {
            this._callback.handleUrl(str);
        }
    }
}
