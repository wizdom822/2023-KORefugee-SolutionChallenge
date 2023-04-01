package com.example.korefugee.My

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.korefugee.*
import com.example.korefugee.Login.LoginActivity
import com.example.korefugee.Map.MapActivity
import com.example.korefugee.Voca.VocaMainActivity
import com.example.korefugee.databinding.FragmentGuideBinding
import com.example.korefugee.databinding.FragmentMyBinding
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyFragment : Fragment() {

    lateinit var accesstoken:String
    lateinit var refreshtoken:String
    private lateinit var mybinding : FragmentMyBinding

    // api 이용하기 위한 객체
    val api = APIS.create()

    lateinit var language: String
    lateinit var name: String
    lateinit var birth: String
    lateinit var gender: String
    lateinit var status: String
    lateinit var nation: String
    lateinit var email: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mybinding = FragmentMyBinding.inflate(inflater, container, false)

        accesstoken = arguments?.getString("accesstoken").toString()
        refreshtoken = arguments?.getString("refreshtoken").toString()
        language =  arguments?.getString("language").toString()
        name =  arguments?.getString("name").toString()
        birth =  arguments?.getString("birth").toString()
        gender =  arguments?.getString("gender").toString()
        status =  arguments?.getString("status").toString()
        nation =  arguments?.getString("nation").toString()
        email =  arguments?.getString("email").toString()

        mybinding.name.setText(name)
        mybinding.position.setText(status)

        mybinding.myinfo.setOnClickListener {
            val intent = Intent(activity, MyIndoActivity::class.java)
            intent.putExtra("language", language)
            intent.putExtra("name", name)
            intent.putExtra("birth", birth)
            intent.putExtra("gender", gender)
            intent.putExtra("status", status)
            intent.putExtra("nation", nation)
            intent.putExtra("email", email)
            startActivity(intent)
        }


        mybinding.logout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        return mybinding.root    }

}