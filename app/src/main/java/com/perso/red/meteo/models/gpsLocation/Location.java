package com.perso.red.meteo.models.gpsLocation;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class Location {

    private String                  formatted_address;
    private List<String>            types;

    public String getFormatted_address() {
        return formatted_address;
    }

    public List<String> getTypes() {
        return types;
    }
}
