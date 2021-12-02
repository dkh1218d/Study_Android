package com.example.ipc

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyService : Service() {
    var load = 0
    val binder = LocalBinder()

    // 외부에서 Service에 접속하면 호출되는 메서드
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("service", "service", NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor
            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, "service")
            builder.setContentTitle("Service ~~")
            builder.setContentText("Play Service")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            val notification = builder.build()
            startForeground(10, notification)
        }
        load = 0
        thread {
            while (load<100){
                SystemClock.sleep(500)
                load++
                Log.d("test", load.toString())
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    // 변수의 값을 반환하는 메서드
    fun getNum() : Int{
        return load
    }

    //  접속하는 Activity에서 서비스를 추출하는 객체
    inner class LocalBinder : Binder(){
        fun getService() : MyService{
            return this@MyService
        }
    }
}