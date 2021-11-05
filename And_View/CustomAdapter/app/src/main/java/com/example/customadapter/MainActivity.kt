package com.example.customadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.rowText
import kotlinx.android.synthetic.main.row.rowbutton1
import kotlinx.android.synthetic.main.row.view.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("1번", "2번", "3번", "4번", "5번", "6번")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list1.adapter = adapter1
    }
    // BaseAdapter를 상속받아 Adapter를 커스텀
    // getCount, getView 를 주로 사용
    val adapter1 = object : BaseAdapter() {
        // 항목의 갯수를 반환
        override fun getCount(): Int {
            return data1.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
        // 화면상에 표시하는 뷰를 호출
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // 재사용 가능한 View를 변수에 담음
            var rowView = convertView
            if(rowView == null){
                rowView = layoutInflater.inflate(R.layout.row, null)
            }
            // 리스트의 관련 항목1개 이기에 activity에서 자동으로 주소를 받지 못함
            // 항목 뷰의 내부에 배치되어 있는 뷰들의 주소값을 받아옴
            /*val row_text1 = rowView?.findViewById<TextView>(R.id.textView)
            val row_btn1 = rowView?.findViewById<Button>(R.id.rowbutton1)
            val row_btn2 = rowView?.findViewById<Button>(R.id.rowbutton2)

            row_text1?.text = data1[position]
            // 객체 하나를 뷰에 저장 가능 -> 리스너에 버튼 객체가 호출 -> 리스너에서 버튼의 매개변수를 사용 가능
            row_btn1?.tag = position+1
            row_btn2?.tag = position+1
            row_btn1?.setOnClickListener {textView.text = "1번버튼${it.tag}"} // position이 호출됨
            row_btn2?.setOnClickListener {textView2.text = "2번버튼${it.tag}"}
            코틀린으로 간소화 가능*/
            // 이 View가 갖고있는 view의 주소값을 가져와서 변수가 자동 선언
            rowView?.run {
                rowbutton1.tag = position+1
                rowbutton2.tag = position+1
                rowbutton1.setOnClickListener { textView.text = "1번버튼 ${it.tag}" }
                rowbutton2.setOnClickListener { textView2.text = "2번버튼 ${it.tag}" }

            }

            return rowView!!
        }

    }
}