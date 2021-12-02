package com.example.actionbarnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // HomeButton 메뉴를 활성화
        supportActionBar?.setHomeButtonEnabled(true)
        // HomeButton 노출
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // HomeButton icon 이미지 설정
        supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // optionMenu Id가 정해져 있다
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}