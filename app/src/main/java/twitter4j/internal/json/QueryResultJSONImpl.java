package twitter4j.internal.json;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
final class QueryResultJSONImpl extends TwitterResponseImpl implements Serializable, QueryResult {
    static Method queryFactoryMethod = null;
    private static final long serialVersionUID = -6781654399437121238L;
    private double completedIn;
    private int count;
    private long maxId;
    private String nextResults;
    private String query;
    private String refreshUrl;
    private long sinceId;
    private List<Status> tweets;

    static {
        Method[] declaredMethods = Query.class.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Method method = declaredMethods[i];
            if (!method.getName().equals("createWithNextPageQuery")) {
                i++;
            } else {
                queryFactoryMethod = method;
                queryFactoryMethod.setAccessible(true);
                break;
            }
        }
        if (queryFactoryMethod == null) {
            throw new ExceptionInInitializerError(new NoSuchMethodException("twitter4j.Query.createWithNextPageQuery(java.lang.String)"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryResultJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        try {
            JSONObject jSONObject = asJSONObject.getJSONObject("search_metadata");
            this.completedIn = z_T4JInternalParseUtil.getDouble("completed_in", jSONObject);
            this.count = z_T4JInternalParseUtil.getInt("count", jSONObject);
            this.maxId = z_T4JInternalParseUtil.getLong("max_id", jSONObject);
            this.nextResults = jSONObject.has("next_results") ? jSONObject.getString("next_results") : null;
            this.query = z_T4JInternalParseUtil.getURLDecodedString("query", jSONObject);
            this.refreshUrl = z_T4JInternalParseUtil.getUnescapedString("refresh_url", jSONObject);
            this.sinceId = z_T4JInternalParseUtil.getLong("since_id", jSONObject);
            JSONArray jSONArray = asJSONObject.getJSONArray("statuses");
            this.tweets = new ArrayList(jSONArray.length());
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                this.tweets.add(new StatusJSONImpl(jSONArray.getJSONObject(i), configuration));
            }
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + asJSONObject.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryResultJSONImpl(Query query) {
        this.sinceId = query.getSinceId();
        this.count = query.getCount();
        this.tweets = new ArrayList(0);
    }

    @Override // twitter4j.QueryResult
    public long getSinceId() {
        return this.sinceId;
    }

    @Override // twitter4j.QueryResult
    public long getMaxId() {
        return this.maxId;
    }

    @Override // twitter4j.QueryResult
    public String getRefreshUrl() {
        return getRefreshURL();
    }

    @Override // twitter4j.QueryResult
    public String getRefreshURL() {
        return this.refreshUrl;
    }

    @Override // twitter4j.QueryResult
    public int getCount() {
        return this.count;
    }

    @Override // twitter4j.QueryResult
    public double getCompletedIn() {
        return this.completedIn;
    }

    @Override // twitter4j.QueryResult
    public String getQuery() {
        return this.query;
    }

    @Override // twitter4j.QueryResult
    public List<Status> getTweets() {
        return this.tweets;
    }

    @Override // twitter4j.QueryResult
    public Query nextQuery() {
        if (this.nextResults == null) {
            return null;
        }
        try {
            return (Query) queryFactoryMethod.invoke(null, this.nextResults);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // twitter4j.QueryResult
    public boolean hasNext() {
        return this.nextResults != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryResult queryResult = (QueryResult) obj;
        if (Double.compare(queryResult.getCompletedIn(), this.completedIn) == 0 && this.maxId == queryResult.getMaxId() && this.count == queryResult.getCount() && this.sinceId == queryResult.getSinceId() && this.query.equals(queryResult.getQuery())) {
            if (this.refreshUrl == null ? queryResult.getRefreshUrl() != null : !this.refreshUrl.equals(queryResult.getRefreshUrl())) {
                return false;
            }
            if (this.tweets != null) {
                if (this.tweets.equals(queryResult.getTweets())) {
                    return true;
                }
            } else if (queryResult.getTweets() == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.refreshUrl != null ? this.refreshUrl.hashCode() : 0) + (((((int) (this.sinceId ^ (this.sinceId >>> 32))) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)))) * 31)) * 31) + this.count;
        long doubleToLongBits = this.completedIn != 0.0d ? Double.doubleToLongBits(this.completedIn) : 0L;
        return (((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.query.hashCode()) * 31) + (this.tweets != null ? this.tweets.hashCode() : 0);
    }

    public String toString() {
        return "QueryResultJSONImpl{sinceId=" + this.sinceId + ", maxId=" + this.maxId + ", refreshUrl='" + this.refreshUrl + "', count=" + this.count + ", completedIn=" + this.completedIn + ", query='" + this.query + "', tweets=" + this.tweets + '}';
    }
}
