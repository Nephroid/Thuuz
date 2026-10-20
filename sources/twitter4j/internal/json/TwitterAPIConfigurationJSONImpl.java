package twitter4j.internal.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import twitter4j.MediaEntity;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.json.MediaEntityJSONImpl;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
class TwitterAPIConfigurationJSONImpl extends TwitterResponseImpl implements TwitterAPIConfiguration {
    private static final long serialVersionUID = 5786291660087491465L;
    private int charactersReservedPerMedia;
    private int maxMediaPerUpload;
    private String[] nonUsernamePaths;
    private int photoSizeLimit;
    private Map<Integer, MediaEntity.Size> photoSizes;
    private int shortURLLength;
    private int shortURLLengthHttps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TwitterAPIConfigurationJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject jSONObject;
        try {
            JSONObject asJSONObject = httpResponse.asJSONObject();
            this.photoSizeLimit = z_T4JInternalParseUtil.getInt("photo_size_limit", asJSONObject);
            this.shortURLLength = z_T4JInternalParseUtil.getInt("short_url_length", asJSONObject);
            this.shortURLLengthHttps = z_T4JInternalParseUtil.getInt("short_url_length_https", asJSONObject);
            this.charactersReservedPerMedia = z_T4JInternalParseUtil.getInt("characters_reserved_per_media", asJSONObject);
            JSONObject jSONObject2 = asJSONObject.getJSONObject("photo_sizes");
            this.photoSizes = new HashMap(4);
            this.photoSizes.put(MediaEntity.Size.LARGE, new MediaEntityJSONImpl.Size(jSONObject2.getJSONObject("large")));
            if (jSONObject2.isNull("med")) {
                jSONObject = jSONObject2.getJSONObject("medium");
            } else {
                jSONObject = jSONObject2.getJSONObject("med");
            }
            this.photoSizes.put(MediaEntity.Size.MEDIUM, new MediaEntityJSONImpl.Size(jSONObject));
            this.photoSizes.put(MediaEntity.Size.SMALL, new MediaEntityJSONImpl.Size(jSONObject2.getJSONObject("small")));
            this.photoSizes.put(MediaEntity.Size.THUMB, new MediaEntityJSONImpl.Size(jSONObject2.getJSONObject("thumb")));
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
                DataObjectFactoryUtil.registerJSONObject(this, httpResponse.asJSONObject());
            }
            JSONArray jSONArray = asJSONObject.getJSONArray("non_username_paths");
            this.nonUsernamePaths = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.nonUsernamePaths[i] = jSONArray.getString(i);
            }
            this.maxMediaPerUpload = z_T4JInternalParseUtil.getInt("max_media_per_upload", asJSONObject);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getPhotoSizeLimit() {
        return this.photoSizeLimit;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getShortURLLength() {
        return this.shortURLLength;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getShortURLLengthHttps() {
        return this.shortURLLengthHttps;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getCharactersReservedPerMedia() {
        return this.charactersReservedPerMedia;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public Map<Integer, MediaEntity.Size> getPhotoSizes() {
        return this.photoSizes;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public String[] getNonUsernamePaths() {
        return this.nonUsernamePaths;
    }

    @Override // twitter4j.TwitterAPIConfiguration
    public int getMaxMediaPerUpload() {
        return this.maxMediaPerUpload;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterAPIConfigurationJSONImpl)) {
            return false;
        }
        TwitterAPIConfigurationJSONImpl twitterAPIConfigurationJSONImpl = (TwitterAPIConfigurationJSONImpl) obj;
        if (this.charactersReservedPerMedia == twitterAPIConfigurationJSONImpl.charactersReservedPerMedia && this.maxMediaPerUpload == twitterAPIConfigurationJSONImpl.maxMediaPerUpload && this.photoSizeLimit == twitterAPIConfigurationJSONImpl.photoSizeLimit && this.shortURLLength == twitterAPIConfigurationJSONImpl.shortURLLength && this.shortURLLengthHttps == twitterAPIConfigurationJSONImpl.shortURLLengthHttps && Arrays.equals(this.nonUsernamePaths, twitterAPIConfigurationJSONImpl.nonUsernamePaths)) {
            if (this.photoSizes != null) {
                if (this.photoSizes.equals(twitterAPIConfigurationJSONImpl.photoSizes)) {
                    return true;
                }
            } else if (twitterAPIConfigurationJSONImpl.photoSizes == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.photoSizes != null ? this.photoSizes.hashCode() : 0) + (((((((this.photoSizeLimit * 31) + this.shortURLLength) * 31) + this.shortURLLengthHttps) * 31) + this.charactersReservedPerMedia) * 31)) * 31) + (this.nonUsernamePaths != null ? Arrays.hashCode(this.nonUsernamePaths) : 0)) * 31) + this.maxMediaPerUpload;
    }

    public String toString() {
        return "TwitterAPIConfigurationJSONImpl{photoSizeLimit=" + this.photoSizeLimit + ", shortURLLength=" + this.shortURLLength + ", shortURLLengthHttps=" + this.shortURLLengthHttps + ", charactersReservedPerMedia=" + this.charactersReservedPerMedia + ", photoSizes=" + this.photoSizes + ", nonUsernamePaths=" + (this.nonUsernamePaths == null ? null : Arrays.asList(this.nonUsernamePaths)) + ", maxMediaPerUpload=" + this.maxMediaPerUpload + '}';
    }
}
