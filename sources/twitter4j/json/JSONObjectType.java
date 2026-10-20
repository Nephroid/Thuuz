package twitter4j.json;

import twitter4j.internal.logging.Logger;

/* loaded from: classes.dex */
public final class JSONObjectType {
    private static final Logger logger = Logger.getLogger(JSONObjectType.class);

    /* loaded from: classes.dex */
    public enum Type {
        SENDER,
        STATUS,
        DIRECT_MESSAGE,
        DELETE,
        LIMIT,
        STALL_WARNING,
        SCRUB_GEO,
        FRIENDS,
        FAVORITE,
        UNFAVORITE,
        FOLLOW,
        UNFOLLOW,
        USER_LIST_MEMBER_ADDED,
        USER_LIST_MEMBER_DELETED,
        USER_LIST_SUBSCRIBED,
        USER_LIST_UNSUBSCRIBED,
        USER_LIST_CREATED,
        USER_LIST_UPDATED,
        USER_LIST_DESTROYED,
        USER_UPDATE,
        BLOCK,
        UNBLOCK,
        DISCONNECTION,
        UNKNOWN
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:46:0x0130
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0114 -> B:31:0x0120). Please report as a decompilation issue!!! */
    public static twitter4j.json.JSONObjectType.Type determine(twitter4j.internal.org.json.JSONObject r3) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: twitter4j.json.JSONObjectType.determine(twitter4j.internal.org.json.JSONObject):twitter4j.json.JSONObjectType$Type");
    }
}
