package com.example.educate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TextView
        /* 자바 view id 호출
        val text_plate = findViewById<TextView>(R.id.text_plate)
        text_plate.text = "텍스트 플레이트 입니다"*/

        text_plate.text = ""
        text_plate.append("이름을 적어 주세요")

        // button Listener

        button1.setOnClickListener(button_listener)
        button2.setOnClickListener(button_listener)

        //엔터 처리
        name_plate.setOnEditorActionListener { v, actionId, event ->
            text_plate.text = "엔터를 이용하지 말아 주세요"
            false  // false : 키보드 내려감 true : 키보드 남음
        }
    }

    // Button Listener
    val button_listener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.button1 -> {
                    text_plate.text = name_plate.text

                    // 에딧텍스트 뷰 포커스 제거
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(name_plate.windowToken, 0)
                    name_plate.clearFocus()
                }
                R.id.button2 -> {
                    text_plate.text = ""
                    name_plate.getText().clear()
                }
            }
        }
    }

}