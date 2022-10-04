package com.example.KickOns

import com.google.android.gms.common.api.Response

interface FirebaseCallback {
    fun onResponse(response: Array<MutableList<out Any>>)

//    fun cardResponse(response: MutableList<CardItem>)
}