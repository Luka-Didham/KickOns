package com.example.KickOns

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopulateDecks(ctx: Context) {
    val db = CardDB.getDatabase(ctx)
    val deckDao = db.deckDAO()
    val cardDao = db.cardDAO()

    //val d = DeckItem(1,"Andrew Starter Deck")
    val d = DeckItem(1,"NSFW Starter Deck")
    //Cards for the deck
    /*
    0 = standard card
    1 = powerup card
    2 = law card
    3 = handicap card
    */
    var tList = listOf(
        arrayOf("Take a penalty for every time you have broken production\n",0),
        arrayOf("Take a penalty for each of your repos which don’t have a readme\n",0),
        arrayOf("Team game. Thumbs up for python and down for Java. Everyone on the losing team take a penalty\n",0),
        arrayOf("@player1 tell the group your biggest development mistake. Everyone who has made that mistake take a penalty. \n",0),
        arrayOf("Name as many programming languages as you can. The player who fails, take a penalty. @player1 you start.\n",0),
        arrayOf("@Player1 cannot speak for the next 3 cards or takes a maximum penalty.\n",3),
        arrayOf("Every player must end ever sentence with “semi-colon” or take a penalty.\n",2),
        arrayOf("Every time any Player looks at @Player1, @Player2 and @Player3 must take a penalty\n",1),
    )

  //Cards for the deck
  /*
  0 = standard card
  1 = powerup card
  2 = law card
  3 = handicap card
  */
  var aList = listOf(

    arrayOf("Put up 5 fingers. Never have I ever. The first player to drop all fingers takes a penalty. @player you start.\n",0),
    arrayOf("@player1, you have 3 seconds to say the exact number of people in the room. Fail and take a penalty. 3...2...1\n",0),
    arrayOf("Name \"Educational Jobs\". If you repeat or can't think of one take a penalty. @player you start",0),
    arrayOf("@player1 lick any part of @person2's body. If either refuse take a penalty",0),
    arrayOf("@player1 say as many other players names as you can in 3 seconds. Each time their name is said they take a penalty ",1),
    arrayOf("All players play the next 3 cards on all fours. Any players who refuse take a penalty",2),
    arrayOf("@player1, @player2, and @player3 have a three way kiss. Who ever refuses takes a maximum penalty\n",0),
    arrayOf("@player1 tell the room which player you find the most attractive or take a penalty",0),
    arrayOf("@player1, @player2 is you slave. Everytime you say \"drink monkey\" they must take a penalty",1),
    arrayOf("@player1 get your cock out or take a maximum penalty",0),
    arrayOf("@player1 do a body shot off of @player2 or take a maximum penalty",0),
    arrayOf("@player1 you are a begger. Walk around the group on your knees begging each player to pour some of their drink in your mouth as tribute",3),
    arrayOf("Foot Fetish paradise. Everyone get your dogs out. If you refuse take a penalty",2),
    arrayOf("@player1, show @player2 a nude of yourself on your phone. If you refuse take a penalty",3),
    arrayOf("@player1 tell the group the worst thing you have ever done or take a maximum penalty", 0),
    arrayOf("Everyone, Take as many drinks as times you have cheated" ,0),
    arrayOf("@player1 get your toes sucked by @player2, @players3, @Players4. Anyone who refuses takes a penalty", 0) ,
    arrayOf("Waterfall, @player1 you start",0),
    arrayOf("@player1, guess @player2's body count. Take a penalty for each body you are away",3),
    arrayOf("Who is the biggest Hoe of the group? Hoe takes 3 penalties", 0),
    arrayOf("Court Fines: @player1 You have 30 seconds to clear your name for why you don't deserve a maximum penalty. After your plea all other players put their thumbs down for drinks or up for forgivness" ,3),
    arrayOf("@player1 every time you say \"jeep\" take a penalty", 3),
    arrayOf("@player1, and @player2. Where are the weirdest places you've had sex. Werido take a penalty", 0),
    arrayOf("@player1 you have been a bad boy. @player2 gets to slap your ass in the middle of the group", 0),
    arrayOf("Happy Hour: Go around the group and say one thing your thankful for. The first player to laugh or can't think of anything take a penalty. @player1 you start", 0),
    arrayOf("@player1, you will only be referred to as retard from now on. Any player who fails to call them by their KickOns given name takes a penalty", 3),
    arrayOf("Kissing Booth: @Player1 is the judge. @player2 and @player3 french kiss @player1 and @player1 decides who was better. Kissing victor give out a maximum penalty", 0),
    arrayOf("Boat Race: @Player1 race @player2 to the bottom of their drink. If you lose, race the person to their left", 0),
    arrayOf("The first player to get their nipples out gets to give out 10 penalties", 1),
    arrayOf("@player1 and @player2 go nose to nose. First player to laugh or back away takes 3 penalties", 0),
    arrayOf("Roast: @player1 take the center stage. Each player give their best roast. @player2 you start", 0),
    arrayOf("Everyone switch names with the person to your right for the next five cards. Anyone who calls someone by the wrong name take a penalty", 2),
    arrayOf("@player1 it's lapdance time. Pick one player to give you a lap dance. If either refuse take a maximum penalty", 0),
    arrayOf("@player1 take the center stage. Spin 15 times then finish your drink", 3),
    arrayOf("@player1 tell us about the last date you went on or take a penalty", 0),
    arrayOf("@player1 point to the player who you think has the most STI's. Everyone who agrees take a drink", 0),
    arrayOf("@player1 point to the player who you think has cheated the most. Everyone who agrees take a drink", 0),
    arrayOf("Everytime a player says IMAGINE take a penalty", 2),
    arrayOf("Everytime a player takes a drink you need to moan or finish your drink", 2),
    arrayOf("@player1 tell the group the most messed up thing you've masturbated to on the internet. Anyone else who's watched it finish your vessel", 0),
    arrayOf("@player1 who do you think likes high school students the most? @player2 or @player3. That player finishes their vessel", 0),
    arrayOf("@player1 ride @player2 like a horse while @player3 stands in a T pose position a slight distance away. Any who refuse take a penalty", 0),
    arrayOf("@player1 and @player2. Both get eye to eye and see who can put their fingers further down their own throat. Loser finishes your vessel", 0),
    arrayOf("Covid City: First player to cough or clear their throat finsih their vessel", 2)
    )

    fun insert(){
        for (t in aList){
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

