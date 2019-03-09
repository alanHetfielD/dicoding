package com.madukubah.alan.footballclubfinal.view.team.fragment

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.madukubah.alan.footballclubfinal.model.Team
import com.madukubah.alan.footballclubfinal.view.team.ui.ItemTeamsUI
import com.madukubah.alan.footballclubfinal.viewholder.TeamViewHolder
import org.jetbrains.anko.AnkoContext

class TeamsAdapter
(
        private val listTeam : List<Team>,
        private val listener : (Team) -> Unit
)
    : RecyclerView.Adapter<TeamViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder( ItemTeamsUI().createView( AnkoContext.create( parent.context, parent ) ) )
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(listTeam[ position ], listener )
    }
}