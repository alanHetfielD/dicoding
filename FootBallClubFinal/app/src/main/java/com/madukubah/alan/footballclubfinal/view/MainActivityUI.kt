package com.madukubah.alan.footballclubfinal.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.LinearLayout
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.color.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

//class MainActivityUI : AnkoComponent<MainActivity>  {
//    @SuppressLint("NewApi")
//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
//    override fun createView(ui: AnkoContext<MainActivity>) = with( ui ){
//        relativeLayout {
//            lparams(width = matchParent, height = matchParent)
//
//            frameLayout {
//                id = R.id.container
//            }.lparams(width = matchParent, height = matchParent) {
//                above(R.id.bottom_layout)
//            }
//
//            linearLayout {
//                id = R.id.bottom_layout
//                orientation = LinearLayout.VERTICAL
//
////                garis
//                view {
//                    background = resources.getDrawable(R.drawable.shadow)
//                }.lparams(height = dip(4), width = matchParent)
//
//                bottomNavigationView {
//                    id = R.id.navigation
//                    inflateMenu(R.menu.nav)
//                    itemBackgroundResource = colorWhite
//                    backgroundColor = android.R.attr.windowBackground
////                    itemTextColor = resources.getColorStateList(colorPrimary)
////                    itemIconTintList = resources.getColorStateList(colorPrimaryDark)
//                    itemTextColor = resources.getColorStateList(colorPrimary)
//                    itemIconTintList = resources.getColorStateList(colorPrimaryDark)
//                }.lparams(width = matchParent, height = wrapContent) {
//                    marginEnd = dip(0)
//                    marginStart = dip(0)
//                }
//            }.lparams(width = matchParent, height = wrapContent) {
//                alignParentBottom()
//            }
//        }
//    }
//}