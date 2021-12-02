package com.example.cardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // ListView
    val data1 = arrayOf(
        "1번", "2번", "3번", "4번", "5번",
        "6번", "7번", "8번", "9번", "10번",
        "11번", "12번", "13번", "14번", "15번",
        "16번", "17번", "18번", "19번", "20번"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adapter 지정(배열) - context , 항목을 구성하기 위한 layout파일, 데이터
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data1)
        list1.adapter = adapter1

        list1.setOnItemClickListener(listener)
    }
    
    val listener = object : AdapterView.OnItemClickListener {
        // 이벤트가 발생한 항목을 가지고 있는 AdapterView
        // 이벤트가 발생한 항목 view
        // 이벤트가 발생한 항목의 index
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(parent?.id){
                R.id.list1 -> textView2.text = "${data1[position]} 항목을 클릭했습니다"
            }
        }
    }
}