// Activity 생명주기
package com.example.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    // Activity가 생성될 때 자동으로 호출
    // 가로, 세로 화면전환 발생 시 자동으로 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // println("Hello World") 불필요한 로그가 찍힘 / Logcat 사용
        Log.d("test1", "OnCreate") // Log.d : 검정색 / Log.e : 빨간색 tag는 메시지 구분 레이블
    }

    // OnCreate 메서드 호출 이후 자동으로 호출
    // Activity가 정지상태 이후 활동상태로 돌아올 때 호출
    override fun onStart() {
        super.onStart()
        Log.d("test1","OnStart")
    }

    // OnStart 메서드 호출 이후 자동으로 호출
    // Activity가 일시 정지 후 다시 행동할 때 호출
    override fun onResume() {
        super.onResume()
        Log.d("test1", "OnResume")
    }

    // Activity가 정지상태 이후 활동상태로 돌아올 때 OnStart 이전에 호출
    override fun onRestart() {
        super.onRestart()
        Log.e("test1", "OnRestart")
    }

    // Activity가 일시 정지 상태가 될 때 호출
    // 화면상에서 사라지거나 현재 화면 위로 작은 팝업 창 같은 것이 나타날 때 호출
    override fun onPause() {
        super.onPause()
        Log.e("test1", "OnPause")
    }

    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
        Log.e("test1", "OnStop")
    }

    // Activity의 수행이 완전히 종료되어 메모리상에서 제거될 때 호출
    override fun onDestroy() {
        super.onDestroy()
        Log.e("test1", "OnDestroy")
    }
}