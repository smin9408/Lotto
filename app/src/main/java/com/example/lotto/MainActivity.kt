package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //    컴퓨터가 뽑은 당첨번호 6개를 저장할 ArrayList
    val mWinNumburList = ArrayList<Int>()

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

//        ArrayList는 목록을 계속 누적한다
//        당첨번호 뽑기 전에, 기존의 당첨번호는 전부 삭제하고 다시 뽑자
        mWinNumburList.clear()

        for (i in 0 until 6) {

//            괜찮은 번호가 나올 때 까지 무한 반복
            while (true) {

//                1 ~ 45 의 랜덤 숫자
                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복 검사 통과 시 while 깨기
//                contains = mWinNumburList 에 randomNum 가 들어있는가? boolean으로 나옴 들어가 있으면 true
                if (!mWinNumburList.contains(randomNum)) {

//                    당첨번호로 뽑은 랜덤 숫자 등록
                    mWinNumburList.add(randomNum)


                    break
                }

            }

        }


//        만들어진 당첨번호 6개를 -> 텍스트뷰에 표현
        Log.d("당첨번호 목록", mWinNumburList.toString())

        for (winNum in mWinNumburList) {

        }

//        보너스번호 생성

//        텍스트뷰에 배치
    }

    private fun setValues() {

    }
}