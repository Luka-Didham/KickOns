package com.example.KickOns

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopulateDecks(ctx: Context) {
    val db = CardDB.getDatabase(ctx)
    val deckDao = db.deckDAO()
    val cardDao = db.cardDAO()

    val d = DeckItem(1,"Starter Deck")
    //val d = DeckItem(1,"NSFW Starter Deck")
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

    arrayOf("Put up 5 fingers. Never have I ever. The first player to drop all fingers takes a penalty. @player1 you start.\n",0),
    arrayOf("@player1, you have 3 seconds to say the exact number of people in the room. Fail and take a penalty. 3...2...1\n",0),
    arrayOf("Name \"Educational Jobs\". If you repeat or can't think of one take a penalty. @player1 you start",0),
    arrayOf("@player1 lick any part of @player2 's body. If either refuse take a penalty",0),
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
    arrayOf("Happy Thoughts: Go around the group and say one thing your thankful for. The first player to laugh or can't think of anything take a penalty. @player1 you start", 0),
    arrayOf("@player1, you will only be referred to as retard from now on. Any player who fails to call them by their KickOns given name takes a penalty", 3),
    arrayOf("Kissing Booth: @Player1 is the judge. @player2 and @player3 french kiss @player1 and @player1 decides who was better. Kissing victor give out a maximum penalty", 0),
    arrayOf("Boat Race: @Player1 race @player2 to the bottom of their drink. If you lose, race the player to their left", 0),
    arrayOf("The first player to get their nipples out gets to give out 10 penalties", 1),
    arrayOf("@player1 and @player2 go nose to nose. First player to laugh or back away takes 3 penalties", 0),
    arrayOf("Roast: @player1 take the center stage. Each player give their best roast. @player2 you start", 0),
    arrayOf("Everyone switch names with the player to your right for the next five cards. Anyone who calls someone by the wrong name take a penalty", 2),
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
    arrayOf("Covid City: First player to cough or clear their throat finish their vessel", 2),
      arrayOf("@player1 tell the group the most messed up thing you've masturbated to on the internet. Anyone else who's watched it finish your vessel", 0),
      arrayOf("Going around the group, finish a sentence starting with I once. You can lie or tell the truth and everyone votes if its a lie or the truth. Losers take a penalty. @player1 you start", 0),
      arrayOf("Take a penalty if you have ever burned your notes at the end of the year", 0),
      arrayOf("@player1, choose the next song. Any player who dosen't like it take 5 penalties", 0),
      arrayOf("@player1 you are now the groups parrot. Repeat everything @player2 says", 3),
      arrayOf("@player1 finish your drink bitch", 0),
      arrayOf("Evil comic book or movie villians. If you repeat or can]'t think of one take a penalty. @player1 you start", 0),
      arrayOf("Body parts that are usually licked during sex. @player1 you start", 0),
      arrayOf("@player1 if you have ever ended up in a seroius relationship with a fuckbuddy give out 3 penalties or drink them if not", 0),
      arrayOf("Ways of making a women cum. First to run out of ideas take 5 penalties. @player1 you start", 0),
      arrayOf("Things @player1 can't stand. @player2 you start", 0),
      arrayOf("Would you rather fall in love everyday or never at all. Thumbs up and down vote. Losers drink", 0),
      arrayOf("Would you rather piss blood or cry piss. Thumbs up and down vote. Losers drink", 0),
      arrayOf("Would you rather fuck your mum or dad. Thumbs up and down vote. Losers drink", 0),
      arrayOf("Would you rather cheat on your girlfriend or give your entire friend group an STI. Thumbs up and down vote. Losers drink", 0),
      arrayOf("@player1 send a naughty message to a person decided by @player2", 0),
      arrayOf("Would you rather cum everytime you fart or fart everytime you cum. Thumbs up and down vote. Losers drink", 0),
      arrayOf("Would you rather shit out a water mellon or piss out a golf ball. Thumbs up and down vote. Losers drink", 0),
      arrayOf("Would you rather watch your parents have sex every night or join them once to make it stop. Thumbs up and down vote. Losers drink", 0),
      arrayOf("@player1 is now forbidden of saying any vowels for the next 3 cards. Everytime they do take a penalty", 3),
      arrayOf("@player1 if you manage to kiss another player without them catching you in the next 3 cards they finish their drink.", 1),
      arrayOf("@player1 choose someone to have their hand down your crouch for the next 3 cards while you down your drink. Inside the pants", 3),
      arrayOf("Would you rather a good handjob or a bad handjob from your grandma. Thumbs up and down vote. Losers drink", 0),

      arrayOf("@player1 you are under arrest. Put your hands above your head for the next 3 cards or finish your drink ", 3),
      arrayOf("@player1 blindfold yourself and @player2 will touch you with a part of their body. If @player1 guesses right everyone finishes their drinks or if wrong they do", 3),
      arrayOf("@player1 and @player2 read out the predictive text after writing penis in the search bar", 0),
      arrayOf("If you are going bald take a drink", 0),
      arrayOf("If you have ever screamed out the wrong name during sex finish your drink", 0),
      arrayOf("@player1 and @player2 give out as many penalties as you your combined body count", 0),
      arrayOf("@player1 for as many2 penalties as you can take double you can give out", 1),
      arrayOf("Everyone must snarl everytime they take a sip for the next 3 cards. Fail to do so take a max penlty", 2),
      arrayOf("@player1 you are the pig. You can only communicate with pig oinks for the next 3 cards", 0),
      arrayOf("Buffalo", 2),
      arrayOf("Kiss party: go around circle and kiss everyone to the left and right or take 3 panlties. @player1 you start", 0),
      arrayOf("Gentalman Rules: You must address all other players by Sir or Miss or finish your drink for the next 3 cards", 2),
      arrayOf("@player1 say how much your shoes cost. Any player with more expensive kicks drink", 0),
      arrayOf("Stranger Thongs: If you are wearing thongs flash your ass or finish your drink", 0),
      arrayOf("The first player out of @player1 or @player2 find an animal penis on the internet hands out 5 penalties", 0),
      arrayOf("@player1 act like a slug for 5 seconds or take 5 penalties", 0),
      arrayOf("@player1 and @player2 have a moo off. Loser finish your drink", 0),
      arrayOf("all players give out a penalty everytime you've fucked in the last 7 days", 0),
      arrayOf("@player1 blindfold yourself and 2 other players kiss @player1 anywhere. If @player1 guesses either two players give out 5 penalties", 0),
      arrayOf("@player1, who do you think wants to sleep with you more @player3 or @player2. Player chosen take 3 penalties", 0),
      arrayOf("Medusa: @player1 if any other player looks you in the eyes for the next 3 cards they must finsih their drink", 1),
      arrayOf("Everyone must speak in an accent for the next 3 cards. Take a penalty every time you mess up", 2),
      arrayOf("@player1, take a penalty for each hot girl in the game", 0),
      arrayOf("Oscar Piss-torious: Take 5 penalties everytime someone gets up to go to the bathroom", 2),
      arrayOf("@player1 simulate an orgasm before every drink for the next 3 cards ", 3),
      arrayOf("@player1 Give out 21 minus the age of the youngest body count in penalties. If their older you take the penalties ", 0),
      arrayOf("Take 5 penalties if you have ever masturbated in the same house as your BF/GF", 0),
      arrayOf("Rum or Whisky. Vote with your thumbs and losers take a penalty ", 0),
      arrayOf("Everyone vote for who should finish their vessel ", 0),
      arrayOf("If you have ever fantasised about a player in the game take a penalty ", 0),
      arrayOf("Point to the player most likely to fantasise about @player1 ", 0),
      arrayOf("@player1 if you had a choice to kill @player2 or @player3 the person chosen take 3 penalties", 0),


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

