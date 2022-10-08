package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cosc345.kickons.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * This screen is displayed immediately after the splash screen,
 * shows the buttons to start the game, help button, and online deck downloader.
 *
 */
class WelcomePage : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)

        //Buttons
        val btnHelp = findViewById<Button>(R.id.btnHelp)
        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnOnline = findViewById<Button>(R.id.btnOnline)

        analytics = Firebase.analytics

        //SHOWCASE MODE
        PopulateDecks(applicationContext).clearDeck()
        PopulateDecks(applicationContext).insert()

      // when the player clicks on the the play button it goes to the AddPlayer class
        btnPlay.setOnClickListener {
            intent = checkPlayers(Intent(this, DeckPicker::class.java))
            intent.putExtra("mode", true);
            startActivity(intent)
        }

        // when the player clicks on the the help button it goes to the help page class
        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpPage::class.java)
            startActivity(intent)
        }
        btnOnline.setOnClickListener{
            intent = checkPlayers(Intent(this, OnlineDeckPicker::class.java))
            intent.putExtra("mode", false);
            startActivity(intent)
        }
    }

  /**
   * Method check if players have been set before allowing user to proceed.
   *
   */
    private fun checkPlayers(intent: Intent): Intent{
        if (playerList.isEmpty()) return Intent(this, AddPlayer::class.java)
        return intent
    }
}
