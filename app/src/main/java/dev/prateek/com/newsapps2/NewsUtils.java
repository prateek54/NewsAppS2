package dev.prateek.com.newsapps2;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class NewsUtils {
    private static final String LOG_TAG = NewsUtils.class.getSimpleName();

    public NewsUtils() {
    }

    public static List<News> getNewsLists(String url) throws IOException {

        URL newsURL = returnUrl(url);

        String jsonGuardianContent = makeHttpRequest(newsURL);

        List<News> newsList = extractFeatureFromJson(jsonGuardianContent);

        return newsList;
    }


    private static List<News> extractFeatureFromJson(String jsonContent) {
        if (TextUtils.isEmpty(jsonContent)) {
            return null;
        }

        List<News> news = new ArrayList<>();

        try {
            JSONObject baseJsonNewsResponse = new JSONObject(jsonContent);
            JSONObject responseJsonNews = baseJsonNewsResponse.getJSONObject("response");
            JSONArray newsArray = responseJsonNews.getJSONArray("results");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentItem = newsArray.getJSONObject(i);

                String itemCategory = currentItem.getString("sectionName");
                String itemTitle = currentItem.getString("webTitle");
                String itemUrl = currentItem.getString("webUrl");

                String itemDate = "N/A";
                if (currentItem.has("webPublicationDate")) {
                    itemDate = currentItem.getString("webPublicationDate");
                }

                JSONArray currentNewsAuthorArray = currentItem.getJSONArray("tags");

                String itemAuthor = "author: N/A";
                int tagsLenght = currentNewsAuthorArray.length();

                if (tagsLenght == 1) {
                    JSONObject currentNewsAuthor = currentNewsAuthorArray.getJSONObject(0);
                    String authorName = currentNewsAuthor.getString("webTitle");
                    itemAuthor = "author: " + authorName;
                }


                News itemNews = new News(itemTitle, itemUrl, itemCategory, itemDate, itemAuthor);
                news.add(itemNews);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot parse JSON content.");
        }

        return news;
    }

    private static URL returnUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "URL building problem.", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {

            Log.e(LOG_TAG, "Connection was not established. Problem retrieving JSON News results.", e);
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}


