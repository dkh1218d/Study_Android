package com.example.activitycontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.second_fraglayout.*

class SecondFragment: Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.second_fraglayout, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mactivity = activity as MainActivity
        val manager = mactivity.supportFragmentManager

        var value = mactivity.value.split("_")
        second_text.text = "${value[0]}, Wellcome"
        second_text2.text = value[1]

        second_button.setOnClickListener{
            while(manager.backStackEntryCount!=0) {
                manager.popBackStackImmediate()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).setFragment("first")
    }
}