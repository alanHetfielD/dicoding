package com.madukubah.alan.footballclubfinal.view

import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import com.madukubah.alan.footballclubfinal.R
import com.madukubah.alan.footballclubfinal.R.id.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)
    private val wait: Long = 10000

    @Test
    fun testAppBehaviourUI() {
        Thread.sleep(wait)

        Espresso.onView(ViewMatchers.withId(navigation_teams)).perform(ViewActions.click())
        Thread.sleep(10000)
        Espresso.onView(ViewMatchers.withId(R.id.rvListTeams)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(rvListTeams))
                .perform(RecyclerViewActions.actionOnItemAtPosition
                <RecyclerView.ViewHolder>(5, ViewActions.click()));

        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(lyTeamDetail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(300)

        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(300)
        Espresso.pressBack()

        Thread.sleep(wait)

        Espresso.onView(ViewMatchers.withId(navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(300)
        Espresso.onView(ViewMatchers.withId(navigation_favorites)).perform(ViewActions.click())
    }

}