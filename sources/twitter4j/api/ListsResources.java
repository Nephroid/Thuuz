package twitter4j.api;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserList;

/* loaded from: classes.dex */
public interface ListsResources {
    UserList addUserListMember(int i, long j);

    UserList addUserListMember(long j, String str, long j2);

    UserList addUserListMembers(int i, long[] jArr);

    UserList addUserListMembers(int i, String[] strArr);

    UserList addUserListMembers(long j, String str, long[] jArr);

    UserList addUserListMembers(long j, String str, String[] strArr);

    UserList createUserList(String str, boolean z, String str2);

    UserList createUserListMember(int i, long j);

    UserList createUserListMember(long j, String str, long j2);

    UserList createUserListMember(String str, String str2, long j);

    UserList createUserListMembers(int i, long[] jArr);

    UserList createUserListMembers(int i, String[] strArr);

    UserList createUserListMembers(long j, String str, long[] jArr);

    UserList createUserListMembers(long j, String str, String[] strArr);

    UserList createUserListMembers(String str, String str2, long[] jArr);

    UserList createUserListMembers(String str, String str2, String[] strArr);

    UserList createUserListSubscription(int i);

    UserList createUserListSubscription(long j, String str);

    UserList createUserListSubscription(String str, String str2);

    UserList deleteUserListMember(int i, long j);

    UserList deleteUserListMember(long j, String str, long j2);

    UserList destroyUserList(int i);

    UserList destroyUserList(long j, String str);

    UserList destroyUserList(String str, String str2);

    UserList destroyUserListMember(int i, long j);

    UserList destroyUserListMember(long j, String str, long j2);

    UserList destroyUserListMember(String str, String str2, long j);

    UserList destroyUserListSubscription(int i);

    UserList destroyUserListSubscription(long j, String str);

    UserList destroyUserListSubscription(String str, String str2);

    PagableResponseList<User> getUserListMembers(int i, long j);

    PagableResponseList<User> getUserListMembers(long j, String str, long j2);

    PagableResponseList<User> getUserListMembers(String str, String str2, long j);

    PagableResponseList<UserList> getUserListMemberships(long j);

    PagableResponseList<UserList> getUserListMemberships(long j, long j2);

    PagableResponseList<UserList> getUserListMemberships(long j, long j2, boolean z);

    PagableResponseList<UserList> getUserListMemberships(String str, long j);

    PagableResponseList<UserList> getUserListMemberships(String str, long j, boolean z);

    ResponseList<Status> getUserListStatuses(int i, Paging paging);

    ResponseList<Status> getUserListStatuses(long j, String str, Paging paging);

    ResponseList<Status> getUserListStatuses(String str, String str2, Paging paging);

    PagableResponseList<User> getUserListSubscribers(int i, long j);

    PagableResponseList<User> getUserListSubscribers(long j, String str, long j2);

    PagableResponseList<User> getUserListSubscribers(String str, String str2, long j);

    PagableResponseList<UserList> getUserListSubscriptions(String str, long j);

    ResponseList<UserList> getUserLists(long j);

    ResponseList<UserList> getUserLists(String str);

    UserList showUserList(int i);

    UserList showUserList(long j, String str);

    UserList showUserList(String str, String str2);

    User showUserListMembership(int i, long j);

    User showUserListMembership(long j, String str, long j2);

    User showUserListMembership(String str, String str2, long j);

    User showUserListSubscription(int i, long j);

    User showUserListSubscription(long j, String str, long j2);

    User showUserListSubscription(String str, String str2, long j);

    UserList updateUserList(int i, String str, boolean z, String str2);

    UserList updateUserList(long j, String str, String str2, boolean z, String str3);

    UserList updateUserList(String str, String str2, String str3, boolean z, String str4);
}
