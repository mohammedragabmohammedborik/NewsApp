package com.example.mohammedragab.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * URL for News data using guardian api
     */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=research&format=json&tag=technology/technology&from-date=2017-01-01&show-fields=headline,lastModified,thumbnail&lang=en&order-by=relevance&api-key=test";
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int News_LOADER_ID = 1;

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find list view for items display
        ListView newsList = (ListView) findViewById(R.id.list);
        // find view for empty text
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        // create new adapter to pass item to list view
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // find Current Position
                News cuurentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(cuurentNews.getmUrl());
                Intent websiteNwes = new Intent(Intent.ACTION_VIEW, newsUri);
                // send intent to lunch web site.
                startActivity(websiteNwes);

            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).loaderManager.initLoader(NEWS_LOADER_ID, null, this);
            loaderManager.initLoader(News_LOADER_ID, null, this);
        } else {
            if(mAdapter!=null){
                mAdapter.clear();
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);}
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        // Create a new loader for the given URL
        NewsLoader newsLoader = new NewsLoader(this, GUARDIAN_REQUEST_URL);
        return newsLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsData) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter of previous news data
        mAdapter.clear();

        // data set. This will trigger the ListView to update.
        if (newsData != null && !newsData.isEmpty()) {
            mAdapter.addAll(newsData);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }
}
