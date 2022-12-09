package com.contraction.interviewdemo.apitools

import com.contraction.interviewdemo.modelclass.ApiDemoClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("remyndr/services2.0/index.php?")
    fun setdata(
        @Query("action") q: String,
        @Query("user_address") q1: String,
        @Query("vendor_id") q2: String,
        @Query("route_id") q3: String,
        @Query("udid") key: String
    ): Call<ApiDemoClass>
}