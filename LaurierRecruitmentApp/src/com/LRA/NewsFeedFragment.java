package com.LRA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsFeedFragment extends ListFragment {
	private ArrayList<FeedMessage> stories;
	private String graphurl, graphtoken;
	private final String TAG = this.getClass().getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setHasOptionsMenu(false);
		this.graphtoken = getResources().getString(R.string.app_id) + "|"
				+ getResources().getString(R.string.app_secret);
		this.graphurl = getResources().getString(R.string.facebook_graph)
				+ getResources().getString(R.string.facebook_page)
				+ getResources().getString(R.string.facebook_feedsuff);
		stories = new ArrayList<FeedMessage>();
		setListAdapter(new FeedAdapter(this.getActivity()));
		new DownloadWebpage(this).execute(graphurl, graphtoken);
	}
	
	private class FeedAdapter extends ArrayAdapter<FeedMessage> {
        public FeedAdapter(Context c) {
            super(c, android.R.layout.simple_list_item_1, stories);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.social_feed_item, null);
            }

            // configure the view for this Contact
            FeedMessage f = getItem(position);
            
            TextView date =
                (TextView)convertView.findViewById(R.id.message_date);
            date.setText(f.getDate());
            TextView message =
                    (TextView)convertView.findViewById(R.id.message);
            message.setText(f.getMessage());
            ImageView icon =
                	(ImageView)convertView.findViewById(R.id.social_icon);

            return convertView;
        }
    }
	private class DownloadWebpage extends AsyncTask<String, Void, String> {
		private final String TAG = this.getClass().getName();
		private NewsFeedFragment frag;
		private JSONObject feed;

		public DownloadWebpage(NewsFeedFragment nff) {
			this.frag = nff;
			feed = null;
		}

		@Override
		protected String doInBackground(String... urls) {
			try {
				String s = downloadUrl(urls[0], urls[1]);
				if (s != "" && this.feed != null) return "";
				try {
					JSONArray j = this.feed.getJSONArray("data");
					for (int i = 0; i < j.length(); ++i) {
						JSONObject j2 = j.getJSONObject(i);
						String type = j2.getString("type");
						if (type.equalsIgnoreCase("status") || type.equalsIgnoreCase("photo")) {
							//"2014-04-07T19:03:36+0000" sample date before
							String date = j2.getString("created_time").replace("T", " @ ").replace("+0000", "");
							String message = j2.getString("message");
							FeedMessage f = new FeedMessage(date, message);
							stories.add(f);
							Log.d(TAG, f.toString());
						}
					}
				} catch (JSONException e) {
					Toast.makeText(frag.getActivity(), "Unable to fetch data.", Toast.LENGTH_SHORT).show();
				}
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
			return "";
		}

	@Override
	protected void onPostExecute(String result) {
		if (stories.size()>0)
			((FeedAdapter)frag.getListAdapter()).notifyDataSetChanged();
	}

		private String downloadUrl(String address, String param)
				throws IOException {
			InputStream is = null;
			try {
				URL url = new URL(address + param);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setReadTimeout(10000 /* milliseconds */);
				conn.setConnectTimeout(15000 /* milliseconds */);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.connect();
				int response = conn.getResponseCode();
				Log.d(TAG, "The response is: " + response);
				is = conn.getInputStream();
				this.feed = convertStreamToJSON(is);
				return "";
			} catch (Exception e) {
				return "Error fetching data :(";
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}

		private JSONObject convertStreamToJSON(InputStream is)
				throws JSONException {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new JSONObject(sb.toString());
		}
	}
}