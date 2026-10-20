package com.yozio.android.async;

import android.os.AsyncTask;
import com.yozio.android.helpers.Api;
import com.yozio.android.interfaces.SendEmailsCallback;

/* loaded from: classes.dex */
public class SendEmailsTask extends AsyncTask<Void, Void, Boolean> {
    private final SendEmailsCallback _callback;
    private final String _fromEmail;
    private final String _fromName;
    private final Object[] _toEmails;

    public SendEmailsTask(String str, String str2, Object[] objArr, SendEmailsCallback sendEmailsCallback) {
        this._fromEmail = str;
        this._fromName = str2;
        this._toEmails = objArr;
        this._callback = sendEmailsCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(Void... voidArr) {
        return Boolean.valueOf(Api.getInstance().sendEmails(this._fromEmail, this._fromName, this._toEmails));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        if (this._callback != null) {
            this._callback.sendEmailStatus(bool.booleanValue());
        }
    }
}
