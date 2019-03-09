package com.madukubah.alan.footballclubfinal.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.view.favorite.fragment.FavoritesFragment
import com.madukubah.alan.footballclubfinal.view.match.fragment.MatchesFragment
import com.madukubah.alan.footballclubfinal.view.team.fragment.TeamsFragment
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity()
{
    lateinit var bottomNavigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MainActivityUI().setContentView(this)
        setContentView(R.layout.activity_base)

        bottomNavigation = findViewById( R.id.navigation )

        bottomNavigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener )
        if (savedInstanceState == null) {
            addFragment(MatchesFragment.matchesInstance())
        }
    }

    private val mOnNavigationItemSelectedListener
        = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                val matchesFragment = MatchesFragment.matchesInstance()
                addFragment(matchesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                val teamsFragment = TeamsFragment.teamsInstance()
                addFragment(teamsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoFragment = FavoritesFragment.favoritesInstance()
                addFragment(favoFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment( fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace( R.id.container , fragment)
        transaction.commit()
    }
}
