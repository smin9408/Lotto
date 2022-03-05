package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    private fun setupEvents() {

        btnBuyLotto.setOnClickListener {

            Toast.makeText(this, "로또 구매 버튼 눌림", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buyLotto(){

//        6개의 당첨번호

//        만들어진 당첨번호 6개를 -> 텍스트뷰
    }

    private fun setValues() {

    }
}