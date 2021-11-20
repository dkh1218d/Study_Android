/*  ActionBar를 보다 자유롭게 사용할 수 있도록 ToolBar라는 View를 제공하고 있다
    ToolBar : 기본적인 기능은 ActionBar와 동일하며, 탭 등 다양한 기능들이 추가 제공된다
    activity_main.xml의 container 항목에 toolbar 존재
 */
package com.example.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ToolBar를 ActionBar 대신 사용하도록 설정
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 ->{
                textView.text = "1번 클릭"
            }
            R.id.item2 ->{
                textView.text = "2번 클릭"
            }
            R.id.item3 ->{
                textView.text = "3번 클릭"
            }
        }
        return super.onOptionsItemSelected(item)
    }
}