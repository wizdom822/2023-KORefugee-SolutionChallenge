package com.example.korefugee

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import java.io.File

// request model
data class LoginModel(

    var email : String? =null ,
    var password : String? =null
)

data class SignUpModel(
    var email : String? =null ,
    var password : String? =null,
    var name : String? =null,
    var birth : String? =null,
    var gender : String? =null,
    var status : String? =null,
    var nation : String? =null,
    var language : String? =null
)

data class TransModel(
    val imgName: String,
    val transLan: String,
    val file: File
)
data class response(
    val imgName: String,
    val transLan: String,
    val file: File
)
// Respond Model
data class Login_R_Model(
    val code: Int,
    val error: String,
    val success: String,
    val token: Token
){
    data class Token(
        val accesstoken: String,
        val refreshtoken: String
    )
}

data class SignUp_R_Model(
    var success:String? = null,
    var error:String? = null
)

data class MY_R_Model(
    val data: Data,
    val error: String,
    val message: String,
    val success: String
) {
    data class Data(
        val birth: String,
        val email: String,
        val gender: String,
        val language: String,
        val name: String,
        val nation: String,
        val password: String,
        val status: String,
        val userId: Int
    )
}

data class WordDate_R_Model(
    val data: List<Data>,
    val error: String,
    val success: String
) {
    data class Data(
        val studyDate: Int
    )
}



data class WordList_R_Model(
    val `data`: List<Data>,
    val error: String,
    val success: String
) {
    data class Data(
        val wordId: Int,
        val words: String,
        val wordP:String
    )
}

data class TRANS_R_Model(
    val email: String,
    val fileExtension: String,
    val imgId: Int,
    val imgName: String,
    val imgPath: String,
    val transLan: String,
    val transPath: Any
)

data class Place(
    val name: String,
    val latLng: LatLng,
    val address: LatLng,
    val rating: Float
)