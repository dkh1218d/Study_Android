/*  Preferences Screen : UI를 통해 Preferences를 사용할 수 있도록 제공되는 개념
    PreferencesFragment를 사용하며 저장 기능까지 모두 구현되어 있다(SettingActivity)
    res의 xml폴더에서 Preferences Screen 메뉴를 구성할 수 있다
    
    single, multi List 사용할 경우 values 폴더의 string.xml에서 미리 항목을 지정해야 한다
    entry list : 화면상에 표시될 항목
    values list : 실제 값을 가지는 항목
    default list : multi list에서 default값으로 가져갈 항목
 */
package com.example.preferencesscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val setfrag = SettingFragment()
    val refrag = ResultFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container, setfrag)
            tran.commit()
        }
        button2.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container, refrag)
            tran.commit()
        }
    }
}