package com.example.kotlin_memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.example.kotlin_memoapp.databinding.ActivityMemoAddBinding
import java.text.SimpleDateFormat
import java.util.*

class MemoAddActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoAddBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // toolbar 설정
        setSupportActionBar(binding.memoAddToolbar)
        title = "메모 추가"

        // 이전화면 이동 버튼
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 쓰레드를 준다.
        Thread {
            // 500 밀리세컨트만큼 딜레이
            SystemClock.sleep(500)

            // 사용자가 발생시킨 쓰레드에서는 화면처리가 불가능하다
            // runOnUiThread { } 구현필요
            runOnUiThread {
                // 포커스를 준다.
                binding.addMemoSubjet.requestFocus()

                // 키보드가 자동으로 올라오게 한다.
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                // 키패드 올로오면 어디에 입력 할건지 인자로 지정해줘야한다.
                imm.showSoftInput(binding.addMemoSubjet, InputMethodManager.SHOW_FORCED)
                // SHOW_FORCED - 는 강제
            }

        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // 이전버튼 클릭 - 이미 지정되어 있음 ( android.R.id.home )
            android.R.id.home -> {
                // 엑티비티 종료
                finish()
            }

            // 저장버튼
            R.id.add_menu_write -> {
                // 사용자가 입력한 내용을 가지고 온다.
                val memo_subject = binding.addMemoSubjet.text
                val memo_text = binding.addMemoText.text

                // 쿼리문
                var sql = """
                    insert into MemoTable (
                        meno_subject, memo_text, memo_date
                    ) values(? ? ?)
                """.trimIndent()

                // 데이터베이스 오픈
                val helper = DBHelper(this)

                // 현재 시간을 구한다. 디바이스 시간 가져옴
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                //  ?에 셋팅할 값
                var arg1 = arrayOf(memo_subject, memo_text, now)

                // 저장
                helper.writableDatabase.execSQL(sql, arg1)

                // DB 연결 종료
                helper.writableDatabase.close()

                // 현재 액티비티 종료
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}