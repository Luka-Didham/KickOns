package com.example.KickOns
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.KickOns.R
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_game.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class Game : AppCompatActivity() {

    private var createDeck: DeckCreation? = null
    private var createCard: CardCreation? = null
    private lateinit var db: CardDB

    //Parse in deck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        db = CardDB.getDatabase(this)
        //Get Cards from deck

        val cardDao = db.cardDAO()
        val title = findViewById(R.id.title) as TextView
        val challenge = findViewById(R.id.card_challenge) as TextView

        var click = 0
        val len = cardList.size

        btnNext.setOnClickListener{
            if (click < len){
                challenge.text = cardList.get(click).challenge
                click ++;
            } else {
                click = 0;
            }
        }

        btnExit.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

    }



    //Store in list
        //Read list values



    }
    /*
    1 = strandard card
    2 = powerup card
    3 = law card
    4 = handicap card
     */

