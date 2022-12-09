package com.contraction.interviewdemo.modelclass

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.modelclass.Datum
import com.contraction.interviewdemo.modelclass.Pickupdatum
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class Pickupdatum {
    @SerializedName("pickup_type_id")
    @Expose
    var pickupTypeId: String? = null

    @SerializedName("pickup_type_date")
    @Expose
    var pickupTypeDate: String? = null

    @SerializedName("pickup_name")
    @Expose
    var pickupName: String? = null

    @SerializedName("pickup_icon_url")
    @Expose
    var pickupIconUrl: String? = null

    @SerializedName("pickup_noti_setting")
    @Expose
    var pickupNotiSetting: String? = null

    @SerializedName("pickup_email")
    @Expose
    var pickupEmail: String? = null
}