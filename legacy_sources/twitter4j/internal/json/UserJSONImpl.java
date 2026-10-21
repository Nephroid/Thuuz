package twitter4j.internal.json;

import android.net.http.Headers;
// [removed legacy tracker import]
import com.samsung.android.sdk.richnotification.Utilities;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class UserJSONImpl extends TwitterResponseImpl implements Serializable, User {
    private static final long serialVersionUID = -6345893237975349030L;
    private Date createdAt;
    private String description;
    private URLEntity[] descriptionURLEntities;
    private int favouritesCount;
    private int followersCount;
    private int friendsCount;
    private long id;
    private boolean isContributorsEnabled;
    private boolean isFollowRequestSent;
    private boolean isGeoEnabled;
    private boolean isProtected;
    private boolean isVerified;
    private String lang;
    private int listedCount;
    private String location;
    private String name;
    private String profileBackgroundColor;
    private String profileBackgroundImageUrl;
    private String profileBackgroundImageUrlHttps;
    private boolean profileBackgroundTiled;
    private String profileBannerImageUrl;
    private String profileImageUrl;
    private String profileImageUrlHttps;
    private String profileLinkColor;
    private String profileSidebarBorderColor;
    private String profileSidebarFillColor;
    private String profileTextColor;
    private boolean profileUseBackgroundImage;
    private String screenName;
    private boolean showAllInlineMedia;
    private Status status;
    private int statusesCount;
    private String timeZone;
    private boolean translator;
    private String url;
    private URLEntity urlEntity;
    private int utcOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(this, asJSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    UserJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            this.id = z_T4JInternalParseUtil.getLong("id", jSONObject);
            this.name = z_T4JInternalParseUtil.getRawString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
            this.screenName = z_T4JInternalParseUtil.getRawString("screen_name", jSONObject);
            this.location = z_T4JInternalParseUtil.getRawString(Headers.LOCATION, jSONObject);
            this.descriptionURLEntities = getURLEntitiesFromJSON(jSONObject, "description");
            this.descriptionURLEntities = this.descriptionURLEntities == null ? new URLEntity[0] : this.descriptionURLEntities;
            URLEntity[] uRLEntitiesFromJSON = getURLEntitiesFromJSON(jSONObject, NativeProtocol.IMAGE_URL_KEY);
            if (uRLEntitiesFromJSON != null && uRLEntitiesFromJSON.length > 0) {
                this.urlEntity = uRLEntitiesFromJSON[0];
            }
            this.description = z_T4JInternalParseUtil.getRawString("description", jSONObject);
            if (this.description != null) {
                this.description = HTMLEntity.unescapeAndSlideEntityIncdices(this.description, null, this.descriptionURLEntities, null, null);
            }
            this.isContributorsEnabled = z_T4JInternalParseUtil.getBoolean("contributors_enabled", jSONObject);
            this.profileImageUrl = z_T4JInternalParseUtil.getRawString("profile_image_url", jSONObject);
            this.profileImageUrlHttps = z_T4JInternalParseUtil.getRawString("profile_image_url_https", jSONObject);
            this.url = z_T4JInternalParseUtil.getRawString(NativeProtocol.IMAGE_URL_KEY, jSONObject);
            this.isProtected = z_T4JInternalParseUtil.getBoolean("protected", jSONObject);
            this.isGeoEnabled = z_T4JInternalParseUtil.getBoolean("geo_enabled", jSONObject);
            this.isVerified = z_T4JInternalParseUtil.getBoolean("verified", jSONObject);
            this.translator = z_T4JInternalParseUtil.getBoolean("is_translator", jSONObject);
            this.followersCount = z_T4JInternalParseUtil.getInt("followers_count", jSONObject);
            this.profileBackgroundColor = z_T4JInternalParseUtil.getRawString("profile_background_color", jSONObject);
            this.profileTextColor = z_T4JInternalParseUtil.getRawString("profile_text_color", jSONObject);
            this.profileLinkColor = z_T4JInternalParseUtil.getRawString("profile_link_color", jSONObject);
            this.profileSidebarFillColor = z_T4JInternalParseUtil.getRawString("profile_sidebar_fill_color", jSONObject);
            this.profileSidebarBorderColor = z_T4JInternalParseUtil.getRawString("profile_sidebar_border_color", jSONObject);
            this.profileUseBackgroundImage = z_T4JInternalParseUtil.getBoolean("profile_use_background_image", jSONObject);
            this.showAllInlineMedia = z_T4JInternalParseUtil.getBoolean("show_all_inline_media", jSONObject);
            this.friendsCount = z_T4JInternalParseUtil.getInt("friends_count", jSONObject);
            this.createdAt = z_T4JInternalParseUtil.getDate("created_at", jSONObject, "EEE MMM dd HH:mm:ss z yyyy");
            this.favouritesCount = z_T4JInternalParseUtil.getInt("favourites_count", jSONObject);
            this.utcOffset = z_T4JInternalParseUtil.getInt("utc_offset", jSONObject);
            this.timeZone = z_T4JInternalParseUtil.getRawString("time_zone", jSONObject);
            this.profileBackgroundImageUrl = z_T4JInternalParseUtil.getRawString("profile_background_image_url", jSONObject);
            this.profileBackgroundImageUrlHttps = z_T4JInternalParseUtil.getRawString("profile_background_image_url_https", jSONObject);
            this.profileBannerImageUrl = z_T4JInternalParseUtil.getRawString("profile_banner_url", jSONObject);
            this.profileBackgroundTiled = z_T4JInternalParseUtil.getBoolean("profile_background_tile", jSONObject);
            this.lang = z_T4JInternalParseUtil.getRawString("lang", jSONObject);
            this.statusesCount = z_T4JInternalParseUtil.getInt("statuses_count", jSONObject);
            this.listedCount = z_T4JInternalParseUtil.getInt("listed_count", jSONObject);
            this.isFollowRequestSent = z_T4JInternalParseUtil.getBoolean("follow_request_sent", jSONObject);
            if (!jSONObject.isNull("status")) {
                this.status = new StatusJSONImpl(jSONObject.getJSONObject("status"));
            }
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    private static URLEntity[] getURLEntitiesFromJSON(JSONObject jSONObject, String str) {
        if (!jSONObject.isNull("entities")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("entities");
            if (!jSONObject2.isNull(str)) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject(str);
                if (!jSONObject3.isNull("urls")) {
                    JSONArray jSONArray = jSONObject3.getJSONArray("urls");
                    int length = jSONArray.length();
                    URLEntity[] uRLEntityArr = new URLEntity[length];
                    for (int i = 0; i < length; i++) {
                        uRLEntityArr[i] = new URLEntityJSONImpl(jSONArray.getJSONObject(i));
                    }
                    return uRLEntityArr;
                }
            }
        }
        return null;
    }

    @Override // java.lang.Comparable
    public int compareTo(User user) {
        return (int) (this.id - user.getId());
    }

    @Override // twitter4j.User
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.User
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.User
    public String getScreenName() {
        return this.screenName;
    }

    @Override // twitter4j.User
    public String getLocation() {
        return this.location;
    }

    @Override // twitter4j.User
    public String getDescription() {
        return this.description;
    }

    @Override // twitter4j.User
    public boolean isContributorsEnabled() {
        return this.isContributorsEnabled;
    }

    @Override // twitter4j.User
    public String getProfileImageURL() {
        return this.profileImageUrl;
    }

    @Override // twitter4j.User
    public String getBiggerProfileImageURL() {
        return toResizedURL(this.profileImageUrl, "_bigger");
    }

    @Override // twitter4j.User
    public String getMiniProfileImageURL() {
        return toResizedURL(this.profileImageUrl, "_mini");
    }

    @Override // twitter4j.User
    public String getOriginalProfileImageURL() {
        return toResizedURL(this.profileImageUrl, "");
    }

    private String toResizedURL(String str, String str2) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("_");
        int lastIndexOf2 = str.lastIndexOf(".");
        int lastIndexOf3 = str.lastIndexOf("/");
        String str3 = str.substring(0, lastIndexOf) + str2;
        if (lastIndexOf2 > lastIndexOf3) {
            return str3 + str.substring(lastIndexOf2);
        }
        return str3;
    }

    @Override // twitter4j.User
    public URL getProfileImageUrlHttps() {
        try {
            return new URL(this.profileImageUrlHttps);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override // twitter4j.User
    public String getProfileImageURLHttps() {
        return this.profileImageUrlHttps;
    }

    @Override // twitter4j.User
    public String getBiggerProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, "_bigger");
    }

    @Override // twitter4j.User
    public String getMiniProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, "_mini");
    }

    @Override // twitter4j.User
    public String getOriginalProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, "");
    }

    @Override // twitter4j.User
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.User
    public boolean isProtected() {
        return this.isProtected;
    }

    @Override // twitter4j.User
    public int getFollowersCount() {
        return this.followersCount;
    }

    @Override // twitter4j.User
    public String getProfileBackgroundColor() {
        return this.profileBackgroundColor;
    }

    @Override // twitter4j.User
    public String getProfileTextColor() {
        return this.profileTextColor;
    }

    @Override // twitter4j.User
    public String getProfileLinkColor() {
        return this.profileLinkColor;
    }

    @Override // twitter4j.User
    public String getProfileSidebarFillColor() {
        return this.profileSidebarFillColor;
    }

    @Override // twitter4j.User
    public String getProfileSidebarBorderColor() {
        return this.profileSidebarBorderColor;
    }

    @Override // twitter4j.User
    public boolean isProfileUseBackgroundImage() {
        return this.profileUseBackgroundImage;
    }

    @Override // twitter4j.User
    public boolean isShowAllInlineMedia() {
        return this.showAllInlineMedia;
    }

    @Override // twitter4j.User
    public int getFriendsCount() {
        return this.friendsCount;
    }

    @Override // twitter4j.User
    public Status getStatus() {
        return this.status;
    }

    @Override // twitter4j.User
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.User
    public int getFavouritesCount() {
        return this.favouritesCount;
    }

    @Override // twitter4j.User
    public int getUtcOffset() {
        return this.utcOffset;
    }

    @Override // twitter4j.User
    public String getTimeZone() {
        return this.timeZone;
    }

    @Override // twitter4j.User
    public String getProfileBackgroundImageUrl() {
        return getProfileBackgroundImageURL();
    }

    @Override // twitter4j.User
    public String getProfileBackgroundImageURL() {
        return this.profileBackgroundImageUrl;
    }

    @Override // twitter4j.User
    public String getProfileBackgroundImageUrlHttps() {
        return this.profileBackgroundImageUrlHttps;
    }

    @Override // twitter4j.User
    public String getProfileBannerURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/web";
        }
        return null;
    }

    @Override // twitter4j.User
    public String getProfileBannerRetinaURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/web_retina";
        }
        return null;
    }

    @Override // twitter4j.User
    public String getProfileBannerIPadURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/ipad";
        }
        return null;
    }

    @Override // twitter4j.User
    public String getProfileBannerIPadRetinaURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/ipad_retina";
        }
        return null;
    }

    @Override // twitter4j.User
    public String getProfileBannerMobileURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/mobile";
        }
        return null;
    }

    @Override // twitter4j.User
    public String getProfileBannerMobileRetinaURL() {
        if (this.profileBannerImageUrl != null) {
            return this.profileBannerImageUrl + "/mobile_retina";
        }
        return null;
    }

    @Override // twitter4j.User
    public boolean isProfileBackgroundTiled() {
        return this.profileBackgroundTiled;
    }

    @Override // twitter4j.User
    public String getLang() {
        return this.lang;
    }

    @Override // twitter4j.User
    public int getStatusesCount() {
        return this.statusesCount;
    }

    @Override // twitter4j.User
    public boolean isGeoEnabled() {
        return this.isGeoEnabled;
    }

    @Override // twitter4j.User
    public boolean isVerified() {
        return this.isVerified;
    }

    @Override // twitter4j.User
    public boolean isTranslator() {
        return this.translator;
    }

    @Override // twitter4j.User
    public int getListedCount() {
        return this.listedCount;
    }

    @Override // twitter4j.User
    public boolean isFollowRequestSent() {
        return this.isFollowRequestSent;
    }

    @Override // twitter4j.User
    public URLEntity[] getDescriptionURLEntities() {
        return this.descriptionURLEntities;
    }

    @Override // twitter4j.User
    public URLEntity getURLEntity() {
        if (this.urlEntity == null) {
            String str = this.url == null ? "" : this.url;
            this.urlEntity = new URLEntityJSONImpl(0, str.length(), str, str, str);
        }
        return this.urlEntity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PagableResponseList<User> createPagableUserList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject asJSONObject = httpResponse.asJSONObject();
            JSONArray jSONArray = asJSONObject.getJSONArray("users");
            int length = jSONArray.length();
            PagableResponseListImpl pagableResponseListImpl = new PagableResponseListImpl(length, asJSONObject, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserJSONImpl userJSONImpl = new UserJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userJSONImpl, jSONObject);
                }
                pagableResponseListImpl.add(userJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(pagableResponseListImpl, asJSONObject);
            }
            return pagableResponseListImpl;
        } catch (TwitterException e) {
            throw e;
        } catch (JSONException e2) {
            throw new TwitterException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<User> createUserList(HttpResponse httpResponse, Configuration configuration) {
        return createUserList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<User> createUserList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserJSONImpl userJSONImpl = new UserJSONImpl(jSONObject);
                responseListImpl.add(userJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userJSONImpl, jSONObject);
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

    public int hashCode() {
        return (int) this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            return (obj instanceof User) && ((User) obj).getId() == this.id;
        }
        return true;
    }

    public String toString() {
        return "UserJSONImpl{id=" + this.id + ", name='" + this.name + "', screenName='" + this.screenName + "', location='" + this.location + "', description='" + this.description + "', isContributorsEnabled=" + this.isContributorsEnabled + ", profileImageUrl='" + this.profileImageUrl + "', profileImageUrlHttps='" + this.profileImageUrlHttps + "', url='" + this.url + "', isProtected=" + this.isProtected + ", followersCount=" + this.followersCount + ", status=" + this.status + ", profileBackgroundColor='" + this.profileBackgroundColor + "', profileTextColor='" + this.profileTextColor + "', profileLinkColor='" + this.profileLinkColor + "', profileSidebarFillColor='" + this.profileSidebarFillColor + "', profileSidebarBorderColor='" + this.profileSidebarBorderColor + "', profileUseBackgroundImage=" + this.profileUseBackgroundImage + ", showAllInlineMedia=" + this.showAllInlineMedia + ", friendsCount=" + this.friendsCount + ", createdAt=" + this.createdAt + ", favouritesCount=" + this.favouritesCount + ", utcOffset=" + this.utcOffset + ", timeZone='" + this.timeZone + "', profileBackgroundImageUrl='" + this.profileBackgroundImageUrl + "', profileBackgroundImageUrlHttps='" + this.profileBackgroundImageUrlHttps + "', profileBackgroundTiled=" + this.profileBackgroundTiled + ", lang='" + this.lang + "', statusesCount=" + this.statusesCount + ", isGeoEnabled=" + this.isGeoEnabled + ", isVerified=" + this.isVerified + ", translator=" + this.translator + ", listedCount=" + this.listedCount + ", isFollowRequestSent=" + this.isFollowRequestSent + '}';
    }
}
