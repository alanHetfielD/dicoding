package com.madukubah.alan.footballclubfinal.view.team.fragment

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.ResponseTeam
import com.madukubah.alan.footballclubfinal.view.match.activity.DetailMatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(
        private val view : TeamsView,
        private val apiRepository : ApiRepository,
        private val gson : Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
)
    :
        AnkoLogger
{
    fun getTeamList( nameLeague: String ){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getListTeamByLeague(nameLeague)),
                        ResponseTeam::class.java
                )
            }
            view.showTeamList(data.await().team )
            view.hideLoading()
        }
    }

    fun getSearchTeam(team: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getSearchTeamByName(team)),
                        ResponseTeam::class.java
                )
            }
            view.showTeamList(data.await().team)
            view.hideLoading()
        }
    }
}