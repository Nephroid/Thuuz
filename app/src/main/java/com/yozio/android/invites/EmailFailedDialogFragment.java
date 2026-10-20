package com.yozio.android.invites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.yozio.android.R;

/* loaded from: classes.dex */
public class EmailFailedDialogFragment extends DialogFragment {
    EmailFailedDialogListener _listener;

    /* loaded from: classes.dex */
    public interface EmailFailedDialogListener {
        void onEmailFailedDialogPositiveClick(DialogFragment dialogFragment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this._listener = (EmailFailedDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.valueOf(activity.toString()) + " must implement EmailFailedDialogListener");
        }
    }

    @Override // android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.yozio_invites_email_failed);
        builder.setMessage(R.string.yozio_please_try_again);
        builder.setPositiveButton(R.string.yozio_ok, new DialogInterface.OnClickListener() { // from class: com.yozio.android.invites.EmailFailedDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                EmailFailedDialogFragment.this._listener.onEmailFailedDialogPositiveClick(EmailFailedDialogFragment.this);
            }
        });
        return builder.create();
    }
}
