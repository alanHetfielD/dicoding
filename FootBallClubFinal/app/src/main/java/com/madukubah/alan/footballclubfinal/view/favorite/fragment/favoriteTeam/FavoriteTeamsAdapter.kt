package com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteTeam

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.madukubah.alan.footballclubfinal.database.TableTeam
import com.madukubah.alan.footballclubfinal.view.team.ui.ItemTeamsUI
import com.madukubah.alan.footballclubfinal.viewholder.TeamViewHolder
import org.jetbrains.anko.AnkoContext

class FavoriteTeamsAdapter(private val favoriteMatches: List<TableTeam>,
                           private val listener: (TableTeam) -> Unit):
        RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemTeamsUI().createView(AnkoContext.
                create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindFavorite(favoriteMatches[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatches.size
}