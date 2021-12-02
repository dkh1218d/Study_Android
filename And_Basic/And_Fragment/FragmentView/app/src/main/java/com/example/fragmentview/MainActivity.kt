// Fragment 내의 View를 이용하기 위해 findViewById 매서드로 View의 주소값을 가져온다
package com.example.fragmentview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_fragmentlayout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag = FirstFragment()

        button.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.addToBackStack(null)
            tran.replace(R.id.frag_container, frag)
            tran.commit()
        }

        button2.setOnClickListener {
            while (supportFragmentManager.backStackEntryCount!=0) {
                supportFragmentManager.popBackStackImmediate()
            }
        }


    }
}