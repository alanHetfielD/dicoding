package com.madukubah.alan.footballclubfinal.view.favorite.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteTeam.FavoriteTeamsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamsUI : AnkoComponent<FavoriteTeamsFragment> {

    override fun createView(ui: AnkoContext<FavoriteTeamsFragment>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout {
                id = R.id.swipeRefreshFavoTeams
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        id = R.id.rvFavoTeams
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = matchParent) {
                        centerInParent()
                    }

                    progressBar {
                        id = R.id.pbFavoTeams
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}