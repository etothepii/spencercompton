package uk.co.epii.conservatives.spencercompton;

/**
 * Created by james on 25/04/14.
 */
public class Result {

    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "Result[url:" + url +",title:" + title + "]";
    }
}
