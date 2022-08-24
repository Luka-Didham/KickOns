package com.example.KickOns
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.KickOns.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var createDeck: DeckCreation? = null
    private var createCard: CardCreation? = null
//    private lateinit var db : CardDB

    override fun onCreate(savedInstanceState: Bundle?) {
//        db = CardDB.getDatabase(this)
//        val cardDao = db.cardDAO()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var clicked = 1
        btnScreen.setOnClickListener {
            clicked += 1
            if (clicked>4)clicked=1
                changeCard(clicked)
            }

        btnCreateCard.setOnClickListener {
            val intent = Intent(this, CardCreation()::class.java)
            startActivity(intent)
        }

        btnCreateDeckFromMain.setOnClickListener {
            val intent = Intent(this, DeckCreation()::class.java)
            startActivity(intent)
        }

        }

    /*
    1 = strandard card
    2 = powerup card
    3 = law card
    4 = handicap card
     */
    fun changeCard(cardType:Int){
        val screenView = findViewById<RelativeLayout>(R.id.layout)

        //standard card
        if (cardType==1){
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.INVISIBLE
            screenView.background = resources.getDrawable(R.drawable.standard,theme)
            btnScreen.text = "normal mode: Akshay loves men. If you are akshay take a long cold shower"
        }
        //powerup card
        if(cardType==2) {
            img_powerup.visibility = View.VISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.INVISIBLE
            btnScreen.text = "powerup mode: Akshay loves men. If you are akshay take a long cold shower"
            screenView.background = resources.getDrawable(R.drawable.powerup, theme)
        }
        //law card
        if(cardType==3) {
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.INVISIBLE
            img_law.visibility = View.VISIBLE
            btnScreen.text = "law mode: Akshay loves men. If you are akshay take a long cold shower"
            screenView.background = resources.getDrawable(R.drawable.law, theme)
        }
        //handicap card
        if(cardType==4) {
            img_powerup.visibility = View.INVISIBLE
            img_handicap.visibility = View.VISIBLE
            img_law.visibility = View.INVISIBLE
            btnScreen.text = "handicap mode: Akshay loves men. If you are akshay take a long cold shower"
            screenView.background = resources.getDrawable(R.drawable.handicap, theme)
        }

    }

    }