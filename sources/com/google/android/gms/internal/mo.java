package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aic;
import defpackage.kb;

/* loaded from: classes.dex */
public final class mo implements SafeParcelable {
    public final int bw;
    public final String bx;
    public static final mo a = a("accounting");
    public static final mo b = a("airport");
    public static final mo c = a("amusement_park");
    public static final mo d = a("aquarium");
    public static final mo e = a("art_gallery");
    public static final mo f = a("atm");
    public static final mo g = a("bakery");
    public static final mo h = a("bank");
    public static final mo i = a("bar");
    public static final mo j = a("beauty_salon");
    public static final mo k = a("bicycle_store");
    public static final mo l = a("book_store");
    public static final mo m = a("bowling_alley");
    public static final mo n = a("bus_station");
    public static final mo o = a("cafe");
    public static final mo p = a("campground");
    public static final mo q = a("car_dealer");
    public static final mo r = a("car_rental");
    public static final mo s = a("car_repair");
    public static final mo t = a("car_wash");
    public static final mo u = a("casino");
    public static final mo v = a("cemetery");
    public static final mo w = a("church");
    public static final mo x = a("city_hall");
    public static final mo y = a("clothing_store");
    public static final mo z = a("convenience_store");
    public static final mo A = a("courthouse");
    public static final mo B = a("dentist");
    public static final mo C = a("department_store");
    public static final mo D = a("doctor");
    public static final mo E = a("electrician");
    public static final mo F = a("electronics_store");
    public static final mo G = a("embassy");
    public static final mo H = a("establishment");
    public static final mo I = a("finance");
    public static final mo J = a("fire_station");
    public static final mo K = a("florist");
    public static final mo L = a("food");
    public static final mo M = a("funeral_home");
    public static final mo N = a("furniture_store");
    public static final mo O = a("gas_station");
    public static final mo P = a("general_contractor");
    public static final mo Q = a("grocery_or_supermarket");
    public static final mo R = a("gym");
    public static final mo S = a("hair_care");
    public static final mo T = a("hardware_store");
    public static final mo U = a("health");
    public static final mo V = a("hindu_temple");
    public static final mo W = a("home_goods_store");
    public static final mo X = a("hospital");
    public static final mo Y = a("insurance_agency");
    public static final mo Z = a("jewelry_store");
    public static final mo aa = a("laundry");
    public static final mo ab = a("lawyer");
    public static final mo ac = a("library");
    public static final mo ad = a("liquor_store");
    public static final mo ae = a("local_government_office");
    public static final mo af = a("locksmith");
    public static final mo ag = a("lodging");
    public static final mo ah = a("meal_delivery");
    public static final mo ai = a("meal_takeaway");
    public static final mo aj = a("mosque");
    public static final mo ak = a("movie_rental");
    public static final mo al = a("movie_theater");
    public static final mo am = a("moving_company");
    public static final mo an = a("museum");
    public static final mo ao = a("night_club");
    public static final mo ap = a("painter");
    public static final mo aq = a("park");
    public static final mo ar = a("parking");
    public static final mo as = a("pet_store");
    public static final mo at = a("pharmacy");
    public static final mo au = a("physiotherapist");
    public static final mo av = a("place_of_worship");
    public static final mo aw = a("plumber");
    public static final mo ax = a("police");
    public static final mo ay = a("post_office");
    public static final mo az = a("real_estate_agency");
    public static final mo aA = a("restaurant");
    public static final mo aB = a("roofing_contractor");
    public static final mo aC = a("rv_park");
    public static final mo aD = a("school");
    public static final mo aE = a("shoe_store");
    public static final mo aF = a("shopping_mall");
    public static final mo aG = a("spa");
    public static final mo aH = a("stadium");
    public static final mo aI = a("storage");
    public static final mo aJ = a("store");
    public static final mo aK = a("subway_station");
    public static final mo aL = a("synagogue");
    public static final mo aM = a("taxi_stand");
    public static final mo aN = a("train_station");
    public static final mo aO = a("travel_agency");
    public static final mo aP = a("university");
    public static final mo aQ = a("veterinary_care");
    public static final mo aR = a("zoo");
    public static final mo aS = a("administrative_area_level_1");
    public static final mo aT = a("administrative_area_level_2");
    public static final mo aU = a("administrative_area_level_3");
    public static final mo aV = a("colloquial_area");
    public static final mo aW = a("country");
    public static final mo aX = a("floor");
    public static final mo aY = a("geocode");
    public static final mo aZ = a("intersection");
    public static final mo ba = a("locality");
    public static final mo bb = a("natural_feature");
    public static final mo bc = a("neighborhood");
    public static final mo bd = a("political");
    public static final mo be = a("point_of_interest");
    public static final mo bf = a("post_box");
    public static final mo bg = a("postal_code");
    public static final mo bh = a("postal_code_prefix");
    public static final mo bi = a("postal_town");
    public static final mo bj = a("premise");
    public static final mo bk = a("room");
    public static final mo bl = a("route");
    public static final mo bm = a("street_address");
    public static final mo bn = a("sublocality");
    public static final mo bo = a("sublocality_level_1");
    public static final mo bp = a("sublocality_level_2");
    public static final mo bq = a("sublocality_level_3");
    public static final mo br = a("sublocality_level_4");
    public static final mo bs = a("sublocality_level_5");
    public static final mo bt = a("subpremise");
    public static final mo bu = a("transit_station");
    public static final mo bv = a("other");
    public static final aic CREATOR = new aic();

    public mo(int i2, String str) {
        kb.a(str);
        this.bw = i2;
        this.bx = str;
    }

    public static mo a(String str) {
        return new mo(0, str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aic aicVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof mo) && this.bx.equals(((mo) obj).bx);
    }

    public int hashCode() {
        return this.bx.hashCode();
    }

    public String toString() {
        return this.bx;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        aic aicVar = CREATOR;
        aic.a(this, parcel, i2);
    }
}
