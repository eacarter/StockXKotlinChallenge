package com.erickson.stockxcodechallenge

import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @Before
    @CallSuper
    open fun before(){
        Intents.init()
    }

    @After
    @CallSuper
    open fun after(){
        Intents.release()
    }

    protected fun <T: AndroidViewModel> getViewModel(activity: FragmentActivity,
                                                     factory: ViewModelProvider.Factory, c: Class<T>): T{
        return ViewModelProvider(activity, factory).get(c)
    }
}