package com.madukubah.alan.footballclubfinal.config

import android.content.Context
import android.view.View
import com.madukubah.alan.footballclubfinal.database.DatabaseFavorite
import java.text.SimpleDateFormat
import java.util.*

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun dateToString(date : Date) : String{
    val format = SimpleDateFormat("E, dd MMM yyyy")
    return format.format(date)
}
