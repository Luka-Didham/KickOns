package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.deck_picker.*
import kotlinx.android.synthetic.main.welcome_page.*

class DeckPicker : AppCompatActivity() {

    private var playAddPlayer: MainActivity? = null
    private var playWithDefault: MainActivity? = null
    private var makeDeck : DeckCreation? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_picker)

        btnPlayFromChooseDeck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCreateDeckFromChooseDeck.setOnClickListener {
            val intent = Intent(this, DeckCreation::class.java)
            startActivity(intent)
        }

    }
}