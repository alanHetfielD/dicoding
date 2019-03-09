package com.madukubah.alan.footballclubfinal.view.team.activity

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.madukubah.alan.footballclubfinal.model.Player
import com.madukubah.alan.footballclubfinal.model.Team
import com.madukubah.alan.footballclubfinal.view.team.ui.ItemListPlayerUI
import com.madukubah.alan.footballclubfinal.viewholder.ListPlayerViewHolder
import org.jetbrains.anko.AnkoContext

class DetailTeamsAdapter
(
        private val players: List<Player>,
        private val listener : (Player) -> Unit
) :
        RecyclerView.Adapter<ListPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlayerViewHolder {
        return ListPlayerViewHolder(ItemListPlayerUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ListPlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener )
    }
}