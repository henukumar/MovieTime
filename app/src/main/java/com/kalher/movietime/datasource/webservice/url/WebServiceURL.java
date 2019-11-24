package com.kalher.movietime.datasource.webservice.url;

public enum  WebServiceURL {

    /* Search */
    SEARCH("", ""),   // Text based search, takes into account all original, translated, alternative names and title

    DISCOVER("", ""),    // Search based on filters or definable values like ratings, certifications or release dates

    FIND("", ""),    // Search using IMDB id

    /* MOVIE */
    TOP_RATED_MOVIES("movie/top_rated", "T"),   // adding leading / removes character from base url

    UPCOMING_MOVIES("movie/upcoming", "U"),

    NOW_PLAYING_MOVIES("movie/now_playing", "N"),

    POPULAR_MOVIES("movie/popular", "P"),



    /* TV */
    TV("", ""),

    POPULAR_PEOPLE("", ""),

    ;

    private String endPoint;
    private String tag;

    WebServiceURL(String endPoint, String tag){
        this.endPoint = endPoint;
        this.tag = tag;
    }

    public String getEndPoint(){
        return endPoint;
    }

    public String getURL(){
        int apiVersion = 3;
        String serviceUrl = "https://api.themoviedb.org/" + apiVersion + "/";
        return serviceUrl + getEndPoint();
    }

}
