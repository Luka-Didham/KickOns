package com.example.KickOns


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable

import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeGesture(val context: Context): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private val background = ColorDrawable()
    private val backgroundColor = Color.parseColor("#cf142b")


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    //TODO("Make the swipe feel smoother")
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue/2
    }
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.6f
    }
    //TODO("Highlight Selected item")
    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val delBorder = ImageView(context)
        delBorder.bringToFront()
        delBorder.setImageResource(R.drawable.del_border)
        val delBorderDrw = delBorder.drawable

        //Card Sized
        //delBorderDrw.setBounds(0,itemView.top,-dX.toInt()/4, itemView.bottom)

        //Card Sized Smaller
        delBorderDrw.setBounds(0,itemView.top+25,-dX.toInt()/6, itemView.bottom - 25)

        //Full Sized
        //delBorderDrw.setBounds(0,0,-dX.toInt()/4, c.height)

        delBorderDrw.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}