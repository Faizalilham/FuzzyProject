package com.example.fuzzy_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.fuzzy_project.Constant.Constant
import com.example.fuzzy_project.databinding.ActivityInfoBinding

class Activity_Info : AppCompatActivity() {

    private lateinit var binding : ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        declareAnimation()

        binding.BtnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.infoKesehatan.setOnClickListener {
            startActivity(Intent(this,Activity_Info_Kesehatan::class.java).also{
                it.putExtra("id",Constant.kesehatan)
            })
        }
        binding.InfoTinggi.setOnClickListener {
            startActivity(Intent(this,Activity_Info_Kesehatan::class.java).also{
                it.putExtra("id",Constant.tinggi)
            })
        }
        binding.InfoBerat.setOnClickListener {
            startActivity(Intent(this,Activity_Info_Kesehatan::class.java).also{
                it.putExtra("id",Constant.berat)
            })
        }

    }

    private fun declareAnimation(){
        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        val ttb2 = AnimationUtils.loadAnimation(this,R.anim.ttb2)
        val ltr = AnimationUtils.loadAnimation(this,R.anim.ltr)
        val ltr2 = AnimationUtils.loadAnimation(this,R.anim.ltr2)
        val ltr3 = AnimationUtils.loadAnimation(this,R.anim.ltr3)

        binding.title.startAnimation(ttb2)
        binding.subTitle.startAnimation(ttb)
        binding.infoKesehatan.startAnimation(ltr)
        binding.InfoTinggi.startAnimation(ltr2)
        binding.InfoBerat.startAnimation(ltr3)
    }
}