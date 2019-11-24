package com.kalher.movietime.datasource.webservice;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kalher.movietime.datasource.webservice.interceptor.TMDBParamInterceptor;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {

    private static TMDBWebservice mRetrofitService = null;

    public static TMDBWebservice getTMDBClient(){

        if(mRetrofitService == null){
            final OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            builder.addInterceptor(new TMDBParamInterceptor());
            builder.addNetworkInterceptor(new StethoInterceptor());

            final OkHttpClient client = builder.build();

            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PascalCaseStrategy());

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create(mapper))
                    .baseUrl(TMDBWebservice.BASE_VERSION_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            mRetrofitService = retrofit.create(TMDBWebservice.class);
        }
        return mRetrofitService;
    }

}
