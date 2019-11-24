package com.kalher.movietime.datasource.webservice.url;

public enum TVWebServiceURL implements IWebServiceURL {
    POPULAR(),

    TOP_RATED(),

    ON_THE_AIR(),

    AIRING_TODAY(),
    ;

    @Override
    public String getEndPoint() {
        return null;
    }
}
