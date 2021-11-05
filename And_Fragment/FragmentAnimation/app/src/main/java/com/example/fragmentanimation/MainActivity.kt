/*  FragmentAnimation : 애니메이션 설정은 Fragment를 교체하기 전에 해야 한다
    애니메이션을 커스터마이징 할 경우 Fragment 교체 후 설정하면 적용되지 않는다
    setTransition : 새로운 Fragment로 교체할 때 설정하며, 돌아올 때도 적용된다(FADE, OPEN, CLOSE)
    XML파일을 통해 애니메이션 커스터마이징 가능
    -> 나타날 때, 사라질 때, 이전 Fragment로 돌아가며 나타날 때, 이전 Fragment로 돌아가며 사라질 때 / 4가지 설정 필요

    속성  objectAnimater
    android:propertyName    fade="alpha" 투명도 / slide="x" "y" 가로 세로
    android:valueType       floatType 실수
    android:valueFrom       fade="0" 없음 / slide="0" 왼쪽 끝 기준
    android:valueTo         fade="1" 있음 / slide="1080+" 우측(화면 픽셀)
    android:duration        애니메이션 유지시간
*/
package com.example.fragmentanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val frag1 = FirstFragment()
    val frag2 = SecondFragment()
    var fname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            setFragment("first")
        }
        button2.setOnClickListener {
            setFragment("second")
        }
    }

    fun setFragment(name:String){
        val tran = supportFragmentManager.beginTransaction()
        when(name){
            "first" ->{
                supportFragmentManager.popBackStack()

                // Open Animation 설정 : 커지면서 등장
                // tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                tran.setCustomAnimations(R.anim.slide_xml1, R.anim.slide_xml2, R.anim.slide_xml3, R.anim.slide_xml4)

                tran.replace(R.id.frag_view, frag1)
                tran.addToBackStack("first")
                fname = "first"
            }
            "second" ->{
                supportFragmentManager.popBackStack()
                // Close Animation 설정 : 작아지면서 등장
                // tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                // Fade Animation 설정 : 서서히 생기면서 등장
                // tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                // Custom Animation 설정
                // tran.setCustomAnimations(R.anim.fade_xml1, R.anim.fade_xml2, R.anim.fade_xml1, R.anim.fade_xml2)
                tran.setCustomAnimations(R.anim.slide_xml1, R.anim.slide_xml2, R.anim.slide_xml3, R.anim.slide_xml4)

                tran.replace(R.id.frag_view, frag2)
                tran.addToBackStack("second")
                fname = "second"
            }
        }
        tran.commit()
    }
}