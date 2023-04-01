package com.example.korefugee.Guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_guide_explain.*

class GuideExplainActivity : AppCompatActivity() {
    lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_explain)


        // intent로 category 받아오기
        if (intent.hasExtra("title")){
            title = intent.getStringExtra("title").toString()
        }
        if(title == "procedure"){
            //어댑터 연결하기
            val adapter=ViewPagerAdapter()
            pager.adapter=adapter
        }
        else if (title == "rights"){
            val adapter=ViewPagerAdapter2()
            pager.adapter=adapter
        }



    }
}