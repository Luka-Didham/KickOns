package com.example.KickOns

import android.content.Context
import androidx.room.Room
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.google.apphosting.datastore.testing.DatastoreTestTrace
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.io.IOException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.samePropertyValuesAs
import org.junit.jupiter.api.Test

internal class CardItemTest {

    private lateinit var db: DatastoreTestTrace.FirestoreV1Action

    private lateinit var cardDao: CardDAO
    private lateinit var deckDao: DeckDAO

    private val deck1: DeckItem? = null
    private val deck2: DeckItem? = null

    private val card1: CardItem? = null
    private val card2: CardItem? = null
    private val card3: CardItem? = null
    private val card4: CardItem? = null

    //    Before the beginning of every test, us these setup methods
    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {

        deck1?.id = 0
        deck1?.name = "Test deck 1"

        deck2?.id = 1
        deck1?.name = "Test deck 2"

        card1?.id = 0
        card1?.cardType = 0
        card1?.challenge = "Test normal challenge card"
        card1?.deckId = 0

        card2?.id = 1
        card2?.cardType = 1
        card2?.challenge = "Test power-up card"
        card2?.deckId = 0

        card3?.id = 2
        card3?.cardType = 2
        card3?.challenge = "Test law card"
        card3?.deckId = 1

        card4?.id = 3
        card4?.cardType = 3
        card4?.challenge = "Test handicap card"
        card4?.deckId = 1

    }

    @AfterEach
    @Throws(IOException::class)
    fun cleanUp() {
//        Delete every instance made in this test class here so that when the
//        tests run again, the data won't already be there and ruin the tests.
        db.deleteDocument
    }

    @Test
    @Throws(IOException::class)
    fun testAddDeck() {
//        Testing out if we can add a deck to the database
        cardDao.addCard(card1)

    }

    @Test
    @Throws(IOException::class)
    fun testAddCard() {

    }

    @Test
    @Throws(IOException::class)
    fun testDeleteCard() {

    }

    @Test
    @Throws(IOException::class)
    fun testGetCard() {

    }

    @Test
    @Throws(IOException::class)
    fun testUpdateCard() {

    }

}