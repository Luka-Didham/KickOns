package com.example.colorchanging


import org.junit.Test
import com.example.domains.Card
import com.example.domains.Deck
import org.jetbrains.annotations.TestOnly

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    //TODO `feels very wrong having shared data among tests
    val c1 = Card("Question", "When did I ask?", "Kieran")
    val c2 = Card("Question", "Hey how are you?", "Luka")
    val c3 = Card("Challenge", "Empty you vessel on Kieran", "Marcus")
    var d1 = mutableListOf<Card>(c1,c2,c3)

    @Test
    fun createCard() {
        assert(c1.type == "Question")
    }

    @Test
    fun createDeck() {
        assert(d1.size == 3);
    }
    @Test
    fun returnCard() {
        assert(d1[2].players == "Marcus")
    }

}