package com.LRA;


import java.io.File;
import java.io.InputStream;

import twitter4j.AccountSettings;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.api.UsersResources;


public class Twitter implements UsersResources{
	User twitterUser;
	static String TWITTER_CONSUMER_KEY = "PutYourConsumerKeyHere"; // place your cosumer key here
    static String TWITTER_CONSUMER_SECRET = "PutYourConsumerSecretHere"; // place your consumer secret here

	public static void TwitterLogin(String id, String pw){
		
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

	

}
