// Pending Intent : Notification을 터치해서 Activity를 실행하고 데이터를 전송하는 것
package com.example.pendingintent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Notification 객체
        button.setOnClickListener {
            val builder = getNotificationBuilder("pending", "First")
            builder.setContentTitle("First Noti")
            builder.setContentText("첫 번쨰")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)

            // 메시지를 터치하면 자동 제거
            builder.setAutoCancel(true)
            // 메시지를 터치하면 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, SecondActivity::class.java)
            // 데이터 전송
            intent1.putExtra("data1", "First Data")
            intent1.putExtra("data2", 100)

            // PendingIntent.FLAG_UPDATE_CURRENT -> Activity 실행 목적이 어떠한 정보를 갱신해서 보여주는 의미
            val pending = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pending)

            // Action : Android 4.0 이상부터 Notification에 다수의 실행 버튼 생성 가능
            val intent2 = Intent(this, FourthActivity::class.java)
            val pending2 = PendingIntent.getActivity(this, 20, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder2 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_compass, "Action1", pending2)
            val action = builder2.build()
            builder.addAction(action)

            val intent3 = Intent(this, FifthActivity::class.java)
            val pending3 = PendingIntent.getActivity(this, 30, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder3 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_agenda, "Action2", pending3)
            val action2 = builder3.build()
            builder.addAction(action2)


            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        button2.setOnClickListener {
            val builder = getNotificationBuilder("pending1", "First")
            builder.setContentTitle("Second Noti")
            builder.setContentText("두 번쨰")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)

            builder.setAutoCancel(true)
            val intent1 = Intent(this, ThirdActivity::class.java)
            val pending = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pending)

            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String): NotificationCompat.Builder{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            manager.createNotificationChannel(channel)
            val build = NotificationCompat.Builder(this, id)
            return build

        }else{
            val build = NotificationCompat.Builder(this)
            return build
        }

    }
}