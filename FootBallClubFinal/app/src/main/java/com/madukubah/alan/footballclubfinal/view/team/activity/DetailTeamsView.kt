package com.madukubah.alan.footballclubfinal.view.team.activity

import com.madukubah.alan.footballclubfinal.model.Player
import com.madukubah.alan.footballclubfinal.model.Team

interface DetailTeamsView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Team>, player: List<Player>)
}