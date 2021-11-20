package com.example.httpjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
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
            textView.text = ""
            thread {
                val url = URL("http://192.168.0.8:8090/json.jsp")
                val conn = url.openConnection() as HttpURLConnection

                val isr = InputStreamReader(conn.inputStream, "UTF-8")
                val buf = BufferedReader(isr)

                var str:String? = null
                val sbf = StringBuffer()
                do {
                    str = buf.readLine()
                    if(str!=null){
                        sbf.append("$str")
                    }
                }while (str != null)

                val data = sbf.toString()

                // JSON 데이터 분석
                val root = JSONArray(data) // JSON 배열 생성
                for(i in 0 until root.length()){ // 객체 만큼 반복
                    val obj = root.getJSONObject(i)
                    val data1 = obj.getString("data1")
                    val data2 = obj.getInt("data2")
                    val data3 = obj.getDouble("data3")

                    runOnUiThread {
                        textView.append("data1 : $data1\n")
                        textView.append("data2 : $data2\n")
                        textView.append("data3 : $data3\n\n")
                    }
                }
            }
        }
    }
}