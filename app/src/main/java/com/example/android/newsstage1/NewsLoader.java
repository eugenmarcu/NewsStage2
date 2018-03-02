package com.example.android.newsstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Eugen on 02-Mar-18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        List<News> news = QueryUtils.extractNews(mUrl);

        return news;
    }
}
