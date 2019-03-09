package com.madukubah.alan.footballclubfinal.view.team.activity

import com.madukubah.alan.footballclubfinal.model.Player

interface DetailPlayerView {
    fun hideLoading()
    fun showLoading()
    fun showPlayerDetail(player: List<Player>)
}