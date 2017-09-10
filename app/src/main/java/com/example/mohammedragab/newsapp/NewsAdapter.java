package com.example.mohammedragab.newsapp;

import android.content.Context;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by MohammedRagab on 6/6/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context, 0, news);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // get object in this position
        News currentNews = getItem(position);
        // find text view of title of news and set it
        TextView titleNews = (TextView) listItemView.findViewById(R.id.headline);
        titleNews.setText(currentNews.getmTitle());
        // find text view of title of news and set it
        TextView sectionName = (TextView) listItemView.findViewById(R.id.section_name);
        sectionName.setText(currentNews.getmSectionName());
        // find text view of title of news and set it
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        String fdate=getDateFromatedString(currentNews.getmDate());
        date.setText(fdate);
        return listItemView;

    }
    public String getDateFromatedString(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        if (date == null) {
            return "";
        }

        SimpleDateFormat convetDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return convetDateFormat.format(date);
    }
}
