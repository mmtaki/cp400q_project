package cp400q.chaumoha.a05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PostalFragment extends Fragment {
	private final String TAG = this.getClass().getName();
	private final String BASE_URL = "http://hopper.wlu.ca/~choang/iPhone/http/getLocationFromFile.php?zip=";
	private EditText mSearchText;
	private ImageButton mSearch;
	private TextView mLabel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_postal, parent, false);
		mLabel = (TextView)v.findViewById(R.id.instruction);
		mSearchText = (EditText) v.findViewById(R.id.postal_input);
		mSearch = (ImageButton) v.findViewById(R.id.search_button);
		mSearch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String s = mSearchText.getText().toString().toUpperCase();
				//mSearchText.setText("");

				ConnectivityManager connMgr = (ConnectivityManager) getActivity()
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					new DownloadWebpage(getActivity()).execute(BASE_URL, s);
				} else {
					Toast.makeText(getActivity(), R.string.no_network,
							Toast.LENGTH_LONG).show();
				}
			}
		});
		return v;
	}
}

class DownloadWebpage extends AsyncTask<String, Void, String> {
	private final String TAG = this.getClass().getName();
	private Context context;
	public DownloadWebpage (Context c) {
		this.context = c;
	}
	@Override
	protected String doInBackground(String... urls) {
		try {
			return downloadUrl(urls[0], urls[1]);
		} catch (IOException e) {
			return "Unable to retrieve web page. URL may be invalid.";
		}
	}

	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, result,
				Toast.LENGTH_LONG).show();
	}

	private String downloadUrl(String myurl, String zip) throws IOException {
		InputStream is = null;
		try {
			URL url = new URL(myurl+zip);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();
			int response = conn.getResponseCode();
			Log.d(TAG, "The response is: " + response);
			is = conn.getInputStream();
			return zip + ": " + convertStreamToString(is);
		} catch (Exception e) {
			return "Error fetching data :(";
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}