package com.example.toolbaractionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("abcd", "efg", "aqqw", "anananfk", "cdef")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1)
        list1.adapter = adapter

        list1.isTextFilterEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)

        val item = menu?.findItem(R.id.item1)
        val search1 = item?.actionView as  SearchView
        search1.queryHint = "Searching~"

        // 액션뷰가 접히거나 펼쳐질 때 반응하는 리스너
        val listener1 = object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                textView.text = "접힘"
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                textView.text = "펼쳐짐"
                return true
            }
        }

        item.setOnActionExpandListener(listener1)

        val listener2 = object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                textView.text = "입력 중"
                textView2.text = "${newText}"
                list1.setFilterText(newText)
                if(newText?.length==0)
                    list1.clearTextFilter()
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                textView.text = "입력 완료"
                textView2.text = "입력 완 : ${query}"
                search1.clearFocus()
                return true
            }
        }

        search1.setOnQueryTextListener(listener2)

        return true
    }


}