package com.example.KickOns

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.SpringForce.*
import com.example.KickOns.databinding.ActivityDeckEditBinding
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.math.abs
import kotlin.math.log

class EditDeck(): AppCompatActivity(){
    private var swiped = false
    private var pos = 0
    private lateinit var db: CardDB
    private lateinit var cardDao: CardDAO
    private lateinit var gestureDetector: GestureDetector

    //Card View Elements
    private lateinit var childText: EditText
    private lateinit var cardText: EditText
    private lateinit var editCrd: CardView
    private lateinit var editType: ImageView
    private lateinit var childType: ImageView

    //Buttons
    private lateinit var delBtn: ImageView
    private lateinit var svBtn: ImageView
    private lateinit var editButton: ImageView
    private lateinit var addBtn: ImageView

    //Views
    private lateinit var editDeck: View
    private lateinit var view: View


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)
        //REMOVE WHEN DONE
        cardList.clear()
        db = CardDB.getDatabase(this)
        cardDao = db.cardDAO()

        //Position of card in deck
        val binding = ActivityDeckEditBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(R.layout.activity_deck_edit)

        //Elements
        editCrd = findViewById(R.id.editCrd)
        editDeck = findViewById(R.id.edit_deck)

        editType = findViewById(R.id.editType)
        childType = findViewById(R.id.childType)


        //need to pass in deck_id

        //Buttons
        //val btn_next = findViewById<Button>(R.id.btn_next)
       // val btn_prev = findViewById<Button>(R.id.btn_prev)

        delBtn = findViewById(R.id.delBtn)
        svBtn = findViewById(R.id.svBtn)
        editButton = findViewById(R.id.edtBtn)
        addBtn = findViewById(R.id.addBtn)

        delBtn.visibility = View.INVISIBLE
        svBtn.visibility = View.INVISIBLE


        //var
        var t = false

        //Snap Animation
        val gestureListener = object : CardSwipeGesture(this){
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if(swiped) return true
                val snsty = 0.2F
                var dX = e1.rawX - e2.rawX
                var dY = e1.rawY - e2.rawY
                dX *= snsty
                dY *= snsty

                editCrd.x -= dX
                editCrd.y -= dY
                //editCrd.pivotX = e1.rawX
                editCrd.pivotY = (editCrd.height * 0.75).toFloat()
                editCrd.rotation -= dX/10

                if (editCrd.x < -400) removeCard(dX,dY)
                return true
            }
        }

        gestureDetector = GestureDetector(this,gestureListener)
        editCrd.setOnTouchListener { _, e ->
            when(e.action){
                MotionEvent.ACTION_UP -> {
                    snapCard()
                }
                else -> {
                    gestureDetector.onTouchEvent(e)
                }
            }
            true
        }

        //Get Cards for specified deck
        cardText = findViewById(R.id.editCard)
        childText = findViewById(R.id.editChild)
        //nextCard(pos);

        delBtn.setOnClickListener {
            GlobalScope.launch {
                cardDao.delete(cardList[pos])
                withContext(Dispatchers.Main){
                    cardList.removeAt(pos)
                    pos = posInc(pos);
                    nextCard(pos)
                }
            }
        }

        svBtn.setOnClickListener{
            val text = cardText.text.toString()
            cardList[pos].challenge = text
            addCard(cardList[pos])
            t = toggleEdit(t)
        }
//        svBtn.setOnClickListener {
//            cardList[pos].challenge = cardText.text.toString()
//            GlobalScope.launch {
//                cardDao.update(cardList[pos])
//                withContext(Dispatchers.Main) {
//                    t = toggleEdit(t)
//                }
//            }
//        }
        editButton.setOnClickListener{
            t = toggleEdit(t)
        }

        addBtn.setOnClickListener{
            //Fresh Card
            newCard()
            pos = cardList.size -1
        }
        newCard()
    }
    //TO NOTE add to end of list
    //Option 1
    //Save btn has two modes
    //Save and Update
    //On update mode it just adds the card to the cardList and updates the db
    //On save mode it adds the card to the cardList and adds to the db
    //To set the mode there will be an input in the function
    //
    //Option 2
    //There are two buttons
    //Update and save
    //
    //Option 3
    // You have 1 btn that has two different states
    // Update or Save
    // (Could be done with an interface)
    // On save state run different behaviour to update state
    //AddCards
    //Gets a blank template ready for you to enter stuff into
    //On Save
    //It saves the data from the blank template into a card
    //Adds them to a card list
    //Saves them to a db
    //If the card is being updated
    //Do not add to card list
    //Update card inside db
    //make a system to adc cards
    //worry about the rest latter

    private fun toggleEdit(t: Boolean): Boolean{
        //TODO Maybe make grey or display a fresh card to edit
        if(t) {
            editMode(View.INVISIBLE,false)
            return !t
        }
            editMode(View.VISIBLE,true)
        return !t

    }

    private fun editMode(vis: Int, focus: Boolean){
        delBtn.visibility = vis
        svBtn.visibility = vis
        cardText.isFocusableInTouchMode = focus
        cardText.isFocusable = focus
        swiped = focus
        lastCard()
    }

    private fun posDec(pos: Int) : Int {
        if (pos - 1 < 0) return cardList.size - 1
        return pos-1
    }

    private fun posInc(pos : Int): Int {
        if (pos+1 > cardList.size-1) return 0
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

    private fun sThresh(dX:Float, dY:Float): Boolean{
        if (dX * dY > 5000) return true
        return false
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
        editCrd.x = (editDeck.width/2 - editCrd.width/2).toFloat()
        editCrd.y = (editDeck.height/2 - editCrd.height/2-100).toFloat()
        editCrd.rotation = 0F
    }
    private fun snapCard(){
        //TODO Change multiple apply to a spring force
        if (swiped) return

        editCrd.let { crd ->
            SpringAnimation(crd,DynamicAnimation.ROTATION, 0F).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
           SpringAnimation(crd,DynamicAnimation.X, (editDeck.width/2 - editCrd.width/2).toFloat()).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
            //TODO("Think its not centering because its using sp instead of dp, vica versa)
           SpringAnimation(crd,DynamicAnimation.Y, (editDeck.height/2 - editCrd.height/2 - 80).toFloat()).apply{
                spring.dampingRatio = DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = STIFFNESS_LOW
                start()
            }
        }
    }

    private fun addCard(c :CardItem){
        GlobalScope.launch{
            cardList.add(c)
            cardDao.addCard(c)
        }
    }

    private fun lastCard(): Boolean{
        if(cardList.size == 1) {
            delBtn.visibility = View.INVISIBLE
            return true
        }
        return false
    }

    private fun newCard(){
        //Display fresh card
        //Set Main Card
        cardText.setText("")
        editType.setImageResource(0)
        editMode(View.VISIBLE,true)
        var c = CardItem(null,0,"",1)
        cardList.add(c)
        pos = cardList.size - 1
    }

    private fun nextCard(pos : Int){
        if (lastCard()) return

        //Set Main Card
        cardText.setText(cardList[pos].challenge)
        editType.setImageResource(getTypeImage(cardList[pos].cardType))

        //Set Child card
        childText.setText(cardList[posInc(pos)].challenge)
        childType.setImageResource(getTypeImage(cardList[pos+1].cardType))
    }

    private fun getTypeImage(x: Int): Int {
        when(x){
            0 -> return 0
            1 -> return R.drawable.powerup_title
            2 -> return R.drawable.law_title
            3 -> return R.drawable.handicap_title
        }
        return 0
    }
}