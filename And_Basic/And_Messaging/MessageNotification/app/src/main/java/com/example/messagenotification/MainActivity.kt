// Android9.0 이상에서만 작동하는 다자간의 메시지 내용을 표시하는 Notification
package com.example.messagenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                val build = getNotificationBuilder("message", "messageStyle")
                build.setContentTitle("message 1")
                build.setContentText("message Style Text")
                build.setSmallIcon(android.R.drawable.ic_menu_delete)

                // MessageNotification 은 다자간의 대화 표시 - 2명 이상의 사용자 필요
                val playerbuilder1 = Person.Builder()
                val icon1 = IconCompat.createWithResource(this, android.R.drawable.ic_media_next)
                playerbuilder1.setIcon(icon1)
                playerbuilder1.setName("Sana")
                val player1 = playerbuilder1.build() // 사용자 생성

                val playerbuilder2 = Person.Builder()
                val icon2 = IconCompat.createWithResource(this, R.mipmap.ic_launcher)
                playerbuilder2.setIcon(icon2)
                playerbuilder2.setName("Momo")
                val player2 = playerbuilder2.build()

                // MessageNotification 객체 생성
                val messageStyle = NotificationCompat.MessagingStyle(player1)
                // (보낼 메시지, 보낸 시간, 보낸 사람)
                messageStyle.addMessage("Hi", System.currentTimeMillis()-60000, player1)
                messageStyle.addMessage("Oh! Hi~", System.currentTimeMillis()-20000, player2)
                messageStyle.addMessage("What the!", System.currentTimeMillis(), player2)

                build.setStyle(messageStyle)

                val notification = build.build()
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(10, notification)
            }
        }

    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.enableVibration(true)
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