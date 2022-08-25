package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.add_player.*
import kotlinx.android.synthetic.main.card_creation.*
import kotlinx.android.synthetic.main.welcome_page.*

class AddPlayer : AppCompatActivity() {

    private var startGame: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)

        val spinner = findViewById<Spinner>(R.id.spinNumPlayers) as Spinner
        val numPlayers = resources.getStringArray(R.array.numPlayers)

        btnStartFromChoosePlayers.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numPlayers)
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
                        this@AddPlayer, getString(R.string.selected_item) + " " +
                                "" + numPlayers[position], Toast.LENGTH_SHORT
                    ).show()

                    clicked = position + 1
                    changeNumPlayers(clicked)

                }
            }

        }

    }

    private fun changeNumPlayers(numPeople: Int) {
        val screenView = findViewById<ConstraintLayout>(R.id.setNumPlayers)
        //standard card
        if (numPeople == 1) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.INVISIBLE
            editPlayer3.visibility = View.INVISIBLE
            editPlayer4.visibility = View.INVISIBLE
            editPlayer5.visibility = View.INVISIBLE
            editPlayer6.visibility = View.INVISIBLE
        }
        //power-up card
        if (numPeople == 2) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.VISIBLE
            editPlayer3.visibility = View.INVISIBLE
            editPlayer4.visibility = View.INVISIBLE
            editPlayer5.visibility = View.INVISIBLE
            editPlayer6.visibility = View.INVISIBLE
        }
        //law card
        if (numPeople == 3) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.VISIBLE
            editPlayer3.visibility = View.VISIBLE
            editPlayer4.visibility = View.INVISIBLE
            editPlayer5.visibility = View.INVISIBLE
            editPlayer6.visibility = View.INVISIBLE
        }
        //handicap card
        if (numPeople == 4) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.VISIBLE
            editPlayer3.visibility = View.VISIBLE
            editPlayer4.visibility = View.VISIBLE
            editPlayer5.visibility = View.INVISIBLE
            editPlayer6.visibility = View.INVISIBLE
        }

        if (numPeople == 5) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.VISIBLE
            editPlayer3.visibility = View.VISIBLE
            editPlayer4.visibility = View.VISIBLE
            editPlayer5.visibility = View.VISIBLE
            editPlayer6.visibility = View.INVISIBLE
        }

        if (numPeople == 6) {
            editPlayer1.visibility = View.VISIBLE
            editPlayer2.visibility = View.VISIBLE
            editPlayer3.visibility = View.VISIBLE
            editPlayer4.visibility = View.VISIBLE
            editPlayer5.visibility = View.VISIBLE
            editPlayer6.visibility = View.VISIBLE
        }
    }


}