/*  ActionView : ActionBar에 View를 배치하고 접었다 폈다 할 수 있는 개념
    주로 검색창에 이용
    menu Code에서 app:actionViewClass="androidx.appcompat.widget.SearchView" 작성 필요


    SearchView 속성 참조 : https://landroid.tistory.com/5
 */
package com.example.actionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("1항목", "2항목", "3항목", "4항목", "5항목", "6항목", "7항목", "8항목")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1)
        listview.adapter = adapter

        // 검색어 기준으로 필터링 될 수 있도록 설정
        listview.isTextFilterEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)

        // SearchView를 갖고 있는 메뉴 아이템 객체를 추출
        val item1 = menu?.findItem(R.id.searchitem)
        val search1 = item1?.actionView as SearchView

        search1.queryHint = "검색어 입력!~" // 검색창 hint 변경
        search1.isSubmitButtonEnabled= true // 검색 Enter 아이콘
        search1.maxWidth = Int.MAX_VALUE // 검색창 x축 사이즈

        // 메뉴 아이템에 배치된 View가 펼쳐지거나 접힐 때 반응하는 리스너
        val listener1 = object : MenuItem.OnActionExpandListener{
            // 접힐 때
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                textView.text = "접힘"
                return true
            }
            // 펼칠 때
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                textView.text = "펼침"
                return true
            }
        }
        item1.setOnActionExpandListener(listener1)

        // SearchView의 리스너
        val listener2 = object : SearchView.OnQueryTextListener{
            // SearchView에 문자열을 입력 할 때 마다 호출
            override fun onQueryTextChange(newText: String?): Boolean {
                textView.text = "문자열 입력 중"
                textView2.text = "${newText}"

                // SearchView에 입력한 내용을 ListView의 필터 문자열로 설정
                listview.setFilterText(newText)
                // 입력한 문자열의 길이가 0이라면 필터 문자열을 제거한다
                if(newText?.length==0){
                    listview.clearTextFilter() 
                }

                return true
            }
            // 키보드의 Enter key를 눌렀을 때 호출
            override fun onQueryTextSubmit(query: String?): Boolean {
                textView.text = "문자열 입력 완료"
                textView2.text = "${query} 를 검색"
                search1.clearFocus()
                return true
            }
        }
        search1.setOnQueryTextListener(listener2)

        return true
    }

}