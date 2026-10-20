package com.facebook.widget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.android.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class UserSettingsFragment extends FacebookFragment {
    private static final String FIELDS = "fields";
    private static final String NAME = "name";
    private TextView connectedStateLabel;
    private LoginButton loginButton;
    private LoginButton.LoginButtonProperties loginButtonProperties = new LoginButton.LoginButtonProperties();
    private Session.StatusCallback sessionStatusCallback;
    private GraphUser user;
    private Session userInfoSession;
    private Drawable userProfilePic;
    private String userProfilePicID;
    private static final String ID = "id";
    private static final String PICTURE = "picture";
    private static final String REQUEST_FIELDS = TextUtils.join(",", new String[]{ID, "name", PICTURE});

    @Override // com.facebook.widget.FacebookFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // com.facebook.widget.FacebookFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.facebook.widget.FacebookFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.com_facebook_usersettingsfragment, viewGroup, false);
        this.loginButton = (LoginButton) inflate.findViewById(R.id.com_facebook_usersettingsfragment_login_button);
        this.loginButton.setProperties(this.loginButtonProperties);
        this.loginButton.setFragment(this);
        this.loginButton.setLoginLogoutEventName(AnalyticsEvents.EVENT_USER_SETTINGS_USAGE);
        Session session = getSession();
        if (session != null && !session.equals(Session.getActiveSession())) {
            this.loginButton.setSession(session);
        }
        this.connectedStateLabel = (TextView) inflate.findViewById(R.id.com_facebook_usersettingsfragment_profile_name);
        if (inflate.getBackground() == null) {
            inflate.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        } else {
            inflate.getBackground().setDither(true);
        }
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        fetchUserInfo();
        updateUI();
    }

    @Override // com.facebook.widget.FacebookFragment
    public void setSession(Session session) {
        super.setSession(session);
        if (this.loginButton != null) {
            this.loginButton.setSession(session);
        }
        fetchUserInfo();
        updateUI();
    }

    public void setDefaultAudience(SessionDefaultAudience sessionDefaultAudience) {
        this.loginButtonProperties.setDefaultAudience(sessionDefaultAudience);
    }

    public SessionDefaultAudience getDefaultAudience() {
        return this.loginButtonProperties.getDefaultAudience();
    }

    public void setReadPermissions(List<String> list) {
        this.loginButtonProperties.setReadPermissions(list, getSession());
    }

    public void setReadPermissions(String... strArr) {
        this.loginButtonProperties.setReadPermissions(Arrays.asList(strArr), getSession());
    }

    public void setPublishPermissions(List<String> list) {
        this.loginButtonProperties.setPublishPermissions(list, getSession());
    }

    public void setPublishPermissions(String... strArr) {
        this.loginButtonProperties.setPublishPermissions(Arrays.asList(strArr), getSession());
    }

    public void clearPermissions() {
        this.loginButtonProperties.clearPermissions();
    }

    public void setLoginBehavior(SessionLoginBehavior sessionLoginBehavior) {
        this.loginButtonProperties.setLoginBehavior(sessionLoginBehavior);
    }

    public SessionLoginBehavior getLoginBehavior() {
        return this.loginButtonProperties.getLoginBehavior();
    }

    public void setOnErrorListener(LoginButton.OnErrorListener onErrorListener) {
        this.loginButtonProperties.setOnErrorListener(onErrorListener);
    }

    public LoginButton.OnErrorListener getOnErrorListener() {
        return this.loginButtonProperties.getOnErrorListener();
    }

    public void setSessionStatusCallback(Session.StatusCallback statusCallback) {
        this.sessionStatusCallback = statusCallback;
    }

    public Session.StatusCallback getSessionStatusCallback() {
        return this.sessionStatusCallback;
    }

    @Override // com.facebook.widget.FacebookFragment
    protected void onSessionStateChange(SessionState sessionState, Exception exc) {
        fetchUserInfo();
        updateUI();
        if (this.sessionStatusCallback != null) {
            this.sessionStatusCallback.call(getSession(), sessionState, exc);
        }
    }

    List<String> getPermissions() {
        return this.loginButtonProperties.getPermissions();
    }

    private void fetchUserInfo() {
        final Session session = getSession();
        if (session != null && session.isOpened()) {
            if (session != this.userInfoSession) {
                Request newMeRequest = Request.newMeRequest(session, new Request.GraphUserCallback() { // from class: com.facebook.widget.UserSettingsFragment.1
                    @Override // com.facebook.Request.GraphUserCallback
                    public void onCompleted(GraphUser graphUser, Response response) {
                        if (session == UserSettingsFragment.this.getSession()) {
                            UserSettingsFragment.this.user = graphUser;
                            UserSettingsFragment.this.updateUI();
                        }
                        if (response.getError() != null) {
                            UserSettingsFragment.this.loginButton.handleError(response.getError().getException());
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString(FIELDS, REQUEST_FIELDS);
                newMeRequest.setParameters(bundle);
                Request.executeBatchAsync(newMeRequest);
                this.userInfoSession = session;
                return;
            }
            return;
        }
        this.user = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI() {
        if (isAdded()) {
            if (isSessionOpen()) {
                this.connectedStateLabel.setTextColor(getResources().getColor(R.color.com_facebook_usersettingsfragment_connected_text_color));
                this.connectedStateLabel.setShadowLayer(1.0f, 0.0f, -1.0f, getResources().getColor(R.color.com_facebook_usersettingsfragment_connected_shadow_color));
                if (this.user != null) {
                    ImageRequest imageRequest = getImageRequest();
                    if (imageRequest != null) {
                        URI imageUri = imageRequest.getImageUri();
                        if (!imageUri.equals(this.connectedStateLabel.getTag())) {
                            if (this.user.getId().equals(this.userProfilePicID)) {
                                this.connectedStateLabel.setCompoundDrawables(null, this.userProfilePic, null, null);
                                this.connectedStateLabel.setTag(imageUri);
                            } else {
                                ImageDownloader.downloadAsync(imageRequest);
                            }
                        }
                    }
                    this.connectedStateLabel.setText(this.user.getName());
                    return;
                }
                this.connectedStateLabel.setText(getResources().getString(R.string.com_facebook_usersettingsfragment_logged_in));
                Drawable drawable = getResources().getDrawable(R.drawable.com_facebook_profile_default_icon);
                drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height));
                this.connectedStateLabel.setCompoundDrawables(null, drawable, null, null);
                return;
            }
            int color = getResources().getColor(R.color.com_facebook_usersettingsfragment_not_connected_text_color);
            this.connectedStateLabel.setTextColor(color);
            this.connectedStateLabel.setShadowLayer(0.0f, 0.0f, 0.0f, color);
            this.connectedStateLabel.setText(getResources().getString(R.string.com_facebook_usersettingsfragment_not_logged_in));
            this.connectedStateLabel.setCompoundDrawables(null, null, null, null);
            this.connectedStateLabel.setTag(null);
        }
    }

    private ImageRequest getImageRequest() {
        try {
            return new ImageRequest.Builder(getActivity(), ImageRequest.getProfilePictureUrl(this.user.getId(), getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height))).setCallerTag(this).setCallback(new ImageRequest.Callback() { // from class: com.facebook.widget.UserSettingsFragment.2
                @Override // com.facebook.internal.ImageRequest.Callback
                public void onCompleted(ImageResponse imageResponse) {
                    UserSettingsFragment.this.processImageResponse(UserSettingsFragment.this.user.getId(), imageResponse);
                }
            }).build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processImageResponse(String str, ImageResponse imageResponse) {
        Bitmap bitmap;
        if (imageResponse != null && (bitmap = imageResponse.getBitmap()) != null) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            bitmapDrawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height));
            this.userProfilePic = bitmapDrawable;
            this.userProfilePicID = str;
            this.connectedStateLabel.setCompoundDrawables(null, bitmapDrawable, null, null);
            this.connectedStateLabel.setTag(imageResponse.getRequest().getImageUri());
        }
    }
}
