package com.example.mohammedragab.newsapp;

/**
 * Created by MohammedRagab on 6/5/2017.
 */
//  class news that can  provide to get title of news , date , section name.
public class News {
    private String mTitle;
    private String mSectionName;
    private String mUrl;
    private String mDate;

    // constructor  of news
    public News(String title, String sectionName, String date, String url) {
        mTitle = title;
        mSectionName = sectionName;
        mDate = date;
        mUrl = url;
    }

    public News(String title, String sectionName) {
        mTitle = title;
        mSectionName = sectionName;
    }

    // get methods.
    public String getmTitle() {
        return mTitle;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        return mDate;
    }
}
