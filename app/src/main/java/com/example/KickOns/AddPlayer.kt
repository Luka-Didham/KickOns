package com.example.KickOns
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.AddPlayerBinding
import com.example.KickOns.databinding.AddPlayerItemBinding


/** A class that is able to add players to the game as
 * well as set restrictions on how many players can play and the minimum number of players needed.
 */


class AddPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)
        val binding = AddPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        val RVplayers = findViewById<RecyclerView>(R.id.RVplayers)
        RVplayers.adapter = AddPlayerAdapter(playerList)
        RVplayers.layoutManager = GridLayoutManager(this, 3)
        var editText = findViewById<EditText>(R.id.textInputEditText)
        var MAX_PLAYERS = 30
        val btnAddPlayer = findViewById<Button>(R.id.btnAddPlayer)
        val btnStartFromChoosePlayers = findViewById<Button>(R.id.btnStartFromChoosePlayers)

        btnAddPlayer.setOnClickListener{
            val text = editText.getText().toString()
            if(text == ""){
                editText.hint = "Please Add Name"
            }else{
                if(playerList.size<MAX_PLAYERS) {
                    editText.setText("")
                    val p: Player = Player(text)
                    playerList.add(p)
//                    adapter.notifyDataSetChanged()

                }else{
                    editText.setText("")
                    editText.hint = "Max 30 players"
                }
            }
        }

        btnStartFromChoosePlayers.setOnClickListener{
            if(playerList.size>1) {
                val intent = Intent(this, DeckPicker()::class.java)
                startActivity(intent)
            }else{
              editText.setText("")
              editText.hint = "Minimum 2 players"
            }
        }

    }
}

