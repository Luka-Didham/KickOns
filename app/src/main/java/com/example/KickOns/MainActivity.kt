package com.example.KickOns
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.KickOns.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*


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
                changeCard(cardList.get(clicked).cardType, cardList.get(clicked).challenge)
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
    0 = strandard card
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

    }
