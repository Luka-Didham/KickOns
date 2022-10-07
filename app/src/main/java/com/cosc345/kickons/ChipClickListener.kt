package com.cosc345.kickons

import android.view.View
import com.google.android.material.chip.ChipGroup
/**
 * Interface for handling on click events for the player chips
 *
 * @see com.cosc345.kickons.AddPlayer
 */
interface ChipClickListener: View.OnClickListener {
    fun onClick(cG: ChipGroup, v: View?) {
    }
}