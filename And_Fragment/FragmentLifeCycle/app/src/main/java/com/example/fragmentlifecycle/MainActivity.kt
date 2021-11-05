package com.example.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_fragment.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag1 = FirstFragment()

        button.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container1, frag1)
            tran.addToBackStack(null)
            tran.commit()
        }
        button2.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            // remove는 backstack에서 제거하지 못하고 화면에서만 제거한다
            tran.remove(frag1)
            tran.commit()
        }

        button3.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            // BackStack에서 순차적 제거
            supportFragmentManager.popBackStackImmediate()
            // BackStack의 갯수
            textView.text = "${supportFragmentManager.backStackEntryCount} 개"
            tran.commit()
        }
    }
}