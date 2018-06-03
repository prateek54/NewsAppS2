package dev.prateek.com.newsapps2;


public class Article {

    private String section;
    private String title;
    private String author;
    private String url;
    private String date;
    //private String image ; pending

    /*
    Public constructor
    @param section that indicates the section the article belongs to
    @param title with the name of the article
    @param url with the web url to open the page where the article was published when item is clicked
    @param author to display if applicable
    @param date to display the date when the article was published
     */
    public Article(String section, String title, String author, String date, String url) {
        this.section = section;
        this.title = title;
        this.author = author;
        this.url = url;
        this.date = date;

    }


    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }
}
