package com.cosc345.kickons

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce.*
import com.cosc345.kickons.databinding.ActivityDeckEditBinding
import kotlinx.coroutines.*

class EditDeck(): AppCompatActivity(){
    var isEdit = false
    private var swiped = false
    private var pos = 0
    private var canCreate = true

    private lateinit var db: CardDB
    private lateinit var cardDao: CardDAO
    private lateinit var gestureDetector: GestureDetector

    //Card View Elements
    private lateinit var childText: EditText
    private lateinit var cardText: EditText
    private lateinit var editCrd: CardView
    private lateinit var childCrd: CardView
    private lateinit var editType: ImageView
    private lateinit var childType: ImageView
    private lateinit var cardPos: TextView
    private lateinit var childPos: TextView


    //Buttons
    private lateinit var delBtn: ImageView
    private lateinit var svBtn: ImageView
    private lateinit var editButton: ImageView
    private lateinit var addBtn: ImageView
    private lateinit var btnPrev: ImageView
    private lateinit var btnNext: ImageView

    //Views
    private lateinit var editDeck: View
    private lateinit var view: View


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)
        db = CardDB.getDatabase(this)
        cardDao = db.cardDAO()

        //Intent
        val deckId = intent.getIntExtra("id",0)

        //Position of card in deck
        val binding = ActivityDeckEditBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(R.layout.activity_deck_edit)

        //Elements
        childCrd = findViewById(R.id.childCrd)
        editCrd = findViewById(R.id.editCrd)
        editDeck = findViewById(R.id.edit_deck)

        editType = findViewById(R.id.editType)
        childType = findViewById(R.id.childType)

        cardPos = findViewById(R.id.cardPos)
        childPos = findViewById(R.id.childPos)

        //need to pass in deck_id

        //Buttons
        //val btn_next = findViewById<Button>(R.id.btn_next)
       // val btn_prev = findViewById<Button>(R.id.btn_prev)
        val btnDone = findViewById<Button>(R.id.btnDone)
        val editType = findViewById<ImageView>(R.id.editType)

        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        delBtn = findViewById(R.id.delBtn)
        svBtn = findViewById(R.id.svBtn)
        editButton = findViewById(R.id.edtBtn)
        addBtn = findViewById(R.id.addBtn)

        delBtn.visibility = View.INVISIBLE
        svBtn.visibility = View.INVISIBLE




        //var
        var t = false
        var tPos = 0
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
                if(cardList.size < 2) return true
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

        //Done button
        btnDone.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            pos = posInc(pos)
            nextCard()
        }

        btnPrev.setOnClickListener{
            pos = posDec(pos)
            nextCard()
        }

        editType.setOnClickListener {
            if(isEdit) changeType()
        }
        delBtn.setOnClickListener{
            deleteCard()
            pos = posDec(pos)
            nextCard()
            canCreate = true
        }

        svBtn.setOnClickListener{
            val text = cardText.text.toString()
            cardList[pos].challenge = text
            addCard(cardList[pos])
            nextCard()
            editMode(false)
            canCreate = true
        }

        editButton.setOnClickListener{
            t = toggleEdit(t)
        }

        addBtn.setOnClickListener{
            //Fresh Card
            newCard(deckId)
            pos = cardList.size -1
        }
        if (cardList.size == 0) newCard(deckId);
        nextCard()
        updateCardPos()
        t = toggleEdit(t)
    }

    private fun toggleEdit(t: Boolean): Boolean{
        editMode(t)
        return !t
    }
    /**
     * Sets weather the cards can be edited
     *
     * @param edit bool value to deicde if the cards can be edited
     */
    private fun editMode(edit: Boolean){
        isEdit = edit
        showDirButtons(edit)
        showEditButtons(edit)
        focusText(edit)
        swiped = edit
        lastCard()
    }

    /**
     * Onclick function that removes a player view item from the main view.
     *
     * @param deck_id (Int?) of the deck
     * @return The list of card items contained inside the deck
     * i.e card items with matching deck_ids
     *
     * @see com.cosc345.kickons.CardItem
     */
    private fun changeType(){
        val type = cardList[pos].cardType
        cardList[pos].cardType = tPosInc(type)
        nextCard()
    }

    private fun tPosInc(tPos: Int): Int{
        if (tPos + 1 >= 4) return 0
        return tPos + 1
    }

    private fun focusText(focus : Boolean){
        cardText.isFocusableInTouchMode = focus
        cardText.isFocusable = focus
    }

    private fun showEditButtons(show: Boolean){
        val vis = if(show) View.VISIBLE
        else View.INVISIBLE
        svBtn.visibility = vis
        delBtn.visibility = vis

    }

    private fun showDirButtons(show: Boolean){
        val vis = if(!show) View.VISIBLE
        else View.INVISIBLE
        btnPrev.visibility = vis
        btnNext.visibility = vis
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

    private fun replaceCard(){
        //Set Swiped to false
        //SnapCard Back into place//
        editCrd.visibility = View.GONE
        resetCard()
        editCrd.visibility = View.VISIBLE
        pos = posInc(pos)
        nextCard()
        swiped = false
        snapCard()
    }

    private fun resetCard(){
        editCrd.x = childCrd.x
        editCrd.y = childCrd.y
//        editCrd.x = (editDeck.width/2 - editCrd.width/2).toFloat()
//        editCrd.y = (editDeck.height/2 - editCrd.height/2).toFloat()
        editCrd.rotation = 0F
    }
    /**
     * Returns card back into place with animation after finger is released
     * @see com.cosc345.kickons.CardItem
     */
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

    private fun updateCardPos(){
        val s = cardList.size
        cardPos.text = "${pos+1} / $s"
        childPos.text = "${posInc(pos)+1} / $s"
    }

    private fun addCard(c :CardItem){
        GlobalScope.launch{
            cardDao.addCard(c)
        }
    }

    private fun goToEnd(){
        pos = cardList.size-1
        nextCard()
    }
    private fun newCard(id: Int){
        // Check that the previous new card has been saved before
        // a new one may be created
        //TODO This is super messy find a better fix
        if (!canCreate) {
            goToEnd()
            return
        }

        canCreate = false

        cardText.setText("")
        editType.setImageResource(0)
        editMode(true)
        val c = CardItem(null,0,"",id)
        cardList.add(c)
        pos = cardList.size - 1
        updateCardPos()
        lastCard()
    }

    /**
     * Checks to see if the card is the last card then refreshes the cards data
     * aswell as updating the child card
     * @see com.cosc345.kickons.CardItem
     */
    private fun nextCard(){
        lastCard()
        //Set Main Card
        cardText.setText(cardList[pos].challenge)
        editType.setImageResource(getTypeImage(cardList[pos].cardType))
        //Set Child card
        childText.setText(cardList[posInc(pos)].challenge)
        childType.setImageResource(getTypeImage(cardList[posInc(pos)].cardType))
        updateCardPos()
    }

    private fun deleteCard(){
        val c = cardList[pos]
        cardList.remove(c)
        GlobalScope.launch{
            cardDao.delete(c)
        }
    }

    private fun lastCard(): Boolean{
        if(cardList.size == 1) {
            delBtn.visibility = View.INVISIBLE
            return true
        }
        return false
    }


    private fun getTypeImage(x: Int): Int {
        when(x){
            0 -> return R.drawable.title_standard
            1 -> return R.drawable.powerup_title
            2 -> return R.drawable.law2
            3 -> return R.drawable.handicap_title
        }
        return 0
    }
}

