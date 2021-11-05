package com.example.listdialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf("First", "Second", "Third", "fourth", "fifth")
    val data2 = arrayOf("test1", "test2", "test3", "test4", "test5")
    val data3 = intArrayOf(R.drawable.testimage, R.drawable.testimage2, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("First Dialog")
            builder.setPositiveButton("Success", null)
            builder.setNegativeButton("Cancle", null)

            builder.setItems(data1){dialogInterface, i ->
                textView.text = "Basic Dialog : ${data1[i]}"
            }
            builder.show()
        }
        button2.setOnClickListener {
            val list1 = ArrayList<HashMap<String, Any?>>()

            for(idx in data2.indices){
                val map = HashMap<String, Any?>()
                map["data2"] = data2[idx]
                map["data3"] = data3[idx]

                list1.add(map)
            }
            val key = arrayOf("data2", "data3")
            val ids = intArrayOf(R.id.textView4, R.id.imageView)

            val adapter = SimpleAdapter(this, list1, R.layout.list_layout, key, ids)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Second Dialog")
            builder.setAdapter(adapter){dialogInterface, i ->
                textView.text = "Basic Dialog : ${data2[i]} : ${data3[i]}"
            }
            builder.setNegativeButton("Cancle", null)

            builder.show()
        }
    }
}