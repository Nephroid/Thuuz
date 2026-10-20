package com.thuuz.gtv.settings.loc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Rect;
import android.net.http.Headers;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.nobar.OnboardPage4TvActivity;
import com.thuuz.gtv.data.Locations;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bpu;
import defpackage.bpy;
import defpackage.bqc;
import defpackage.bwr;
import defpackage.bwt;
import defpackage.bxc;
import defpackage.bxd;
import defpackage.bxe;
import defpackage.bxf;
import defpackage.bxg;
import defpackage.bxh;
import defpackage.bxi;
import defpackage.bxj;
import defpackage.bxk;
import defpackage.bxl;
import defpackage.bxm;
import defpackage.bxn;
import defpackage.bxo;
import defpackage.bxp;
import defpackage.bxq;
import defpackage.bxr;
import defpackage.bxs;
import defpackage.bxt;
import defpackage.bxu;
import defpackage.bxv;
import defpackage.bxw;
import defpackage.cak;
import defpackage.caq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class TVProviderFragment extends bbf {
    private boolean add_no_provider_button;
    private TextView cityView;
    private View device_edit_lay;
    private TextView device_name;
    private Spinner device_spinner;
    private View device_spinner_section;
    private Button discover_button;
    private View discover_lay;
    private Locations existingLocs;
    private boolean isNew;
    private ArrayAdapter<bwt> listProviderAdapter;
    private LinearLayout loadingProvidersLayout;
    private View needWifiMessage;
    private String original_prov_id;
    private View progress_lay;
    private View remoteInstructions;
    private LinearLayout selectProviderLayout;
    private String selectedDeviceId;
    private bpu selectedLocation;
    private String selectedProviderId;
    private Button skipDiscoverButton;
    private ArrayAdapter<bwt> spinnerProviderAdapter;
    private View spinner_selected_tv_provider_lay;
    private bqc startPairDevice;
    private ListView tvProviderList;
    private TextView tvProviderPrompt;
    private Spinner tvProviderSpinner;
    private LinearLayout tv_provider_list_container;
    private EditText zipEditText;
    private View zipPrompt;
    private bxt zipWatcher;
    private Integer location_id = 0;
    private boolean isFirst = true;
    private final List<bwt> providerListData = new ArrayList();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        bpu a;
        bpu a2;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        View view = null;
        try {
            View inflate = layoutInflater.inflate(bbr.tv_provider_frag, viewGroup, false);
            if (inflate == null) {
                return null;
            }
            try {
                Bundle arguments = getArguments();
                if (arguments != null) {
                    this.location_id = Integer.valueOf(arguments.getInt(Headers.LOCATION, -1));
                    this.add_no_provider_button = arguments.getBoolean("Add no provider", false);
                }
                this.zipEditText = (EditText) inflate.findViewById(bbq.zipcode);
                this.cityView = (TextView) inflate.findViewById(bbq.city);
                this.progress_lay = inflate.findViewById(bbq.discover_progress_lay);
                this.loadingProvidersLayout = (LinearLayout) inflate.findViewById(bbq.loading_section);
                this.selectProviderLayout = (LinearLayout) inflate.findViewById(bbq.select_provider_section);
                this.tvProviderList = (ListView) inflate.findViewById(bbq.tv_provider_list);
                this.tv_provider_list_container = (LinearLayout) inflate.findViewById(bbq.tv_provider_list_container);
                this.spinner_selected_tv_provider_lay = inflate.findViewById(bbq.selected_tv_provider_lay);
                this.zipWatcher = new bxt(this, null);
                this.zipEditText.addTextChangedListener(this.zipWatcher);
                this.needWifiMessage = inflate.findViewById(bbq.wifi_instructions);
                this.remoteInstructions = inflate.findViewById(bbq.remote_instructions);
                AbstractActivity fragsActivity = getFragsActivity();
                if (fragsActivity != null && fragsActivity.j().a((Activity) fragsActivity) && (layoutParams3 = this.remoteInstructions.getLayoutParams()) != null) {
                    layoutParams3.width = fragsActivity.j().q(400);
                }
                ((TextView) inflate.findViewById(bbq.hopper_trouble)).setOnClickListener(new bxc(this));
                this.tvProviderList.setOnItemClickListener(new bxv(this, null));
                this.tvProviderSpinner = (Spinner) inflate.findViewById(bbq.tv_provider_spinner);
                this.tvProviderSpinner.setOnItemSelectedListener(new bxw(this, null));
                this.tvProviderPrompt = (TextView) inflate.findViewById(bbq.tv_provider_prompt);
                this.device_spinner_section = inflate.findViewById(bbq.select_device_section);
                this.device_spinner = (Spinner) inflate.findViewById(bbq.device_spinner);
                this.device_spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(getFragsActivity(), bbr.wrapping_spinner));
                this.device_spinner.setOnItemSelectedListener(new bxs(this, null));
                this.discover_lay = inflate.findViewById(bbq.discover_device_lay);
                if (fragsActivity != null && fragsActivity.j().a((Activity) fragsActivity) && (layoutParams2 = this.discover_lay.getLayoutParams()) != null) {
                    layoutParams2.width = fragsActivity.j().q(400);
                }
                this.discover_button = (Button) inflate.findViewById(bbq.discover_button);
                this.discover_button.setOnClickListener(new bxk(this));
                if (fragsActivity != null) {
                    fragsActivity.expandWidthByDevice(this.discover_button);
                }
                this.device_edit_lay = inflate.findViewById(bbq.device_edit_lay);
                this.device_name = (TextView) inflate.findViewById(bbq.current_device);
                ((TextView) inflate.findViewById(bbq.disconnect_device)).setOnClickListener(new bxl(this));
                ((TextView) inflate.findViewById(bbq.rediscover_device)).setOnClickListener(new bxm(this));
                ((TextView) inflate.findViewById(bbq.hopper_trouble2)).setOnClickListener(new bxn(this));
                if (fragsActivity != null && fragsActivity.j().a((Activity) fragsActivity) && (layoutParams = this.device_edit_lay.getLayoutParams()) != null) {
                    layoutParams.width = fragsActivity.j().q(400);
                }
                this.isNew = -1 == this.location_id.intValue();
                if (fragsActivity != null) {
                    this.existingLocs = fragsActivity.j().ax();
                    if (!this.isNew && (a2 = this.existingLocs.a(this.location_id)) != null) {
                        this.original_prov_id = a2.d();
                    }
                    if (this.location_id.intValue() >= 0) {
                        this.selectedLocation = this.existingLocs.a(this.location_id);
                    }
                }
                boolean z = this.selectedLocation != null;
                if (!this.isNew && !z) {
                    getLogger().severe("cannot find prev. selected loc " + this.location_id);
                    this.isNew = true;
                }
                this.skipDiscoverButton = (Button) inflate.findViewById(bbq.skip_discover_button);
                this.skipDiscoverButton.setOnClickListener(new bxo(this));
                this.listProviderAdapter = new bxp(this, getFragsActivity(), bbr.provider_item_layout, this.providerListData);
                this.spinnerProviderAdapter = new ArrayAdapter<>(getFragsActivity(), bbr.wrapping_spinner, this.providerListData);
                this.tvProviderList.setAdapter((ListAdapter) this.listProviderAdapter);
                this.tvProviderSpinner.setAdapter((SpinnerAdapter) this.spinnerProviderAdapter);
                if (!this.isNew) {
                    if (fragsActivity != null && (a = fragsActivity.j().ax().a(this.location_id)) != null) {
                        this.zipEditText.setText(a.c());
                        Editable text = this.zipEditText.getText();
                        if (text != null) {
                            this.zipEditText.setSelection(text.length());
                        }
                    }
                    ((TextView) inflate.findViewById(bbq.select_device_prompt)).setText("Device to control:");
                    if (z) {
                        this.spinnerProviderAdapter.add(new bwt(this.selectedLocation));
                    }
                }
                this.zipPrompt = inflate.findViewById(bbq.prompt_zip_lay);
                showTvProvidersExpandedList(z);
                if (this.isNew) {
                    this.selectProviderLayout.setVisibility(8);
                }
                showDiscoverySection(false);
                showDiscoveryInProgress(false);
                showRemoteInstructions(false);
                showDeviceEditLay(z && this.selectedLocation.l());
                showDeviceSpinnerLay(false);
                if ((fragsActivity != null && fragsActivity.j().e()) || !this.isNew) {
                    showZipPrompt(false);
                } else {
                    showZipPrompt(true);
                }
                if (this.add_no_provider_button) {
                    showZipPrompt(false);
                    TextView textView = (TextView) inflate.findViewById(bbq.zip_label);
                    textView.setText("Please enter your US Zip Code...");
                    textView.setTextSize(2, 20.0f);
                    centerInLayout(textView);
                    addTopMargin(inflate.findViewById(bbq.zipcode_section), 60);
                    centerInLayout(this.zipEditText);
                    View findViewById = inflate.findViewById(bbq.no_zipcode);
                    findViewById.setVisibility(0);
                    centerInLayout(findViewById);
                    findViewById.setOnClickListener(new bxq(this));
                }
                this.loadingProvidersLayout.setVisibility(8);
                this.selectProviderLayout.setVisibility(8);
                return inflate;
            } catch (Throwable th) {
                view = inflate;
                th = th;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return view;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void addTopMargin(View view, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = i;
        }
    }

    private void centerInLayout(View view) {
        if (view != null) {
            try {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.gravity = 1;
                    layoutParams.leftMargin = 0;
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && fragsActivity.d()) {
                cak.a(getFragsActivity(), this.tvProviderList);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public bxt getZipWatcher() {
        return this.zipWatcher;
    }

    public void validateZip(AbstractActivity abstractActivity) {
        getZipWatcher().a(abstractActivity);
    }

    public View getNeedWifiMessage() {
        return this.needWifiMessage;
    }

    public EditText getZipEditText() {
        return this.zipEditText;
    }

    public LinearLayout getSelectProviderLayout() {
        return this.selectProviderLayout;
    }

    public void onFinish() {
        if (this.selectedLocation != null && this.selectedLocation.o() && getFragsActivity().j().ax().size() == 0) {
            getLogger().info("adding loc within finish()");
            getFragsActivity().j().ax().d(this.selectedLocation);
        }
    }

    public void handleProviderSelected(AdapterView<?> adapterView, int i, bxu bxuVar) {
        try {
            bwt bwtVar = (bwt) adapterView.getItemAtPosition(i);
            if (bwtVar != null) {
                if (!bwtVar.a.equals("-1") || !bxuVar.a()) {
                    if (bwtVar.a.equals("I don't have a TV provider")) {
                        ((OnboardPage4TvActivity) getFragsActivity()).aL();
                        return;
                    }
                    if (!bwtVar.a.equals(this.selectedProviderId)) {
                        String str = "";
                        if (this.zipEditText.getText() != null) {
                            str = this.zipEditText.getText().toString();
                        }
                        if (str.length() < 5) {
                            getFragsActivity().c("U.S. Only for Now", "Please enter a US zip code.");
                            return;
                        }
                        this.selectedLocation = new bpu(this.location_id, str + ": " + bwtVar.b, str, bwtVar.a, bwtVar.b, bwtVar.c);
                        this.selectedProviderId = bwtVar.a;
                        showDeviceEditLay(false);
                        if (!getIEditActivity().a(this.selectedLocation)) {
                            showDiscoverySection(false);
                            showNeedWifiMessage(false);
                            showRemoteInstructions(false);
                            saveSelectedProviderAndFinish();
                            return;
                        }
                        this.selectedLocation = getFragsActivity().j().ax().e(this.selectedLocation);
                        this.location_id = this.selectedLocation.a();
                        doAnimation();
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    private int getRelativeTop(View view, View view2) {
        if (view.getParent() == view2) {
            return view.getTop();
        }
        return getRelativeTop((View) view.getParent(), view2) + view.getTop();
    }

    private void doAnimation() {
        collapseAnimation();
        waitForCollapseAnimation();
    }

    private void waitForCollapseAnimation() {
        getFragsActivity().aa().postDelayed(new bxr(this), 500L);
    }

    public int scrollAnimation() {
        Rect rect = new Rect();
        this.tvProviderPrompt.getLocalVisibleRect(rect);
        if (rect.height() > 0) {
            return 0;
        }
        ScrollView aM = ((bwr) getFragsActivity()).aM();
        new bxd(this, 500L, 25L, aM, aM.getScrollY() - getRelativeTop(this.tvProviderPrompt, aM)).start();
        return 500;
    }

    public void waitForScrollAnimation(int i) {
        getFragsActivity().aa().postDelayed(new bxe(this), i);
    }

    private void collapseAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f);
        scaleAnimation.setDuration(500L);
        animationSet.addAnimation(scaleAnimation);
        this.tv_provider_list_container.setLayoutAnimation(new LayoutAnimationController(animationSet, 0.0f));
        this.tv_provider_list_container.startAnimation(animationSet);
    }

    public bwr getIEditActivity() {
        return (bwr) getFragsActivity();
    }

    public void saveSelectedProviderAndFinish() {
        App j = getFragsActivity().j();
        Locations ax = j.ax();
        if (this.selectedLocation == null) {
            getLogger().severe("selectedLocation null during save in Edit Location activity");
            return;
        }
        if (this.selectedLocation.a().intValue() == -1) {
            this.selectedLocation = ax.e(this.selectedLocation);
            this.location_id = this.selectedLocation.a();
            ax.a(this.selectedLocation);
        } else {
            if (this.original_prov_id != null && this.original_prov_id.length() > 0 && !this.original_prov_id.equals(this.selectedLocation.d()) && this.existingLocs.a(this.selectedLocation.d())) {
                getFragsActivity().c("Already Exists", "Note: this provider is already in the list of locations.");
            }
            updateLocation(this.location_id, this.selectedLocation);
        }
        j.bm();
        getIEditActivity().aH();
    }

    private void updateLocation(Integer num, bpu bpuVar) {
        if (bpuVar != null) {
            Locations ax = getFragsActivity().j().ax();
            bpu a = ax.a(num);
            a.a(bpuVar.b());
            a.b(bpuVar.c());
            a.c(bpuVar.d());
            a.d(bpuVar.e());
            a.m(bpuVar.k());
            a.a((bqc) null);
            ax.b(a);
        }
    }

    public void onDevicesDiscovered(List<bqc> list) {
        if (list != null) {
            try {
                setupDeviceSpinner(list);
                if (list.size() == 0) {
                    showDiscoverySection(true);
                    return;
                }
                if (list.size() > 1) {
                    showRemoteInstructions(false);
                    showDeviceSpinnerLay(true);
                    showDeviceEditLay(false);
                    return;
                }
                bqc bqcVar = list.get(0);
                AlertDialog.Builder builder = new AlertDialog.Builder(getFragsActivity());
                builder.setTitle("Connect to: \"" + bqcVar.a() + "\" ?");
                if (bqcVar.e()) {
                    builder.setMessage("In the next step, you will need to type in a 'pairing' code that will appear on your TV.");
                }
                builder.setCancelable(false);
                AlertDialog create = builder.create();
                create.setButton(-1, "Ok", new bxf(this, bqcVar, create));
                create.setButton(-2, "Cancel", new bxg(this, create));
                getFragsActivity().a(create);
                if (!getFragsActivity().isDestroyed()) {
                    create.show();
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private void setupDeviceSpinner(List<bqc> list) {
        String str;
        ArrayAdapter arrayAdapter = (ArrayAdapter) this.device_spinner.getAdapter();
        arrayAdapter.clear();
        arrayAdapter.add(new bqc("-1", getResources().getString(bbt.select)));
        arrayAdapter.add(new bqc("-2", "Cancel: do not connect"));
        bpu a = getFragsActivity().j().ax().a(this.location_id);
        if (a == null) {
            str = null;
        } else {
            str = a.h();
        }
        Collections.sort(list);
        for (bqc bqcVar : list) {
            arrayAdapter.add(bqcVar);
            if (bqcVar.b().equals(str)) {
                arrayAdapter.notifyDataSetChanged();
                this.selectedDeviceId = str;
                this.device_spinner.setSelection(arrayAdapter.getCount() - 1);
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    public void showPairingDialog(bqc bqcVar) {
        try {
            getFragsActivity().j().k("/connected_tv/pair");
            Dialog dialog = new Dialog(getFragsActivity());
            dialog.setContentView(bbr.pair_dialog_lay);
            dialog.setTitle("Pairing");
            dialog.setCancelable(true);
            Button button = (Button) dialog.findViewById(bbq.pair_button);
            EditText editText = (EditText) dialog.findViewById(bbq.pairing_text);
            button.setOnClickListener(new bxh(this, button, editText));
            this.startPairDevice = bqcVar;
            getFragsActivity().a(dialog);
            if (!getFragsActivity().isDestroyed()) {
                dialog.show();
                editText.requestFocus();
                getFragsActivity().f(true);
                getFragsActivity().aa().postDelayed(new bxi(this, editText), 100L);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void showDiscoverySection(boolean z) {
        int i = 8;
        if (z) {
            String str = "Connect to DISH Hopper DVR...";
            String str2 = "I don't have a Hopper";
            if (this.selectedLocation != null && this.selectedLocation.p()) {
                str = "Connect to DIRECTV...";
                str2 = "No thanks";
            }
            this.discover_button.setText(str);
            this.skipDiscoverButton.setText(str2);
            i = 0;
        }
        this.discover_lay.setVisibility(i);
        this.discover_button.setEnabled(z);
    }

    public void showDiscoveryInProgress(boolean z) {
        int i = 8;
        if (z) {
            i = 0;
        }
        this.progress_lay.setVisibility(i);
    }

    public void showNeedWifiMessage(boolean z) {
        int i = 0;
        if (!z) {
            i = 8;
        }
        this.needWifiMessage.setVisibility(i);
    }

    public void showDeviceSpinnerLay(boolean z) {
        int i = 0;
        if (!z) {
            i = 8;
        }
        this.device_spinner_section.setVisibility(i);
    }

    public void showDeviceEditLay(boolean z) {
        int i = 0;
        if (!z) {
            i = 8;
        }
        try {
            this.device_edit_lay.setVisibility(i);
            if (this.selectedLocation != null && this.selectedLocation.l()) {
                this.device_name.setText(this.selectedLocation.m().a());
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void showRemoteInstructions(boolean z) {
        int i = 0;
        if (!z) {
            i = 8;
        }
        this.remoteInstructions.setVisibility(i);
    }

    public void showZipPrompt(boolean z) {
        int i = 0;
        if (!z) {
            i = 8;
        }
        this.zipPrompt.setVisibility(i);
    }

    public void showTvProvidersExpandedList(boolean z) {
        this.selectProviderLayout.setVisibility(0);
        if (z) {
            this.tvProviderList.setVisibility(0);
            this.spinner_selected_tv_provider_lay.setVisibility(8);
            this.listProviderAdapter.notifyDataSetChanged();
            cak.a(getFragsActivity(), this.tvProviderList);
            return;
        }
        hideListAndShowSpinner();
    }

    public void hideListAndShowSpinner() {
        int i;
        int i2 = 0;
        this.tvProviderList.setVisibility(8);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.tvProviderSpinner.getOnItemSelectedListener();
        this.tvProviderSpinner.setOnItemSelectedListener(null);
        this.spinner_selected_tv_provider_lay.setVisibility(0);
        Iterator<bwt> it = this.providerListData.iterator();
        while (true) {
            i = i2;
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (it.next().a.equals(this.selectedProviderId)) {
                break;
            } else {
                i2 = i + 1;
            }
        }
        if (i >= 0) {
            this.tvProviderSpinner.setSelection(i);
        }
        this.tvProviderSpinner.setOnItemSelectedListener(onItemSelectedListener);
        this.spinnerProviderAdapter.notifyDataSetChanged();
    }

    public void focusOnZip() {
        this.zipEditText.requestFocus();
    }

    public TextView getCityView() {
        return this.cityView;
    }

    public LinearLayout getLoadingProvidersLayout() {
        return this.loadingProvidersLayout;
    }

    public ListView getTvProviderList() {
        return this.tvProviderList;
    }

    public View getSpinner_selected_tv_provider_lay() {
        return this.spinner_selected_tv_provider_lay;
    }

    public String getZipText() {
        Editable text = this.zipEditText.getText();
        return text != null ? text.toString() : "";
    }

    public void setCityText(String str) {
        this.cityView.setText(str);
    }

    public void onTVProvidersFetched(caq caqVar) {
        int i;
        int i2 = -1;
        try {
            this.loadingProvidersLayout.setVisibility(8);
            if (caqVar.b()) {
                getFragsActivity().f(false);
            } else {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getFragsActivity());
                    builder.setTitle("Cannot Download List of TV Providers");
                    builder.setMessage("Retype the zip code to retry the download");
                    builder.setCancelable(true);
                    AlertDialog create = builder.create();
                    create.setButton(-1, "Ok", new bxj(this, create));
                    getFragsActivity().a(create);
                    if (!getFragsActivity().isDestroyed()) {
                        create.show();
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    getLogger().log(Level.SEVERE, "problem: ", th);
                }
            }
            List<bpy> a = caqVar.a();
            if (a.size() == 0) {
                this.tvProviderPrompt.setText(getFragsActivity().getResources().getString(bbt.no_tv_providers_found));
                this.tvProviderList.setVisibility(8);
            } else {
                this.tvProviderPrompt.setText("TV provider:");
            }
            bpu a2 = this.existingLocs.a(this.location_id);
            ArrayList arrayList = new ArrayList();
            Collections.sort(a);
            int i3 = 0;
            for (bpy bpyVar : a) {
                String c = bpyVar.c();
                String b = bpyVar.b();
                if (c.length() > 0 && !b.contains(c)) {
                    b = b + " (" + c + ")";
                }
                arrayList.add(new bwt(bpyVar.a(), b, bpyVar.d()));
                boolean z = a2 != null && bpyVar.a().equals(a2.d());
                if (this.isNew || !z) {
                    i = i2;
                } else {
                    this.selectedProviderId = bpyVar.a();
                    i = i3;
                }
                i3++;
                i2 = i;
            }
            if (this.add_no_provider_button) {
                arrayList.add(new bwt("I don't have a TV provider", "I don't have a TV provider", ""));
            }
            this.providerListData.clear();
            this.providerListData.addAll(arrayList);
            if (i2 >= 0) {
                this.tvProviderList.setSelection(i2);
                this.tvProviderSpinner.setSelection(i2);
                boolean z2 = a2 != null && a2.h().length() > 0;
                showDiscoverySection(false);
                if (((bwr) getFragsActivity()).a(a2) && (this.isNew || !z2)) {
                    if (this.selectedLocation != null && this.selectedLocation.f()) {
                        showRemoteInstructions(true);
                    }
                    if (getFragsActivity().T()) {
                        showDiscoverySection(true);
                    } else {
                        showNeedWifiMessage(true);
                    }
                }
                if (z2) {
                    this.selectedDeviceId = a2.h();
                    showDeviceSpinnerLay(false);
                    showDeviceEditLay(true);
                }
            }
            this.listProviderAdapter.notifyDataSetChanged();
            this.spinnerProviderAdapter.notifyDataSetChanged();
            showTvProvidersExpandedList(this.isNew || getFragsActivity().j().e());
            getFragsActivity().c();
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th2);
        }
    }

    public void setZip(String str) {
        if (!getFragsActivity().isDestroyed()) {
            this.zipEditText.setText(str);
            Editable text = this.zipEditText.getText();
            if (text != null) {
                this.zipEditText.setSelection(text.length());
            }
            if (this.isFirst) {
                this.isFirst = false;
                if (str.length() >= 5) {
                    ((bwr) getFragsActivity()).aG();
                }
            }
        }
    }
}
