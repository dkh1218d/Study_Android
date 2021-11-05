/* ActivityController : Fragment를 관리하기 위한 방법
   Controller : 개발 시 눈에 보이는 화면을 관리하는 요소
   Fragment를 관리하는 Activity가 Controller의 역할을 수행
   Activity = 각 Fragment를 교환하고 관리하는 역할 / Fragment들이 사용할 데이터를 관리하는 역할
*/
package com.example.activitycontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val frag1 = FirstFragment()
    val frag2 = SecondFragment()

    var value = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment("first")
        button.setOnClickListener {

        }

    }

    // Fragment를 관리하는 메서드
    fun setFragment(name:String){
        val tran = supportFragmentManager.beginTransaction()
        when(name){
            "first" -> tran.replace(R.id.frag_layout, frag1)
            "second" -> {
                tran.replace(R.id.frag_layout, frag2)
                tran.addToBackStack(null)
            }
        }
        tran.commit()
    }
}