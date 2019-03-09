package com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.api.ApiRepository
import com.madukubah.alan.footballclubfinal.config.invisible
import com.madukubah.alan.footballclubfinal.config.visible
import com.madukubah.alan.footballclubfinal.model.ModelMatch
import com.madukubah.alan.footballclubfinal.view.match.activity.DetailMatchActivity
import com.madukubah.alan.footballclubfinal.view.match.fragment.AdapterListMatches
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class MatchFragment : Fragment(), MatchView, AnkoLogger {
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<ModelMatch>?) {
        info("MatchFragment data = "+data)
        swipeRefresh.isRefreshing = false
        listMatch.clear()
        if( data != null ){
            listMatch.addAll(data)
        }
        adapterListMatches.notifyDataSetChanged()
    }

    override fun errorMessage(message: String?) {
        view?.let { message?.let { lol -> longSnackbar(it, lol) } }
    }

    private lateinit var listSchedules: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private var listMatch : MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenterMatch: PresenterMatch
    private lateinit var adapterListMatches: AdapterListMatches


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        initAdapter()

    }

    private fun initAdapter(){
        val mode = arguments?.getInt(ARG_MATCH_MODE, 0)
        val spinnerItems = resources.getStringArray( R.array.league_event_array )
        val spinnerItemsValue = resources?.getIntArray( R.array.league_event_array_values )
        info("MatchFragment spinnerItemsValue0  = "+spinnerItemsValue[0] )
        val spinnerAdapter = ArrayAdapter(
                ctx,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerItems
            )
        spinner.adapter = spinnerAdapter;

        presenterMatch = PresenterMatch( this, ApiRepository(), Gson() )



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                presenterMatch.getMatchList( ""+spinnerItemsValue[position], mode )
            }
        }

        adapterListMatches =AdapterListMatches( listMatch ){
            ctx.startActivity<DetailMatchActivity>(
                    DetailMatchActivity.MATCH to it
            )
//            ctx.startActivity<DetailMatchActivity1>(
//                    ctx.getString(R.string.item_eventdetail_id) to it.idEvent,
//                    ctx.getString(R.string.item_home_id) to it.idHomeTeam,
//                    ctx.getString(R.string.item_away_id) to it.idAwayTeam)
        }
        listSchedules.adapter = adapterListMatches

        swipeRefresh.onRefresh {
            presenterMatch.getMatchList( ""+spinnerItemsValue[spinner.selectedItemPosition], mode )
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(8)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner = spinner()

                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listSchedules = recyclerView {
                            id = R.id.rvPastEvent
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(width = matchParent, height = matchParent) {
                            centerInParent()
                            below(R.id.spinner_matches)
                        }

                        progressBar = progressBar {
                            id = R.id.pbNextEvent
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }.view
    }

    companion object {
        fun matchInstance( mode : Int  ) : MatchFragment{
            val fragment= MatchFragment()
            val args = Bundle()
            args.putInt( ARG_MATCH_MODE, mode  )
            fragment.arguments = args
            return fragment
        }
        const val  PAST = 0;
        const val NEXT = 1;
        private val ARG_MATCH_MODE = "ARG_MATCH_MODE"
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.backgroundColorResource = R.color.colorWhite
        searchView.setQueryHint(getString(R.string.example_search_matches))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                info("MatchFragment query = "+ query)
                presenterMatch.getSearchEventList(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                info("MatchFragment query = "+ query)
                presenterMatch.getSearchEventList(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
