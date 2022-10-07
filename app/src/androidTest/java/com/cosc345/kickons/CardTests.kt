package com.cosc345.kickons

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
    private lateinit var cardDao: com.cosc345.kickons.CardDAO
    private lateinit var db: com.cosc345.kickons.CardDB

    @Before
    fun createDB(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, com.cosc345.kickons.CardDB::class.java).build()
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
     * @see com.cosc345.kickons.CardItem
     * @see com.cosc345.kickons.CardDAO
     */
    @Test
    @Throws(Exception::class)
    fun insertOrReplace(){
        val c = com.cosc345.kickons.CardItem(1, 1, "Card 1", -1)
        val c2 = com.cosc345.kickons.CardItem(1, 3, "Card 2", -1)
        val c3 = com.cosc345.kickons.CardItem(2, 3, "Card 2", -1)
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
        val c = com.cosc345.kickons.CardItem(null, 2, "test", -1)
        GlobalScope.launch {
            cardDao.addCard(c)
            assertTrue(cardDao.getAll().size == 1)
            assertTrue(cardDao.getAll()[0].id != null)
        }
    }

}

