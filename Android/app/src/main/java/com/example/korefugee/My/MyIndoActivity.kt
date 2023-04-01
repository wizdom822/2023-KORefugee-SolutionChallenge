package com.example.korefugee.My

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_my_indo.*

class MyIndoActivity : AppCompatActivity() {


    lateinit var language: String
    lateinit var name: String
    lateinit var birth: String
    lateinit var gender: String
    lateinit var status: String
    lateinit var nation: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_indo)

        language =  intent.getStringExtra("language").toString()
        name =  intent.getStringExtra("name").toString()
        birth =  intent.getStringExtra("birth").toString()
        gender =  intent.getStringExtra("gender").toString()
        status =  intent.getStringExtra("status").toString()
        nation =  intent.getStringExtra("nation").toString()
        email =  intent.getStringExtra("email").toString()

        email_tv.text = email
        name_tv.text = name
        position_tv.text = status
        nation_tv.text = nation
        language_tv.text = language
    }
}