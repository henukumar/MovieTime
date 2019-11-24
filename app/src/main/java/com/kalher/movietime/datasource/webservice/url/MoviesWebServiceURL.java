package com.kalher.movietime.datasource.webservice.url;

public enum MoviesWebServiceURL implements IWebServiceURL{

    TOP_RATED(),

    UPCOMING(),

    NOW_PLAYING(),

    POPULAR(),

    ;

    @Override
    public String getEndPoint() {
        return null;
    }

}
