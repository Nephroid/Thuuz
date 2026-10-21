package twitter4j.api;

import java.io.File;
import java.io.InputStream;
import twitter4j.AccountSettings;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.User;

/* loaded from: classes.dex */
public interface UsersResources {
    User createBlock(long j);

    User createBlock(String str);

    User destroyBlock(long j);

    User destroyBlock(String str);

    AccountSettings getAccountSettings();

    IDs getBlocksIDs();

    IDs getBlocksIDs(long j);

    PagableResponseList<User> getBlocksList();

    PagableResponseList<User> getBlocksList(long j);

    ResponseList<User> getContributees(long j);

    ResponseList<User> getContributees(String str);

    ResponseList<User> getContributors(long j);

    ResponseList<User> getContributors(String str);

    ResponseList<User> lookupUsers(long[] jArr);

    ResponseList<User> lookupUsers(String[] strArr);

    void removeProfileBanner();

    ResponseList<User> searchUsers(String str, int i);

    User showUser(long j);

    User showUser(String str);

    AccountSettings updateAccountSettings(Integer num, Boolean bool, String str, String str2, String str3, String str4);

    User updateProfile(String str, String str2, String str3, String str4);

    User updateProfileBackgroundImage(File file, boolean z);

    User updateProfileBackgroundImage(InputStream inputStream, boolean z);

    void updateProfileBanner(File file);

    void updateProfileBanner(InputStream inputStream);

    User updateProfileColors(String str, String str2, String str3, String str4, String str5);

    User updateProfileImage(File file);

    User updateProfileImage(InputStream inputStream);

    User verifyCredentials();
}
