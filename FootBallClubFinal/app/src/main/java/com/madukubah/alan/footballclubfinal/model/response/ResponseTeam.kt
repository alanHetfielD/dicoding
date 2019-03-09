package com.madukubah.alan.footballclubfinal.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.alan.footballclubfinal.model.Team

data class ResponseTeam (
        @SerializedName("teams")
        val team: List<Team>
)