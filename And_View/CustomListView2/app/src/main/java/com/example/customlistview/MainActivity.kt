package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("1", "2", "3")

    // drawable 이미지에 접근하는 정수형 배열
    val data2 = intArrayOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground)
    val data3 = arrayOf("하나", "둘")
    val data4 = arrayOf("01", "02")
    val data5 = arrayOf("-1", "-2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 문자열 1개만 세팅 = ArrayAdapter
        val adapter1 = ArrayAdapter(this, R.layout.row, R.id.row_text, data1)
        list1.adapter = adapter1

        list1.setOnItemClickListener { parent, view, position, id ->
            textView.text = "${data1[position]}을 터치"
        }

        // 문자열, 이미지 등 다양한 세팅 = SimpleAdapter
        // HashMap 객체가 한 항목의 데이터를 포함하고 넘겨줌
       val adapterData = ArrayList<HashMap<String, Any>>()

        for(i in data2.indices){
            val map = HashMap<String, Any>()
            map["img"] = data2[i]
            map["data1"] = data3[i]
            map["data2"] = data4[i]
            map["data3"] = data5[i]
            // 키워드 / 값
            adapterData.add(map)
        }

        // Hashmap에 데이터를 저장했을 때 사용했던 이름 배열
        val keys = arrayOf("img", "data1", "data2", "data3")
        // 데이터를 셋팅할 view의 id 배열
        val ids = intArrayOf(R.id.imageView, R.id.row2_text1, R.id.row2_text2, R.id.row2_text3)

        val adapter2 = SimpleAdapter(this, adapterData, R.layout.row2, keys, ids)
        list2.adapter = adapter2
        list2.setOnItemClickListener { parent, view, position, id ->
            textView2.text = "${data3[position]}을 터치"
        }
    }

}