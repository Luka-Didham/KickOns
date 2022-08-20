package com.example.colorchanging

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnColorChange.setOnClickListener {
            btnColorChange.text = "But we still love him"
        }

    }
}