package com.cosc345.kickons
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cosc345.kickons.R

/**
 * This help page is shown when the help button
 * is clicked on the welcome page. It displays the xml help_page with information
 * on how to play the game as well as being able to scroll up and down.
 */

class HelpPage : AppCompatActivity() {
    // Button button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page) // displays the contents of the xml page onto the page

        //Elements
        val btnGoBack = findViewById<Button>(R.id.btnGobacK)

        //  button = (Button) findViewById (R.id.btnHelp);

        //SHOWCASE MODE
        //  PopulateDecks(applicationContext).insert()


        // this is a back button that allows the user to go back to the main page from the help page
        btnGoBack.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
} // end class



