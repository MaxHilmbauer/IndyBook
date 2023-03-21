package mh.easyindy.indy.model;

import mh.easyindy.indy.Indy;
import org.json.JSONObject;

import java.util.*;

public class IndyEvent {

    private final Indy indy;

    private final JSONObject jsonObject;

    private final String classes;

    private final boolean future;

    private final String date;

    private final String day;

    private final int hourCount;

    private final int[] hours;

    public IndyEvent(Indy indy, JSONObject jsonObject) {
        this.indy = indy;
        this.jsonObject = jsonObject;
        this.classes = jsonObject.getString("class");

        this.future = jsonObject.optString("pastOrFuturePopUp", "past").equals("future");
        this.date = jsonObject.optString("number", "");
        this.day = jsonObject.optString("day", "");

        this.hourCount = 2;
        this.hours = new int[]{3, 4};
    }

    public IndyEvent(IndyEvent event) {
        this.indy = event.getIndy();
        this.jsonObject = event.getJsonObject();
        this.classes = event.getClasses();
        this.future = event.isFuture();
        this.date = event.getDate();
        this.day = event.getDay();
        this.hourCount = event.getHourCount();
        this.hours = event.getHours();
    }

    public IndyEventDetailed getDetails() {
        Indy indy = Indy.getInstance();
        return indy.getIndyEventDetailed(this);
    }

    public List<? extends Map.Entry<String, ?>> getEventRequestData() {
        List<AbstractMap.SimpleEntry<String, ?>> entries = new ArrayList<>();
        entries.add(new AbstractMap.SimpleEntry<>("day", day));
        entries.add(new AbstractMap.SimpleEntry<>("date", date));
        entries.add(new AbstractMap.SimpleEntry<>("totalHours", hourCount));
        for (int hour : hours) {
            entries.add(new AbstractMap.SimpleEntry<>("specificHours[]", hour));
        }

        return entries;
    }

    public Indy getIndy() {
        return indy;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getClasses() {
        return classes;
    }

    public boolean isFuture() {
        return future;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public int getHourCount() {
        return hourCount;
    }

    public int[] getHours() {
        return hours;
    }

    public boolean isHoliday() {
        return classes.equals("CalendarHoliday");
    }



}
