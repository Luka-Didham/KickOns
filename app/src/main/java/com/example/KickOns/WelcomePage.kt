package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.KickOns.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * This screen is displayed immediately after the splash screen,
 * shows the buttons to start the game, and also a help button.
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

        analytics = Firebase.analytics
        // Write a message to the database
        val db = Firebase.firestore
        // Create a new user with a first and last name

        db.collection("Decks")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }

        //SHOWCASE MODE
        //TODO "make sure cards arent added twice to example deck"
        PopulateDecks(applicationContext).clearDeck()
        PopulateDecks(applicationContext).insert()

      // when the player clicks on the the play button it goes to the AddPlayer class
        btnPlay.setOnClickListener {
            val intent = Intent(this, AddPlayer::class.java)
            startActivity(intent)
        }

        // when the player clicks on the the help button it goes to the help page class
      btnHelp.setOnClickListener {
          val intent = Intent(this, HelpPage::class.java)
       startActivity(intent)
    }

    }

} // end class