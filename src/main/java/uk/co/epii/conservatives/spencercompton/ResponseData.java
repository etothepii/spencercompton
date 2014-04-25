package uk.co.epii.conservatives.spencercompton;

import java.util.List;

/**
 * Created by james on 25/04/14.
 */
public class ResponseData {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String toString() {
        return "Results[" + results + "]";
    }
}
