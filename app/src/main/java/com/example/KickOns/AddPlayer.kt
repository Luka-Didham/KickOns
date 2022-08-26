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


var players = mutableListOf<Button>()

class AddPlayer : AppCompatActivity() {

    private var startGame: MainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)

        var editText = textInputEditText
        var MAX_PLAYERS = 30
        var positionCount = 1
        redraw()
        btnAddPlayer.setOnClickListener{

            var text = editText.getText().toString()
            print(text)
            val p: Player = Player(text)
            playerList.add(p)
            if(text == ""){
                editText.hint = "Please Add Name"
                }else{
                if(playerList.size<MAX_PLAYERS) {
                    editText.setText("")
                    val num = playerList.size
                    val idString = "btnPlayer$num"
                    val buttonID = resources.getIdentifier(idString, "id", packageName)
                    val btn = findViewById<Button>(buttonID)
                    btn.text = p.name
                    btn.visibility = View.VISIBLE
                    btn.setOnClickListener {
                       playerList.remove(p)
                       //btn.visibility = View.INVISIBLE
                        redraw()
                    }
                    redraw()
                }else{
                    editText.hint = "Max 30 players"
                }


        }
        }
        btnStartFromChoosePlayers.setOnClickListener{
            if(playerList.size>1) {
                val intent = Intent(this, DeckPicker()::class.java)
                startActivity(intent)
            }else{
                editText.hint = "Minimum 1 player"
            }
        }



    }

    fun redraw(){
        if(!playerList.isEmpty()) {
            for (player in playerList) {
                val num = playerList.indexOf(player) + 1
                val idString = "btnPlayer$num"
                val buttonID = resources.getIdentifier(idString, "id", packageName)

                var btn = findViewById<Button>(buttonID)
                btn.text = player.name
                btn.visibility = View.VISIBLE
                btn.setOnClickListener {
                    playerList.removeAt(playerList.indexOf(player))
                    btn.visibility = View.INVISIBLE
                }

            }

        }
    }
}

