package com.example.preferencesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val data1 = pref.getString("edit1", null)
        val data2 = pref.getBoolean("chk1", false)
        val data3 = pref.getBoolean("switch1", false)
        textView2.text = "data1 : ${data1}"
        textView2.append("\ncheck : ${data2}")
        textView2.append("\nswit : ${data3}")

        // SingleList - 문자형태로 저장
        val data4 = pref.getString("single1", null)
        textView2.append("\nsig : ${data4}")

        val data5 = pref.getStringSet("multi1", null)
        for(i in data5!!){
            textView2.append("\nmul : ${i}")
        }

    }
}