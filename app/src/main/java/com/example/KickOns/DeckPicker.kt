package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.KickOns.databinding.ActivityMainBinding
import com.example.KickOns.databinding.DeckPickerBinding
import kotlinx.android.synthetic.main.deck_creation.*
import kotlinx.android.synthetic.main.deck_picker.*
import kotlinx.coroutines.*

class DeckPicker() : AppCompatActivity(), DeckClickListener {

    private var backToMain: MainActivity? = null
    private var confirmName: CardCreation? = null
    private lateinit var db: CardDB
    private lateinit var binding: DeckPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainActivity = this
        db = CardDB.getDatabase(this)
        val deckDao = db.deckDAO()
        var deckList: List<DeckItem> = emptyList()

        GlobalScope.launch{
            val deckList = async {deckDao.getAll()}
            withContext(Dispatchers.Main){
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    adapter = DeckAdapter(deckList.await(),mainActivity)
                }
            }
        }

        btnCreateDeck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onClick(deck: DeckItem) {
       val intent = Intent(this, DeckCards::class.java)
        intent.putExtra("deck_id", deck.id)
        startActivity(intent)

    }


}

