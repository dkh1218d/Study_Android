// DatePicker, TimePicker 등 달력, 시간 설정 Dialog 세팅가능
package com.example.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_layout1.view.*
import java.sql.Time
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // Builder를 통해 Dialog 생성
            var builder = AlertDialog.Builder(this)
            builder.setTitle("First Dialog")
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setMessage("Basic Dialog First")

            // button 위치에 따른 총 3개까지 세팅 가능, Listener 설정 가능
            builder.setPositiveButton("Success", DialogInterface.OnClickListener { dialog, which ->
                textView.text = "성공"
            })
            builder.setNeutralButton("Assist", DialogInterface.OnClickListener { dialog, which ->
                textView.text = "도움"
            })
            builder.setNegativeButton("Fail", DialogInterface.OnClickListener { dialog, which ->
                textView.text = "취소"
            })

            builder.show()
        }

        button2.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Second Custom Dialog")
            builder.setIcon(R.mipmap.ic_launcher)

            // Builder에 생성한 view를 세팅
            val custom_view = layoutInflater.inflate(R.layout.custom_layout1, null)
            builder.setView(custom_view)

            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                custom_view.run { // Kotlin 클로저 로 호출이 가능
                    textView.text = "${name_edit.text}\n"
                    textView.append("${intro_edit.text}")
                }
            })
            builder.setNegativeButton("Cancle", DialogInterface.OnClickListener { dialog, which ->
                null
            })
            builder.show()
        }

        // 현재의 날짜 정보를 불러오기 위함
        val calendar = Calendar.getInstance()
        button3.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val date = calendar.get(Calendar.DATE)

            val listener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                textView.text = "$year 년 ${month+1} 월 $dayOfMonth 일"
            }
            val picker = DatePickerDialog(this, listener, year, month, date)
            picker.show()
        }

        button4.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            val listener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                textView.text = "$hourOfDay 시 : $minute 분"
            }
            val picker = TimePickerDialog(this, listener, hour, minute, true)
            picker.show()
        }
    }
}