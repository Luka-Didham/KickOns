package com.example.KickOns

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.KickOns.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.lang.Thread.sleep

class SplashScreenActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.mytune)
        mediaPlayer.start()
        logo.alpha = 0f
        logo.animate().setDuration(10000).alpha(1f).withEndAction {
            val app = Intent(this, WelcomePage::class.java)
            startActivity(app)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    protected override fun onPause() {
        super.onPause()
        mediaPlayer.release()
        finish()

    }
}
