package com.example.colorchanging

import android.graphics.Color.red
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val screenView = findViewById<RelativeLayout>(R.id.layout)
        var clicked = 0
        btnScreen.setOnClickListener {
            clicked += 1
            if (clicked>3) clicked = 1
            when(clicked){
                1 -> btnScreen.text = "Pressed 1"
                2 -> btnScreen.text = "Pressed 2"
                3 -> btnScreen.text = "Pressed 3"
            }
        }

    }
}