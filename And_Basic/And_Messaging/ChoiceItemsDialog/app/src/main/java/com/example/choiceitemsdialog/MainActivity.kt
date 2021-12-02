package com.example.choiceitemsdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("first", "second", "third", "fourth", "fifth", "sixth")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Single")
            // 라디오 형태의 Dialog
            builder.setSingleChoiceItems(data1, 0){dialogInterface, i ->
                // 항목을 체크하자마자 동작하는 리스너
                // Toast로 체크한 데이터 처리
                val toast = Toast.makeText(this, data1[i], Toast.LENGTH_SHORT)
                toast.show()
            }
            builder.setPositiveButton("Success"){dialogInterface, i ->
                // 추출할 dialogInterface를 형변환
                val alert = dialogInterface as AlertDialog
                // 현재 선택한 아이템의 Index
                val idx = alert.listView.checkedItemPosition
                textView.text = "Select ${data1[idx]}"

            }
            builder.setNegativeButton("Cancle", null)
            builder.show()

        }

        button2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Multi")
            // 체크박스 형태의 Dialog / 멀티 형태이기에 처음 체크돼 있는 항목을 설정해야 한다
            val chk = booleanArrayOf(false, false, false, false, false, false)
            // 매개변수 (데이터배열, boolean 배열, 리스너)
            // 고차함수 (객체, index, 체크상태boolean)
            builder.setMultiChoiceItems(data1, chk){it, i, b ->
                if(b){
                    val toa = Toast.makeText(this, "${data1[i]} 체크", Toast.LENGTH_SHORT)
                    toa.show()
                }
            }
            builder.setPositiveButton("Success"){it, i ->
                val alert = it as AlertDialog
                textView.text = ""
                // 체크상태가 변경된 항목의 index번호와 체크 여부값을 추출
                val positions = alert.listView.checkedItemPositions
                for(i in 0 until positions.size()){
                    // 체크상태가 변경된 항목의 Index
                    val index = positions.keyAt(i)
                    if(positions.get(index)){
                        textView.append("${data1[index]}\n")
                    }
                }
            }
            builder.setNegativeButton("Cancle", null)


            builder.show()
        }
    }
}