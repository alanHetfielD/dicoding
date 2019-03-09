package com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.ResponseMatch
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

class PresenterMatch(
        private val view : MatchView,
        private val apiRepository : ApiRepository,
        private val gson : Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
)
    :AnkoLogger
{
    fun getMatchList( league : String, mode: Int? ){

        view.showLoading()
        when( mode ){
            0 ->
            {
                info("MatchFragment url = "+ TheSportsDBApi.getPastEvent(league) )
                async(context.main){
                    val data = bg {
                        gson.fromJson(apiRepository.doRequest( TheSportsDBApi.getPastEvent(league) ),
                                ResponseMatch::class.java
                        )
                    }
                    view.showEventList( data.await().Match )
                    view.hideLoading()
                }
            }
            1 ->
            {
                async(context.main){
                    val data = bg {
                        gson.fromJson(apiRepository.doRequest( TheSportsDBApi.getNextEvent(league) ),
                                ResponseMatch::class.java
                        )
                    }
                    view.showEventList( data.await().Match )
                    view.hideLoading()
                }
                info("MatchFragment url = "+ TheSportsDBApi.getPastEvent(league) )
            }
        }
    }

    fun getSearchEventList(match: String?) {
        info("MatchFragment url = "+ TheSportsDBApi.getSearchEventByName(match) )
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getSearchEventByName(match)),
                        ResponseMatch::class.java
                )
            }
            view.showEventList(data.await().searchEvent)
            view.hideLoading()
        }
    }
}