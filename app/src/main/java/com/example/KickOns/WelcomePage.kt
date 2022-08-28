package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.KickOns.R
import kotlinx.android.synthetic.main.welcome_page.*

class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)

        //SHOWCASE MODE
        PopulateDecks(applicationContext).insert()

        btnPlay.setOnClickListener {
            val intent = Intent(this, AddPlayer::class.java)
            startActivity(intent)
        }

    }

}