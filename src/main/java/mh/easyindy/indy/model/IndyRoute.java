package mh.easyindy.indy.model;

import mh.easyindy.indy.service.IndyRequest;
import net.dongliu.requests.Methods;

import java.util.Objects;

public class IndyRoute {

    private final String method;
    private final String url;

    public IndyRoute(String method, String url) {
        this.method = method;
        this.url = IndyRoutes.BASE_URL + url;
    }

    public static IndyRoute post(String url) {
        return new IndyRoute(Methods.POST, url);
    }

    public static IndyRoute get(String url) {
        return new IndyRoute(Methods.GET, url);
    }

    public IndyRequest newRequest() {
        return new IndyRequest(this);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndyRoute route = (IndyRoute) o;
        return Objects.equals(method, route.method) &&
                Objects.equals(url, route.url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(method, url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Route{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
