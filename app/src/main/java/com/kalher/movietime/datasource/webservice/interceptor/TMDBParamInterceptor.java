package com.kalher.movietime.datasource.webservice.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TMDBParamInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
        HttpUrl mUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key","insert api key here")
                .build();
        Request.Builder requestBuilder  = originalRequest.newBuilder()
                .url(mUrl);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
