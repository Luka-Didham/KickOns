package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.text.Regex
/**
 * This MainActivity class is
 *
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var db : CardDB

    private lateinit var btnScreen: Button
    private lateinit var imgType: ImageView
    private lateinit var view: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = CardDB.getDatabase(this)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.layout)
        view.setBackgroundResource(getBackGround(0))
        //Elements

        val btnPlayerSelection = findViewById<Button>(R.id.btnPlayerSelection)
        val btnDeckSelection = findViewById<Button>(R.id.btnDeckSelection)

        btnScreen = findViewById<Button>(R.id.btnScreen)
        imgType = findViewById(R.id.img_type)



        var clicked = 0
        val len = cardList.size
        var cardListShuffled = cardList.shuffled()
        btnScreen.setOnClickListener {
            if(clicked<len) {
                changeCard(cardListShuffled.get(clicked).cardType, randomPlayer(cardListShuffled.get(clicked).challenge))
                clicked++
            }else {
                clicked = 0
            }
        }

        // button to select and add players
        btnPlayerSelection.setOnClickListener {
            val intent = Intent(this, AddPlayer()::class.java)
            startActivity(intent)
        }

        // button to select the decks
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
    private fun changeCard(cardType:Int, prompt:String){
        view.setBackgroundResource(getBackGround(cardType))
        btnScreen.text = prompt

        if(cardType==0){
            imgType.isVisible = false;
        }
        if(cardType==1){
            imgType.setBackgroundResource(R.drawable.powerup_title)
            imgType.isVisible = true;
        }
        if(cardType==2){
            imgType.setBackgroundResource(R.drawable.law2)
            imgType.isVisible = true;
        }
        if(cardType==3){
            imgType.setBackgroundResource(R.drawable.handicap_title)
            imgType.isVisible = true;
        }


    }

    private fun getBackGround(x: Int): Int{
        when(x){
            0 -> return R.drawable.standard
            1 -> return R.drawable.powerup
            2 -> return R.drawable.law
            3 -> return R.drawable.handicap
        }
        return 0
    }

    private fun getMax(it: Sequence<MatchResult>): Int{
        var m = 0
        it.forEach {
            val v = it.value.last().digitToInt()
            if (v > m) m = v
        }
        return m
    }

    fun randomPlayer(prompt: String): String{
        val sList = playerList.shuffled()
        var newPrompt = prompt.lowercase()
        var regex =  Regex("@player\\w+")
        val matches = regex.findAll(newPrompt)

        if(getMax(matches) > sList.count()){
            return "Not enough players added for card"
        }
        for(m in matches){
            val s = m.value

            newPrompt = newPrompt.replace(m.value, sList[s.last().digitToInt()-1].name.toString())
        }
        return newPrompt
    }


} // end class