package com.madukubah.alan.footballclubfinal.view.team.ui

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.color.colorWhite
import org.jetbrains.anko.*

class ItemListPlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            relativeLayout {

                backgroundColorResource = colorWhite

                imageView {
                    id = R.id.ivListPlayer
                }.lparams(width = dip(50), height = dip(50)) {
                    alignParentLeft()
                }

                textView {
                    id = R.id.tvListNamePlayer
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerInParent()
                    leftMargin = dip(8)
                }

                textView {
                    id = R.id.tvPositionListPlayer
                    textSize = 16f
                }.lparams(width = wrapContent, height = wrapContent) {
                    alignParentRight()
                    centerVertically()
                    leftMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }
        }
    }
}