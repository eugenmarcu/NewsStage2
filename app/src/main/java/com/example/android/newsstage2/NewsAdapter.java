package com.example.android.newsstage2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Eugen on 02-Mar-18.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    Context context;

    public NewsAdapter(@NonNull Context context, List<News> newsList) {
        super(context, 0, newsList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View listItemView, @NonNull ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        News currentNews = getItem(position);
        TextView section = listItemView.findViewById(R.id.list_item_section);
        section.setText(currentNews.getCategory());
        TextView headline = listItemView.findViewById(R.id.list_item_headline);
        headline.setText(currentNews.getHeadline());
        TextView trailText = listItemView.findViewById(R.id.list_item_trail_text);
        trailText.setText(currentNews.getTrailText());
        TextView date = listItemView.findViewById(R.id.list_item_date);
        date.setText(formatDate(currentNews.getDate()));
        TextView author = listItemView.findViewById(R.id.list_item_author);
        author.setText(context.getString(R.string.by) + currentNews.getAuthor());

        return listItemView;
    }

    private String formatDate(String dateObject) {
        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateObject);
        } catch (ParseException e) {

        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL yyyy");

        return dateFormat.format(date1).toString();
    }
}
