package com.perso.red.meteo.models;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class AddressComponent {

    private String          long_name;
    private String          short_name;
    private List<String>    types;

    public String getLong_name() {
        return long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public List<String> getTypes() {
        return types;
    }
}
