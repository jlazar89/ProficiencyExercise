package com.arinspect.proficiencyexercise.screens.list

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.arinspect.proficiencyexercise.R
import com.arinspect.proficiencyexercise.di.generateTestAppComponent
import com.arinspect.proficiencyexercise.ui.list.AboutCanadaAdapter
import com.arinspect.proficiencyexercise.ui.main.MainActivity
import com.arinspect.proficiencyexercise.base.BaseUITest
import com.arinspect.proficiencyexercise.helpers.recyclerItemAtPosition
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class AboutCanadaListTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val mTitleOne = "Beavers"
    val mDescriptionOne = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"
    val mTitleTwo = "Language"
    val mDescriptionTwo = "Nous parlons tous les langues importants."

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    //Inject Mockwebserver created with koin
    val  mMockWebServer : MockWebServer by inject()

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_allDataPresent() {
        mActivityTestRule.launchActivity(null)
        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.rvItems))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mTitleOne))
                    )
                ))

        onView(withId(R.id.rvItems))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mDescriptionOne))
                    )
                ))

        //Scroll to last index in json
        onView(withId(R.id.rvItems)).perform(
            RecyclerViewActions.scrollToPosition<AboutCanadaAdapter.AboutCanadaViewHolder>(13))

        //Check if item last position is having last position in  json
        onView(withId(R.id.rvItems))
            .check(matches(recyclerItemAtPosition(13, ViewMatchers.hasDescendant(withText(mTitleTwo)))))

        onView(withId(R.id.rvItems))
            .check(matches(recyclerItemAtPosition(13, ViewMatchers.hasDescendant(withText(mDescriptionTwo)))))

    }
}