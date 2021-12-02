/* Fragment : Activity내의 작은 화면 조각으로 Activity의 화면을 여러 작은 영역으로 나누어 관리하는 목적
   add - Fragment를 지정된 레이아웃에 추가 / replace  : 레이아웃에 기존의 Fragment를 제거하고 새 Fragment를 추가(교체)
   * Fragment는 Activity가 아니므로 Back button으로 제거할 수 없다
   -> AddToBackStack 메서드를 통해 BackStack에 포함시켜 제거가 가능

 */
package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag1 = FirstFragment()
        val frag2 = SecondFragment()

        button.setOnClickListener {
            // Fragment 작업 시작
            var tran = supportFragmentManager.beginTransaction()
            // Fragment 를 셋팅할 Layoutcontainer를 지칭
            // tran.add(R.id.container1, frag1)
            // add를 하면 같은 container에 다른 fragment를 셋팅할 시 중첩되어 화면에 표시 -> 교체의 replace 사용
            tran.replace(R.id.container1, frag1)
            // BackStack에 추가 / BackStack에서의 구분자
            tran.addToBackStack("first")
            tran.commit()
        }

        button2.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            // tran.add(R.id.container1, frag2)
            tran.replace(R.id.container1, frag2)
            tran.addToBackStack("second")
            tran.commit()
        }
    }
}