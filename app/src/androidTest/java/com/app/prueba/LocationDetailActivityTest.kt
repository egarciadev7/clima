package com.app.prueba

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.app.prueba.ui.activities.LocationDetailActivity
import org.junit.Rule
import org.junit.Test

class LocationDetailActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<LocationDetailActivity> = ActivityScenarioRule(LocationDetailActivity::class.java)

    @Test
    fun isActivityInViewTest() {

        Espresso.onView(ViewMatchers.withId(R.id.locationDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun visibilityCurrentWeatherListTest() {

        Espresso.onView(ViewMatchers.withId(R.id.weatherContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.weatherList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}