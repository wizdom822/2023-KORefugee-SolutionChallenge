package com.example.korefugee.Community

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityTranslateApp2Binding
import org.json.JSONArray
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class TranslateApp : AppCompatActivity() {
    lateinit var binding: ActivityTranslateApp2Binding

    lateinit var way:String

    lateinit var filePath: String
    lateinit var accesstoken:String
    lateinit var refreshtoken:String

    var language_short: String = "en"
    private val REQUEST_PERMISSIONS = 1

    private val timeStamp: String =
        SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTranslateApp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("way")&&intent.hasExtra("accesstoken")&&intent.hasExtra("refreshtoken")) {
            way = intent.getStringExtra("way").toString()
            accesstoken = intent.getStringExtra("accesstoken").toString()
            refreshtoken = intent.getStringExtra("refreshtoken").toString()
        }

        val jsonString = assets.open("lang_list.json").reader().readText()
        val jsonArray = JSONArray(jsonString)

        binding.language.setOnClickListener(View.OnClickListener { v ->
                val popupMenu = PopupMenu(this, v)
                val inflater = popupMenu.menuInflater
                val menu = popupMenu.menu
                inflater.inflate(R.menu.languagelist, menu)
                popupMenu.setOnMenuItemClickListener { item ->

                    var x =item.title.toString()  // 메뉴의 타이틀을 불러와서

                    binding.language.setText(x)    // 카테고리 텍스트뷰에 출력해줌
                    // 그 나라 언어와 연결하기
                    for (index in 0 until jsonArray.length()){

                        val jsonObject = jsonArray.getJSONObject(index)
                        val name = jsonObject.getString("name")
                        val language = jsonObject.getString("language")

                        if(name == x){
                            language_short = language

                        }
                    }
                    false
                }
                popupMenu.show()
            })




        val request =
            registerForActivityResult(ActivityResultContracts
                .StartActivityForResult())
            {
                it.data?.data?.let { uri->
                    contentResolver.takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    getRealPathFromURI(uri)?.let { it1 -> filePath = it1 }
                }
            }

        if(way == "camera"){
            // 카메라 앱 실행 및 api로 넘기기
           val storageDir: File? =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES) // 외장 메모리 공간에 저장하겠다
            val file = File.createTempFile(
                "JPEG_${timeStamp}_", // 이름
                ".jpg", // 형식
                storageDir // 저장될 공간
            )

            filePath = file.absolutePath // 절대 경로로

            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.korefugee.fileprovider", file
            )

            checkPermission()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 카메라 앱 구동
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI) // 우리 파일에 write 해줌 - 파일 정보 넘기기
            request.launch(intent)
        }
        else if(way == "pdf") {
            checkPermission()
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "application/pdf"
            request.launch(intent)

        }

        binding.startButton.setOnClickListener {
            val intent = Intent(this, Translateloading::class.java)
            intent.putExtra("way",way)
            intent.putExtra("language_short",language_short)
            intent.putExtra("file",filePath)
            intent.putExtra("accesstoken",accesstoken)
            intent.putExtra("refreshtoken",refreshtoken)
            startActivity(intent)
            finish()
        }


    }

    private fun checkPermission() {
        var permission = mutableMapOf<String, String>()
        permission["storageRead"] = Manifest.permission.READ_EXTERNAL_STORAGE
        permission["storageWrite"] =  Manifest.permission.WRITE_EXTERNAL_STORAGE

        // 현재 권한 상태 검사
        var denied = permission.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        // 마시멜로 버전 이후
        if(denied > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_PERMISSIONS)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            // inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            // 로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
    private fun getRealPathFromURI(contentUri: Uri): String? {
        if (contentUri.path!!.startsWith("/document")) {
            return contentUri.path
        }
        val id = DocumentsContract.getDocumentId(contentUri).split(":".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()[1]
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id
        val cursor = contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            columns,
            selection,
            null,
            null
        )
        try {
            val columnIndex = cursor!!.getColumnIndex(columns[0])
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor!!.close()
        }
        return null
    }
}