package com.example.KickOns
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.LayoutDirection.RTL
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.AddPlayerBinding
import com.example.KickOns.databinding.AddPlayerItemBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


/** A class that is able to add players to the game as
 * well as set restrictions on how many players can play and the minimum number of players needed.
 */


class AddPlayer : AppCompatActivity() {
    private lateinit var cG: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)
        val binding = AddPlayerBinding.inflate(layoutInflater)
        cG = findViewById(R.id.chipGroup)

        var editText = findViewById<EditText>(R.id.textInputEditText)
        var MAX_PLAYERS = 30
        val btnAddPlayer = findViewById<Button>(R.id.btnAddPlayer)
        val btnStartFromChoosePlayers = findViewById<Button>(R.id.btnStartFromChoosePlayers)

        btnAddPlayer.setOnClickListener{
            val text = editText.text.toString()
            if(text == ""){
                editText.hint = "Please Add Name"
            }else{
                if(playerList.size<MAX_PLAYERS) {
                    editText.setText("")
                    val p = Player(text)
                    playerList.add(p)
                    addChip(p)

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

    refresh()
    }

    /**
     * Onclick function that removes a player view item from the main view.
     *
     * @param v The current view in the app
     * @param p The player that was clicked
     *
     * @see com.example.KickOns.Player
     * @see com.example.KickOns.AddPlayerAdapter
     * @see com.example.KickOns.PlayerViewHolder
     */
    private fun onClick(v: View?, p: Player) {
        playerList.remove(p)
        cG.removeView(v)

    }

    //Creates a chip and adds it to the chip group
    @RequiresApi(Build.VERSION_CODES.M)
    private fun addChip(p: Player){
        val c = Chip(this)
        c.text = p.name.toString()
        c.textSize = 14F
        c.chipBackgroundColor = getColorStateList(R.color.color_accent)
        c.setTextColor(getColor(R.color.color_primary))
        c.chipIcon = getDrawable(R.drawable.cancel_button)
        c.typeface = getFont(this,R.font.chango_regular)
        c.layoutDirection = View.LAYOUT_DIRECTION_RTL
        c.setOnClickListener{
            onClick(c,p)
        }
        cG.addView(c)
    }

    private fun refresh(){
        cG.removeAllViews()
        playerList.forEach{
            addChip(it)
        }
    }
}

