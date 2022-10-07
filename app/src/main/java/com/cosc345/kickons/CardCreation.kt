package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/** Allows the players to create cards and save them onto the deck
 *
 */
@Suppress
class CardCreation : AppCompatActivity(){

    private var backToMain: MainActivity? = null
    private lateinit var db : CardDB
    private lateinit var ivCardTypePowerUp : ImageView
    private lateinit var ivCardTypeHandicap : ImageView
    private lateinit var ivCardTypeLaw : ImageView
    private lateinit var editCardDetails : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        db = CardDB.getDatabase(this)
        val deck_id: Long? = intent.extras?.getLong("deck_id")
        super.onCreate(savedInstanceState)
        var clicked = 0

        setContentView(R.layout.card_creation)
        //Elements
        ivCardTypePowerUp = findViewById(R.id.ivCardTypePowerUp)
        ivCardTypeHandicap = findViewById(R.id.ivCardTypeHandicap)
        ivCardTypeLaw = findViewById(R.id.ivCardTypeLaw)
        editCardDetails = findViewById(R.id.editCardDetails)
        //Buttons
        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val btnBackFromCreateCard = findViewById<Button>(R.id.btnBackFromCreateCard)
        val btnNextCardType = findViewById<Button>(R.id.btnNextCardType)
        val btnPrevCardType = findViewById<Button>(R.id.btnPrevCardType)
        val text = findViewById<EditText>(R.id.editCardDetails)

        btnEnter.setOnClickListener {
            //TODO `add error checking
            save(clicked,text.text.toString(),deck_id?.toInt())
            text.text.clear()
        }

        btnBackFromCreateCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnNextCardType.setOnClickListener {
            clicked += 1
            if (clicked > 3) {
                clicked = 0
            }
            text.text.clear()
            changeCard(clicked)
        }

        btnPrevCardType.setOnClickListener {
            clicked -= 1
            if (clicked < 0) {
                clicked = 3
            }
            text.text.clear()
            changeCard(clicked)
        }

    }

    private fun save(id: Int, text: String, deck_id:Int?) {
        //TODO("Add alert telling the user they're arent enough players")
        if(pCheck(text)) return;
        val c = CardItem(null, id, text, deck_id)
        GlobalScope.launch {
            db.cardDAO().addCard(c)

        }
    }


    private fun pCheck(text: String): Boolean {
        var newPrompt = text.lowercase()
        var regex = Regex("(@player[1-9])\\w+")
        val matches = regex.findAll(newPrompt)

        for (m in matches) {
            val s = m.value
            if (s.last().digitToInt() >= playerList.size){
                return true
            }
        }
        return false
    }


    private fun changeCard(cardType: Int) {
        val screenView = findViewById<ConstraintLayout>(R.id.make_card)
        //standard card
        if (cardType == 0) {
            ivCardTypePowerUp.visibility = View.INVISIBLE
            ivCardTypeHandicap.visibility = View.INVISIBLE
            ivCardTypeLaw.visibility = View.INVISIBLE
            editCardDetails.hint =
                "@Player1, howl at the moon 3 times with @player2 or take a penalty"
            screenView.background = resources.getDrawable(R.drawable.standard, theme)

        }
        //power-up card
        if (cardType == 1) {
            ivCardTypePowerUp.visibility = View.VISIBLE
            ivCardTypeHandicap.visibility = View.INVISIBLE
            ivCardTypeLaw.visibility = View.INVISIBLE
            editCardDetails.hint =
                "@Player1, if any player makes eye contact with you, they take a penalty."
            screenView.background = resources.getDrawable(R.drawable.powerup, theme)
        }
        //law card
        if (cardType == 2) {
            ivCardTypeLaw.visibility = View.VISIBLE
            ivCardTypeHandicap.visibility = View.INVISIBLE
            ivCardTypePowerUp.visibility = View.INVISIBLE
            editCardDetails.hint = "Everyone, take a penalty every time someone says 'uhmm'"
            screenView.background = resources.getDrawable(R.drawable.law, theme)
        }
        //handicap card
        if (cardType == 3) {
            ivCardTypeHandicap.visibility = View.VISIBLE
            ivCardTypeLaw.visibility = View.INVISIBLE
            ivCardTypePowerUp.visibility = View.INVISIBLE
            editCardDetails.hint = "@Player1, you now have to play the next 12 rounds on all fours."
            screenView.background = resources.getDrawable(R.drawable.handicap, theme)
        }
    }
}
