// Spinner mode : dialog, dropdown(기본)
package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("1번","2번","3번","4번","5번","6번")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adapter 생성. 접혔을 때의 모습을 구성할 Layout을 설정
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
        // 펼쳤을 때의 모습을 구성할 Layout을 설정
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter1

        button.setOnClickListener {
            textView.text = "선택 : ${spinner.selectedItemPosition+1}번 항목"
        }
        // 메소드가 아닌 프로퍼티로 리스너를 구성
        spinner.onItemSelectedListener = lis1
    }

    val lis1 = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(parent?.id){
                R.id.spinner -> {
                    textView.text = "${position+1}번 항목 셀렉트"
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
}