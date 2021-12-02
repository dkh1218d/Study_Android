package com.example.chip_progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Chip - 테마 theme.material.components.Light
                - 스타일 Action / Filter / Entry / Choice */

        chip1.setOnClickListener {
            textView.text = ""
            textView.append(" Act 칩\n")
            if(chip2.isChecked) textView.append(" Filt 칩\n")
            if(chip3.isChecked) textView.append(" Entry 칩\n")
            textView.append(" 을(를) 선택하셨습니다")
        }

        button.setOnClickListener {
            textView2.text = ""
            if(chip4.isChecked) textView2.append("1번")
        }

        button2.setOnClickListener {
            if(switch1.isChecked){
                progressBar.progress += 5
                textView3.text = "${progressBar.progress}%"
                if(progressBar.progress==100) textView3.text = "완료"
            }else {
                progressBar.progress -= 5
                textView3.text = "${progressBar.progress}%"
                if(progressBar.progress==0) textView3.text = "제거"
            }
        }
    }
}