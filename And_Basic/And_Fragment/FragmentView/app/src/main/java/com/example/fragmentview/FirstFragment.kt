package com.example.fragmentview

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_fragmentlayout.*

class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.first_fragmentlayout, null)

        // 내부의 View들의 주소값 추출 - View를 생성한 후 Return하면서 View 내부의 주소값을 할당하기 때문에 findViewById로 가져와야 함
        /*
        val ftext = view.findViewById<TextView>(R.id.textView2)
        val fbtn = view.findViewById<Button>(R.id.button3)
        val fprogress = view.findViewById<ProgressBar>(R.id.progressBar)
        fbtn.setOnClickListener {
            fprogress.progress++
            ftext.text = "${fprogress.progress} %"
        }*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View를 생성한 후 호출되는 메서드로 View id에 직접 접속 가능
        button3.setOnClickListener {
            progressBar.progress+=2
            textView2.text = "${progressBar.progress} %"
        }

        // MainActivity에 데이터 전송
        button4.setOnClickListener {
            // MainActivity를 추출
            val parent = activity as MainActivity
            parent.textView.text = "${progressBar.progress} 를 진행중"
        }
    }
}