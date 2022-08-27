package com.example.KickOns

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopulateDecks(ctx: Context) {
    val db = CardDB.getDatabase(ctx)
    val deckDao = db.deckDAO()
    val cardDao = db.cardDAO()

    val d = DeckItem(1,"Sample")

    //Cards for the deck
    var tList = listOf(
        arrayOf("Take a penalty for every time you have broken production",0),
        arrayOf("Take a penalty for each of your repos which don’t have a readme",0),
        arrayOf("Team game. Thumbs up for python and down for Java. Everyone on the smallest team take a penalty ",0),
        arrayOf("[player1] tell the group your biggest development mistake. Everyone who has made that mistake take a penalty. \n",0),
        arrayOf("Name as many programming languages as you can. The player who fails take a penalty [player1] you start.",0),
        arrayOf("[Player1] cannot speak for the next 3 cards or takes a maximum penalty.\n",3),
        arrayOf("Every player must end ever sentence with “semi-colon” or take a penalty.",2),
        arrayOf("[Player1] you are a junior developer. [Player2] you are the scrum master. [Player1] be [player2]’s chair for the next 3 cards or take a maximum penalty.",1)
    )

    fun insert(){
        for (t in tList){
            addCards(t.get(1) as Int, t.get(0) as String)
        }
    }

    fun addCards(type: Int, text: String){
        val c = CardItem(null,type,text,1)
        GlobalScope.launch {
            deckDao.addDeck(d)
            cardDao.addCard(c)}
        cardList.add(c)
    }

}

