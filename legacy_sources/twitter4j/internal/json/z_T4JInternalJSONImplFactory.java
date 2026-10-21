package twitter4j.internal.json;

import java.util.Map;
import twitter4j.AccountSettings;
import twitter4j.AccountTotals;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.SimilarPlaces;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserMentionEntity;
import twitter4j.api.HelpResources;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;
import twitter4j.internal.util.z_T4JInternalStringUtil;

/* loaded from: classes.dex */
public class z_T4JInternalJSONImplFactory implements z_T4JInternalFactory {
    private static final long serialVersionUID = 5217622295050444866L;
    private Configuration conf;

    public z_T4JInternalJSONImplFactory(Configuration configuration) {
        this.conf = configuration;
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Status createStatus(JSONObject jSONObject) {
        return new StatusJSONImpl(jSONObject);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public User createUser(JSONObject jSONObject) {
        return new UserJSONImpl(jSONObject);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public UserList createAUserList(JSONObject jSONObject) {
        return new UserListJSONImpl(jSONObject);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public DirectMessage createDirectMessage(JSONObject jSONObject) {
        return new DirectMessageJSONImpl(jSONObject);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse httpResponse) {
        return RateLimitStatusJSONImpl.createRateLimitStatuses(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Status createStatus(HttpResponse httpResponse) {
        return new StatusJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<Status> createStatusList(HttpResponse httpResponse) {
        return StatusJSONImpl.createStatusList(httpResponse, this.conf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GeoLocation createGeoLocation(JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull("coordinates")) {
                String[] split = z_T4JInternalStringUtil.split(jSONObject.getJSONObject("coordinates").getString("coordinates").substring(1, r0.length() - 1), ",");
                return new GeoLocation(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
            }
            return null;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GeoLocation[][] coordinatesAsGeoLocationArray(JSONArray jSONArray) {
        try {
            GeoLocation[][] geoLocationArr = new GeoLocation[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                geoLocationArr[i] = new GeoLocation[jSONArray2.length()];
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONArray jSONArray3 = jSONArray2.getJSONArray(i2);
                    geoLocationArr[i][i2] = new GeoLocation(jSONArray3.getDouble(1), jSONArray3.getDouble(0));
                }
            }
            return geoLocationArr;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public static RateLimitStatus createRateLimitStatusFromResponseHeader(HttpResponse httpResponse) {
        return RateLimitStatusJSONImpl.createFromResponseHeader(httpResponse);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Trends createTrends(HttpResponse httpResponse) {
        return new TrendsJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public User createUser(HttpResponse httpResponse) {
        return new UserJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<User> createUserList(HttpResponse httpResponse) {
        return UserJSONImpl.createUserList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<User> createUserListFromJSONArray(HttpResponse httpResponse) {
        return UserJSONImpl.createUserList(httpResponse.asJSONArray(), httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<User> createUserListFromJSONArray_Users(HttpResponse httpResponse) {
        try {
            return UserJSONImpl.createUserList(httpResponse.asJSONObject().getJSONArray("users"), httpResponse, this.conf);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public QueryResult createQueryResult(HttpResponse httpResponse, Query query) {
        try {
            return new QueryResultJSONImpl(httpResponse, this.conf);
        } catch (TwitterException e) {
            if (404 == e.getStatusCode()) {
                return new QueryResultJSONImpl(query);
            }
            throw e;
        }
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public IDs createIDs(HttpResponse httpResponse) {
        return new IDsJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public PagableResponseList<User> createPagableUserList(HttpResponse httpResponse) {
        return UserJSONImpl.createPagableUserList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public UserList createAUserList(HttpResponse httpResponse) {
        return new UserListJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse) {
        return UserListJSONImpl.createPagableUserListList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<UserList> createUserListList(HttpResponse httpResponse) {
        return UserListJSONImpl.createUserListList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<Category> createCategoryList(HttpResponse httpResponse) {
        return CategoryJSONImpl.createCategoriesList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public DirectMessage createDirectMessage(HttpResponse httpResponse) {
        return new DirectMessageJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<DirectMessage> createDirectMessageList(HttpResponse httpResponse) {
        return DirectMessageJSONImpl.createDirectMessageList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Relationship createRelationship(HttpResponse httpResponse) {
        return new RelationshipJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse) {
        return FriendshipJSONImpl.createFriendshipList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public AccountTotals createAccountTotals(HttpResponse httpResponse) {
        return new AccountTotalsJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public AccountSettings createAccountSettings(HttpResponse httpResponse) {
        return new AccountSettingsJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public SavedSearch createSavedSearch(HttpResponse httpResponse) {
        return new SavedSearchJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse) {
        return SavedSearchJSONImpl.createSavedSearchList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<Location> createLocationList(HttpResponse httpResponse) {
        return LocationJSONImpl.createLocationList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public Place createPlace(HttpResponse httpResponse) {
        return new PlaceJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<Place> createPlaceList(HttpResponse httpResponse) {
        try {
            return PlaceJSONImpl.createPlaceList(httpResponse, this.conf);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 404) {
                return new ResponseListImpl(0, (HttpResponse) null);
            }
            throw e;
        }
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public SimilarPlaces createSimilarPlaces(HttpResponse httpResponse) {
        return SimilarPlacesImpl.createSimilarPlaces(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public TwitterAPIConfiguration createTwitterAPIConfiguration(HttpResponse httpResponse) {
        return new TwitterAPIConfigurationJSONImpl(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public ResponseList<HelpResources.Language> createLanguageList(HttpResponse httpResponse) {
        return LanguageJSONImpl.createLanguageList(httpResponse, this.conf);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public <T> ResponseList<T> createEmptyResponseList() {
        return new ResponseListImpl(0, (HttpResponse) null);
    }

    @Override // twitter4j.internal.json.z_T4JInternalFactory
    public OEmbed createOEmbed(HttpResponse httpResponse) {
        return new OEmbedJSONImpl(httpResponse, this.conf);
    }

    public static HashtagEntity createHashtagEntity(int i, int i2, String str) {
        return new HashtagEntityJSONImpl(i, i2, str);
    }

    public static UserMentionEntity createUserMentionEntity(int i, int i2, String str, String str2, long j) {
        return new UserMentionEntityJSONImpl(i, i2, str, str2, j);
    }

    public static URLEntity createUrlEntity(int i, int i2, String str, String str2, String str3) {
        return new URLEntityJSONImpl(i, i2, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z_T4JInternalJSONImplFactory)) {
            return false;
        }
        z_T4JInternalJSONImplFactory z_t4jinternaljsonimplfactory = (z_T4JInternalJSONImplFactory) obj;
        if (this.conf != null) {
            if (this.conf.equals(z_t4jinternaljsonimplfactory.conf)) {
                return true;
            }
        } else if (z_t4jinternaljsonimplfactory.conf == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.conf != null) {
            return this.conf.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "JSONImplFactory{conf=" + this.conf + '}';
    }
}
