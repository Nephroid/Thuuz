package twitter4j.api;

import twitter4j.Category;
import twitter4j.ResponseList;
import twitter4j.User;

/* loaded from: classes.dex */
public interface SuggestedUsersResources {
    ResponseList<User> getMemberSuggestions(String str);

    ResponseList<Category> getSuggestedUserCategories();

    ResponseList<User> getUserSuggestions(String str);
}
