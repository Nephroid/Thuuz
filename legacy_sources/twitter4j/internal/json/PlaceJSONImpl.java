package twitter4j.internal.json;
// [removed legacy tracker import]
import com.samsung.android.sdk.richnotification.Utilities;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class PlaceJSONImpl extends TwitterResponseImpl implements Serializable, Place {
    private static final long serialVersionUID = -2873364341474633812L;
    private GeoLocation[][] boundingBoxCoordinates;
    private String boundingBoxType;
    private Place[] containedWithIn;
    private String country;
    private String countryCode;
    private String fullName;
    private GeoLocation[][] geometryCoordinates;
    private String geometryType;
    private String id;
    private String name;
    private String placeType;
    private String streetAddress;
    private String url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaceJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, asJSONObject);
        }
    }

    PlaceJSONImpl(JSONObject jSONObject, HttpResponse httpResponse) {
        super(httpResponse);
        init(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaceJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    PlaceJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            this.name = z_T4JInternalParseUtil.getUnescapedString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
            this.streetAddress = z_T4JInternalParseUtil.getUnescapedString("street_address", jSONObject);
            this.countryCode = z_T4JInternalParseUtil.getRawString("country_code", jSONObject);
            this.id = z_T4JInternalParseUtil.getRawString("id", jSONObject);
            this.country = z_T4JInternalParseUtil.getRawString("country", jSONObject);
            if (!jSONObject.isNull("place_type")) {
                this.placeType = z_T4JInternalParseUtil.getRawString("place_type", jSONObject);
            } else {
                this.placeType = z_T4JInternalParseUtil.getRawString("type", jSONObject);
            }
            this.url = z_T4JInternalParseUtil.getRawString(NativeProtocol.IMAGE_URL_KEY, jSONObject);
            this.fullName = z_T4JInternalParseUtil.getRawString("full_name", jSONObject);
            if (!jSONObject.isNull("bounding_box")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("bounding_box");
                this.boundingBoxType = z_T4JInternalParseUtil.getRawString("type", jSONObject2);
                this.boundingBoxCoordinates = z_T4JInternalJSONImplFactory.coordinatesAsGeoLocationArray(jSONObject2.getJSONArray("coordinates"));
            } else {
                this.boundingBoxType = null;
                this.boundingBoxCoordinates = (GeoLocation[][]) null;
            }
            if (!jSONObject.isNull("geometry")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("geometry");
                this.geometryType = z_T4JInternalParseUtil.getRawString("type", jSONObject3);
                JSONArray jSONArray = jSONObject3.getJSONArray("coordinates");
                if (this.geometryType.equals("Point")) {
                    this.geometryCoordinates = (GeoLocation[][]) Array.newInstance((Class<?>) GeoLocation.class, 1, 1);
                    this.geometryCoordinates[0][0] = new GeoLocation(jSONArray.getDouble(0), jSONArray.getDouble(1));
                } else if (this.geometryType.equals("Polygon")) {
                    this.geometryCoordinates = z_T4JInternalJSONImplFactory.coordinatesAsGeoLocationArray(jSONArray);
                } else {
                    this.geometryType = null;
                    this.geometryCoordinates = (GeoLocation[][]) null;
                }
            } else {
                this.geometryType = null;
                this.geometryCoordinates = (GeoLocation[][]) null;
            }
            if (!jSONObject.isNull("contained_within")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("contained_within");
                this.containedWithIn = new Place[jSONArray2.length()];
                for (int i = 0; i < jSONArray2.length(); i++) {
                    this.containedWithIn[i] = new PlaceJSONImpl(jSONArray2.getJSONObject(i));
                }
                return;
            }
            this.containedWithIn = null;
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Place place) {
        return this.id.compareTo(place.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Place> createPlaceList(HttpResponse httpResponse, Configuration configuration) {
        JSONObject jSONObject = null;
        try {
            jSONObject = httpResponse.asJSONObject();
            return createPlaceList(jSONObject.getJSONObject("result").getJSONArray("places"), httpResponse, configuration);
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Place> createPlaceList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                PlaceJSONImpl placeJSONImpl = new PlaceJSONImpl(jSONObject);
                responseListImpl.add(placeJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(placeJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (TwitterException e) {
            throw e;
        } catch (JSONException e2) {
            throw new TwitterException(e2);
        }
    }

    @Override // twitter4j.Place
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Place
    public String getStreetAddress() {
        return this.streetAddress;
    }

    @Override // twitter4j.Place
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override // twitter4j.Place
    public String getId() {
        return this.id;
    }

    @Override // twitter4j.Place
    public String getCountry() {
        return this.country;
    }

    @Override // twitter4j.Place
    public String getPlaceType() {
        return this.placeType;
    }

    @Override // twitter4j.Place
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.Place
    public String getFullName() {
        return this.fullName;
    }

    @Override // twitter4j.Place
    public String getBoundingBoxType() {
        return this.boundingBoxType;
    }

    @Override // twitter4j.Place
    public GeoLocation[][] getBoundingBoxCoordinates() {
        return this.boundingBoxCoordinates;
    }

    @Override // twitter4j.Place
    public String getGeometryType() {
        return this.geometryType;
    }

    @Override // twitter4j.Place
    public GeoLocation[][] getGeometryCoordinates() {
        return this.geometryCoordinates;
    }

    @Override // twitter4j.Place
    public Place[] getContainedWithIn() {
        return this.containedWithIn;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            return (obj instanceof Place) && ((Place) obj).getId().equals(this.id);
        }
        return true;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "PlaceJSONImpl{name='" + this.name + "', streetAddress='" + this.streetAddress + "', countryCode='" + this.countryCode + "', id='" + this.id + "', country='" + this.country + "', placeType='" + this.placeType + "', url='" + this.url + "', fullName='" + this.fullName + "', boundingBoxType='" + this.boundingBoxType + "', boundingBoxCoordinates=" + (this.boundingBoxCoordinates == null ? null : Arrays.asList(this.boundingBoxCoordinates)) + ", geometryType='" + this.geometryType + "', geometryCoordinates=" + (this.geometryCoordinates == null ? null : Arrays.asList(this.geometryCoordinates)) + ", containedWithIn=" + (this.containedWithIn != null ? Arrays.asList(this.containedWithIn) : null) + '}';
    }
}
