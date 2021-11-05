// ListFragment : layout의 id를 android:id/list 로 설정하면 자동으로 가져온다(code)
package com.example.listfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val sub = SubFragment()
    var value = ""
    val tran = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment("sub")
    }

    fun setFragment(name:String){
        when(name){
            "sub" -> {
                tran.replace(R.id.fragcon, sub)
                tran.commit()
            }
        }
    }
}