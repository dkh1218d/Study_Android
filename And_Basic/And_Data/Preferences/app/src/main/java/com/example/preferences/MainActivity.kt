/*  Preferences : 안드로이드의 저장방식 중 하나
    대량의 데이터 저장 - SQLite / 소규모 데이터 저장 - Preferences
    애플리케이션의 설정 데이터 같은 유일한 정보를 저장할 때 사용
 */
package com.example.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // Preferences 객체 추출
            // MODE_APPEND : 기존의 데이터를 유지하고 새로운 데이터는 추가, 이름이 같으면 덮어씌운다
            // MODE_PRIVATE : 기존의 데이터를 전부 제거하고 새로운 데이터를 저장한다
            val pref = getSharedPreferences("data", Context.MODE_PRIVATE)

            // 데이터 저장을 위한 객체 추출
            val edit = pref.edit()
            edit.putBoolean("BoolD", true)
            edit.putFloat("FloD", 10.5f)
            edit.putInt("IntD", 100)
            edit.putString("StrD", "문자")

            // 문자열을 담은 Set을 저장 가능하다
            val set = hashSetOf<String>()
            set.add("철수")
            set.add("영희")
            set.add("민수")
            set.add("영희")
            set.add("진희")
            edit.putStringSet("SetD", set)

            edit.commit()
            textView.text = "Save Compleite"
        }

        button2.setOnClickListener {
            textView.text = ""
            val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
            // 해당하는 key의 값이 없으면 default 값이 반환
            textView.append("bool : ${pref.getBoolean("BoolD", false)}")
            textView.append("\nflo : ${pref.getFloat("FloD", 0.0f)}")
            textView.append("\nInt : ${pref.getInt("IntD", 0)}")
            textView.append("\nStr : ${pref.getString("StrD", "초기값")}\n")
            val set = pref.getStringSet("SetD", null)
            for(i in set!!){
                textView.append("\nSet : ${i}")
            }
        }
    }
}
