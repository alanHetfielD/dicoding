package com.madukubah.alan.footballclubfinal.model

import com.google.gson.annotations.SerializedName

data class Team (
        @SerializedName("idTeam") var teamId: String? = null,
        @SerializedName("strTeam") var teamName: String? = null,
        @SerializedName("strTeamBadge") var teamBadge: String? = null,
        @SerializedName("strStadium") var stadiumName: String? = null,
        @SerializedName("intFormedYear") var formedYear: String? = null,
        @SerializedName("strDescriptionEN") var descriptionTeam: String? = null
)