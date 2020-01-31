package com.karam.searchswitchmap.model


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class GithubResponse (



    @SerializedName("items")
    @Expose
    var items:List<SearchResult>
)
