package com.madukubah.alan.footballclubfinal.view.match.activity

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.string.match_detail
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.config.dateToString
import com.madukubah.alan.footballclubfinal.config.db
import com.madukubah.alan.footballclubfinal.config.invisible
import com.madukubah.alan.footballclubfinal.config.visible
import com.madukubah.alan.footballclubfinal.database.TableMatch
import com.madukubah.alan.footballclubfinal.model.ModelMatch
import com.madukubah.alan.footballclubfinal.model.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity
    :
        AppCompatActivity(),
        DetailMatchView,
        AnkoLogger
{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(teams: List<Team>?, pos: Int) {
        info("badge = "+teams?.get(0)?.teamBadge)
        when(pos){
            0->
                Glide.with(this).load(teams?.get(0)?.teamBadge).into(imageViewHome)
            1->
                Glide.with(this).load(teams?.get(0)?.teamBadge).into(imageViewAway)
        }
    }

    lateinit var tvDate : TextView
    lateinit var tvScore  : TextView
    lateinit var tvHomeName  : TextView
    lateinit var tvAwayName   : TextView
    lateinit var tvGoalsHome    : TextView
    lateinit var tvGoalsAway    : TextView
    lateinit var tvShotsHome     : TextView
    lateinit var tvShotsAway     : TextView
    lateinit var tvGoalKeepersHome      : TextView
    lateinit var tvGoalKeepersAway       : TextView
    lateinit var tvDefenceHome        : TextView
    lateinit var tvDefenceAway         : TextView
    lateinit var tvMidfieldHome          : TextView
    lateinit var tvMidfieldAway           : TextView
    lateinit var tvForwardHome            : TextView
    lateinit var tvForwardAway             : TextView
    lateinit var tvSubsitutesHome              : TextView
    lateinit var tvSubsitutesAway               : TextView
    lateinit var progressBar               : ProgressBar
    lateinit var imageViewHome : ImageView
    lateinit var imageViewAway   : ImageView
    lateinit var swipeRefresh   : SwipeRefreshLayout

    lateinit var presenterDetail : DetailMatchPresenter
    lateinit var linearLayout: LinearLayout
    private lateinit var match : ModelMatch

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEventDetail: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(match_detail)
        swipeRefresh = swipeRefreshLayout {
            id = R.id.swipeRefreshDetail
            setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
            scrollView(){
                linearLayout = linearLayout(){
                    lparams(){
                        padding = dip(8)
                        width = matchParent
                        height = wrapContent
                    }
                    orientation = LinearLayout.VERTICAL



                    tvDate = textView("tanggal"){
                        gravity = Gravity.CENTER_HORIZONTAL
                        textColor = Color.rgb( 241 ,182 ,46)
                    }.lparams(width = matchParent){
                        height = wrapContent
                    }
//            versus
                    linearLayout(){
                        lparams(){
                            width = matchParent
                            height = dip(150)

                        }
                        orientation = LinearLayout.HORIZONTAL

                        imageViewHome  = imageView{

                        }.lparams( ){
                            width = matchParent
                            weight = 1f
                        }
//                            .setImageResource(R.drawable.img_arsenal)

                        tvScore = textView("1 VS 1"){
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 18f

                            gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        imageViewAway= imageView(){

                        }.lparams(  ){
                            width = matchParent
                            weight = 1f
                        }
//                            .setImageResource(R.drawable.img_arsenal)
                    }
//team name
                    linearLayout(){
                        orientation = LinearLayout.HORIZONTAL
                        lparams(){
                            width = matchParent
                            height = wrapContent
                        }
                        tvHomeName = textView("arsenal"){
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 18f
                            textColor = Color.rgb( 241 ,182 ,46)
                            gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        textView(""){
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 24f

                            gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvAwayName = textView("arsenal"){
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = 18f
                            textColor = Color.rgb( 241 ,182 ,46)
                            gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }
                    }
//                garis
                    view(){
                        backgroundColor = Color.GRAY
                    }.lparams(width = matchParent){
                        height = dip(1)
                    }
//                goals
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvGoalsHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("goals"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvGoalsAway= textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }

//                shots
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvShotsHome = textView("2"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("shots"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvShotsAway= textView("5"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
//                garis
                    view(){
                        backgroundColor = Color.GRAY
                    }.lparams(width = matchParent){
                        height = dip(1)
                    }
//                line ups
                    textView("lineups"){
                        textSize = 14f
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = matchParent){
                        height = wrapContent
                        weight = 1f
                    }
//                goalkeepers
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvGoalKeepersHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("goalkeepers"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvGoalKeepersAway = textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
//                defence
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvDefenceHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("defence"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvDefenceAway = textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
//                midfield
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvMidfieldHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("midfields"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvMidfieldAway = textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
//                forward
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvForwardHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("forward"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvForwardAway = textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
//                subsitues
                    linearLayout(){

                        orientation = LinearLayout.HORIZONTAL
                        lparams(){

                            topMargin = dip(4)
                            width = matchParent
                            height = matchParent
                        }

                        tvSubsitutesHome = textView("alan\nalan\nalan"){
                            textSize = 12f
                            gravity = Gravity.LEFT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }

                        textView("subsitutes"){
                            textSize = 12f
                            gravity = Gravity.CENTER_HORIZONTAL
                            textColor = Color.rgb( 241 ,182 ,46)
                        }.lparams(width = matchParent){
                            height = matchParent
                            weight = 1f
                        }

                        tvSubsitutesAway = textView("alan\n alan\n alan"){
                            textSize = 12f
                            gravity = Gravity.RIGHT
                        }.lparams(width = matchParent){
                            height = wrapContent
                            weight = 1f
                        }
                    }
                    progressBar = progressBar {

                    }.lparams{
                        gravity = Gravity.CENTER
                    }
                }


            }
        }

        match = intent.getParcelableExtra( MATCH )
        idEventDetail = match.idEvent
        tvDate.text =  dateToString( match.dateEvent)
//        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                .parse(match.dateEvent)
//        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
//                .format(timeEvent)
//        tvDate.text =  dateEvent
        if( match.intHomeScore != null ){
            tvScore.text = ""+match.intHomeScore+" VS "+""+match.intAwayScore
            tvShotsHome.text = "" + match.intHomeScore
            tvShotsAway.text = "" + match.intAwayScore
        }else{
            tvScore.text = " VS "
            tvShotsHome.text = ""
            tvShotsAway.text = ""
        }

        tvHomeName.text = match.strHomeTeam
        tvAwayName.text = match.strAwayTeam

        var homeGoaldetails:String? = match.strHomeGoalDetails?.replace(
                ";",
                "\n",
                false
        )
        var awayGoaldetails:String? = match.strAwayGoalDetails?.replace(
                ";",
                "\n",
                false
        )
        tvGoalsHome.text = homeGoaldetails
        tvGoalsAway.text = awayGoaldetails

        var text = listOf(
                match.strHomeLineupGoalkeeper,
                match.strAwayLineupGoalkeeper,

                match.strHomeLineupDefense,
                match.strAwayLineupDefense,

                match.strHomeLineupMidfield,
                match.strAwayLineupMidfield,

                match.strHomeLineupForward,
                match.strAwayLineupForward,

                match.strHomeLineupSubstitutes,
                match.strAwayLineupSubstitutes
        )
        var target = listOf(
                tvGoalKeepersHome,
                tvGoalKeepersAway,

                tvDefenceHome,
                tvDefenceAway,

                tvMidfieldHome,
                tvMidfieldAway,

                tvForwardHome,
                tvForwardAway,

                tvSubsitutesHome,
                tvSubsitutesAway
        )

        for ( index in text.indices ){
            target[ index ].text = text[ index  ]?.replace(
                    "; ",
                    "\n",
                    false
            )
        }
        presenterDetail = DetailMatchPresenter(
                this,
                ApiRepository(),
                Gson()
        )

        presenterDetail.getTeam( ""+match.idHomeTeam, 0 )
        presenterDetail.getTeam( ""+match.idAwayTeam, 1 )

    }

    companion object {
        const val MATCH = "MATCH"
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        info(" DetailMatchActivity menu creatted ")
        menuInflater.inflate(R.menu.detail, menu)
        menuItem = menu
        isFavorite = isExist()
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        menuItem?.getItem(0)?.icon =
                if(isFavorite)
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_added_to_favorite)
                else
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_add_to_favorite)
    }


    private fun addToFavorite() {
        try {
            db.use {
                insert(TableMatch.TABLE_MATCHES,
                        TableMatch.MATCHES_ID to match.idEvent
                )
            }
            snackbar(swipeRefresh, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            db.use {
                delete(TableMatch.TABLE_MATCHES, "(${TableMatch.MATCHES_ID} = {id})",
                        "id" to match.idEvent)
            }
            snackbar(linearLayout, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(linearLayout, e.localizedMessage).show()
        }
    }
    private fun isExist() : Boolean{
        var found=false
        db?.use {
            val e = select(TableMatch.TABLE_MATCHES)
                    .whereArgs("(${TableMatch.MATCHES_ID} = {id})","id" to match.idEvent)
                    .parseOpt(LongParser)
            if (e != null) {
                found=true
            }
        }
        return found
    }
}
