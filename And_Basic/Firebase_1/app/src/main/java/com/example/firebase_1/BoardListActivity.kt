package com.example.firebase_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.ScrollView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_board_list.*

class BoardListActivity : AppCompatActivity() {

    lateinit var lv_adapter : Listview_adapter

    val m_list = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        getData()

        lv_adapter = Listview_adapter(m_list)
        val lv = findViewById<ListView>(R.id.board_list)
        lv.adapter = lv_adapter

        val w_btn = findViewById<Button>(R.id.write_btn)
        w_btn.setOnClickListener {
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)

        }

        // ListView가 터치 될 때 ScrollView의 스크롤을 Disallow(잠금)
        val t_listener = object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val list_scroll = findViewById<ScrollView>(R.id.list_scroll)
                list_scroll.requestDisallowInterceptTouchEvent(true)
                return false
            }
        }

        lv.setOnTouchListener(t_listener)
    }

    // 글 목록 리스트 데이터 수집
    fun getData(){
        // FireBase 데이터의 경로
        val database = Firebase.database
        val myRef = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(datamodel in dataSnapshot.children){
                    Log.d("test", "$datamodel")

                    // Model의 형태로 Data를 받아온다
                    val item = datamodel.getValue(Model::class.java)
                    Log.d("test", "item : $item")

                    m_list.add(item!!)
                }

                // 비동기 형태의 Firebase가 데이터를 받아오는 중 다음 view가 호출되어 listview에 데이터 입력이 안됨
                // 그러므로 데이터 호출이 끝난 후 Adapter를 다시 동기화 시켜야 할 필요가 있다
                lv_adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}