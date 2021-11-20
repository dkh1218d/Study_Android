/*  HttpServer : Client의 요청이 발생하면 서버가 데이터를 보내주는 방식
    한번의 송, 수신이 끝나면 연결이 해제된다
    온라인으로 유지되기 때문에 서버의 부담이 적고 구현도 쉽다
    * 자유로운 통신이 구현하기 어렵다 ( Web Socket 방식을 통해 온라인 유지가 가능하다 )
    JSP, PHP, ASP 등을 이용

    Android 9.0 부터 Http 프로토콜로 인한 접속이 기본 차단 (Https 가 기본)
    Manifest.xml 에서 android:usesCleartextTraffic="true" 속성을 부여해 해결
 */
package com.example.httpnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            thread {
                // 접속할 주소
                val site = "http://192.168.0.8:8090/basic.jsp"
                val url = URL(site)
                // 접속
                val conn = url.openConnection() as HttpURLConnection

                val isr = InputStreamReader(conn.inputStream)
                val br = BufferedReader(isr)
                val buf = StringBuffer()
                var str:String? = null
                do{
                    str = br.readLine()
                    if(str != null)
                        buf.append("$str\n")
                }while (str!=null)
                runOnUiThread{
                    textView.text = buf.toString()
                }
            }
        }
    }
}