package com.contraction.interviewdemo.newsmodel

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.contraction.interviewdemo.newsmodel.Article
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class NewsModelClass {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
}