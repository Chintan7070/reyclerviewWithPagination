package com.contraction.interviewdemo.modelclass

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.modelclass.Datum
import com.contraction.interviewdemo.modelclass.Pickupdatum
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class ApiDemoClass {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    @SerializedName("pickupdata")
    @Expose
    var pickupdata: List<Pickupdatum>? = null

    @SerializedName("app_support_email")
    @Expose
    var appSupportEmail: String? = null

    @SerializedName("vendor_sponsor")
    @Expose
    var vendorSponsor: String? = null

    @SerializedName("vendor_banner_image")
    @Expose
    var vendorBannerImage: String? = null

    @SerializedName("vendor_banner_click")
    @Expose
    var vendorBannerClick: String? = null
}