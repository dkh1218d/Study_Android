package com.example.activitycontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.first_fraglayout.*

class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.first_fraglayout, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        first_button.setOnClickListener {
            first_text.text = ""
            if(first_plain.text.length>=6 && first_plain2.text.length>=4){

                // Activity에 접근
                val mactivity = activity as MainActivity

                // MainActivity의 변수에 데이터 할당
                mactivity.value = "${first_plain.text}_${first_plain2.text}"

                mactivity.setFragment("second")
            }else{
                first_text.text = "아이디나 비밀번호가 올바르지 않습니다"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        first_plain.setText("")
        first_plain2.setText("")
    }
}