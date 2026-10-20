package com.appsflyer;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.internal.ServerProtocol;
import com.thuuz.mob.R;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class InAppEventsActivity extends ListActivity implements AdapterView.OnItemSelectedListener {
    private TextView a;
    private List<String> b;
    private int c = 1;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.abs__action_bar_tab);
        this.a = (TextView) findViewById(R.xml.widget_info);
        this.b = new ArrayList();
        this.b.add("af_list_view");
        this.b.add("af_content_view");
        this.b.add("Criteo - af_add_to_cart");
        this.b.add("Criteo - af_purchase");
        this.b.add("af_level_achieved");
        this.b.add("af_user_status");
        this.b.add("af_achievement_unlocked");
        this.b.add("af_login");
        this.b.add("fb-af_level_achieved");
        this.b.add("fb-af_add_to_cart");
        this.b.add("fb-af_add_to_wishlist");
        this.b.add("fb-af_complete_registration");
        this.b.add("fb-af_tutorial_completion");
        this.b.add("fb-af_initiated_checkout");
        this.b.add("fb-af_purchase");
        this.b.add("fb-af_rate");
        this.b.add("fb-af_search");
        this.b.add("fb-af_spent_credits");
        this.b.add("fb-af_achievement_unlocked");
        this.b.add("fb-af_content_view");
        this.b.add("fb-af_travel_booking");
        setListAdapter(new ArrayAdapter(this, R.layout.abs__action_bar_home, R.xml.analytics, this.b));
        Spinner spinner = (Spinner) findViewById(2131034115);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_item, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 100, 500, 1000}));
        spinner.setOnItemSelectedListener(this);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        AppsFlyerLib.a((Activity) this);
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        AppsFlyerLib.b((Activity) this);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        AppsFlyerLib.a((Activity) this);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        AppsFlyerLib.b((Activity) this);
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView listView, View view, int i, long j) {
        super.onListItemClick(listView, view, i, j);
        String str = (String) getListView().getItemAtPosition(i);
        for (int i2 = 0; i2 < this.c; i2++) {
            a(str);
            if (i2 < this.c - 1) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.c = ((Integer) adapterView.getItemAtPosition(i)).intValue();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void a(String str) {
        HashMap hashMap = new HashMap();
        long time = new Date().getTime();
        hashMap.put("test-id", Long.valueOf(time));
        this.a.setText("Event " + str + " sent. (test-id " + time + ")");
        if (str.equals("af_list_view")) {
            hashMap.put("af_content_id", new String[]{"324219284", "324346544", "324242096"});
            hashMap.put("af_currency", "USD");
        } else if (str.equals("af_content_view")) {
            hashMap.put("af_content_id", "324219284");
            hashMap.put("af_currency", "USD");
        } else if (str.equals("Criteo - af_add_to_cart")) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("af_content_id", "1000001");
            hashMap2.put("af_price", Double.valueOf(10.2d));
            hashMap2.put("af_quantity", 1);
            HashMap hashMap3 = new HashMap();
            hashMap3.put("af_content_id", "1000002");
            hashMap3.put("af_price", Double.valueOf(1.1d));
            hashMap3.put("af_quantity", 2);
            HashMap hashMap4 = new HashMap();
            hashMap4.put("af_content_id", "1000003");
            hashMap4.put("af_price", Double.valueOf(9.3d));
            hashMap4.put("af_quantity", 5);
            hashMap.put("product, ", new Map[]{hashMap2, hashMap3, hashMap4});
            hashMap.put("af_currency", "USD");
        } else if (str.equals("Criteo - af_purchase")) {
            HashMap hashMap5 = new HashMap();
            hashMap5.put("af_content_id", "1000001");
            hashMap5.put("af_price", Double.valueOf(10.2d));
            hashMap5.put("af_quantity", 1);
            HashMap hashMap6 = new HashMap();
            hashMap6.put("af_content_id", "1000002");
            hashMap6.put("af_price", Double.valueOf(1.1d));
            hashMap6.put("af_quantity", 2);
            HashMap hashMap7 = new HashMap();
            hashMap7.put("af_content_id", "1000003");
            hashMap7.put("af_price", Double.valueOf(9.3d));
            hashMap7.put("af_quantity", 5);
            hashMap.put("product, ", new Map[]{hashMap5, hashMap6, hashMap7});
            hashMap.put("af_currency", "USD");
            hashMap.put("af_receipt_id", "transactionuid");
            hashMap.put("af_revenue", Double.valueOf(58.9d));
        } else if (str.equals("af_level_achieved")) {
            hashMap.put("af_level", 3);
        } else if (str.equals("af_user_status")) {
            hashMap.put("af_status", "subscriber");
        } else if (str.equals("af_achievement_unlocked")) {
            hashMap.put("af_description", "special_key");
        } else if (!str.equals("af_login")) {
            if (str.equals("fb-af_level_achieved")) {
                hashMap.put("af_level", "level_1");
            } else if (str.equals("fb-af_add_payment_info")) {
                hashMap.put("af_success", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else if (str.equals("fb-af_add_to_cart")) {
                hashMap.put("af_price", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_currency", "USD");
            } else if (str.equals("fb-af_add_to_wishlist")) {
                hashMap.put("af_price", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_currency", "USD");
            } else if (str.equals("fb-af_complete_registration")) {
                hashMap.put("af_registration_method", "resg");
            } else if (str.equals("fb-af_tutorial_completion")) {
                hashMap.put("af_success", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                hashMap.put("af_content_id", "324219284");
            } else if (str.equals("fb-af_initiated_checkout")) {
                hashMap.put("af_price", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_currency", "USD");
                hashMap.put("af_quantity", 2);
                hashMap.put("af_payment_info_available", "false");
            } else if (str.equals("fb-af_purchase")) {
                hashMap.put("af_revenue", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_currency", "USD");
                hashMap.put("af_quantity", 5);
            } else if (str.equals("fb-af_rate")) {
                hashMap.put("af_rating_value", 55);
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_max_rating_value", 5555);
            } else if (str.equals("fb-af_search")) {
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_search_string", "search string");
                hashMap.put("af_success", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else if (str.equals("fb-af_spent_credits")) {
                hashMap.put("af_price", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
            } else if (str.equals("fb-af_achievement_unlocked")) {
                hashMap.put("af_description", "desc");
            } else if (str.equals("fb-af_content_view")) {
                hashMap.put("af_price", Double.valueOf(25.5d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_currency", "USD");
            } else if (str.equals("fb-af_travel_booking")) {
                hashMap.put("af_revenue", Double.valueOf(335.25d));
                hashMap.put("af_content_type", "content type");
                hashMap.put("af_content_id", "324219284");
                hashMap.put("af_success", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            }
        }
        if (str.contains("fb-")) {
            str = str.substring(3);
        }
        AppsFlyerLib.a(this, str, hashMap);
    }
}
