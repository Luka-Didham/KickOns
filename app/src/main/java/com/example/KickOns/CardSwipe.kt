package com.example.KickOns


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable

import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Class for handling swipe gestures for DeckPicker cards
 *
 * @param context The apps current context
 *
 * @see com.example.KickOns.Player
 * @see com.example.KickOns.AddPlayerAdapter
 * @see com.example.KickOns.PlayerViewHolder
 */
abstract class CardSwipe(context: Context): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
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
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}