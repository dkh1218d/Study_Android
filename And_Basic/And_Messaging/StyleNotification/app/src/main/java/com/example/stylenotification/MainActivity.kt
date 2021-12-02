// BigPicture Style : 메시지 펼쳤을 시 이미지 표시
// BigText Style : 메시지를 펼쳤을 시 장문의 문자열
// InBox Style : 메시지를 펼쳤을 시 문자열
package com.example.stylenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
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
            val build = getNotificationBuilder("Style", "StyleNotification")
            build.setContentTitle("BigPicture")
            build.setContentText("BPN")
            build.setSmallIcon(android.R.drawable.ic_menu_search)

            // BigPicture 객체 생성
            val big = NotificationCompat.BigPictureStyle(build)
            // 보여줄 이미지 설정 (내부 이미지 사용)
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            big.bigPicture(bitmap)
            big.setBigContentTitle("Big Content Title")
            big.setSummaryText("Summary Text")

            val notification = build.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        button2.setOnClickListener {
            val build = getNotificationBuilder("Style", "StyleNotification")
            build.setContentTitle("BigText")
            build.setContentText("BPN")
            build.setSmallIcon(android.R.drawable.ic_menu_search)

            // BigText 객체 생성
            val big = NotificationCompat.BigTextStyle(build)
            big.bigText("가나다라마바사 아자차카타파하 하나둘셋넷다섯여섯일곱여덟가나다라마바사 아자차카타파하 하나둘셋넷다섯여섯일곱여덟\n가나다라마바사 아자차카타파하 하나둘셋넷다섯여섯일곱여덟가나다라마바사 아자차카타파하 하나둘셋넷다섯여섯일곱여덟") // 장문의 문자열
            big.setSummaryText("BPT")
            big.setBigContentTitle("BigText")

            val notification = build.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(11, notification)
        }

        button3.setOnClickListener {
            val build = getNotificationBuilder("Style", "StyleNotification")
            build.setContentTitle("InBox")
            build.setContentText("BPN")
            build.setSmallIcon(android.R.drawable.ic_menu_search)

            // InBox 객체 생성
            val box = NotificationCompat.InboxStyle(build)
            box.addLine("1번줄~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            box.addLine("2번줄~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            box.addLine("3번줄~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            box.addLine("4번줄~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            box.addLine("5번줄~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            box.setBigContentTitle("InBox")
            box.setSummaryText("BPI")


            val notification = build.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(12, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("Style", "StyleNotification", NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            manager.createNotificationChannel(channel)
            val build = NotificationCompat.Builder(this, id)
            return build
        }else{
            val build = NotificationCompat.Builder(this, id)
            return build
        }
    }
}