package com.contraction.interviewdemo.modelclass

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.modelclass.Datum
import com.contraction.interviewdemo.modelclass.Pickupdatum
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class Datum {
    @SerializedName("calendar_id")
    @Expose
    var calendarId: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("pickup_type_id")
    @Expose
    var pickupTypeId: String? = null

    @SerializedName("text_color")
    @Expose
    var textColor: String? = null

    @SerializedName("service_information")
    @Expose
    var serviceInformation: String? = null

    @SerializedName("route_id")
    @Expose
    var routeId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}