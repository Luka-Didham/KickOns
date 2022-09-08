package com.example.KickOns

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.AbsListView
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.set
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.ActivityDeckEditBinding
import com.example.KickOns.databinding.ActivityMainBinding.inflate
import com.example.KickOns.databinding.DeckPickerBinding
import kotlinx.android.synthetic.main.activity_deck_edit.*
import kotlinx.android.synthetic.main.activity_deck_edit.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs

class EditDeck(): AppCompatActivity(){
    private lateinit var db: CardDB
    private lateinit var cardDao: CardDAO
    private lateinit var binding: ActivityDeckEditBinding
    private lateinit var gestureDetector: GestureDetector


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)

        db = CardDB.getDatabase(this)
        cardDao = db.cardDAO()

        //Position of card in deck
        var pos = 0
        val binding = ActivityDeckEditBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(R.layout.activity_deck_edit)

        //Swipe detection
        val gestureListener = object : CardSwipeGesture(this){
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {

                editCrd.x -= distanceX
                editCrd.y -= distanceY
                Log.d("x", "$distanceX")
                Log.d("y", "$distanceY")
                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val difX = e1!!.rawX - e2!!.rawX
                val difY = e1!!.rawY - e2!!.rawY

                Log.d("e", "e1 = $difX e2 = $difY")
                return true
            }
        }

        view.setOnScrollChangeListener(object: View.OnScrollChangeListener{
            override fun onScrollChange(p0: View?, scrollX: Int, scrollY: Int, x: Int, y: Int) {


            }
        })

        gestureDetector = GestureDetector(this,gestureListener)


        edit_deck.setOnTouchListener { _, e ->
            gestureDetector.onTouchEvent(e)
            true
        }

        //Get Cards for specified deck
        val id = intent.extras?.getInt("id")
        getCards(id!!);

        val cardText = findViewById<EditText>(R.id.editCard)
        nextCard(cardText, pos);


        btn_next.setOnClickListener {
            pos = posInc(pos)
            nextCard(cardText, pos)

        }

        btn_prev.setOnClickListener {
            pos = posDec(pos)
            nextCard(cardText, pos)

        }

        del_card.setOnClickListener {
            GlobalScope.launch {
                cardDao.delete(cardList[pos])
            }
            cardList.removeAt(pos)
            pos = posInc(pos);
            nextCard(cardText, pos)
        }

        sv_crd.setOnClickListener {
            cardList[pos].challenge = cardText.text.toString()
            GlobalScope.launch {
                cardDao.update(cardList[pos])

                withContext(Dispatchers.Main) {
                    pos = posInc(pos);
                    nextCard(cardText, pos)
                }
            }
        }
    }

    private fun posDec(pos: Int) : Int {
        if (pos - 1 < 0) return cardList.size - 1
        return pos-1
    }

    private fun posInc(pos : Int): Int {
        if (pos+1 == cardList.size) return 0
        return pos+1
    }

    private fun nextCard(text: EditText, pos : Int){
            text.setText(cardList[pos].challenge)
    }

    private fun getCards(id: Int){
        GlobalScope.launch {
            val cards = db.cardDAO().getByDeckId(id)
            withContext(Dispatchers.Main){
                cards.forEach{
                    cardList.add(it)
                }
            }
        }

    }

}