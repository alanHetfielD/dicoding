package com.madukubah.alan.footballclubfinal.view.team.fragment

import com.madukubah.alan.footballclubfinal.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams : List<Team> ?)
}