package com.yozio.android.helpers;

import com.yozio.android.async.SendEmailsTask;
import com.yozio.android.interfaces.SendEmailsCallback;

/* loaded from: classes.dex */
public class Email {
    private static Email _instance = new Email();
    private String _name = null;
    private String _email = null;

    public static Email getInstance() {
        return _instance;
    }

    private Email() {
    }

    public void setSenderNameAndEmail(String str, String str2) {
        this._name = str;
        this._email = str2;
    }

    public void sendEmails(Object[] objArr, SendEmailsCallback sendEmailsCallback) {
        new SendEmailsTask(this._email, this._name, objArr, sendEmailsCallback).execute(new Void[0]);
    }
}
