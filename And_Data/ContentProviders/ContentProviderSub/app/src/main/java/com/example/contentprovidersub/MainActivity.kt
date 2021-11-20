// Android11 버전부터 AndroidManifest.xml에 호출할 Content Provider의 Authorities를 명시해야 객체 호출 가능

package com.example.contentprovidersub

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Content Provider의 이름을 가지고 있는 uri 객체를 생성
        // content라는 스키마를 가진다
        val uri = Uri.parse("content://com.example.contentprovider.dbprovider")

        button.setOnClickListener {
            // ContentProvider를 이용할 수 있는 객체를 반환
            // 첫 번째 : 접속할 Provider uri
            // 두 번째 : 가져올 컬럼 목록, null이면 모두
            // 세 번째 : 조건절
            // 네 번째 : 조건절의 ?에 바인딩 될 값
            // 다섯 번째 : 정렬 기준
            val c1 = contentResolver.query(uri, null, null, null, null)
            textView.text = ""

            while (c1?.moveToNext()!!){
                val ind1 = c1?.getColumnIndex("idx")
                val ind2 = c1?.getColumnIndex("textData")
                val ind3 = c1?.getColumnIndex("intData")
                val ind4 = c1?.getColumnIndex("floatData")
                val ind5 = c1?.getColumnIndex("dateData")

                textView.append("idx : ${c1.getInt(ind1)}")
                textView.append("\ntext : ${c1.getString(ind2)}")
                textView.append("\nint : ${c1.getInt(ind3)}")
                textView.append("\nfloat : ${c1.getFloat(ind4)}")
                textView.append("\ndate : ${c1.getString(ind5)}")

                if(textView.text!="") textView.append("\n\n")
            }
        }

        button2.setOnClickListener {
            val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val cv = ContentValues()
            cv.put("textData", "문자열 10")
            cv.put("floatData", "5.999")
            cv.put("dateData", now)

            contentResolver.insert(uri, cv)
            textView.text = "insert Complete"
        }

        button3.setOnClickListener {
            val cv = ContentValues()
            cv.put("textData", "gkgkakak")
            val where = "idx = ?"
            val args = arrayOf("2")

            val cnt = contentResolver.update(uri, cv, where, args)
            textView.text = "update Success"
        }

        button4.setOnClickListener {
            val where = "idx = ? or idx = ?"
            val args = arrayOf("1", "7")
            contentResolver.delete(uri, where, args)
            textView.text = "delete!!!"
        }
    }
}