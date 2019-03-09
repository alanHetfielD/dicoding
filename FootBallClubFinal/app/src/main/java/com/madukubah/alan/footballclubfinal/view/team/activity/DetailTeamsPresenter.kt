package com.madukubah.alan.footballclubfinal.view.team.activity

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.PlayerResponse
import com.madukubah.alan.footballclubfinal.model.response.ResponseTeam
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamsPresenter
(
        private val view: DetailTeamsView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
)
{
    fun getTeamDetail(idTeam: String?) {
        view.showLoading()
        async(context.main){
            val eventDetailTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getListDetailTeam(idTeam)),
                        ResponseTeam::class.java)
            }
            val listPlayerTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getListPlayerTeam(idTeam)),
                        PlayerResponse::class.java)
            }
            view.showEventList(eventDetailTeam.await().team, listPlayerTeam.await().player)
            view.hideLoading()
        }
    }
}