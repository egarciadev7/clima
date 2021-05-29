package com.app.prueba

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.prueba.ui.activities.MainActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.app.prueba.ui.model.Location
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    val fakeLocations: List<Location> = listOf(Location("San Francisco",  "2487956"), Location("Chicago",  "2379574") )

    @Test
    fun isActivityInViewTest() {

        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun visibilitySearchListLocationTest() {

        onView(withId(R.id.txtSearch)).check(matches(isDisplayed()))

        onView(withId(R.id.locationsList)).check(matches(isDisplayed()))
    }

    @Test
    fun visibilityLocationListTest() {
        onView(withId(R.id.locationsList)).check(matches(isDisplayed()))
    }

}