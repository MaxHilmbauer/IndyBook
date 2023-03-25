package mh.indybook.indy.model;

import net.dongliu.requests.RawResponse;
import org.json.JSONObject;



public class IndyResponse {

    private RawResponse response;

    public IndyResponse(RawResponse response) {
        this.response = response;
    }

    public int getStatusCode() {
        return this.response.statusCode();
    }

    public String asString() {
        return this.response.readToText();
    }

    public JSONObject asJson() {
        return new JSONObject(asString());
    }




}
