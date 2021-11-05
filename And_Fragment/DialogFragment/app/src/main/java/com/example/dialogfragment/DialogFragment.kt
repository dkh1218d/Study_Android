package com.example.dialogfragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class DialogFragment : DialogFragment() {
    // Dialog 생성시 호출
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mactivity = activity as MainActivity
        val builder = AlertDialog.Builder(mactivity)
        builder.setTitle("Dialog~~")
        builder.setMessage("Dialog Desu")
        builder.setPositiveButton("Posi"){d, i ->
            mactivity.textView.text = "positive"
        }

        val alert = builder.create()

        return alert
    }
}