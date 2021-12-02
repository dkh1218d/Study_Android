/*  DrawerLayout
    activity_main.xml의 nav_view는 좌측에서 나오는 메뉴
    - header와 menu 2부분으로 나뉜다
    - include : 다른 layout을 화면에 포함시키는 요소 - Fragment가 교체되는 화면 부분

    activity_main 의 include -> app_bar_main 의 include -> content_main(fragment 교체용 화면)
    DrawerLayout을 통해 교체될 Fragment를 관리하고 각 Fragment의 교체처리를 담당하는 Controller가 제공된다

    mobile_navigation.xml : Controller가 관리할 Fragment를 등록하는 xml
    - Fragment를 등록할 때 메뉴의 좌측에 셋팅된 메뉴item의 id와 동일한 id가 셋팅된 Fragment가 동작한다

    메뉴 추가
    - Fragment, layout을 작성 후 mobile_navigation에 등록
    - activity_main_drawer.xml에 메뉴 등록 후 id를 동일하게 작성
    - MainActivity에서 해당 Fragment를 Controller에 등록
 */
package com.example.drawerlayout

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바
        setSupportActionBar(binding.appBarMain.toolbar)

        // 화면 우측 하단의 플로팅 버튼
        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // content_main의 Fragment Controller
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_sub, R.id.nav_japan
            ), drawerLayout
        )
        // id를 등록해 Fragment를 Controller의 관리 하에 둠
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}