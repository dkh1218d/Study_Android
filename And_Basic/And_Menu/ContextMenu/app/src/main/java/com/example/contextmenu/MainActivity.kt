package com.example.contextmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val list = mutableListOf<String>("1번 리스트", "2번 리스트", "3번 리스트")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ContextMenu View에 등록
        // 항목을 길게 터치할 시 호출되는 메뉴
        registerForContextMenu(textView)

        // ListView로 ContextMenu 호출
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listview1.adapter = adapter
        listview1.setOnItemClickListener { parent, view, position, id ->
             textView.text = "리스트 뷰 항목 : ${list[position]}"
        }

        registerForContextMenu(listview1)
    }

    // ContextMenu가 등록된 View를 길게 누를 시 호출되는 메서드
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo? // 호출한 항목의 Index가 들어있음
    ) {
        when(v?.id){
            R.id.textView -> {
                menu?.setHeaderTitle("TextVIew Menu")
                menuInflater.inflate(R.menu.menu1, menu)
            }
            R.id.listview1 -> {
                val info = menuInfo as AdapterView.AdapterContextMenuInfo // 형변환
                menu?.setHeaderTitle("${info.position+1}번 : ListView Menu")
                menuInflater.inflate(R.menu.menu2, menu)
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    // 사용자가 메뉴를 선택했을 때 호출되는 메서드
    override fun onContextItemSelected(item: MenuItem): Boolean {
        // 리스트 항목의 Index를 받을 변수
        var position = 0
        when(item?.itemId){
            R.id.list_item1, R.id.list_item2 ->{
                // menuInfo 객체를 추출
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                position = info.position+1
            }
        }

        // 메뉴 id로 분기
        when(item?.itemId){
            R.id.textitem1 -> textView.text = "1번 항목"
            R.id.textitem2 -> textView.text = "2번 항목"

            R.id.list_item1 -> textView.text = "${position}번 : 리스트 1번 항목"
            R.id.list_item2 -> textView.text = "${position}번 : 리스트 1번 항목"
        }

        return super.onContextItemSelected(item)
    }
}