package com.example.KickOns


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.KickOns.databinding.*


import kotlinx.coroutines.*

class DeckCards() : AppCompatActivity(){

    private lateinit var db: CardDB
    private lateinit var binding: DeckCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val deck_id = intent.getIntExtra("deck_id",0)
        //Elements
        val btnBack = findViewById<Button>(R.id.btnBack)

        db = CardDB.getDatabase(this)
        val cardDao = db.cardDAO()

        GlobalScope.launch{
            //TODO(Change hard coded deck_id to extra)
            val cardList = async {cardDao.getByDeckId(deck_id)}
            withContext(Dispatchers.Main){
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    adapter = CardAdapter(cardList.await())
                }
            }
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

    }




}

