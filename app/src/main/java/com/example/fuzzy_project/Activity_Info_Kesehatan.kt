package com.example.fuzzy_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fuzzy_project.Constant.Constant
import com.example.fuzzy_project.databinding.ActivityInfoKesehatanBinding

class Activity_Info_Kesehatan : AppCompatActivity() {
    private lateinit var binding : ActivityInfoKesehatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoKesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnBack.setOnClickListener {
            startActivity(Intent(this,Activity_Info::class.java).also{finish()})
        }
        val getId = intent.getIntExtra("id",0)
        when(getId){
            Constant.kesehatan ->{

                binding.TVJudul.text = getString(R.string.Judul_Sehat)
                binding.Nomor1.text = getString(R.string.sub_TS1)
                binding.Sub1.text = getString(R.string.TS1)
                binding.Nomor2.text = getString(R.string.sub_TS2)
                binding.Sub2.text = getString(R.string.TS2)
                binding.Nomor3.text = getString(R.string.sub_TS3)
                binding.Sub3.text = getString(R.string.TS3)
                binding.Nomor4.text = getString(R.string.sub_TS4)
                binding.Sub4.text = getString(R.string.TS4)
                binding.Nomor5.text = getString(R.string.sub_TS5)
                binding.Sub5.text = getString(R.string.TS5)

            }
            Constant.tinggi ->{
                binding.TVJudul.text = getString(R.string.Judul_Tinggi)
                binding.Nomor1.text = getString(R.string.sub_T1)
                binding.Sub1.text = getString(R.string.T1)
                binding.Nomor2.text = getString(R.string.sub_T2)
                binding.Sub2.text = getString(R.string.T2)
                binding.Nomor3.text = getString(R.string.sub_T3)
                binding.Sub3.text = getString(R.string.T3)
                binding.Nomor4.text = getString(R.string.sub_T4)
                binding.Sub4.text = getString(R.string.T4)

            }
            Constant.berat ->{
                binding.TVJudul.text = getString(R.string.Judul_Berat)
                binding.Nomor1.text = getString(R.string.sub_B1)
                binding.Sub1.text = getString(R.string.B1)
                binding.Nomor2.text = getString(R.string.sub_B2)
                binding.Sub2.text = getString(R.string.B2)
                binding.Nomor3.text = getString(R.string.sub_B3)
                binding.Sub3.text = getString(R.string.B3)
                binding.Nomor4.text = getString(R.string.sub_B4)
                binding.Sub4.text = getString(R.string.B1)
            }
        }

    }



}