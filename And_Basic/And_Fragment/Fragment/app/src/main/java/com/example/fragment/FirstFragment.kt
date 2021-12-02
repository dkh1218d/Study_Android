package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

// Fragment는 Basic이 없기 때문에 Class에서 상속받아 생성
class FirstFragment : Fragment() {
    // 여기서 만든 View를 Fragment의 View로 사용
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 생성한 Layout을 inflater로 호출
        val view = inflater.inflate(R.layout.first_fragmentlayout, null)

        return view
    }
}