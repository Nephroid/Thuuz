package com.yozio.android.invites;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.yozio.android.Constants;
import com.yozio.android.R;
import com.yozio.android.YozioAdvanced;
import com.yozio.android.YozioService;
import com.yozio.android.helpers.Configs;
import com.yozio.android.helpers.Email;
import com.yozio.android.helpers.Sms;
import com.yozio.android.helpers.Utils;
import com.yozio.android.interfaces.SendEmailsCallback;
import com.yozio.android.invites.ContactsListFragment;
import com.yozio.android.invites.EmailFailedDialogFragment;
import com.yozio.android.receivers.SmsDeliverReceiver;
import com.yozio.android.receivers.SmsSentReceiver;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ContactsListActivity extends FragmentActivity implements ContactsListFragment.OnContactsInteractionListener, EmailFailedDialogFragment.EmailFailedDialogListener {
    private ProgressDialog _progressDialog;
    private boolean _isSearchResultView = false;
    private final SmsSentReceiver _smsSentReceiver = new SmsSentReceiver();
    private final SmsDeliverReceiver _smsDeliverReceiver = new SmsDeliverReceiver();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        ActionBar actionBar;
        super.onCreate(bundle);
        if (Utils.hasHoneycomb() && (actionBar = getActionBar()) != null) {
            actionBar.hide();
        }
        setContentView(R.layout.yozio_contacts_list);
        initializeSearchTextListener();
        initializeButtonBarStates();
        this._progressDialog = new ProgressDialog(this);
        this._progressDialog.setMessage(getString(R.string.yozio_please_wait));
        this._progressDialog.setCancelable(false);
        YozioAdvanced.trackInviteActivityShown();
    }

    protected void initializeSearchTextListener() {
        ((EditText) findViewById(R.id.yozio_contacts_list_searchtextbox)).addTextChangedListener(new TextWatcher() { // from class: com.yozio.android.invites.ContactsListActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ImageView imageView = (ImageView) ContactsListActivity.this.findViewById(R.id.yozio_contacts_list_search);
                if (!editable.toString().equals("")) {
                    imageView.setImageResource(R.drawable.yozio_ic_clear_search_api_holo_light);
                }
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ContactsListFragment contactsListFragment = ContactsListActivity.this.getContactsListFragment();
                String charSequence2 = !TextUtils.isEmpty(charSequence.toString()) ? charSequence.toString() : null;
                if (charSequence2 != null) {
                    ContactsListActivity.this._isSearchResultView = true;
                    if (contactsListFragment.getSearchQuery() == null || !contactsListFragment.getSearchQuery().equals(charSequence2)) {
                        contactsListFragment.setSearchQuery(charSequence.toString());
                        return;
                    }
                    return;
                }
                contactsListFragment.setSearchQuery("");
            }
        });
    }

    protected void initializeButtonBarStates() {
        onContactSelected(getContactsListFragment().getSelectedCount());
    }

    public void onSearchButtonClicked(View view) {
        ContactsListFragment contactsListFragment = getContactsListFragment();
        EditText editText = (EditText) findViewById(R.id.yozio_contacts_list_searchtextbox);
        if (this._isSearchResultView) {
            editText.setText("");
            ((ImageButton) findViewById(R.id.yozio_contacts_list_search)).setImageResource(R.drawable.yozio_ic_search_api_holo_light);
            contactsListFragment.setSearchQuery("");
            this._isSearchResultView = false;
            return;
        }
        editText.requestFocus();
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(editText, 1);
    }

    public void onBackButtonClicked(View view) {
        super.onBackPressed();
    }

    public void onLogoButtonClicked(View view) {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        registerReceiver(this._smsSentReceiver, new IntentFilter("SMS_SENT"));
        registerReceiver(this._smsDeliverReceiver, new IntentFilter("SMS_DELIVERED"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        unregisterReceiver(this._smsSentReceiver);
        unregisterReceiver(this._smsDeliverReceiver);
    }

    @Override // com.yozio.android.invites.ContactsListFragment.OnContactsInteractionListener
    public void onContactSelected(int i) {
        Button button = (Button) findViewById(R.id.yozio_contacts_list_invite_button);
        if (i > 0) {
            button.setTextColor(getResources().getColor(R.color.white));
            button.setBackgroundColor(getResources().getColor(R.color.holo_blue));
            button.setText(Configs.getInstance().getConfig(Configs.KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL_WITH_COUNT).replace("${COUNT}", Integer.toString(Integer.parseInt(Configs.getInstance().getConfig(Configs.KEY_CONTACTS_VIEW_SEND_BUTTON_COUNT_MULTIPLIER)) * i)));
        } else {
            button.setTextColor(getResources().getColor(R.color.black));
            button.setBackgroundColor(getResources().getColor(R.color.light_gray2));
            button.setText(Configs.getInstance().getConfig(Configs.KEY_CONTACTS_VIEW_SEND_BUTTON_LABEL));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return !this._isSearchResultView && super.onSearchRequested();
    }

    public void onSelectAllClicked(View view) {
        ContactsListFragment contactsListFragment = getContactsListFragment();
        if (contactsListFragment.getTotalCount() != 0) {
            if (contactsListFragment.getSelectedCount() == contactsListFragment.getCurrentSelectableCount()) {
                contactsListFragment.deSelectAllEntries();
            } else {
                contactsListFragment.selectAllEntries();
            }
            onContactSelected(contactsListFragment.getSelectedCount());
            YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_ALL, null, null, false);
        }
    }

    public void onSendInvitesClicked(View view) {
        ContactsListFragment contactsListFragment = getContactsListFragment();
        Object[] array = contactsListFragment.getSelectedPhones().keySet().toArray();
        if (array.length > 0) {
            this._progressDialog.setTitle(R.string.yozio_sending_sms);
            this._progressDialog.show();
            Sms.sendSms(array, Configs.getInstance().getConfig(Configs.KEY_INVITE_SMS_BODY));
            YozioService.getInstance().trackInvites(array.length, "sms", false);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("sms_invitees", contactsListFragment.getSelectedPhones().values());
            YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SMS_SENT, null, hashMap, false);
        }
        Object[] array2 = contactsListFragment.getSelectedEmails().values().toArray();
        if (array2.length > 0) {
            this._progressDialog.setTitle(R.string.yozio_sending_email);
            this._progressDialog.show();
            final int length = array2.length;
            Email.getInstance().sendEmails(array2, new SendEmailsCallback() { // from class: com.yozio.android.invites.ContactsListActivity.2
                @Override // com.yozio.android.interfaces.SendEmailsCallback
                public void sendEmailStatus(boolean z) {
                    if (z) {
                        YozioService.getInstance().trackInvites(length, "email", false);
                        ContactsListActivity.this.emailDone(true);
                    } else {
                        ContactsListActivity.this.emailDone(false);
                    }
                }
            });
            return;
        }
        this._progressDialog.dismiss();
        inviteDone(true);
    }

    public void emailDone(boolean z) {
        this._progressDialog.dismiss();
        if (!z) {
            new EmailFailedDialogFragment().show(getSupportFragmentManager(), "yozio_email_failed");
        } else {
            inviteDone(true);
        }
    }

    @Override // com.yozio.android.invites.EmailFailedDialogFragment.EmailFailedDialogListener
    public void onEmailFailedDialogPositiveClick(DialogFragment dialogFragment) {
        inviteDone(false);
    }

    public void inviteDone(boolean z) {
        ContactsListFragment contactsListFragment = getContactsListFragment();
        Object[] array = contactsListFragment.getSelectedPhones().keySet().toArray();
        Object[] array2 = contactsListFragment.getSelectedEmails().values().toArray();
        if (array.length > 0 || array2.length > 0) {
            if (z) {
                YozioService.getInstance().toast(this, getString(R.string.yozio_invites_sent));
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("sms", Integer.toString(array.length));
            hashMap.put("email", Integer.toString(array2.length));
            YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_INVITE_DONE, null, hashMap, true);
            Intent intent = new Intent();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(contactsListFragment.getSelectedPhones().values());
            arrayList.addAll(contactsListFragment.getSelectedEmails().values());
            intent.putExtra("invitees", arrayList);
            setResult(-1, intent);
            finish();
        }
    }

    public ContactsListFragment getContactsListFragment() {
        return (ContactsListFragment) getSupportFragmentManager().findFragmentById(R.id.yozio_contacts_list);
    }
}
