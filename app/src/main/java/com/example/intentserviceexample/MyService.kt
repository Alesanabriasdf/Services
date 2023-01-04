package com.example.intentserviceexample

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i("MyService","servicio iniciado")

        Counter().execute()

        return START_NOT_STICKY
    }

    inner class Counter: AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg p0: Unit?) {
            for (i in 0.. 500){
                Log.i("numero",i.toString())
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Log.d("service","terminado")
            stopSelf()
        }

    }
}