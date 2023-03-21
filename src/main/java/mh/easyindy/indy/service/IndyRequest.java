package mh.easyindy.indy.service;

import mh.easyindy.indy.model.IndyResponse;
import mh.easyindy.indy.model.IndyRoute;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Session;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndyRequest {

    private final IndyRoute ROUTE;
    private JSONObject json;
    private List<? extends Map.Entry<String, ?>> mapEntryBody;


    public IndyRequest(IndyRoute ROUTE) {
        this.ROUTE = ROUTE;
        this.json =new JSONObject();
        mapEntryBody = new ArrayList<>();
    }

    public IndyRequest body(JSONObject json) {
        this.json = json;
        return this;
    }

    public IndyRequest body(List<? extends Map.Entry<String, ?>> mapEntries) {
        this.mapEntryBody = mapEntries;
        return this;
    }

    public IndyResponse send(Session session) {
        RawResponse response = session.newRequest(ROUTE.getMethod(), ROUTE.getUrl())
                .body(json == null ? new HashMap<>() : json.toMap())
                .send();
        return new IndyResponse(response);
    }

    public IndyResponse sendLoadAllRequest(Session session) {
        RawResponse response = session.newRequest(ROUTE.getMethod(), ROUTE.getUrl())
                .body(mapEntryBody)
                .send();

        return new IndyResponse(response);
    }
}
