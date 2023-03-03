package com.samraddhbestwin.microfinance.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public class RestHandler {
    public static final String BASE_URL_DOMAIN = "http://my.samraddhbestwin.com/";//Live
//    public static final String BASE_URL_DOMAIN = "http://uatsamraddh.host4india.in/";// UAT Server
    public static String API_URL = BASE_URL_DOMAIN+"api/";

    private static ApiService API_SERVICE;

    public static ApiService getApiService()  {
        if (API_SERVICE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.connectTimeout(10000, TimeUnit.SECONDS);
            httpClient.readTimeout(10000, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
                Retrofit adapter = new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build();
                API_SERVICE = adapter.create(ApiService.class);
        }
        return API_SERVICE;
    }

}
