/*  Appbar Layout에 TabBarLayout과 ViewPager2를 통해 탭을 구성할 수 있다
    NestedScrollView의 fillviewport를 체크해야 viewPager2가 적용된다
 */
package com.example.tablayout

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    // viewPager2 에 세팅하기 위한 Fragment를 가지고 있는 list
    val fraglist = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionBar(toolbar)
        // appbar의 Code에서 layout_scrollFlags를 지워 스크롤을 비활성화 시킨다

        toolbar.setTitleTextColor(Color.WHITE)
        tabs.setBackgroundColor(Color.CYAN)
        tabs.setTabTextColors(Color.BLACK, Color.RED) // 선택된 탭의 색상 설정

        for(i in 1..10){
            val frag1 = SubFragment("Fragment${i}")
            fraglist.add(frag1)
        }
        val adapter1 = object : FragmentStateAdapter(this){
            override fun createFragment(position: Int): Fragment {
                return fraglist[position]
            }

            override fun getItemCount(): Int {
                return fraglist.size
            }
        }
        pager.adapter = adapter1

        // Tab과 ViewPager를 연동
        TabLayoutMediator(tabs, pager){tab, posision ->
            tab.text = "Tab${posision+1}"
        }.attach()
    }
}