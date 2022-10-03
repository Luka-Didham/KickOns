package com.example.KickOns

import android.view.View
import com.google.android.material.chip.ChipGroup
/**
 * Interface for handling on click events for the player chips
 *
 * @see com.example.KickOns.AddPlayer
 */
interface ChipClickListener: View.OnClickListener {
    fun onClick(cG: ChipGroup, v: View?) {
    }
}