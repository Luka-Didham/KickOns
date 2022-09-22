package com.example.KickOns

import android.view.View
import com.google.android.material.chip.ChipGroup

interface ChipClickListener: View.OnClickListener {
    fun onClick(cG: ChipGroup, v: View?) {
    }
}