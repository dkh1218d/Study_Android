package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var data1 = arrayOf("1번", "2번","3번", "4번","5번", "6번","7번", "8번","9번", "10번","11번", "12번")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val adapter1 = ArrayAdapter(this, R.layout.row, R.id.rowText, data1)
        list1.adapter = adapter1*/
        
        /*list1.setOnItemClickListener { parent, view, position, id ->
            textView.text = "${data1[position]}을 터치"
        }*/
    }
}