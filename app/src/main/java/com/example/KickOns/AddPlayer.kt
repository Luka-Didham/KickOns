package com.example.KickOns
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_player.*
import kotlinx.android.synthetic.main.card_creation.*
import kotlinx.android.synthetic.main.welcome_page.*

/** A class that is able to add players to the game as
 * well as set restrictions on how many players can play and the minimum number of players needed.
 */


class AddPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)
        val adapter = AddPlayerAdapter(playerList)
        RVplayers.adapter = adapter
        RVplayers.layoutManager = LinearLayoutManager(this)

        var editText = textInputEditText
        var MAX_PLAYERS = 30


        btnAddPlayer.setOnClickListener{
            val text = editText.getText().toString()
            if(text == ""){
                editText.hint = "Please Add Name"
            }else{
                if(playerList.size<MAX_PLAYERS) {
                    editText.setText("")
                    val p: Player = Player(text)
                    playerList.add(p)
                    adapter.notifyDataSetChanged()
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
                editText.hint = "Minimum 2 player"
            }
        }

    }
}

