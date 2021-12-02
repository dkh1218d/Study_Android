// PageAdapter를 구현하여 ViewPager 사용 / getCount, instantiateItem, destroyItem
// isViewFromObject : 만든 객체중 어느것을 사용할 것인지의 여부
// 컷 형식으로 화면 페이지를 나눔
package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // ViewPager를 통해 보여줄 View를 담아줄 List
    val viewList = arrayListOf<View>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // xml파일의 정보를 View객체로 생성
        val view1 = layoutInflater.inflate(R.layout.view1, null)
        val view2 = layoutInflater.inflate(R.layout.view2, null)
        val view3 = layoutInflater.inflate(R.layout.view3, null)
        val view4 = layoutInflater.inflate(R.layout.view1, null)
        val view5 = layoutInflater.inflate(R.layout.view2, null)
        val view6 = layoutInflater.inflate(R.layout.view3, null)

        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewList.add(view4)
        viewList.add(view5)
        viewList.add(view6)

        val adapter1 = object : PagerAdapter(){
            // ViewPager가 보여줄 View의 갯수
            override fun getCount(): Int {
                return viewList.size
            }

            // ViewPager가 보여줄 화면을 반환
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                Pager1.addView(viewList[position])
                return viewList[position]
            }

            // 반환한 객체를 화면으로 사용할 것인가 검사하는 메서드
            override fun isViewFromObject(view: View, obj: Any): Boolean {
                return view == obj
            }

            // 사라지는 view 객체를 소멸시키는 메서드
            override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
                Pager1.removeView(obj as View)
            }
        }
        Pager1.adapter = adapter1
        Pager1.addOnPageChangeListener(lis1)
    }

    val lis1 = object : ViewPager.OnPageChangeListener{
        // Page Scroll 상태 변경시
        override fun onPageScrollStateChanged(state: Int) {

        }
        // Page Scroll 끝날 시
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int){
            textView.text = "${position+1}번 째 컷"
        }
        // Page를 선택했을 때
        override fun onPageSelected(position: Int) {

        }
    }
}