package com.example.KickOns

import android.animation.ObjectAnimator
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
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.animation.doOnEnd
import androidx.core.text.set
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.SpringForce.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.ActivityDeckEditBinding
import com.example.KickOns.databinding.ActivityMainBinding.inflate
import com.example.KickOns.databinding.DeckPickerBinding
import kotlinx.android.synthetic.main.activity_deck_edit.*
import kotlinx.android.synthetic.main.activity_deck_edit.view.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.math.abs
import kotlin.math.log

class EditDeck(): AppCompatActivity(){
    private var swiped = false
    private var pos = 0
    private lateinit var db: CardDB
    private lateinit var cardDao: CardDAO
    private lateinit var binding: ActivityDeckEditBinding
    private lateinit var gestureDetector: GestureDetector
    private lateinit var childText: EditText
    private lateinit var cardText: EditText

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)

        db = CardDB.getDatabase(this)
        cardDao = db.cardDAO()

        //Position of card in deck
        val binding = ActivityDeckEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_deck_edit)


        //Snap Animation
        val gestureListener = object : CardSwipeGesture(this){
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if(swiped) return true
                editCrd.x -= distanceX
                editCrd.y -= distanceY
                editCrd.pivotX = e1!!.rawX
                editCrd.pivotY = (edit_deck.height * 0.75).toFloat()
                editCrd.rotation -= distanceX/10

                //TODO("Send Card off at angle e.g distanceX/distanceY")

                if (editCrd.x < -400) removeCard(distanceX,distanceY)
                //TODO("Add remove")
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
        gestureDetector = GestureDetector(this,gestureListener)
        edit_deck.setOnTouchListener { _, e ->
            when(e.action){
                MotionEvent.ACTION_UP -> {
                   //TODO("add sliding animation")
                    snapCard()
                }
                else -> {
                    gestureDetector.onTouchEvent(e)
                }
            }
            true
        }

        //Get Cards for specified deck
        val id = intent.extras?.getInt("id")
        getCards(id!!);

        cardText = findViewById(R.id.editCard)
        childText = findViewById(R.id.editChild)

        nextCard(pos);


        btn_next.setOnClickListener {
            pos = posInc(pos)
            nextCard(pos)

        }

        btn_prev.setOnClickListener {
            pos = posDec(pos)
            nextCard(pos)

        }

        del_card.setOnClickListener {
            GlobalScope.launch {
                cardDao.delete(cardList[pos])
            }
            cardList.removeAt(pos)
            pos = posInc(pos);
            nextCard(pos)
        }

        sv_crd.setOnClickListener {
            cardList[pos].challenge = cardText.text.toString()
            GlobalScope.launch {
                cardDao.update(cardList[pos])

                withContext(Dispatchers.Main) {
                    pos = posInc(pos);
                    nextCard(pos)
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

    private fun removeCard(vX: Float, vY: Float) {
        swiped = true
        val dur = 400L
        val dis = -25

        editCrd.let { crd ->
            ObjectAnimator.ofFloat(crd, "translationY", vY*dis).apply {
                duration = dur
                start()
            }
            ObjectAnimator.ofFloat(crd, "translationX", vX*dis).apply {
                duration = dur
                start()
            }.doOnEnd {
                replaceCard()
            }
        }
    }

    private fun replaceCard(){
        //Set Swiped to false
        //SnapCard Back into place//
        editCrd.visibility = View.GONE
        resetCard()
        editCrd.visibility = View.VISIBLE
        pos = posInc(pos)
        nextCard(pos)
        swiped = false
    }

    private fun resetCard(){
        editCrd.x = (edit_deck.width/2 - editCrd.width/2).toFloat()
        editCrd.y = (edit_deck.height/2 - editCrd.height/2 -80).toFloat()
        editCrd.rotation = 0F
    }
    private fun snapCard(){
        //TODO Change multiple apply to a spring force
        if (swiped) return

        val sf = SpringForce()
        editCrd.let { crd ->
            SpringAnimation(crd,DynamicAnimation.ROTATION, 0F).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
           SpringAnimation(crd,DynamicAnimation.X, (edit_deck.width/2 - editCrd.width/2).toFloat()).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
            //TODO("Think its not centering because its using sp instead of dp, vica versa)
           SpringAnimation(crd,DynamicAnimation.Y, (edit_deck.height/2 - editCrd.height/2 - 80).toFloat()).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
        }
    }

    private fun nextCard(pos : Int){
            cardText.setText(cardList[pos].challenge)
            childText.setText(cardList[posInc(pos)].challenge)
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