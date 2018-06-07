package dev.prateek.com.newsapps2;

public class News {

    final private String mTitle;
    final private String mUrl;
    final private String mCategory;
    final private String mDate;
    final private String mAuthor;

    public News(String title, String url, String category, String date, String author) {
        this.mTitle = title;
        this.mUrl = url;
        this.mCategory = category;
        this.mDate = date;
        this.mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
