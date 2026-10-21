package twitter4j.internal.json;
// [removed legacy tracker import]
import com.samsung.android.sdk.richnotification.Utilities;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class LocationJSONImpl implements Location {
    private static final long serialVersionUID = 7095092358530897222L;
    private final String countryCode;
    private final String countryName;
    private final String name;
    private final int placeCode;
    private final String placeName;
    private final String url;
    private final int woeid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationJSONImpl(JSONObject jSONObject) {
        try {
            this.woeid = z_T4JInternalParseUtil.getInt("woeid", jSONObject);
            this.countryName = z_T4JInternalParseUtil.getUnescapedString("country", jSONObject);
            this.countryCode = z_T4JInternalParseUtil.getRawString("countryCode", jSONObject);
            if (!jSONObject.isNull("placeType")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("placeType");
                this.placeName = z_T4JInternalParseUtil.getUnescapedString(Utilities.DB_KEY_IMAGE_NAME, jSONObject2);
                this.placeCode = z_T4JInternalParseUtil.getInt("code", jSONObject2);
            } else {
                this.placeName = null;
                this.placeCode = -1;
            }
            this.name = z_T4JInternalParseUtil.getUnescapedString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
            this.url = z_T4JInternalParseUtil.getUnescapedString(NativeProtocol.IMAGE_URL_KEY, jSONObject);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Location> createLocationList(HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        return createLocationList(httpResponse.asJSONArray(), configuration.isJSONStoreEnabled());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Location> createLocationList(JSONArray jSONArray, boolean z) {
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, (HttpResponse) null);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LocationJSONImpl locationJSONImpl = new LocationJSONImpl(jSONObject);
                responseListImpl.add(locationJSONImpl);
                if (z) {
                    DataObjectFactoryUtil.registerJSONObject(locationJSONImpl, jSONObject);
                }
            }
            if (z) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (TwitterException e) {
            throw e;
        } catch (JSONException e2) {
            throw new TwitterException(e2);
        }
    }

    @Override // twitter4j.Location
    public int getWoeid() {
        return this.woeid;
    }

    @Override // twitter4j.Location
    public String getCountryName() {
        return this.countryName;
    }

    @Override // twitter4j.Location
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override // twitter4j.Location
    public String getPlaceName() {
        return this.placeName;
    }

    @Override // twitter4j.Location
    public int getPlaceCode() {
        return this.placeCode;
    }

    @Override // twitter4j.Location
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Location
    public String getURL() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocationJSONImpl) && this.woeid == ((LocationJSONImpl) obj).woeid;
    }

    public int hashCode() {
        return this.woeid;
    }

    public String toString() {
        return "LocationJSONImpl{woeid=" + this.woeid + ", countryName='" + this.countryName + "', countryCode='" + this.countryCode + "', placeName='" + this.placeName + "', placeCode='" + this.placeCode + "', name='" + this.name + "', url='" + this.url + "'}";
    }
}
