package com.madukubah.alan.footballclubfinal.view.match.ui

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import android.support.v7.widget.CardView
import com.madukubah.alan.footballclubfinal.R


class ItemMatchesUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView(){
            id = R.id.cardViewMatch
            lparams(width = matchParent, height = wrapContent){

                leftMargin= dip(8)
                rightMargin= dip(8)

                topMargin= dip(2)
                bottomMargin= dip(2)
                padding = dip(8)
            }
            linearLayout(){
                lparams(){
                    padding = dip(8)
                    width = matchParent
                    height = matchParent
                }
                orientation = LinearLayout.VERTICAL


                textView("tanggal"){
                    id = R.id.tvDate
                    textColor = Color.rgb( 241 ,182 ,46)
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(width = matchParent){
                    height = wrapContent
                }

                linearLayout(){
                    orientation = LinearLayout.HORIZONTAL
                    lparams(width = matchParent){
                        height = matchParent
                    }
                    textView("team 1 asdasdfasdfasdfasdf"){
                        id = R.id.tvHome
                        textSize = 16f
                        gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                    }.lparams(width = matchParent){
                        height = matchParent
                        weight = 3f
                    }

                    textView("vs"){
                        id = R.id.tvScore
                        typeface = Typeface.DEFAULT_BOLD
                        textSize = 16f
                        gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                    }.lparams(width = matchParent){
                        height = matchParent
                        weight = 2f
                    }
                    textView("team 2 asdfasdffdsasddffdsa"){
                        id = R.id.tvAway
                        textSize = 16f
                        gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                    }.lparams(width = matchParent){
                        height = matchParent
                        weight = 3f
                    }
                }
            }
        }
    }
}