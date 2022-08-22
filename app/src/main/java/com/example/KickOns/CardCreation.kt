package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_creation.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CardCreation : AppCompatActivity() {

    private var backToMain: MainActivity? = null
    private lateinit var db : CardDB

    override fun onCreate(savedInstanceState: Bundle?) {

        db = CardDB.getDatabase(this)
        val cardDao = db.cardDAO()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_creation)

        val cardTypes = resources.getStringArray(R.array.CardTypes)
        val spinner = findViewById<Spinner>(R.id.spinCardType) as Spinner
        val text = findViewById<EditText>(R.id.editText) as EditText

        btnEnter.setOnClickListener {
            //TODO `add error checking
            save(spinner.selectedItemPosition.toInt(),text.text.toString())
            text.text.clear()
        }

        btnBackFromCreateCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cardTypes)
            spinner.adapter = adapter

            var clicked = 0

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>) {
                    spinner.setSelection(clicked)
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        this@CardCreation, getString(R.string.selected_item) + " " +
                                "" + cardTypes[position], Toast.LENGTH_SHORT
                    ).show()

                    clicked = position
                    changeCard(clicked)

                }
            }

        }

    }

    private fun save(id: Int, text: String){
        if(text != null && id != null){
            val c = CardItem(null,id,text)
            GlobalScope.launch {
                db.cardDAO().addCard(c)
            }

        }
    }

    private fun changeCard(cardType: Int) {
        val screenView = findViewById<ConstraintLayout>(R.id.make_card)
        //standard card
        if (cardType == 0) {
            screenView.background = resources.getDrawable(R.drawable.standard, theme)

        }
        //power-up card
        if (cardType == 1) {
            screenView.background = resources.getDrawable(R.drawable.powerup, theme)
        }
        //law card
        if (cardType == 2) {
            screenView.background = resources.getDrawable(R.drawable.law, theme)
        }
        //handicap card
        if (cardType == 3) {
            screenView.background = resources.getDrawable(R.drawable.handicap, theme)
        }
    }

}
