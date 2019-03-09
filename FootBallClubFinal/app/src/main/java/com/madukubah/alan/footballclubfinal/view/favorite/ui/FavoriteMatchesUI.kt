package com.madukubah.alan.footballclubfinal.view.favorite.ui

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteMatch.FavoriteMatchesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesUI : AnkoComponent<FavoriteMatchesFragment> {

    override fun createView(ui: AnkoContext<FavoriteMatchesFragment>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout {
                id = R.id.swipeRefreshFavoMatch
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView {
                        id = R.id.rvFavoMatch
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = matchParent) {
                        centerInParent()
                    }

                    progressBar {
                        id = R.id.pbFavoMatches
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }

//            progressBar {
//                id = R.id.pbFavoMatches
//            }.lparams {
//                centerHorizontally()
//            }
        }


    }
}