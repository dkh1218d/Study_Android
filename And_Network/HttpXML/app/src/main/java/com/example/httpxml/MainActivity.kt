package com.example.httpxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Element
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = ""
            thread {
                val url = URL("http://192.168.0.8:8090/xml.jsp")
                val conn = url.openConnection() as HttpURLConnection

                // DOM 방식으로 xml 문서를 분석할 수 있는 객체 생성
                val fac = DocumentBuilderFactory.newInstance()
                val builder = fac.newDocumentBuilder()
                val doc = builder.parse(conn.inputStream)

                val root = doc.documentElement // 최상위 태그 추출
                val itemlist = root.getElementsByTagName("item") // 하위 item 태그 추출

                for(i in 0 until itemlist.length){
                    val itemtag = itemlist.item(i) as Element // Element를 dom으로 Import
                    // 내부 태그 추출 ( 데이터가 1개 뿐이어도 list로 받아온다 )
                    val data1_list = itemtag.getElementsByTagName("data1")
                    val data2_list = itemtag.getElementsByTagName("data2")
                    val data3_list = itemtag.getElementsByTagName("data3")

                    val data1 = data1_list.item(0) as Element
                    val data2 = data2_list.item(0) as Element
                    val data3 = data3_list.item(0) as Element
                    // 가져온 아이템의 문자열 데이터 추출
                    val d1 = data1.textContent
                    val d2 = data2.textContent.toInt()
                    val d3 = data3.textContent.toDouble()

                    runOnUiThread {
                        textView.append("1번 : $d1\n")
                        textView.append("2번 : $d2\n")
                        textView.append("3번 : $d3\n\n")
                    }
                }
            }
        }
    }
}