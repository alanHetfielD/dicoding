package com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteMatch

import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.api.TheSportsDBApi
import com.madukubah.alan.footballclubfinal.config.CoroutineContextProvider
import com.madukubah.alan.footballclubfinal.model.response.ResponseMatch
import com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch.MatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class PresenterFavorite
(
        private val view : ViewFavorite,
        private val apiRepository : ApiRepository,
        private val gson : Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
)
    : AnkoLogger
{
    fun getMatchFavorite(id : Long){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest( TheSportsDBApi.getDetailEvent(""+id) ),
                        ResponseMatch::class.java
                )
            }
            view.showMatchList( data.await().Match )
            view.hideLoading()
        }
    }
}