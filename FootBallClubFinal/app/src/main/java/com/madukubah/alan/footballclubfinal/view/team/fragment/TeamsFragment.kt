package com.madukubah.alan.footballclubfinal.view.team.fragment


import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.string.item_teamdetail_id
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.config.invisible
import com.madukubah.alan.footballclubfinal.config.visible
import com.madukubah.alan.footballclubfinal.model.Team
import com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch.MatchFragment
import com.madukubah.alan.footballclubfinal.view.team.activity.DetailTeamsActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class TeamsFragment
    : Fragment(),
        TeamsView,
        AnkoLogger
{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(teams: List<Team>?) {
        info("teams data = "+teams)
        swipeRefresh.isRefreshing = false
        listTeams.clear()
        if( teams != null ){
            listTeams.addAll(teams)
        }
        adapter.notifyDataSetChanged()
    }

    private var listTeams:MutableList<Team> = mutableListOf()
    private lateinit var lisTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar( mToolbar )
        }
        mToolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))
        mToolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        initAdapter()
    }
    private lateinit var mToolbar: Toolbar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return UI {

            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                appBarLayout {
                    id = R.id.appBarTeams
                    lparams(width = matchParent, height = wrapContent)

                    mToolbar = toolbar {
                        lparams(width = matchParent, height = dimenAttr(android.support.design.R.attr.actionBarSize))
                        id = R.id.toolbar_teams
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL

                    topPadding = dip(16)
                    leftPadding = dip(16)
                    rightPadding = dip(16)

                    spinner = spinner()

                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                                android.R.color.holo_green_light,
                                android.R.color.holo_orange_light,
                                android.R.color.holo_red_light)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            lisTeam = recyclerView {
                                id = R.id.rvListTeams
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                            }

                            progressBar = progressBar {
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
                }
            }
        }.view

        mToolbar = find(R.id.toolbar_teams)
    }

    companion object {
        fun teamsInstance() : TeamsFragment = TeamsFragment()
    }
    private fun initAdapter() {
        val spinnerItems = resources.getStringArray( R.array.league_event_array )
        val spinnerItemsValue = resources?.getIntArray( R.array.league_event_array_values )

        val spinnerAdapter = ArrayAdapter(
                ctx,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerItems
        )
        spinner.adapter = spinnerAdapter;

        presenter = TeamsPresenter(
                this,
                ApiRepository(),
                Gson()
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                leagueName = spinner.selectedItem.toString()
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    leagueName = leagueName.replace(" ", "%20")
                }
                presenter.getTeamList( leagueName )
            }
        }

        adapter = TeamsAdapter(
                listTeams
        ){
            ctx.startActivity<DetailTeamsActivity>(
                    ctx.getString(item_teamdetail_id) to it.teamId
            )
        }

        lisTeam.adapter = adapter

        swipeRefresh.onRefresh {
            leagueName = spinner.selectedItem.toString()
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                leagueName = leagueName.replace(" ", "%20")
            }
            presenter.getTeamList( leagueName )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.backgroundColorResource = R.color.colorWhite
        searchView.setQueryHint(getString(R.string.example_search_team))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                info("TeamsFragment query = "+ query)
                presenter.getSearchTeam(query)
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                info("TeamsFragment query = "+ query)
                presenter.getSearchTeam(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

}
