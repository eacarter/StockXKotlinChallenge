package com.erickson.stockxcodechallenge.matcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerViewMatcher {
    fun clickChild(id: Int): ViewAction {
        return object : ViewAction{
            override fun getDescription(): String {
                return "Item Clicked"
            }

            override fun getConstraints(): Matcher<View> {
                TODO("No current use, just here for show")
            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.findViewById<View>(id)?.performClick()
            }

        }
    }

    fun matchText(position:Int, text: String, id: Int): Matcher<View>{
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("Text Mismatch")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewById<TextView>(id).text.contains(text)
            }

        }
    }
}