package com.cosc345.kickons

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.io.IOException
import org.junit.jupiter.api.Test

internal class CardItemTest {
    private var c = CardItem(-1,2,"test",-1)


    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {
        cardList.clear()
    }

    @AfterEach
    @Throws(IOException::class)
    fun cleanUp() {
        cardList.clear()

    }

    @Test
    @Throws(IOException::class)
    fun testAddDeck() {

    }

    @Test
    @Throws(IOException::class)
    fun testAddCard() {
        assertTrue(c.challenge == "test")
        assertTrue(c.id == -1)
        assertTrue(c.deckId == -1)
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