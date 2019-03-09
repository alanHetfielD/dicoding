package com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch

import com.madukubah.alan.footballclubfinal.model.ModelMatch

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<ModelMatch>?)
    fun errorMessage(message: String?)
}