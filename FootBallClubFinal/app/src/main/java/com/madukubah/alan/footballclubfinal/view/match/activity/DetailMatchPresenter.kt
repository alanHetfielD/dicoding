package com.madukubah.alan.footballclubfinal.view.match.activity

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.ResponseMatch
import com.madukubah.alan.footballclubfinal.model.response.ResponseTeam
import com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch.MatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

class DetailMatchPresenter
(
    private val view : DetailMatchView,
    private val apiRepository : ApiRepository,
    private val gson : Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
)
    :
    AnkoLogger
{
    fun getTeam( idTeam : String, pos : Int ){
        info("url = "+ TheSportsDBApi.getNextEvent(idTeam) )
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest( TheSportsDBApi.getTeam(idTeam) ),
                        ResponseTeam::class.java
                )
            }
            view.showTeamList( data.await().team , pos )
            view.hideLoading()
        }
    }
}