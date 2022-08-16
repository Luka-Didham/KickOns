package com.example.colorchanging

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            btnColorChange.setOnClickListener {
                val lL = findViewById<LinearLayout>(R.id.layout)
                val ani = lL.background as AnimationDrawable
                ani.setEnterFadeDuration(4000)
                ani.setExitFadeDuration(2000)
                ani.start()
            }

    }
}