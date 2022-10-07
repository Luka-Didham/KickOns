package com.cosc345.kickons

import android.content.Context
import android.view.GestureDetector

abstract class CardSwipeGesture(context: Context): GestureDetector.SimpleOnGestureListener() {

    var difX: Float = 0.0f
    var difY: Float = 0.0f


    fun drawChild() {

    }
}