package com.madukubah.alan.footballclubfinal.view.match.fragment

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.madukubah.alan.footballclubfinal.model.ModelMatch
import com.madukubah.alan.footballclubfinal.view.match.ui.ItemMatchesUI
import com.madukubah.alan.footballclubfinal.viewholder.ViewHolderMatches
import org.jetbrains.anko.AnkoContext

class AdapterListMatches
(
    private val listMatch : List<ModelMatch>,
    private val listener : ( ModelMatch ) -> Unit
)
    : RecyclerView.Adapter<ViewHolderMatches>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMatches {
        return ViewHolderMatches( ItemMatchesUI().createView( AnkoContext.create( parent.context, parent ) ) )
    }

    override fun getItemCount(): Int = listMatch.size

    override fun onBindViewHolder(holder: ViewHolderMatches, position: Int) {
        holder.bindItem( listMatch[ position ] , listener)
    }
}