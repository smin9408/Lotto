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

            buyLotto()
        }
    }

    private fun buyLotto() {

//        6개의 당첨번호
        for (i in 0 until 6) {

//            괜찮은 번호가 나올 때 까지 무한 반복
            while (true) {

//                1 ~ 45 의 랜덤 숫자
                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복 검사 통과 시 while 깨기
                if (true) {
                    break
                }

            }

        }


//        만들어진 당첨번호 6개를 -> 텍스트뷰

//        보너스번호 생성

//        텍스트뷰에 배치
    }

    private fun setValues() {

    }
}