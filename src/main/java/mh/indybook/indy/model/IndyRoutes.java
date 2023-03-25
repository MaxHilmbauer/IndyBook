package mh.indybook.indy.model;

public class IndyRoutes {

    public static String BASE_URL = "https://indy.sz-ybbs.ac.at/";

    public static IndyRoute LOGIN = IndyRoute.post("pages/loginLogout/ldap_auth.php");

    public static IndyRoute LOGGED_IN = IndyRoute.get("pages/index.php");

    public static IndyRoute EVENTS = IndyRoute.post("pages/calendarStudent/scripts/getEvents.php");

    public static IndyRoute LOAD_ALL = IndyRoute.post("php/queries/get/loadAll.php");


}
