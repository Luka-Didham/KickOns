package com.example.KickOns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeckCreation : AppCompatActivity() {

    private var backToMain: MainActivity? = null
    private var confirmName : CardCreation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_creation)
    }
}