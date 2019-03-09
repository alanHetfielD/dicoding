package com.madukubah.alan.footballclubfinal.view.match.activity

import com.madukubah.alan.footballclubfinal.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams : List<Team> ?, pos : Int)
}