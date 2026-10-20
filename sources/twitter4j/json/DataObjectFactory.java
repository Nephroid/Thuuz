package twitter4j.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import twitter4j.AccountTotals;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.SavedSearch;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class DataObjectFactory {
    private static final Constructor<IDs> IDsConstructor;
    private static final Constructor<AccountTotals> accountTotalsConstructor;
    private static final Constructor<Category> categoryConstructor;
    private static final Constructor<DirectMessage> directMessageConstructor;
    private static final Constructor<Location> locationConstructor;
    private static final Constructor<OEmbed> oembedConstructor;
    private static final Constructor<Place> placeConstructor;
    private static final Method rateLimitStatusConstructor;
    private static final ThreadLocal<Map> rawJsonMap;
    private static final Constructor<Relationship> relationshipConstructor;
    private static final Constructor<SavedSearch> savedSearchConstructor;
    private static final Constructor<Status> statusConstructor;
    private static final Constructor<StatusDeletionNotice> statusDeletionNoticeConstructor;
    private static final Constructor<Trend> trendConstructor;
    private static final Constructor<Trends> trendsConstructor;
    private static final Constructor<User> userConstructor;
    private static final Constructor<UserList> userListConstructor;

    private DataObjectFactory() {
        throw new AssertionError("not intended to be instantiated.");
    }

    static {
        try {
            statusConstructor = Class.forName("twitter4j.internal.json.StatusJSONImpl").getDeclaredConstructor(JSONObject.class);
            statusConstructor.setAccessible(true);
            userConstructor = Class.forName("twitter4j.internal.json.UserJSONImpl").getDeclaredConstructor(JSONObject.class);
            userConstructor.setAccessible(true);
            relationshipConstructor = Class.forName("twitter4j.internal.json.RelationshipJSONImpl").getDeclaredConstructor(JSONObject.class);
            relationshipConstructor.setAccessible(true);
            placeConstructor = Class.forName("twitter4j.internal.json.PlaceJSONImpl").getDeclaredConstructor(JSONObject.class);
            placeConstructor.setAccessible(true);
            savedSearchConstructor = Class.forName("twitter4j.internal.json.SavedSearchJSONImpl").getDeclaredConstructor(JSONObject.class);
            savedSearchConstructor.setAccessible(true);
            trendConstructor = Class.forName("twitter4j.internal.json.TrendJSONImpl").getDeclaredConstructor(JSONObject.class);
            trendConstructor.setAccessible(true);
            trendsConstructor = Class.forName("twitter4j.internal.json.TrendsJSONImpl").getDeclaredConstructor(String.class);
            trendsConstructor.setAccessible(true);
            IDsConstructor = Class.forName("twitter4j.internal.json.IDsJSONImpl").getDeclaredConstructor(String.class);
            IDsConstructor.setAccessible(true);
            rateLimitStatusConstructor = Class.forName("twitter4j.internal.json.RateLimitStatusJSONImpl").getDeclaredMethod("createRateLimitStatuses", JSONObject.class);
            rateLimitStatusConstructor.setAccessible(true);
            categoryConstructor = Class.forName("twitter4j.internal.json.CategoryJSONImpl").getDeclaredConstructor(JSONObject.class);
            categoryConstructor.setAccessible(true);
            directMessageConstructor = Class.forName("twitter4j.internal.json.DirectMessageJSONImpl").getDeclaredConstructor(JSONObject.class);
            directMessageConstructor.setAccessible(true);
            locationConstructor = Class.forName("twitter4j.internal.json.LocationJSONImpl").getDeclaredConstructor(JSONObject.class);
            locationConstructor.setAccessible(true);
            userListConstructor = Class.forName("twitter4j.internal.json.UserListJSONImpl").getDeclaredConstructor(JSONObject.class);
            userListConstructor.setAccessible(true);
            statusDeletionNoticeConstructor = Class.forName("twitter4j.StatusDeletionNoticeImpl").getDeclaredConstructor(JSONObject.class);
            statusDeletionNoticeConstructor.setAccessible(true);
            accountTotalsConstructor = Class.forName("twitter4j.internal.json.AccountTotalsJSONImpl").getDeclaredConstructor(JSONObject.class);
            accountTotalsConstructor.setAccessible(true);
            oembedConstructor = Class.forName("twitter4j.internal.json.OEmbedJSONImpl").getDeclaredConstructor(JSONObject.class);
            oembedConstructor.setAccessible(true);
            rawJsonMap = new ThreadLocal<Map>() { // from class: twitter4j.json.DataObjectFactory.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.ThreadLocal
                public Map initialValue() {
                    return new HashMap();
                }
            };
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        } catch (NoSuchMethodException e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    public static String getRawJSON(Object obj) {
        Object obj2 = rawJsonMap.get().get(obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 != null) {
            return obj2.toString();
        }
        return null;
    }

    public static Status createStatus(String str) {
        try {
            return statusConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static User createUser(String str) {
        try {
            return userConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static AccountTotals createAccountTotals(String str) {
        try {
            return accountTotalsConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Relationship createRelationship(String str) {
        try {
            return relationshipConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Place createPlace(String str) {
        try {
            return placeConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static SavedSearch createSavedSearch(String str) {
        try {
            return savedSearchConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Trend createTrend(String str) {
        try {
            return trendConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Trends createTrends(String str) {
        try {
            return trendsConstructor.newInstance(str);
        } catch (IllegalAccessException e) {
            throw new TwitterException(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError(e3);
        }
    }

    public static IDs createIDs(String str) {
        try {
            return IDsConstructor.newInstance(str);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        }
    }

    public static Map<String, RateLimitStatus> createRateLimitStatus(String str) {
        try {
            return (Map) rateLimitStatusConstructor.invoke(Class.forName("twitter4j.internal.json.RateLimitStatusJSONImpl"), new JSONObject(str));
        } catch (ClassNotFoundException e) {
            throw new TwitterException(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Category createCategory(String str) {
        try {
            return categoryConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static DirectMessage createDirectMessage(String str) {
        try {
            return directMessageConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Location createLocation(String str) {
        try {
            return locationConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static UserList createUserList(String str) {
        try {
            return userListConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static OEmbed createOEmbed(String str) {
        try {
            return oembedConstructor.newInstance(new JSONObject(str));
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    public static Object createObject(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (JSONObjectType.determine(jSONObject)) {
                case SENDER:
                    return registerJSONObject(directMessageConstructor.newInstance(jSONObject.getJSONObject("direct_message")), jSONObject);
                case STATUS:
                    return registerJSONObject(statusConstructor.newInstance(jSONObject), jSONObject);
                case DIRECT_MESSAGE:
                    return registerJSONObject(directMessageConstructor.newInstance(jSONObject.getJSONObject("direct_message")), jSONObject);
                case DELETE:
                    return registerJSONObject(statusDeletionNoticeConstructor.newInstance(jSONObject.getJSONObject("delete").getJSONObject("status")), jSONObject);
                case LIMIT:
                case SCRUB_GEO:
                default:
                    return jSONObject;
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new TwitterException(e2);
        } catch (InvocationTargetException e3) {
            throw new TwitterException(e3);
        } catch (JSONException e4) {
            throw new TwitterException(e4);
        }
    }

    static void clearThreadLocalMap() {
        rawJsonMap.get().clear();
    }

    static <T> T registerJSONObject(T t, Object obj) {
        rawJsonMap.get().put(t, obj);
        return t;
    }
}
