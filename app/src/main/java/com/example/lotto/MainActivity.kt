package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //    내 번호 6개
    //    코틀린은 단순 배열 초기화 int[] arr = { }; 문법 지원 X

    //    숫자 목록을 파라미터로 넣으면 > Array로 만들어주는 함수 실행
    val mMyNumbers = arrayOf(13, 14, 24, 36, 37, 44)

    //    컴퓨터가 뽑은 당첨번호 6개를 저장할 ArrayList
    val mWinNumburList = ArrayList<Int>()
    var mBonusNum = 0 // 보너스 번호는, 매 판마다 새로 뽑아야함.

    //    당첨번호를 보여줄 6개의 텍스트뷰를 담아둘 ArrayList
    val mWinNumTextViewList = ArrayList<TextView>()

//    사용한 금액, 당첨된 금액 합산 변수
    var mUsedMoney = 0
    var mEarnMoney = 0L // 30억 이상의 담청 대비. Long 타입으로 설정.

//    각 등 수 별 횟수 카운팅 변수
    var rankCount1 = 0
    var rankCount2 = 0
    var rankCount3 = 0
    var rankCount4 = 0
    var rankCount5 = 0
    var rankCountFail = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    private fun setupEvents() {

        btnAutoBuy.setOnClickListener {
//            처음 눌리면 > 반복 구매 시작 > 1천만원 사용 할 때까지 반복

//            단순 반복 > 반복 속도가 너무 빨라서, UI가 멈춘것처럼 보인다.
            while (true){
                buyLotto()

                if(mUsedMoney >= 10000000){
                    break
                }
            }

//            반복 구매중에 눌리면 반복 종료
        }

        btnBuyLotto.setOnClickListener {

            buyLotto()
        }
    }

    private fun buyLotto() {

//        사용한 금액 늘려주기
        mUsedMoney += 1000

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


//        만들어진 당첨번호 6개를 -> 작은 수 ~ 큰 수 정리해서 -> 텍스트뷰에 표현
        mWinNumburList.sort()

        Log.d("당첨번호 목록", mWinNumburList.toString())

//        for > 돌면서, 당첨번호도 / 몇번째 바퀴인지도 필요 => 텍스트 뷰를 찾아내야함.
        mWinNumburList.forEachIndexed { index, winNum ->

//            순서에 맞는 텍스트뷰 추출 => 문구로 당첨번호 설정
            mWinNumTextViewList[index].text = winNum.toString()

        }


//        보너스번호 생성 -> 1 ~ 45 하나, 당첨번호와 겹치지 않게.
        while (true) {
            val randomNum = (Math.random() * 45 + 1).toInt()

            if (!mWinNumburList.contains(randomNum)) {
//                겹치지 않는 숫자 뽑음.

                mBonusNum = randomNum
                break
            }
        }


//        텍스트뷰에 배치
        txtBonusNum.text = mBonusNum.toString()

//        내 숫자 6개와 비교, 등 수 판정
        checkLottoRank()

    }

    private fun checkLottoRank() {

//        내 번호 목록 / 당첨 번호 목록 중 , 같은 숫자가 몇개?
        var correctCount = 0

//        내 번호를 하나씩 조회
        for (myNumber in mMyNumbers) {

//            당첨번호를 맞췄는가? => 당첨번호 목록에 내 번호가 들어있나?
            if (mWinNumburList.contains(myNumber)) {
                correctCount++
            }
        }

//        맞춘 개수에 따른 등 수 판정
        when (correctCount) {
            6 -> {
                Toast.makeText(this, "1등 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                mEarnMoney += 3000000000
                rankCount1++
            }
            5 -> {
//                보너스 번호를 맞췄는지? => 보너스 번호가 내 번호 목록에 들어있나?
                if (mMyNumbers.contains(mBonusNum)) {
                    Toast.makeText(this, "2등 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                    mEarnMoney += 50000000
                    rankCount2++
                } else {
                    Toast.makeText(this, "3등 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                    mEarnMoney += 2000000
                    rankCount3++
                }
            }
            4 -> {
                Toast.makeText(this, "4등 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                mEarnMoney += 50000
                rankCount4++
            }
            3 -> {
                Toast.makeText(this, "5등 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                mUsedMoney -= 5000
                rankCount5++

            }
            else -> {
//                Toast.makeText(this, "꽝 입니다. 맞은 개수 ${correctCount}", Toast.LENGTH_SHORT).show()
                rankCountFail++
            }
        }

//        사용 금액 / 당첨 금액을 텍스트뷰에 각각 반영
        txtUsedMoney.text = "${NumberFormat.getInstance().format(mUsedMoney)} 원"
        txtEarnMoney.text = "${NumberFormat.getInstance().format(mEarnMoney)} 원"

//        등 수 별 횟수도 텍스트뷰에 반영
        txtRankCount1.text = "${rankCount1} 회"
        txtRankCount2.text = "${rankCount2} 회"
        txtRankCount3.text = "${rankCount3} 회"
        txtRankCount4.text = "${rankCount4} 회"
        txtRankCount5.text = "${rankCount5} 회"
        txtRankCountFail.text = "${rankCountFail} 회"


    }

    private fun setValues() {

        mWinNumTextViewList.add(txtWinNum01)
        mWinNumTextViewList.add(txtWinNum02)
        mWinNumTextViewList.add(txtWinNum03)
        mWinNumTextViewList.add(txtWinNum04)
        mWinNumTextViewList.add(txtWinNum05)
        mWinNumTextViewList.add(txtWinNum06)
    }
}