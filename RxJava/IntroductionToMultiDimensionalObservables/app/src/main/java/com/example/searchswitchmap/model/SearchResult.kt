package com.karam.searchswitchmap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SearchResult(


    @SerializedName("full_name")
    @Expose
    var full_name: String

)