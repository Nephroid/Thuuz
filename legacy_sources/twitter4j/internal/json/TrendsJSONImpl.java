package twitter4j.internal.json;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class TrendsJSONImpl extends TwitterResponseImpl implements Serializable, Trends {
    private static final long serialVersionUID = -7151479143843312309L;
    private Date asOf;
    private Location location;
    private Date trendAt;
    private Trend[] trends;

    @Override // java.lang.Comparable
    public int compareTo(Trends trends) {
        return this.trendAt.compareTo(trends.getTrendAt());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrendsJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        init(httpResponse.asString(), configuration.isJSONStoreEnabled());
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, httpResponse.asString());
        }
    }

    TrendsJSONImpl(String str) {
        this(str, false);
    }

    TrendsJSONImpl(String str, boolean z) {
        init(str, z);
    }

    void init(String str, boolean z) {
        JSONObject jSONObject;
        try {
            if (str.startsWith("[")) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    jSONObject = jSONArray.getJSONObject(0);
                } else {
                    throw new TwitterException("No trends found on the specified woeid");
                }
            } else {
                jSONObject = new JSONObject(str);
            }
            this.asOf = z_T4JInternalParseUtil.parseTrendsDate(jSONObject.getString("as_of"));
            this.location = extractLocation(jSONObject, z);
            JSONArray jSONArray2 = jSONObject.getJSONArray("trends");
            this.trendAt = this.asOf;
            this.trends = jsonArrayToTrendArray(jSONArray2, z);
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage(), e);
        }
    }

    TrendsJSONImpl(Date date, Location location, Date date2, Trend[] trendArr) {
        this.asOf = date;
        this.location = location;
        this.trendAt = date2;
        this.trends = trendArr;
    }

    static ResponseList<Trends> createTrendsList(HttpResponse httpResponse, boolean z) {
        JSONObject asJSONObject = httpResponse.asJSONObject();
        try {
            Date parseTrendsDate = z_T4JInternalParseUtil.parseTrendsDate(asJSONObject.getString("as_of"));
            JSONObject jSONObject = asJSONObject.getJSONObject("trends");
            Location extractLocation = extractLocation(asJSONObject, z);
            ResponseListImpl responseListImpl = new ResponseListImpl(jSONObject.length(), httpResponse);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                Trend[] jsonArrayToTrendArray = jsonArrayToTrendArray(jSONObject.getJSONArray(str), z);
                if (str.length() == 19) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd HH:mm:ss"), jsonArrayToTrendArray));
                } else if (str.length() == 16) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd HH:mm"), jsonArrayToTrendArray));
                } else if (str.length() == 10) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd"), jsonArrayToTrendArray));
                }
            }
            Collections.sort(responseListImpl);
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + httpResponse.asString(), e);
        }
    }

    private static Location extractLocation(JSONObject jSONObject, boolean z) {
        if (jSONObject.isNull("locations")) {
            return null;
        }
        try {
            ResponseList<Location> createLocationList = LocationJSONImpl.createLocationList(jSONObject.getJSONArray("locations"), z);
            if (createLocationList.size() != 0) {
                return createLocationList.get(0);
            }
            return null;
        } catch (JSONException e) {
            throw new AssertionError("locations can't be null");
        }
    }

    private static Trend[] jsonArrayToTrendArray(JSONArray jSONArray, boolean z) {
        Trend[] trendArr = new Trend[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            trendArr[i] = new TrendJSONImpl(jSONArray.getJSONObject(i), z);
        }
        return trendArr;
    }

    @Override // twitter4j.Trends
    public Trend[] getTrends() {
        return this.trends;
    }

    @Override // twitter4j.Trends
    public Location getLocation() {
        return this.location;
    }

    @Override // twitter4j.Trends
    public Date getAsOf() {
        return this.asOf;
    }

    @Override // twitter4j.Trends
    public Date getTrendAt() {
        return this.trendAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trends)) {
            return false;
        }
        Trends trends = (Trends) obj;
        if (this.asOf == null ? trends.getAsOf() != null : !this.asOf.equals(trends.getAsOf())) {
            return false;
        }
        if (this.trendAt == null ? trends.getTrendAt() != null : !this.trendAt.equals(trends.getTrendAt())) {
            return false;
        }
        return Arrays.equals(this.trends, trends.getTrends());
    }

    public int hashCode() {
        return (((this.trendAt != null ? this.trendAt.hashCode() : 0) + ((this.asOf != null ? this.asOf.hashCode() : 0) * 31)) * 31) + (this.trends != null ? Arrays.hashCode(this.trends) : 0);
    }

    public String toString() {
        return "TrendsJSONImpl{asOf=" + this.asOf + ", trendAt=" + this.trendAt + ", trends=" + (this.trends == null ? null : Arrays.asList(this.trends)) + '}';
    }
}
