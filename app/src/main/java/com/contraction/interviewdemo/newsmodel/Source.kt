package com.contraction.interviewdemo.newsmodel

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.newsmodel.Article
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class Source {
    @SerializedName("id")
    @Expose
    var id: Any? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}