package mh.indybook.indy;

import mh.indybook.indy.model.*;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import org.json.*;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Indy {

    private static Indy instance;
    private IndyCredentials credentials;
    private Session session;


    private Indy() {
        this.session = Requests.session();
    }

    public void login() {
        JSONObject json = new JSONObject()
                .put("LoginName", credentials.getUsername())
                .put("LoginPassword", credentials.getPassword())
                .put("camefrom", "index");
        IndyRoutes.LOGIN.newRequest().body(json).send(session);
    }

    public boolean loggedIn() {
        IndyResponse response = IndyRoutes.LOGGED_IN.newRequest().send(session);
        return response.asString().contains("Abmelden");
    }

    public Set<IndyEvent> getAllEventContexts() {
        JSONObject events = IndyRoutes.EVENTS.newRequest().send(session).asJson();
        return events.keySet()
                .stream()
                .map(events::getJSONObject)
                .map(jsonObject -> new IndyEvent(this, jsonObject))
                .collect(Collectors.toSet());
    }

    public Set<IndyEvent> getNextIndyEvents(int limit) {
        return getAllEventContexts()
                .stream()
                .filter(IndyEvent::isFuture)
                .filter(IndyEvent::isFutureExtended)
                .sorted(Comparator.comparing(IndyEvent::getDate))
                .limit(limit)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public IndyEventDetailed getIndyEventDetailed(IndyEvent indyEvent) {
        JSONObject indyEventDetailed = IndyRoutes.LOAD_ALL.newRequest().body(indyEvent.getEventRequestData()).sendLoadAllRequest(session).asJson();
        System.out.println(indyEventDetailed.toString());
        return new IndyEventDetailed(indyEvent, indyEventDetailed);
    }

    public static Indy getInstance() {
        if (instance == null) {
            instance = new Indy();
        }
        return instance;
    }

    public IndyCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(IndyCredentials credentials) {
        this.credentials = credentials;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
