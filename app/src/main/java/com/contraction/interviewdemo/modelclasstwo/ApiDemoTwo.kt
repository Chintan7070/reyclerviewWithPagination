package com.contraction.interviewdemo.modelclasstwo

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.newsmodel.Article
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class ApiDemoTwo {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("entries")
    @Expose
    var entries: List<Entry>? = null
}