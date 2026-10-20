package twitter4j.internal.json;
// [removed legacy tracker import]
import java.io.Serializable;
import twitter4j.AccountTotals;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class AccountTotalsJSONImpl extends TwitterResponseImpl implements Serializable, AccountTotals {
    private static final long serialVersionUID = -2291419345865627123L;
    private final int favorites;
    private final int followers;
    private final int friends;
    private final int updates;

    private AccountTotalsJSONImpl(HttpResponse httpResponse, JSONObject jSONObject) {
        super(httpResponse);
        this.updates = z_T4JInternalParseUtil.getInt("updates", jSONObject);
        this.followers = z_T4JInternalParseUtil.getInt("followers", jSONObject);
        this.favorites = z_T4JInternalParseUtil.getInt("favorites", jSONObject);
        this.friends = z_T4JInternalParseUtil.getInt(NativeProtocol.AUDIENCE_FRIENDS, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountTotalsJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        this(httpResponse, httpResponse.asJSONObject());
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, httpResponse.asJSONObject());
        }
    }

    AccountTotalsJSONImpl(JSONObject jSONObject) {
        this((HttpResponse) null, jSONObject);
    }

    @Override // twitter4j.AccountTotals
    public int getUpdates() {
        return this.updates;
    }

    @Override // twitter4j.AccountTotals
    public int getFollowers() {
        return this.followers;
    }

    @Override // twitter4j.AccountTotals
    public int getFavorites() {
        return this.favorites;
    }

    @Override // twitter4j.AccountTotals
    public int getFriends() {
        return this.friends;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccountTotalsJSONImpl accountTotalsJSONImpl = (AccountTotalsJSONImpl) obj;
        return this.favorites == accountTotalsJSONImpl.favorites && this.followers == accountTotalsJSONImpl.followers && this.friends == accountTotalsJSONImpl.friends && this.updates == accountTotalsJSONImpl.updates;
    }

    public int hashCode() {
        return (((((this.updates * 31) + this.followers) * 31) + this.favorites) * 31) + this.friends;
    }

    public String toString() {
        return "AccountTotalsJSONImpl{updates=" + this.updates + ", followers=" + this.followers + ", favorites=" + this.favorites + ", friends=" + this.friends + '}';
    }
}
