package com.mohamedabdelaziz.trendingtask.presentation.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import com.mohamedabdelaziz.trendingtask.R
import androidx.test.espresso.assertion.ViewAssertions
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.ArrayList
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun isMainActivityInView (){
            ActivityScenario.launch(MainActivity::class.java)
            Espresso.onView(ViewMatchers.withId(R.id.mainActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

    @Test
    fun testSchedulers() {
        val scheduler = TestScheduler()
        val result: MutableList<Long> = ArrayList()
        Observable.interval(1, TimeUnit.SECONDS, scheduler)
                .take(5)
                .subscribe { e: Long -> result.add(e) }
        Assert.assertTrue(result.isEmpty())
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        Assert.assertEquals(2, result.size.toLong())
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        Assert.assertEquals(5, result.size.toLong())
    }

    @Test
    fun sortListByName(){
        Espresso.onView(ViewMatchers.withId(R.id.sortByName)).perform(click())
    }
    @Test
    fun sortListByStar(){

        Espresso.onView(ViewMatchers.withId(R.id.sortByStar)).perform(click())
    }
    @Test
    fun retryButton(){
        Espresso.onView(ViewMatchers.withId(R.id.retry_btn)).perform(click())
    }@Test
    fun offlineModeButton(){
        Espresso.onView(ViewMatchers.withId(R.id.offline_mode_btn)).perform(click())
    }

}