package com.LRA;


import java.io.File;
import java.io.InputStream;

import twitter4j.AccountSettings;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.api.UsersResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TwitterHelper implements UsersResources{
	User twitterUser;
	private Context c;
	private Activity a;
	static String TWITTER_CONSUMER_KEY = "PutYourConsumerKeyHere"; // place your cosumer key here
    static String TWITTER_CONSUMER_SECRET = "PutYourConsumerSecretHere"; // place your consumer secret here

 // Preference Constants
    static String PREFERENCE_NAME = "twitter_oauth";
    static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

    static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

    // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

    // Login button
    Button btnLoginTwitter;
    // Update status button
    Button btnUpdateStatus;
    // Logout button
    Button btnLogoutTwitter;
    // EditText for update
    EditText txtUpdate;
    // lbl update
    TextView lblUpdate;
    TextView lblUserName;

    // Progress dialog
    ProgressDialog pDialog;

    // Twitter
    private static Twitter twitter;
    private static RequestToken requestToken;
    private AccessToken accessToken;

    // Shared Preferences
    private static SharedPreferences mSharedPreferences;

    
	public TwitterHelper(Activity a){
		this.c = a.getApplicationContext();
		this.a = a;
	}
	
	
	@Override
	public User createBlock(long arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createBlock(String arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyBlock(long arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyBlock(String arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountSettings getAccountSettings() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getBlocksIDs() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getBlocksIDs(long arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagableResponseList<User> getBlocksList() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagableResponseList<User> getBlocksList(long arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> getContributees(long arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> getContributees(String arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> getContributors(long arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> getContributors(String arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> lookupUsers(long[] arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<User> lookupUsers(String[] arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProfileBanner() throws TwitterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseList<User> searchUsers(String arg0, int arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showUser(long arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showUser(String arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountSettings updateAccountSettings(Integer arg0, Boolean arg1,
			String arg2, String arg3, String arg4, String arg5)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfile(String arg0, String arg1, String arg2, String arg3)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileBackgroundImage(File arg0, boolean arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileBackgroundImage(InputStream arg0, boolean arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfileBanner(File arg0) throws TwitterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProfileBanner(InputStream arg0) throws TwitterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateProfileColors(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileImage(File arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileImage(InputStream arg0) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User verifyCredentials() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Function to login twitter
     * */
    protected void loginToTwitter(final Context context) {
        // Check if already logged in
        if (!isTwitterLoggedInAlready()) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
            Configuration configuration = builder.build();

            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();


                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {

                            requestToken = twitter
                                    .getOAuthRequestToken(TWITTER_CALLBACK_URL);
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                                    .parse(requestToken.getAuthenticationURL())));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();         
        } else {
            // user already logged into twitter
            Toast.makeText(context,
                    "Already Logged into twitter", Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * Function to logout from twitter
     * It will just clear the application shared preferences
     * */
    private void logoutFromTwitter() {
        // Clear the shared preferences
        Editor e = mSharedPreferences.edit();
        e.remove(PREF_KEY_OAUTH_TOKEN);
        e.remove(PREF_KEY_OAUTH_SECRET);
        e.remove(PREF_KEY_TWITTER_LOGIN);
        e.commit();

        // After this take the appropriate action
        // I am showing the hiding/showing buttons again
        // You might not needed this code
        btnLogoutTwitter.setVisibility(View.GONE);
        btnUpdateStatus.setVisibility(View.GONE);
        txtUpdate.setVisibility(View.GONE);
        lblUpdate.setVisibility(View.GONE);
        lblUserName.setText("");
        lblUserName.setVisibility(View.GONE);

        btnLoginTwitter.setVisibility(View.VISIBLE);
    }

    /**
     * Check user already logged in your application using twitter Login flag is
     * fetched from Shared Preferences
     * */
    private boolean isTwitterLoggedInAlready() {
        // return twitter login status from Shared Preferences
        return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
    }

    
    /**
     * Function to update status
     * */
    class updateTwitterStatus extends AsyncTask<String, String, String> {
    	private Context context;
    	
    	public updateTwitterStatus(Context context){
    		this.context = context;
    	}
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Updating to twitter...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Places JSON
         * */
        protected String doInBackground(String... args) {
            Log.d("Tweet Text", "> " + args[0]);
            String status = args[0];
            try {
                ConfigurationBuilder builder = new ConfigurationBuilder();
                builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
                builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

                // Access Token 
                String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
                // Access Token Secret
                String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");

                AccessToken accessToken = new AccessToken(access_token, access_token_secret);
                Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);

                // Update status
                twitter4j.Status response = twitter.updateStatus(status);

                Log.d("Status", "> " + response.getText());
            } catch (TwitterException e) {
                // Error in updating status
                Log.d("Twitter Update Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog and show
         * the data in UI Always use runOnUiThread(new Runnable()) to update UI
         * from background thread, otherwise you will get error
         * **/
        protected void onPostExecute(String file_url, final Context context, Activity a) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            a.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,
                            "Status tweeted successfully", Toast.LENGTH_SHORT)
                            .show();
                    // Clearing EditText field
                    txtUpdate.setText("");
                }
            });
        }

    }
    
   
}
