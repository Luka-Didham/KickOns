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
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var cardDao: CardDAO
    private lateinit var deckDao: DeckDAO
    private lateinit var db: CardDB

    @Before
    fun createDB(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, CardDB::class.java).build()
        deckDao = db.deckDAO()
        cardDao = db.cardDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


//Local Deck DB TEST
    @Test
    @Throws(Exception::class)
    fun writeAndReadDeckDB(){
        val deck = DeckItem(-1,"Test")
        GlobalScope.launch {
            deckDao.addDeck(deck)
            val get = deckDao.getAll()
            assertTrue(get.size == 1)
        }
    }

    @Test
    fun writeAndReadCardDB(){
        val card = CardItem(-1,2,"test",-1)
        GlobalScope.launch {
            cardDao.addCard(card)
            val get = cardDao.getAll()
            assertTrue(get.size == 1)
        }
    }

    @Test
    fun getCardsInDeck(){
        val card2 = CardItem(-2,3,"test2",-1)
        GlobalScope.launch {
            cardDao.addCard(card2)
            val cards = cardDao.getByDeckId(-1)
            assertTrue(cards.size == 2)
            assertTrue(cards[1] == card2)
            assertFalse(cards[0] == card2)
        }
    }
}

