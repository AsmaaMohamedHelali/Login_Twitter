package com.example.asmaa.login_twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    private static final String TWITTER_KEY = "uz2v4WzyzG5LkC6fmmr8sCPDf";
    private static final String TWITTER_SECRET = "If5XDQwjMbHtzDDenajP16n2lhnQZo9WFMoUyiMMuGkIqfmsiU";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        textView= (TextView) findViewById(R.id.textView);
//        loginButton.setCallback(new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> result) {
//                // The TwitterSession is also available through:
//                // Twitter.getInstance().core.getSessionManager().getActiveSession()
//                TwitterSession session = result.data;
//                // TODO: Remove toast and use the TwitterSession's userID
//                // with your app's user model
//                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
//                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Login with Twitter failure", exception);
//            }
//        });
        loginButton.setCallback(new LoginHandler());


    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
    private class LoginHandler extends Callback<TwitterSession> {
        @Override
        public void success(Result<TwitterSession> twitterSessionResult) {
            String output = "Status: " +
                    "Your login was successful " +
                    twitterSessionResult.data.getUserName() +
                    "\nAuth Token Received: " +
                    twitterSessionResult.data.getAuthToken().token;

            textView.setText(output);

        }

        @Override
        public void failure(TwitterException e) {
            textView.setText("Status: Login Failed");

        }
    }

}
