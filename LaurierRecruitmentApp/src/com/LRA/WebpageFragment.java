package com.LRA;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class WebpageFragment extends Fragment {
	private final String TAG = this.getClass().getName();
	private WebView mWebView;
	private Tracker tracker;
	private String wlu = "http://wlu.ca/laurierinternational";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		this.tracker = EasyTracker.getInstance(this.getActivity());
		Log.d(TAG, "OnCreate");
		setHasOptionsMenu(true);
	}
	@Override
	public void onResume() {

		super.onResume();

		this.tracker.set(Fields.SCREEN_NAME, getClass().getSimpleName());
		this.tracker.send(MapBuilder.createAppView().build());
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, parent, savedInstanceState);
		Log.d(TAG, "OnCreateView");
		View v = inflater.inflate(R.layout.fragment_webpage, parent, false);

		final ProgressBar progressBar = (ProgressBar) v
				.findViewById(R.id.progressBar);
		progressBar.setMax(100); // WebChromeClient reports in range 0-100
		final TextView titleTextView = (TextView) v
				.findViewById(R.id.titleTextView);

		mWebView = (WebView) v.findViewById(R.id.webView);

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);

		mWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		});
		mWebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView webView, int progress) {
				if (progress == 100) {
					progressBar.setVisibility(View.INVISIBLE);
					tracker.send(MapBuilder.createEvent("ui_action", // Event
							// category
							// (required)
							"page_load", // Event action (required)
							webView.getUrl(), // Event label
							null) // Event value
							.build());
				} else {
					progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(progress);
				}
			}

			public void onReceivedTitle(WebView webView, String title) {
				titleTextView.setText(title);
			}
		});

		mWebView.loadUrl(wlu);

		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.web_nav_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.move_back:
			if(mWebView.canGoBack()) mWebView.goBack();
			return true;
		case R.id.move_forward:
			if(mWebView.canGoForward()) mWebView.goForward();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
