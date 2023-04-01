package com.example.korefugee.Community

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_translateresult.*
import java.io.File
import java.net.MalformedURLException
import java.net.URL

class Translateresult : AppCompatActivity() {
    lateinit var filepath:String

    private var url: URL? = null
    private var fileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translateresult)

        // intent로 category 받아오기
        if (intent.hasExtra("path")){
            filepath = intent.getStringExtra("path").toString()

        }

    }

}