package com.example.KickOns
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.KickOns.R

class HelpPage : AppCompatActivity() {
    // Button button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page)

        //Elements
        val btnGoBack = findViewById<Button>(R.id.btnGobacK)

        //  button = (Button) findViewById (R.id.btnHelp);

        //SHOWCASE MODE
        //  PopulateDecks(applicationContext).insert()

        btnGoBack.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}



