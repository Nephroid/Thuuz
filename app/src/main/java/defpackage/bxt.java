package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxt implements TextWatcher {
    final /* synthetic */ TVProviderFragment a;

    private bxt(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    public /* synthetic */ bxt(TVProviderFragment tVProviderFragment, bxc bxcVar) {
        this(tVProviderFragment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(AbstractActivity abstractActivity) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            String zipText = this.a.getZipText();
            boolean z = zipText.length() >= 5;
            if (z) {
                if (!bwo.a.matcher(zipText).matches()) {
                    abstractActivity.c("Invalid Zipcode", "Please retype");
                    return;
                }
                ((bwr) abstractActivity).aG();
            } else {
                this.a.getCityView().setText("");
            }
            if (z) {
                this.a.selectedProviderId = null;
                fragsActivity = this.a.getFragsActivity();
                fragsActivity.j().x(zipText);
                this.a.getLoadingProvidersLayout().setVisibility(0);
                ((bwr) abstractActivity).g(zipText);
                return;
            }
            this.a.showRemoteInstructions(false);
            this.a.showDiscoverySection(false);
            this.a.showDiscoveryInProgress(false);
            this.a.showDeviceEditLay(false);
            this.a.showDeviceSpinnerLay(false);
            this.a.getTvProviderList().setVisibility(8);
            this.a.getSpinner_selected_tv_provider_lay().setVisibility(8);
            this.a.getLoadingProvidersLayout().setVisibility(8);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        a(fragsActivity);
    }
}
