package com.example.korefugee.Community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.korefugee.R
import com.example.korefugee.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    private lateinit var translatebinding : FragmentCommunityBinding
    // Translated 로 수정!

    lateinit var way:String
    lateinit var accesstoken:String
    lateinit var refreshtoken:String
    lateinit var language_short:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        translatebinding = FragmentCommunityBinding.inflate(inflater, container, false)

        accesstoken = arguments?.getString("accesstoken").toString()
        refreshtoken = arguments?.getString("refreshtoken").toString()

        translatebinding.wayRadioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId){
                R.id.camera -> {
                    way = "camera"
                }

                R.id.pdf -> {
                    way = "pdf"

                }
            }
        }

        translatebinding.startButton.setOnClickListener {
            val intent = Intent(activity, TranslateApp::class.java)
            intent.putExtra("way",way)
            intent.putExtra("accesstoken",accesstoken)
            intent.putExtra("refreshtoken",refreshtoken)
            startActivity(intent)
        }

        return translatebinding.root
    }

}