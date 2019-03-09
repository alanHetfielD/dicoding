package com.madukubah.alan.footballclubfinal.viewholder

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.madukubah.alan.footballclubfinal.R.id.*
import com.madukubah.alan.footballclubfinal.config.dateToString
import com.madukubah.alan.footballclubfinal.database.TableMatch
import com.madukubah.alan.footballclubfinal.model.ModelMatch
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderMatches( containerView : View) :
        RecyclerView.ViewHolder(  containerView)
{
    private val tvTeam1 : TextView = containerView.find(tvHome)
    private val tvTeam2 : TextView = containerView.find(tvAway)
    private val date : TextView = containerView.find(tvDate)
    private val score : TextView = containerView.find(tvScore)
    private val cvItem: CardView = containerView.find(cardViewMatch)

    fun bindItem( match : ModelMatch, listener : ( ModelMatch ) -> Unit){
        if( match.intHomeScore == null  )
            score.text = " VS "
        else
            score.text = ""+ match.intHomeScore + " VS " + match.intAwayScore

//        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                .parse(match.dateEvent)
//        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
//                .format(timeEvent)
        date.text = dateToString(match.dateEvent)
//        date.text = dateEvent
        tvTeam1.text = match.strHomeTeam
        tvTeam2.text = match.strAwayTeam

        cvItem.onClick {
            listener( match )
        }
    }

}