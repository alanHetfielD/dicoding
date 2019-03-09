package com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.config.db
import com.madukubah.alan.footballclubfinal.config.invisible
import com.madukubah.alan.footballclubfinal.config.visible
import com.madukubah.alan.footballclubfinal.database.TableMatch
import com.madukubah.alan.footballclubfinal.model.ModelMatch
import com.madukubah.alan.footballclubfinal.view.favorite.ui.FavoriteMatchesUI
import com.madukubah.alan.footballclubfinal.view.match.activity.DetailMatchActivity
import com.madukubah.alan.footballclubfinal.view.match.fragment.AdapterListMatches
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.LongParser
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.select
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteMatchesFragment
    : Fragment(),
        ViewFavorite,
        AnkoLogger
{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<ModelMatch>?) {
        swipeRefreshLayout.isRefreshing = false
        if( data != null ){
            listMatch.addAll(data)
        }
        adapterMacth.notifyDataSetChanged()
    }

    private var listMatch: MutableList<ModelMatch> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapterMacth: AdapterListMatches
    private lateinit var presenterMatch: PresenterFavorite

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initId()
        adapterMacth = AdapterListMatches(listMatch){
            ctx.startActivity<DetailMatchActivity>(
                    DetailMatchActivity.MATCH to it
            )
        }
        presenterMatch = PresenterFavorite(
                this,
                ApiRepository(),
                Gson()
        )
        recyclerView.adapter = adapterMacth
        getFavorite()
        swipeRefreshLayout.onRefresh {
            getFavorite()
        }
    }

    private fun getFavorite(){
        listMatch.clear()
        adapterMacth.notifyDataSetChanged()
        val ids = getAllFavorite()
        info("ids size"+ ids.size )
        for(i in 0..ids.size-1){
            info("ids"+ ids.get(i) )
            presenterMatch.getMatchFavorite(ids.get(i))
        }
        if(ids.size < 1){
            swipeRefreshLayout.isRefreshing = false
            this.hideLoading()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return FavoriteMatchesUI().createView(AnkoContext.create(ctx, this))
    }
    companion object {
        fun favoriteMatchesInstance(): FavoriteMatchesFragment = FavoriteMatchesFragment()
    }

    private fun initId() {
        swipeRefreshLayout = find(R.id.swipeRefreshFavoMatch)
        recyclerView = find(R.id.rvFavoMatch)
        progressBar = find(R.id.pbFavoMatches)
    }

    private fun getAllFavorite(): List<Long>{
        lateinit var data : List<Long>
        context?.db?.use{
            data=select( TableMatch.TABLE_MATCHES )
                    .orderBy(TableMatch.MATCHES_ID, SqlOrderDirection.DESC)
                    .parseList(LongParser)
        }
//        info("list favorite = "+data)
        return data
    }

}
