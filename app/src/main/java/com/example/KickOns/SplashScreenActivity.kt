package com.example.KickOns

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.KickOns.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/** The Splash Screen is the first page that is displayed when the app is launched
 * it shows the apps logo as well as it plays music.
 */

class SplashScreenActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.mytune)
        mediaPlayer.start()
        logo.alpha = 0f
        //Populate decks during start
        logo.animate().setDuration(6000).alpha(1f).withEndAction {
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
