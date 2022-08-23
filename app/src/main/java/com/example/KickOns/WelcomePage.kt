package com.example.KickOns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WelcomePage : AppCompatActivity() {

    private var backToMain: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)
    }
}