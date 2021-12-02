// ListView +  GridView
// 자유롭게 구현가능 + 배치 선택 가능 -> 자주 사용되는 View
package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row1.view.*

class MainActivity : AppCompatActivity() {
    val img = intArrayOf(R.drawable.abc)
    val data1 = arrayOf("꽃")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter1 = RecyclerAdapter()
        recycler.adapter = adapter1

        // Layout의 구성을 정해야 함
        // recycler.layoutManager = LinearLayoutManager(this) // ListView 화면구성
        // recycler.layoutManager = GridLayoutManager(this, 2) // GridView 화면구성

        recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        // GridView구성에 배치 변경(Horizontal, Vertical) / 크기 맞게 조율 배열

    }

    // Recycler의 Adapter Class
    // 먼저 만든 ViewHolderClass를 Generic으로 사용
    inner class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){

        // ViewHolder Class - 생성자(view 객체)
        // RecyclerView.ViewHolder 클래스를 상속받고 생성자를 넘겨준다
        inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
            // 항목 View 내부의 View 객체의 주소값을 담는다
            val rowimage = itemView.rowimage
            val rowtext = itemView.rowtext

            // ViewHolderClass에 Listener 구현
            override fun onClick(v: View?) {
                textView.text = "${data1[adapterPosition]}"
                // 터치한 항목 Index 가져오기(adapterPosition)
            }
        }

        // 항목구성을 위해 사용할 ViewHolder 객체가 필요할 때 호출되는 메소드
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // 항목으로 사용할 View 객체를 생성
            val itemView = layoutInflater.inflate(R.layout.row1, null)
            val holder = ViewHolderClass(itemView)

            // holder가 layout의 정보를 담고 있기에 Listener 호출 가능
            itemView.setOnClickListener(holder)

            return holder
        }

        // ViewHolder를 통해 항목을 구성할 때 항목 내의 View 객체에 데이터를 세팅한다
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowimage.setImageResource(img[position])
            holder.rowtext.text = data1[position]
        }

        // RecyclerView의 항목 갯수 반환
        override fun getItemCount(): Int {
            return img.size
        }
    }
}