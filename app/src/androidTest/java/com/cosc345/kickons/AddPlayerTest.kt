package com.cosc345.kickons//package com.cosc345.kickons
//
//import androidx.lifecycle.Lifecycle
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.action.ViewActions.typeText
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.Assert.*
//
//@RunWith(AndroidJUnit4::class)
//class AddPlayerTest{
////    @get:Rule
////    val activityRule = ActivityScenarioRule(AddPlayer::class.java)
////
////    @Before
////    fun startUp(){
////        playerList.clear()
////    }
////
////
////    @After
////    fun cleanUp(){
////        playerList.clear()
////    }
//
////    @Test
////    fun testAddPlayer(){
////        activityRule.scenario.onActivity {
////            onView(withId(R.id.textInputEditText)).perform(typeText("Joe"))
////            onView(withId(R.id.btnAddPlayer)).perform(click())
////
////            assertTrue(playerList.size > 1)
////        }
////
////    }
////
////    @Test
////    fun testPlayerName(){
////        assertTrue(playerList[0].name == "Joe")
////    }
//
//}