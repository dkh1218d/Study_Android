package com.example.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

class FirstFragment : Fragment() {
    // Fragment가 Activity와 연결될 때 호출
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("test", "attach")
    }

    // Fragment가 생성될 때 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "create")
    }

    // Fragment를 통해 보여줄 View를 생성하기 위해 호출
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.first_fragment, null)
        Log.d("test", "createView")
        return view
    }

    // *** Fragment를 통해 보여줄 View가 생성된 후에 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test", "viewCreated")
    }

    // Activity에서 보여줄 Fragment가 완전히 생성되면 호출
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("test", "activityCreated")
    }

    // Fragment가 가동 될 때(화면에 표시 될 때)
    override fun onStart() {
        super.onStart()
        Log.d("test", "start")
    }

    // Fragment가 보여지고 나서 호출
    override fun onResume() {
        super.onResume()
        Log.d("test", "resume")
    }

    // Fragment가 일시정지될 때(화면에서 사라질 때)
    override fun onPause() {
        super.onPause()
        Log.d("test", "pause")
    }

    // Fragment가 화면에서 완전히 사라져서 보이지 않을 때
    override fun onStop() {
        super.onStop()
        Log.d("test", "stop")
    }

    // Fragment가 제거될 때
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "destroy")
    }

    // Fragment가 Activity와 연결이 끊기기 전에 호출
    override fun onDetach() {
        super.onDetach()
        Log.d("test", "detach")
    }
}