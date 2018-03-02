package com.example.android.newsstage1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String REQUEST_URL = "http://content.guardianapis.com/search?show-fields=trailText&api-key=test&&section=sport";
    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;
    private NewsAdapter mAdapter;
    private List<News> mNewsList;
    private TextView mEmptyStateTextView;
    private LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        mNewsList = new ArrayList<>();
        mAdapter = new NewsAdapter(this, mNewsList);

        //Check internet connection
        if (!isInternetConnection()) {
            mEmptyStateTextView.setText(R.string.no_internet);
            // Hide progress bar
            ProgressBar progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            return;
        }

        // Get a reference to the LoaderManager, in order to interact with loaders.
        mLoaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        mLoaderManager.initLoader(NEWS_LOADER_ID, null, this);

        ListView newsListView = findViewById(R.id.list);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                News news = mNewsList.get(position);
                intent.setData(Uri.parse(news.getUrl()));
                startActivity(intent);
            }
        });
    }

    private boolean isInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void updateUi() {
        ListView newsListView = findViewById(R.id.list);
        newsListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        mAdapter.addAll(newsList);
        if (newsList.size() == 0) {
            mEmptyStateTextView.setText(R.string.no_news_found);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        } else {
            mEmptyStateTextView.setVisibility(View.GONE);
            mNewsList = newsList;
            mAdapter.notifyDataSetChanged();
            updateUi();
        }


        // Hide progress bar
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsList.clear();
    }
}
