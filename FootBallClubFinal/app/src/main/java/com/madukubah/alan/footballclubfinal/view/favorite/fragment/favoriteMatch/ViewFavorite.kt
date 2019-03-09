package com.madukubah.alan.footballclubfinal.view.favorite.fragment.favoriteMatch

import com.madukubah.alan.footballclubfinal.model.ModelMatch

interface ViewFavorite {
    fun showLoading()
    fun hideLoading()
    fun showMatchList( data : List<ModelMatch>? )
}