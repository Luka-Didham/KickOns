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
        var clicked = 1
        btnScreen.setOnClickListener {
            clicked += 1
            if (clicked>4){
                clicked = 1
                img_powerup.visibility = View.INVISIBLE
                screenView.background = resources.getDrawable(R.drawable.standard,theme)
                btnScreen.text = "normal mode: Akshay loves men. If you are akshay take a long cold shower"
            }
            if(clicked==2) {
                btnScreen.text = "handicap mode: Akshay loves men. If you are akshay take a long cold shower"
                screenView.background = resources.getDrawable(R.drawable.handicap, theme)
                img_handicap.visibility = View.VISIBLE
            }
            if(clicked==3) {
                img_handicap.visibility = View.INVISIBLE
                btnScreen.text = "law mode: Akshay loves men. If you are akshay take a long cold shower"
                screenView.background = resources.getDrawable(R.drawable.law, theme)
                img_law.visibility = View.VISIBLE
            }
            if(clicked==4) {
                img_law.visibility = View.INVISIBLE
                btnScreen.text = "powerup: Akshay loves men. If you are akshay take a long cold shower"
                screenView.background = resources.getDrawable(R.drawable.powerup, theme)
                img_powerup.visibility = View.VISIBLE
            }

            }
        }

    }
