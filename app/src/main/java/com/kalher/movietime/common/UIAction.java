package com.kalher.movietime.common;

import com.kalher.movietime.datasource.webservice.url.WebServiceURL;

public enum UIAction {

    GET_NOW_PLAYING_MOVIES(WebServiceURL.NOW_PLAYING_MOVIES),

    GET_POPULAR_MOVIES(WebServiceURL.POPULAR_MOVIES),

    GET_TOP_RATED_MOVIES(WebServiceURL.TOP_RATED_MOVIES),

    GET_UPCOMING_MOVIES(WebServiceURL.UPCOMING_MOVIES),


    ;

    private WebServiceURL actionUrl;

    UIAction(WebServiceURL actionUrl){
        this.actionUrl = actionUrl;
    }

    public WebServiceURL getActionUrl() {
        return actionUrl;
    }

}
