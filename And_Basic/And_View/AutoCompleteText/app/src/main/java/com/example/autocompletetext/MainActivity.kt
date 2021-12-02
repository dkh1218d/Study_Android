// AutoCompleteView : 자동완성 / MultiAutoCompleteView : 구분자를 이용(setTokenizer)
// completeThreshold : 자동완성 최소 글자수(기본 2)
package com.example.autocompletetext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var data_ = mutableListOf<String>("star", "counting", "night", "peal", "starcraft")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adapter를 프로퍼티로 제공
        val adapter_ = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, data_)
        autoText.setAdapter(adapter_)

        button1.setOnClickListener {textView.text = "${autoText.text} 입력되었습니다"}

        val lis1 = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                textView.text = "${data_[position]}항목"
                // 버그로 인해 id로 인한 분기가 실행되지않음
                /*when(parent?.id){
                    R.id.autoText -> textView.text = "${data1[position]}항목"
                }*/
            }
        }
        autoText.setOnItemClickListener(lis1)

        multiAuto.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer()) // ,를 구분자로
        multiAuto.setAdapter(adapter_)
        button.setOnClickListener {
            val str = multiAuto.text.split(",")
            textView2.text = ""
            for (arr in str){
                if(arr != "") textView2.append("${arr.trim()}\n")
            }
        }
        multiAuto.setOnItemClickListener { parent, view, position, id ->
            textView2.text = "${data_[position]} 항목 클릭"
        }

    }
}