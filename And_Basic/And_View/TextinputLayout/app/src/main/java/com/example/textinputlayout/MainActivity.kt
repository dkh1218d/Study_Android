/*
TextInputLayout property
- Hint : 힌트가 사라지지 않음
- CountEnable. CountMaxLength : 글자수 표시, 제한
- Error : 글자 오류 판단
*/

package com.example.textinputlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            textView1.text = textInput1.editText?.text
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(textInput1.editText?.windowToken, 0)
        }

        button2.setOnClickListener {
            textView1.text = ""
            textInput1.editText?.text = null
        }

        // TextWatcher : 글자 실시간 감시 (Before, After, onText)
        textInput1.editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s!= null){
                    if(s.length > 10){
                        textInput1.error = "10글자 이하로 작성해 주세요"
                    }else{
                        textInput1.error = null
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}