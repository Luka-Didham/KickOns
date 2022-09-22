package com.example.KickOns
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.KickOns.R
import kotlinx.android.synthetic.main.help_page.*
import kotlinx.android.synthetic.main.welcome_page.*

class HelpPage : AppCompatActivity() {
    // Button button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_page)


        //  button = (Button) findViewById (R.id.btnHelp);

        //SHOWCASE MODE
        //  PopulateDecks(applicationContext).insert()

        btnGobacK.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}



