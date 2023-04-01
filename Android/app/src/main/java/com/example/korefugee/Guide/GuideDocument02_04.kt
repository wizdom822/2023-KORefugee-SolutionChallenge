package com.example.korefugee.Guide

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityGuideDocumenr01Binding
import com.example.korefugee.databinding.ActivityGuideDocument0204Binding

class GuideDocument02_04 : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityGuideDocument0204Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGuideDocument0204Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            val intent = Intent(this, GuideDocument03Activity::class.java)
            intent.putExtra("title", "열람")
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, GuideDocument03Activity::class.java)
            intent.putExtra("title", "동석")
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this, GuideDocument03Activity::class.java)
            intent.putExtra("title", "비밀")
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            val intent = Intent(this, GuideDocument03Activity::class.java)
            intent.putExtra("title", "재신청자")
            startActivity(intent)
        }
        binding.moreSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hikorea.go.kr/board/BoardApplicationListR.pt?page=1"))
            startActivity(intent)
        }

    }
}