package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.welcome_page.*

class AddPlayer : AppCompatActivity() {

    private var backToMain: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)


    }


}