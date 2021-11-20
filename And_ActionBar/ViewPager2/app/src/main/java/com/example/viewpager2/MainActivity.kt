/*  viewPager는 view를 전환할 수 있도록 제공 / viewPager2는 Fragment를 전환할 수 있도록 제공
    viewPager2를 ToolBar와 관렬되어 사용이 가능하다

 */
package com.example.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

// FragmentActivity는 AppCompatActivity와 다르게 상단의 ActionBar를 제공하지 않음
class MainActivity : FragmentActivity() {
    val frag1 = SubFragment1()
    val frag2 = SubFragment2()
    val frag3 = SubFragment3()

    val fragList = listOf(frag1, frag2, frag3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ActionBar 생성 / FragmentActivity는 Toolbar를 제공받기 때문에 Code에서 태그 변경 필요
        setActionBar(toolbar)

        // 매개변수로 FragmentActivity를 받는다
        val adapter1 = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }

        viewpager.adapter = adapter1
    }
}