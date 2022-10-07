package com.cosc345.kickons

interface FirebaseCallback {
    fun onResponse(response: Array<MutableList<out Any>>)
}