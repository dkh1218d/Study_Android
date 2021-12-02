// Notification : 화면을 가지지 않는 실행단위의 메시지 표시(상단 메시지)
// 사용자가 메시지를 확인하거나 제거하기 전까지 유지 - 메시지 터치시 Activity 실행
package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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

        button.setOnClickListener {
            // 채널 구분 id
            val builder1 = getNotificationBuilder("chennel1", "FirstChennel")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            // 어플 내부뿐이 아닌 네트워크, 파일 등의 아이콘을 호출하는 경우로 bitmap을 사용
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            builder1.setNumber(100)
            builder1.setContentTitle("First Title")
            builder1.setContentText("First Text")

            // 메시지 객체 생성
            val notification = builder1.build()
            // 알림 메시지 관리 객체 생성
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // 각 메시지를 구분하는 id 설정
            manager.notify(10, notification)
        }

        button2.setOnClickListener {
            val builder1 = getNotificationBuilder("chennel1", "FirstChennel")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            builder1.setNumber(100)
            builder1.setContentTitle("Second Title")
            builder1.setContentText("Second Text")

            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(11, notification)
        }

        // Notification 채널 구분
        button3.setOnClickListener {
            val builder1 = getNotificationBuilder("chennel2", "SecondChennel")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            builder1.setNumber(100)
            builder1.setContentTitle("Third Title")
            builder1.setContentText("Third Text")

            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }

        button4.setOnClickListener {
            val builder1 = getNotificationBuilder("chennel2", "SecondChennel")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            builder1.setNumber(100)
            builder1.setContentTitle("Fourth Title")
            builder1.setContentText("Fourth Text")

            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(40, notification)
        }
    }
    // Android 8.0 이후부터는 Notification Channel로 구분을 해 활성화여부 설정 - 채널설정 필요
    // Android8.0 이상과 미만 버전에 대응하기 위한 채널 설정
    // 매개변수 (id = 각 채널을 관리하기 위한 id, name = 사용자에게 보여줄 채널의 이름)
    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder{

        // os 버전별로 분기 8.0 = O
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            // 알림 메시지를 관리하는 객체를 추출
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // 채널 객체 생성 (중요도 설정 : IMPORTANCE_HIGH)
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true) // LED세팅(메시지 출력시 단말기 LED사용 여부)
            channel.lightColor = Color.BLUE // LED색상 설정
            channel.enableVibration(true) // 진동 사용 여부

            // 알림 메시지를 관리하는 객체에 채널 등록
            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, id)
            return builder

        } else{
            // Android 8.0 이전까지는 애플리케이션 별로 메시지 활성, 비활성을 구분
            val builder = NotificationCompat.Builder(this)
            return builder
        }
    }
}