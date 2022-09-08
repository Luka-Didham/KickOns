package com.example.KickOns

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class CardSwipeGesture(context: Context): GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d("M Event", e1?.x.toString())
        return true
    }

    fun drawChild() {

    }
}