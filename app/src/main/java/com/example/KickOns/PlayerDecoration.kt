package com.example.KickOns

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PlayerDecoration(private val space:Int): RecyclerView.ItemDecoration() {

@Override
fun getItemOffset(outRect: Rect,
                  view: View,
                  parent: RecyclerView,
                  state: RecyclerView.State) {
            outRect.right = space
}

}