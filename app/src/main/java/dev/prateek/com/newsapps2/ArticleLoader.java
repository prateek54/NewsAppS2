package dev.prateek.com.newsapps2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private static final String LOG_TAG = ArticleLoader.class.getSimpleName();
    private String url;

    public ArticleLoader(Context context, String new_url) {
        super(context);
        url = new_url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        if (url == null) {
            return null;
        }
        Log.i(LOG_TAG, url);
        //get data
        List<Article> articles = QueryUtils.fetchArticleData(url);
        return articles;
    }
}
