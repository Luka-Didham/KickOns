package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.text.Regex

class MainActivity : AppCompatActivity() {

    private var createDeck: DeckCreation? = null
    private var createCard: CardCreation? = null
    private lateinit var db : CardDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = CardDB.getDatabase(this)


        setContentView(R.layout.activity_main)
        var clicked = 0
        val len = cardList.size
        btnScreen.setOnClickListener {
            if(clicked<len) {
                changeCard(cardList.get(clicked).cardType, randomPlayer(cardList.get(clicked).challenge))
                //changeCard(cardList.get(clicked).cardType, cardList.get(clicked).challenge)
                clicked++
            }else {
                clicked = 0
            }
        }

        btnPlayerSelection.setOnClickListener {
            val intent = Intent(this, AddPlayer()::class.java)
            startActivity(intent)
        }

        btnDeckSelection.setOnClickListener {
            val intent = Intent(this, DeckPicker()::class.java)
            startActivity(intent)
        }

        }

    /*
    0 = standard card
    1 = powerup card
    2 = law card
    3 = handicap card
     */
    fun changeCard(cardType:Int, prompt:String){
        val screenView = findViewById<RelativeLayout>(R.id.layout)

        //standard card
        if (cardType==0){
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.INVISIBLE
            screenView.background = resources.getDrawable(R.drawable.standard,theme)
            btnScreen.text = prompt
        }
        //powerup card
        if(cardType==1) {
            img_powerup.visibility = View.VISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.INVISIBLE
            btnScreen.text = prompt
            screenView.background = resources.getDrawable(R.drawable.powerup, theme)
        }
        //law card
        if(cardType==2) {
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.VISIBLE
            btnScreen.text = prompt
            screenView.background = resources.getDrawable(R.drawable.law, theme)
        }
        //handicap card
        if(cardType==3) {
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.VISIBLE
            img_law.visibility = View.INVISIBLE
            btnScreen.text = prompt
            screenView.background = resources.getDrawable(R.drawable.handicap, theme)
        }

    }
//    fun rPlayer(prompt: String) : String{
//        var newPrompt = prompt.lowercase()
//        var regex =  Regex("(#player)\\w+")
//        )
//        val matcher: Matcher = pat.matcher(newPrompt)
//        while(matcher.find()) {
//            val randomIndex = Random.nextInt(playerList.size-1)
//            newPrompt = newPrompt.replaceFirst("#player".toRegex(), playerList[randomIndex].name.toString())
//        }
//        return newPrompt
//    }


    fun randomPlayer(prompt: String): String{
        val sList = playerList.shuffled()
        var newPrompt = prompt.lowercase()
        var regex =  Regex("(#player)\\w+")
        val matches = regex.findAll(newPrompt)
        for(m in matches){
            val s = m.value
            //TODO("App Will crash if card prompt has a #player:int where the int > playerList.size
            //  ")
            newPrompt = newPrompt.replace(m.value, sList[s.last().digitToInt()-1].name.toString())
        }
        return newPrompt
    }


}