package twitter4j.internal.json;

import com.facebook.internal.ServerProtocol;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.SimilarPlaces;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class SimilarPlacesImpl extends ResponseListImpl<Place> implements SimilarPlaces {
    private static final long serialVersionUID = -7897806745732767803L;
    private final String token;

    @Override // twitter4j.internal.json.ResponseListImpl, twitter4j.TwitterResponse
    public /* bridge */ /* synthetic */ int getAccessLevel() {
        return super.getAccessLevel();
    }

    @Override // twitter4j.internal.json.ResponseListImpl, twitter4j.ResponseList, twitter4j.TwitterResponse
    public /* bridge */ /* synthetic */ RateLimitStatus getRateLimitStatus() {
        return super.getRateLimitStatus();
    }

    SimilarPlacesImpl(ResponseList<Place> responseList, HttpResponse httpResponse, String str) {
        super(responseList.size(), httpResponse);
        addAll(responseList);
        this.token = str;
    }

    @Override // twitter4j.SimilarPlaces
    public String getToken() {
        return this.token;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimilarPlaces createSimilarPlaces(HttpResponse httpResponse, Configuration configuration) {
        JSONObject jSONObject = null;
        try {
            jSONObject = httpResponse.asJSONObject();
            JSONObject jSONObject2 = jSONObject.getJSONObject("result");
            return new SimilarPlacesImpl(PlaceJSONImpl.createPlaceList(jSONObject2.getJSONArray("places"), httpResponse, configuration), httpResponse, jSONObject2.getString(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN));
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }
}
