package com.perso.red.meteo.models;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class LocationJson {

    public static final String  STATUS_OK = "OK";
    public static final String  STATUS_ZERO_RESULTS = "ZERO_RESULTS";
    public static final String  STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    public static final String  STATUS_REQUEST_DENIED = "REQUEST_DENIED";
    public static final String  STATUS_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String  STATUS_UNKNOWN_ERROR = "UNKNOWN_ERROR";

    private String          status;
    private List<Location>  results;

    public String getStatus() {
        return status;
    }

    public List<Location> getResults() {
        return results;
    }
}
