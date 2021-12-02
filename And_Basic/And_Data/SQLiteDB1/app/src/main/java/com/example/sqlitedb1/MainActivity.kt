/*  SQLite : Android에서 사용하는 내장 DataBase. 표준 SQL문을 사용하는 관계형DB
    MySQL과 유사한 문법을 사용하고 있으며 일반적인 관계형 DB가 갖고있는 기능을 갖고 있다
    임베디드형 DB로 사용하는 Application에 셋팅되는 DB이다
    Android OS에 내장되어 있어 개발자가 만드는 Application은 Android OS에 쿼리문을 전달하고
    Android OS가 직접 DB에 대한 처리를 한다 -> Locale DB (타 단말기에서 접근X)
    - 타 단말기에서 접근하기 위해서는 Network를 통해야 함

    Android에서 SQLite사용은 Query문 방법, 클래스 방법 2가지가 있다
    Query : 일반적인 SQL문법 = MySQL과 유사
    Class : 개발자가 정해줘야 하는 몇 가지 정보(Table 이름, Column 이름)을 제공하면 Query문이 생성, 실행

    SQLite OpenHelper 를 상속받은 Class를 만들어야 한다
    - DB의 이름 설정
    - DB파일 실행 시 파일이 없으면 생성 후 onCreate 메서드 호출. 이 메서드에서 테이블을 만드는 쿼리 실행(최초)
    - Application 서비스 중 Table구조를 변경하려면 DB버전 변경하면 된다(버전 부여 가능). 변경 시 onUpgrade 메서드 호출.
      이 메서드에서 새로운 구조로 변경하는 작업 수행(기존 사용자 Upgrade)
 */

// Query 이용 방식
package com.example.sqlitedb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // insert
        button.setOnClickListener {
            val helper = DBHelper(this)
            val query = """
                            insert into TestTable(intData, floatData, dateData)
                            values (?, ?, ?)                
                        """.trimIndent()


            val now = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())    // 현재 Date
            // ? 에 binding 될 값 column의 타입에 관계 없이 문자열로 작성
            val args = arrayOf("10", "9.87", now)
            val args2 = arrayOf("20", "1.23", now)

            // commit
            helper.writableDatabase.execSQL(query, args)
            helper.writableDatabase.execSQL(query, args2)

            helper.writableDatabase.close()
            textView.text = "Commit Complete"
        }
        // select
        button2.setOnClickListener {
            val helper = DBHelper(this)
            val query = "select * from TestTable"

            val c1 = helper.writableDatabase.rawQuery(query, null)
            textView2.text = ""
            // 다음 raw로 이동(가져올 데이터가 있을 시) true 반환
            while (c1.moveToNext()){
                // column의 이름으로 데이터 파악할 수 없다 -> index 추출 필요
                val idx1 = c1.getColumnIndex("idx")
                val idx2 = c1.getColumnIndex("textData")
                val idx3 = c1.getColumnIndex("intData")
                val idx4 = c1.getColumnIndex("floatData")
                val idx5 = c1.getColumnIndex("dateData")

                // 데이터 추출
                val idx = c1.getInt(idx1)
                val text = c1.getString(idx2)
                val inte = c1.getInt(idx3)
                val fl = c1.getFloat(idx4)
                val date = c1.getString(idx5)
                textView2.append("idx : ${idx}")
                textView2.append("\ntext : ${text}")
                textView2.append("\nint : ${inte}")
                textView2.append("\nfloat : ${fl}")
                textView2.append("\ndate : ${date}")
                if(textView2.text!="")
                    textView2.append("\n\n")
            }
            helper.writableDatabase.close()
        }
        // update
        button3.setOnClickListener {
            val helper = DBHelper(this)
            val query = "update TestTable set textData=? where idx=?"

            val args = arrayOf("문자열 2", "2")
            helper.writableDatabase.execSQL(query, args)
            helper.writableDatabase.close()
            textView.text = "Update Success"
        }
        // delete
        button4.setOnClickListener {
            val helper = DBHelper(this)
            val query = "delete from TestTable where idx=?"
            val args = arrayOf("7")
            helper.writableDatabase.execSQL(query, args)
            helper.writableDatabase.close()
            textView.text = "Delete Success"
        }
    }
}