package dev.prateek.com.newsapps2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_TAG = NewsLoader.class.getSimpleName();

    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        List<News> newsList = new ArrayList<>();

        try {
            newsList = NewsUtils.getNewsLists(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Cannot get News data.");
        }

        return newsList;
    }
}
