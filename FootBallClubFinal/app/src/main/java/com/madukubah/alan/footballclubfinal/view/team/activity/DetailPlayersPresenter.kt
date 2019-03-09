package com.madukubah.alan.footballclubfinal.view.team.activity

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.PlayerResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayersPresenter(private val view: DetailPlayerView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerDetail(idPlayer: String?) {
        view.showLoading()

        async(context.main) {
            val detailPlayerTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getDetailPlayerTeam(idPlayer)),
                        PlayerResponse::class.java)
            }
            view.showPlayerDetail(detailPlayerTeam.await().players)
            view.hideLoading()
        }
    }
}