package com.example.KickOns

import android.provider.Settings
import androidx.room.Dao
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Tests for cardDAO
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CardTests {
    private lateinit var cardDao: CardDAO
    private lateinit var db: CardDB

    @Before
    fun createDB(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, CardDB::class.java).build()
        cardDao = db.cardDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    /**
     * Tests to see if cards are updated when
     * a new card with the same id is added
     * @see com.example.KickOns.CardItem
     * @see com.example.KickOns.CardDAO
     */
    @Test
    @Throws(Exception::class)
    fun insertOrReplace(){
        val c = CardItem(1,1,"Card 1",-1)
        val c2 = CardItem(1,3,"Card 2", -1)
        val c3 = CardItem(2,3,"Card 2", -1)
        GlobalScope.launch {
            cardDao.addCard(c)
            assertTrue(cardDao.getAll().size == 1)
            cardDao.addCard(c2)
            assertTrue(cardDao.getAll().size == 1)
            assertTrue(cardDao.getAll()[0].challenge == "Card 2")
            cardDao.addCard(c3)
            assertFalse(cardDao.getAll().size == 2)
        }
    }

    @Test
    fun testAutoID(){
        val c = CardItem(null,2,"test",-1)
        GlobalScope.launch {
            cardDao.addCard(c)
            assertTrue(cardDao.getAll().size == 1)
            assertTrue(cardDao.getAll()[0].id != null)
        }
    }

}

