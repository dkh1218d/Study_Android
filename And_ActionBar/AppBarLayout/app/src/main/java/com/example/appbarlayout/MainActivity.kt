/*  AppBar Layout : ToolBar와 다른 View들을 관리하는 Layout
    - 반드시 CoodinatiorLayout안에 포함되어 있어야 한다
    CoodinatiorLayout : View를 배치하기보단 배치된 View들을 관리하기 위한 목적으로 사용
    - 배치된 View에서 어떠한 사건이 발생하면 이를 감지하여 다른 View에 전달하거나 스스로 처리를 하는 Layout
    -> 스크롤 가능한 Toolbar를 만들기 위해 사용
 */
package com.example.appbarlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // TextColor 변경
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
        toolbarLayout.setExpandedTitleColor(Color.RED)
        // Text 위치 변경
        toolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL
        toolbarLayout.expandedTitleGravity = Gravity.LEFT+Gravity.TOP
        // Title 변경
        supportActionBar?.title = "ImageTitle"
        // imageView의 collapseMode : 기본은 pin - 이미지 하단이 기준 / parallax - 이미지 스크롤 시 중앙이 기준
        
        imageView.setImageResource(R.mipmap.ic_launcher)

    }
}