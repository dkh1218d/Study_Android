/*  Class를 이용한 SQLite
    ContentValue : DB에 값을 저장 할 때 이름을 부여하는 Class
    값을 저장할 때 사용되는 이름은 Table의 Column 이름과 매칭된다 - insert, update등에 사용

 */
package com.example.sqlitedb2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // insert
        button.setOnClickListener {
            val helper = DBHelper(this)

            // Column에 저장될 데이터를 관리하는 객체
            val cv1 = ContentValues()
            cv1.put("name", "철수")

            // insert 메서드로 입력 / table : 입력할 테이블 , nullColumnHack : null 허용일경우 값이 없으면 null, ContentValues
            helper.writableDatabase.insert("TestTable", null, cv1)
            helper.writableDatabase.close()
            textView.text = "Insert Success"
        }
        // select
        button2.setOnClickListener {
            val helper = DBHelper(this)

            // 첫 번째 : 가져올 데이터가 있는 테이블 이름
            // 두 번째 : 가져올 칼럼의 이름이 담겨져 있는 문자열 배열, null일 경우 모든 컬럼
            // 세 번째 : 조건절 (idx = ? and name = ?), 조건절이 필요가 없으면 null
            // 네 번째 : 조건절 ?에 바인딩 될 값의 배열, 세 번째가 null이면 함께 null
            // 다섯 번째 : Group By 기준 컬럼
            // 여섯 번째 : Having 절에 들어갈 조건문
            // 일곱 번째 : Order By(정렬 기준)
            val c1 = helper.writableDatabase.query("TestTable", null, null,
                                            null, null, null, null)

            textView.text = ""
            while (c1.moveToNext()){
                val index1 = c1.getColumnIndex("idx")
                val index2 = c1.getColumnIndex("name")
                val index3 = c1.getColumnIndex("num")

                val idx = c1.getInt(index1)
                val name = c1.getString(index2)
                val num = c1.getInt(index3)

                textView.append("idx : ${idx}")
                textView.append("\nname : ${name}")
                textView.append("\nnum : ${num}")
                if(textView.text != "") textView.append("\n\n")
            }
            helper.writableDatabase.close()
        }
        // update
        button3.setOnClickListener {
            val helper = DBHelper(this)
            val cv = ContentValues()
            cv.put("name", "민수")
            // 조건절
            val where = "idx = ?"
            val args = arrayOf("4")

            // Table name, ContentValues, Where, Value
            helper.writableDatabase.update("TestTable", cv, where, args)
            helper.writableDatabase.close()
            textView.text = "Update Success"
        }
        // delete
        button4.setOnClickListener {
            val helper = DBHelper(this)
            val where = "idx = ?"
            val args = arrayOf("5")

            // Table name, Where, Value
            helper.writableDatabase.delete("TestTable", where, args)
            helper.writableDatabase.close()
            textView.text = "Delete Success"
        }
    }
}