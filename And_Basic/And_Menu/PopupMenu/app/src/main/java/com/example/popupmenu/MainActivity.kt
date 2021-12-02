package com.example.popupmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // PopupMenu 생성
            val pop = PopupMenu(this, textView)
            menuInflater.inflate(R.menu.menu1, pop.menu)


            pop.setOnMenuItemClickListener {
                when(it?.itemId){
                    R.id.pop1 -> textView.text = "메뉴 1 클릭"
                    R.id.pop2 -> textView.text = "메뉴 2 클릭"
                    R.id.pop3 -> textView.text = "메뉴 3 클릭"
                }
                true // lamda
            }
            pop.show()
        }
    }
}