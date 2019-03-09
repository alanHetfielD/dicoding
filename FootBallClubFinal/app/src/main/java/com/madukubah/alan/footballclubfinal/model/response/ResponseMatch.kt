package com.madukubah.alan.footballclubfinal.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.alan.footballclubfinal.model.ModelMatch

data class ResponseMatch(
        @field:SerializedName("events")
        val Match : List<ModelMatch>,
        @SerializedName("event")
        val searchEvent: List<ModelMatch>? = null
)