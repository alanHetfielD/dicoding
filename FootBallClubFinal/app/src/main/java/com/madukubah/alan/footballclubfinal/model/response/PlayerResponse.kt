package com.madukubah.alan.footballclubfinal.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.alan.footballclubfinal.model.Player

data class PlayerResponse (
        val player: List<Player>,
        @SerializedName("players")
        val players: List<Player>
)