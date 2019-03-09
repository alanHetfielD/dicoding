package com.madukubah.alan.footballclubfinal.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.model.Player
import com.madukubah.alan.footballclubfinal.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivPlayer: ImageView = view.find(R.id.ivListPlayer)
    private val namePlayer: TextView = view.find(R.id.tvListNamePlayer)
    private val namePosition: TextView = view.find(R.id.tvPositionListPlayer)

    fun bindItem(players: Player, listener : (Player) -> Unit) {
        Glide.with(itemView).load(players.strCutout).into(ivPlayer)
        namePlayer.text = players.strPlayer
        namePosition.text = players.strPosition

        itemView.onClick {
            listener( players )
        }
    }
}