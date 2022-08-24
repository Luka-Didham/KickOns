package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.welcome_page.*

class WelcomePage : AppCompatActivity() {

    private var playAddPlayer: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)

        btnPlay.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

    }

}