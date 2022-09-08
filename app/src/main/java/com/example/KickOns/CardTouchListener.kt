package com.example.KickOns

import android.view.MotionEvent
import android.view.View


interface CardTouchListener : View.OnTouchListener{

    override fun onTouch(v: View?, me: MotionEvent?): Boolean {
        v?.performClick()
        return true
    }
}