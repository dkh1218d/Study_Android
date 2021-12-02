package com.example.optionmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    // 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // XML로 메뉴를 구성
        /*menuInflater.inflate(R.menu.main_menu, menu)*/

        // Code로 메뉴를 구성 (groupid, itemId, order(순서), title)
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴1")
        /*menu?.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "코드 메뉴2")*/
        // 하위 메뉴 생성
        val sub = menu?.addSubMenu("코드메뉴 2")
        sub?.add(Menu.NONE, Menu.FIRST+10, Menu.NONE, "코드 메뉴2-1")
        sub?.add(Menu.NONE, Menu.FIRST+20, Menu.NONE, "코드 메뉴2-2")

        menu?.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "코드 메뉴3")

        return true
    }

    // 사용자가 메뉴를 클릭했을때 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // XML메뉴 아이디 별로 분기
        /*when(item.itemId){
            R.id.menu_item1 -> textView.text = "1번 항목 클릭"
            R.id.menu_item2 -> textView.text = "2번 항목 클릭"
            R.id.menu_item3 -> textView.text = "3번 항목 클릭"
            // 하위 메뉴
            R.id.menu2_1 -> textView.text = "2번의 1번 항목"
            R.id.menu2_2 -> textView.text = "2번의 2번 항목"
        }*/

        // 코드 메뉴
        when(item?.itemId){
            Menu.FIRST -> textView.text = "1번 항목 클릭"
            Menu.FIRST+2 -> textView.text = "3번 항목 클릭"
            Menu.FIRST+10 -> textView.text = "2-1번 항목 클릭"
            Menu.FIRST+20 -> textView.text = "2-2번 항목 클릭"
        }


        return super.onOptionsItemSelected(item)
    }
}
