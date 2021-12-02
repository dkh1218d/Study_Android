/*  ActionBar : OptionMenu 구성 시 showAsAction 속성을 이용한다
    showAsAction
    None : 기본. ActionBar에 표시X / Always : 무조건 ActionBar에 표시
    ifRoom : 공간이 허락할 경우 ActionBar에 표시 / Icon : ActionBar에 표시될 경우 사용할 icon
    withText : 공간이 허락할 경우 icon과 함께 문자열 표시
 */
package com.example.actionbar

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmanu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {
                textView.text = "첫번째 항목"
            }
            R.id.menu2 -> {
                textView.text = "두번째 항목"
            }
            R.id.menu3 -> {
                textView.text = "세번째 항목"
            }
            R.id.menu4 -> {
                textView.text = "네번째 항목"
            }
        }
        return super.onOptionsItemSelected(item)
    }
}