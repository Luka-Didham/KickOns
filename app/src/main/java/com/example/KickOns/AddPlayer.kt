package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_player.*
import kotlinx.android.synthetic.main.card_creation.*
import kotlinx.android.synthetic.main.welcome_page.*

class AddPlayer : AppCompatActivity() {

    private var startGame: MainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)
        var players = ArrayList<Button>()
        var editText = textInputEditText
        var MAX_PLAYERS = 30
        var positionCount = 1

        btnAddPlayer.setOnClickListener{
            var text = editText.getText().toString()
            print(text)
            if(text == ""){
                editText.hint = "Please Add Name"
                }else{
                if(players.size<MAX_PLAYERS) {
                    editText.setText("")
                    val num = players.size+1
                    val idString = "btnPlayer$num"
                    val buttonID = resources.getIdentifier(idString, "id", packageName)
                    players.add(findViewById(buttonID))
                    var btn = players[players.size-1]
                    btn.text = text
                    btn.visibility = View.VISIBLE
                    btn.setOnClickListener {
                        if (players.contains(btn)) {
                            players.remove(btn)
                            btn.visibility = View.INVISIBLE
                        }
                    }
                }else{
                    editText.hint = "Max 30 players"
                }

        }
        }
        btnStartFromChoosePlayers.setOnClickListener{
            if(players.size>1) {
                val intent = Intent(this, DeckPicker()::class.java)
                startActivity(intent)
            }else{
                editText.hint = "Minimum 1 player"
            }
        }



    }}

