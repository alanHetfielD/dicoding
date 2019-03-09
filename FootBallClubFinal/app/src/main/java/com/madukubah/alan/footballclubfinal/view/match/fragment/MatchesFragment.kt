package com.madukubah.alan.footballclubfinal.view.match.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.color.colorWhite
import com.madukubah.alan.footballclubfinal.adapter.FragmentAdapter
import com.madukubah.alan.footballclubfinal.view.MainActivity
import com.madukubah.alan.footballclubfinal.view.match.fragment.inmatch.MatchFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx


class MatchesFragment : Fragment() {
    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        (activity as AppCompatActivity).setSupportActionBar(mToolbar)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar( mToolbar )
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate( R.layout.activity_main, container, false )
        mTabLayout = v.find( R.id.tabs_layout )
        mViewPager = v.find( R.id.pager_layout )
        mToolbar = v.find( R.id.toolbar_layout )

        mToolbar.setTitleTextColor( resources.getColor(colorWhite) )

        mTabLayout.setTabTextColors( resources.getColorStateList(colorWhite) )
        setupViewPager(mViewPager)
        mTabLayout.setupWithViewPager(mViewPager)
        return v
    }

    companion object {
        fun matchesInstance() : MatchesFragment = MatchesFragment()
        const val  PAST = 0;
        const  val NEXT = 1;

//        fun findOrCreatingMatchesFragment( manager: FragmentManager): MatchesFragment{
//            val mMatchesFragment = (MatchesFragment)
//            manager.findFragmentById(  )
//        }
    }

    private fun setupViewPager( pager : ViewPager )
    {
        val adapter = fragmentManager?.let {

            FragmentAdapter(it)
        }
        val past = MatchFragment.matchInstance( MatchesFragment.PAST )
        adapter?.addFragment( past , getString(R.string.title_past_fragment) )

        val next= MatchFragment.matchInstance( MatchesFragment.NEXT )
        adapter?.addFragment( next , getString(R.string.title_next_fragment) )

        pager?.adapter = adapter

    }

}
