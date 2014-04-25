package uk.co.epii.conservatives.spencercompton;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by james on 25/04/14.
 */
public class Google {

    public static final String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    public static final String charset = "UTF-8";

    public List<Result> search(String site, String search) {
        return search(String.format("site:%s %s", site, search));
    }

    public List<Result> search(String search) {
        URL url = createURL(search);
        Reader reader = openStream(url);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
        return results.getResponseData().getResults();
    }

    public Reader openStream(URL url) {
        try {
            return new InputStreamReader(url.openStream(), charset);
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public URL createURL(String search) {
        try {
            return new URL(google + URLEncoder.encode(search, charset));
        }
        catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
        catch (MalformedURLException mue) {
            throw new RuntimeException(mue);
        }
    }

}
