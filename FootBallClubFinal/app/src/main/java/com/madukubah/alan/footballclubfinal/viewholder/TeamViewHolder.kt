package com.madukubah.alan.footballclubfinal.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.database.TableTeam
import com.madukubah.alan.footballclubfinal.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class TeamViewHolder( view : View) :
        RecyclerView.ViewHolder(  view)
{
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(teams: Team, listener : (Team) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        Glide.with(itemView).load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName

//        val ctx = itemView.context
        itemView.onClick {
            listener( teams )
        }
//        itemView.setOnClickListener {
//            ctx.startActivity<DetailTeamsActivity>(
//                    ctx.getString(item_teamdetail_id) to teams.teamId)
//        }
    }

    fun bindFavorite(favorite: TableTeam, listener: (TableTeam) -> Unit) {
//        Picasso.get().load(favorite.badgeTeam).into(teamBadge)
        Glide.with(itemView).load(favorite.badgeTeam).into(teamBadge)
        teamName.text = favorite.teamName

        itemView.onClick {
            listener(favorite)
        }
    }
}