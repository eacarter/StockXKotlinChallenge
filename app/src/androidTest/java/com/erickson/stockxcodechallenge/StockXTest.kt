package com.erickson.stockxcodechallenge

import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.erickson.stockxcodechallenge.fragment.WebFragment
import com.erickson.stockxcodechallenge.matcher.RecyclerViewMatcher
import org.junit.Rule
import org.junit.Test

class StockXTest : BaseTest() {

    @get:Rule
    val mainActivity = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Test
    fun checkInitialRecyclerView(){
        mainActivity.launchActivity(null)
        onView(withId(R.id.homeRecyclerView)).check((matches(isDisplayed())))
    }

    @Test
    fun checkSearch(){
        mainActivity.launchActivity(null)
        onView(withId(R.id.homeSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.homeSearch)).perform(typeText("news"))
        onView(withId(R.id.fab)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.searchRecyclerView))
            .check(matches(RecyclerViewMatcher.matchText(0, "r/news", R.id.item_votes)))
    }

    @Test
    fun checkWebViewOpened(){
        mainActivity.launchActivity(null)
        Thread.sleep(2000)
        onView(withId(R.id.homeRecyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, RecyclerViewMatcher.clickChild(R.id.item)))
        onView(withId(R.id.webView)).check((matches(isDisplayed())))
    }

}