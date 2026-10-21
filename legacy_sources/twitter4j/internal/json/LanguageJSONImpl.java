package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.api.HelpResources;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class LanguageJSONImpl implements HelpResources.Language {
    private String code;
    private String name;
    private String status;

    LanguageJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        try {
            this.name = jSONObject.getString(Utilities.DB_KEY_IMAGE_NAME);
            this.code = jSONObject.getString("code");
            this.status = jSONObject.getString("status");
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getCode() {
        return this.code;
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getStatus() {
        return this.status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<HelpResources.Language> createLanguageList(HttpResponse httpResponse, Configuration configuration) {
        return createLanguageList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<HelpResources.Language> createLanguageList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LanguageJSONImpl languageJSONImpl = new LanguageJSONImpl(jSONObject);
                responseListImpl.add(languageJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(languageJSONImpl, jSONObject);
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
}
