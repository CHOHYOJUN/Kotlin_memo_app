package com.example.kotlin_memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlin_memoapp.databinding.ActivityMemoAddBinding

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // 이전버튼 클릭 - 이미 지정되어 있음 ( android.R.id.home )
            android.R.id.home -> {
                // 엑티비티 종료
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}