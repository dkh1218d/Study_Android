package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyService : Service() {
    var isrunning = false

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // Service가 가동될 때 호출되는 메서드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Notification 객체 생성
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("service", "service", NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED

            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, "service")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setContentTitle("Service ~~")
            builder.setContentText("Play Service . . .")
            val notification = builder.build()

            // Notification 메시지를 Foreground Service를 위해 표시 / 서비스 중 표시, 서비스 종료시 제거
            startForeground(10, notification)
        }

        Log.d("test", "Play Service")
        isrunning = true
        thread {
            while (isrunning){
                SystemClock.sleep(1000)
                Log.d("test", System.currentTimeMillis().toString())
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    // Service가 중지, 종료될 때 호출
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "Service Exit")
        isrunning = false
    }
}