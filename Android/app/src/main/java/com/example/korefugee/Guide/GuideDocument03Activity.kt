package com.example.korefugee.Guide

import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.korefugee.databinding.ActivityGuideDocument03Binding
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.net.URL


class GuideDocument03Activity : AppCompatActivity() {
    var permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun PermissionCheck() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        for (permission: String in permission_list) {
            var chk = checkCallingOrSelfPermission(permission)
            if (chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permission_list, 0)
                break
            }
        }
    }
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityGuideDocument03Binding

    lateinit var title:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideDocument03Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val storage = FirebaseStorage.getInstance("gs://korefugee.appspot.com")

        if (intent.hasExtra("title")) {
            title = intent.getStringExtra("title").toString()
        }
        if(title == "출입국항" || title == "거주"){
            title = "Application for Recognition of Refugee Status"
        }
        else if(title == "이의"){
            title = "APPLICATION FOR APPEAL"

        }
        else if(title == "이의_상세"){
            title = "A Guide to Refugee Appeal Process"

        }
        else if(title == "동석"){
            title = "Application to Accompany Asylum Seeker"

        }
        else if(title == "재신청자"){
            title = "Application for Recognition of Refugee Status(Re-application)"

        }
        else if(title == "비밀"){
            title = "Oath of Confidentiality"

        }
        else if(title == "열람"){
            title = "APPLICATION FOR PERUSAL and COPYING"

        }
        binding.papertitle.setText(title)

        binding.back.setOnClickListener {
            val intent = Intent(this, GuideDocumenr01Activity::class.java)
            startActivity(intent)
            finish()
        }

        binding.button1.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/영어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }
        binding.button2.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/한국어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    Log.d("한국어",uri.toString())
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }
        binding.button3.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/러시아어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }
        binding.button4.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/카자흐어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }
        binding.button5.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/아랍어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }
        binding.button6.setOnClickListener {
            val storageRef = storage.reference
            storageRef.child("/말레이어/${title}.pdf")
                .downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }.addOnFailureListener { //이미지 로드 실패시
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                }
        }

    }


}