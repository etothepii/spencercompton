package uk.co.epii.conservatives.spencercompton;

/**
 * Created by James Robinson on 25/04/14.
 */
public class GoogleResults {

    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public String toString() {
        return "ResponseData[" + responseData + "]";
    }

}
