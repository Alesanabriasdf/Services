package com.example.intentserviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("MyIntentService") {

    override fun onHandleIntent(p0: Intent?) {
        Log.d("Servicio Iniciado", "se inicio el servicio ")
        for (i in 0.. 500000){
            Log.d("Servicio corriendo", "num $i")
        }

        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Servicio Terminado", "Se finalizo el servicio")
    }
}