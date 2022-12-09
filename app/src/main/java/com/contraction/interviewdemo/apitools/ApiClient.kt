package com.contraction.interviewdemo.apitools

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    //https://newsapi.org/v2/everything?q=tesla&from=2022-09-17&sortBy=publishedAt&apiKey=c690104f105e4be0b0c73f2b6ac2ff9d
    /*public static String BaseUrl = "https://newsapi.org/";*/
    companion object {
        var BaseUrl = "http://www.remyndr.org/ "

        fun getRetrofit(): Retrofit {
            var retrofit = Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit as Retrofit
        }
    }
}