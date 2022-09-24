package com.example.KickOns

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.io.IOException
import java.util.ArrayList
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.samePropertyValuesAs
import org.junit.jupiter.api.Test

internal class CardItemTest {

    private val deck1: DeckItem? = null
    private val deck2: DeckItem? = null

    private val card1: CardItem? = null
    private val card2: CardItem? = null
    private val card3: CardItem? = null
    private val card4: CardItem? = null

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


    }

    @Test
    @Throws(IOException::class)
    fun testAddDeck() {

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