package com.example.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.view.get
import androidx.fragment.app.ListFragment
import kotlinx.android.synthetic.main.sub_fraglayout.*

class SubFragment : ListFragment() {
    val data1 = listOf<String>("1번", "2번", "3번", "4번", "5번")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.sub_fraglayout, null)

        // 자체로 listAdapter라는 프로퍼티를 보유
        listAdapter = ArrayAdapter<String>(activity as MainActivity, android.R.layout.simple_list_item_1, data1)

        return view
    }

    // ListView의 항목을 터치했을 때 호출되는 메서드
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        sub_text.text = "${data1[position]}"
    }
}