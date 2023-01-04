package com.example.intentserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class MyBoundService: Service() {


    private val binder = MyBinder()

    private val numberGenerator = java.util.Random()


    fun getRandomNumber() = numberGenerator.nextInt(1000)


    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    inner class MyBinder():Binder(){
        fun getService(): MyBoundService = this@MyBoundService

    }


}