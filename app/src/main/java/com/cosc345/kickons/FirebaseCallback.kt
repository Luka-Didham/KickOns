package com.cosc345.kickons

/**
 * Method which handles callbacks from firebase
 */
interface FirebaseCallback {
    fun onResponse(response: Array<MutableList<out Any>>)
}
