package com.madukubah.alan.footballclubfinal.view.team.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.config.db
import com.madukubah.alan.footballclubfinal.config.gone
import com.madukubah.alan.footballclubfinal.config.invisible
import com.madukubah.alan.footballclubfinal.config.visible
import com.madukubah.alan.footballclubfinal.database.TableTeam
import com.madukubah.alan.footballclubfinal.model.Player
import com.madukubah.alan.footballclubfinal.model.Team
import com.madukubah.alan.footballclubfinal.view.team.ui.DetailTeamsUI
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_detail_teams.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailTeamsActivity
    : AppCompatActivity(),
        DetailTeamsView,
        AnkoLogger
{
    override fun hideLoading() {
        progressBar.gone()
        lyTeamDetail.visible()
    }

    override fun showLoading() {
        progressBar.visible()
        lyTeamDetail.gone()
    }

    override fun showEventList(data: List<Team>, player: List<Player>) {
        info("teams data = "+data)
        info("player data = "+player)
        swipeRefreshTeamDetail.isRefreshing = false
        mPlayer.clear()
        mTeams = data[0]
        bindView()
        mPlayer.addAll(player)
        adapter.notifyDataSetChanged()
    }

    private lateinit var mTeams: Team
    private var mPlayer: MutableList<Player> = mutableListOf()

    private lateinit var ivBadgeTeamDetail: ImageView
    private lateinit var tvTitleDetailTeam: TextView
    private lateinit var tvYearDetailTeam: TextView
    private lateinit var tvStadiumDetailTeam: TextView
    private lateinit var tvDescriptionTeamDetail: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshTeamDetail: SwipeRefreshLayout
    private lateinit var lyTeamDetail: LinearLayout
    private lateinit var listPlayer: RecyclerView
    private lateinit var adapter: DetailTeamsAdapter

    private lateinit var presenter: DetailTeamsPresenter
    private lateinit var idTeamDetail: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailTeamsUI().setContentView(this)
        initId()
        if (intent.extras != null) {
            idTeamDetail = intent.getStringExtra(getString(R.string.item_teamdetail_id))
        }

        getTeamDetail()
    }

    private fun initId() {
        listPlayer = find(R.id.rvListPlayerTeam)
        ivBadgeTeamDetail = find(R.id.ivBadgeTeamDetail)
        tvTitleDetailTeam = find(R.id.tvTitleDetailTeam)
        tvYearDetailTeam = find(R.id.tvYearDetailTeam)
        tvStadiumDetailTeam = find(R.id.tvStadiumDetailTeam)
        tvDescriptionTeamDetail = find(R.id.tvDescriptionTeamDetail)
        lyTeamDetail = find(R.id.lyTeamDetail)
        swipeRefreshTeamDetail = find(R.id.swipeRefreshTeamDetail)
        progressBar = find(R.id.pbDetailTeam)
    }

    private fun getTeamDetail() {
        favoriteState()
        listPlayer.layoutManager = LinearLayoutManager(this)
        adapter = DetailTeamsAdapter(mPlayer){
            ctx.startActivity<DetailPlayersActivity>(
            ctx.getString(R.string.item_teamplayer_id) to it.playerId)
        }
        listPlayer.adapter = adapter

        presenter = DetailTeamsPresenter(this, ApiRepository(), Gson())
        presenter.getTeamDetail(idTeamDetail)

        swipeRefreshTeamDetail.onRefresh {
            presenter.getTeamDetail(idTeamDetail)
        }
    }
    private fun bindView() {

        supportActionBar?.title = mTeams.teamName
        Glide.with(this).load(mTeams.teamBadge).into(ivBadgeTeamDetail)

        tvTitleDetailTeam.text = mTeams.teamName
        tvYearDetailTeam.text = mTeams.formedYear
        tvStadiumDetailTeam.text = mTeams.stadiumName
        tvDescriptionTeamDetail.text = mTeams.descriptionTeam
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        menuItem = menu
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
                if (isFavorite)
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_added_to_favorite)
                else
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_add_to_favorite)
    }

    private fun favoriteState(){
        db.use {
            val result = select(TableTeam.TABLE_TEAMS)
                    .whereArgs("(TEAMS_ID = {id})",
                            "id" to idTeamDetail)
            val favorite = result.parseList(classParser<TableTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(TableTeam.TABLE_TEAMS,
                        TableTeam.TEAMS_ID to mTeams.teamId,
                        TableTeam.TEAMS_NAME to mTeams.teamName,
                        TableTeam.BADGE_TEAM to mTeams.teamBadge)
            }
            snackbar(swipeRefreshTeamDetail, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshTeamDetail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            db.use {
                delete(TableTeam.TABLE_TEAMS, "(TEAMS_ID = {id})",
                        "id" to idTeamDetail)
            }
            snackbar(swipeRefreshTeamDetail, "Removed to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshTeamDetail, e.localizedMessage).show()
        }
    }
}
