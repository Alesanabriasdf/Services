package com.example.intentserviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.intentserviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mbound: Boolean = false
    private lateinit var boundService: MyBoundService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //val myIntentForIntentService = Intent(this, MyIntentService::class.java)
        //startService(myIntentForIntentService)

        /* esto es un comentario de mas de una linea
        aca seguimos
        y aca lo terminamos */

        //aca estoy instanciando el servicio My Service

        //val myIntentForService = Intent(this,MyService::class.java)
        //startService(myIntentForService)


        binding.btnGetNumber.setOnClickListener {

            val num = boundService.getRandomNumber().toString()
            if (mbound){
                Toast.makeText(this, num, Toast.LENGTH_LONG).show()
            }

        }

    }

    private val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            Log.i("MainActivity","servicio conectadoooo")

            val binder = service as MyBoundService.MyBinder
            boundService = binder.getService()
            mbound = true

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.i("MainActivity","servicio se desconecto aqui")
            mbound = false
        }
    }

    override fun onStart() {
        super.onStart()
        startMyBoundService()
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mbound=false
    }

    private fun startMyBoundService() {
        val intent= Intent(this, MyBoundService::class.java)
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }

}