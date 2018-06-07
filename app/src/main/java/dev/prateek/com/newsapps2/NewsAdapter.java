package dev.prateek.com.newsapps2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        News currentItem = getItem(position);
        TextView title = listItemView.findViewById(R.id.title);

        title.setText(currentItem.getTitle());

        TextView categoryTextView = listItemView.findViewById(R.id.category);
        categoryTextView.setText(currentItem.getCategory());

        TextView authorTextView = listItemView.findViewById(R.id.author);
        authorTextView.setText(currentItem.getAuthor());

        TextView dateTextView = listItemView.findViewById(R.id.date);

        try {

            SimpleDateFormat dateFormatJSON = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat dateFormatToView = new SimpleDateFormat("EE dd MMM yyyy");

            String sDate = dateFormatToView.format(dateFormatJSON.parse(currentItem.getDate()));
            dateTextView.setText(sDate);

        } catch (ParseException e) {
            Log.e(LOG_TAG, "Cannot parse date from json.", e);
        }

        return listItemView;
    }
}
