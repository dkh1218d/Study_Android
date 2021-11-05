package com.example.check_radio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 체크박스 버튼
        h_button.setOnClickListener {
            h_text.text = ""
            if(hobit1.isChecked) h_text.append("1번취미 ")
            if(hobit2.isChecked) h_text.append("2번취미 ")
            if(hobit3.isChecked) h_text.append("3번취미 ")
            h_text.append("를 선택하셨습니다")
            if(hobit1.isChecked == false && hobit2.isChecked == false && hobit3.isChecked == false)
                h_text.text = "취미를 선택해 주세요"
        }
        /*
        // 체크박스 직접 연결
        hobit1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) h_text.text = "1번 버튼"
        } // 고차함수
        hobit2.setOnCheckedChangeListener(listener1)
        hobit3.setOnCheckedChangeListener(listener1) // 중첩 클래스
        */

        // 라디오 버튼
        g_button.setOnClickListener {
            when(g_group.checkedRadioButtonId){
                R.id.gender1 -> h_text.text = "남성"
                R.id.gender2 -> h_text.text = "여성"
                else -> h_text.text = "성별을 선택해 주세요"
            }
        }

        i_button.setOnClickListener {
            if(i_group.`in`.isChecked) h_text.text = "내국인입니다"
            else if(i_group.out.isChecked) h_text.text = "외국인입니다"
            else h_text.text = "선택해주세요"
        }

        // 스위치
        switch1.setOnClickListener {
            if(switch1.isChecked) switch_text.text="on"
            else switch_text.text = "off"
        }
    }
    
    val listener1 = object : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            when(buttonView?.id) {// buttonView가 매개변수
                R.id.hobit2 -> {
                    if(isChecked) h_text.text = "2번 버튼"
                }
                R.id.hobit3 -> {
                    if(isChecked) h_text.text = "3번 버튼"
                }
            }
        }
        
    }
}