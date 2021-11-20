/*  ContentProvider : 안드로이드 4대 구성요소중 하나
    애플리케이션에 저장한 데이터를 다른 애플리케이션이 사용할 수 있도록 OS에 요청하면 제공해주는 개념

 */
package com.example.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            val helper = DBHelper(this)
            val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val arg = arrayOf("문자열 2", "1.45", now)

            val sql =   """ insert into testTable(textData, floatData, dateData)
                            values(?,?,?)
                        """.trimIndent()

            helper.writableDatabase.execSQL(sql, arg)
            helper.writableDatabase.close()

            textView.text = "insert Complete"
        }

        button2.setOnClickListener {
            val helper = DBHelper(this)
            val sql = "select * from testTable"

            val c1 = helper.writableDatabase.rawQuery(sql, null)

            textView.text = ""
            while (c1.moveToNext()){
                val ind1 = c1.getColumnIndex("idx")
                val ind2 = c1.getColumnIndex("textData")
                val ind3 = c1.getColumnIndex("intData")
                val ind4 = c1.getColumnIndex("floatData")
                val ind5 = c1.getColumnIndex("dateData")

                textView.append("idx : ${c1.getInt(ind1)}")
                textView.append("\ntext : ${c1.getString(ind2)}")
                textView.append("\nint : ${c1.getInt(ind3)}")
                textView.append("\nfloat : ${c1.getFloat(ind4)}")
                textView.append("\ndate : ${c1.getString(ind5)}")

                if(textView.text!="") textView.append("\n\n")
            }
            helper.writableDatabase.close()
        }
    }
}