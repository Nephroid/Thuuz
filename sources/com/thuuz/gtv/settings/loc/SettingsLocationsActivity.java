package com.thuuz.gtv.settings.loc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bpu;
import defpackage.bww;
import defpackage.bwx;
import defpackage.bwy;
import defpackage.bwz;
import defpackage.bxb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsLocationsActivity extends AbstractSettings {
    private ListView f;
    private final ArrayList<bpu> g;

    public SettingsLocationsActivity() {
        super("TV Providers");
        this.g = new ArrayList<>();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_locations_layout);
        Button button = (Button) findViewById(bbq.add_location);
        expandWidthByDevice(button);
        button.setOnClickListener(new bwy(this, null));
        this.f = (ListView) findViewById(bbq.locations_list);
        this.f.setOnItemClickListener(new bwz(this, null));
        this.f.setAdapter((ListAdapter) new bxb(this, this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        c();
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        if (d()) {
            try {
                this.g.clear();
                Iterator<bpu> it = j().ax().values().iterator();
                while (it.hasNext()) {
                    this.g.add(it.next());
                }
                Collections.sort(this.g);
                ((BaseAdapter) this.f.getAdapter()).notifyDataSetChanged();
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/prefs/locations");
    }

    public void e(int i) {
        bpu bpuVar = this.g.get(i);
        AlertDialog.Builder builder = new AlertDialog.Builder(k());
        builder.setMessage("Delete '" + bpuVar.c() + ": " + bpuVar.e() + "'?");
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", new bww(this, bpuVar, i));
        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
        a(builder.create());
        if (!isDestroyed()) {
            builder.show();
        }
    }

    public void f(int i) {
        try {
            bpu bpuVar = this.g.get(i);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(bpuVar.c() + ": " + bpuVar.e());
            builder.setAdapter(new ArrayAdapter(this, bbr.simple_list_item, bbq.text, new String[]{"Set as Current Location", "Edit", "Delete"}), null);
            AlertDialog create = builder.create();
            create.getListView().setOnItemClickListener(new bwx(this, bpuVar, create, i));
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#017";
    }
}
