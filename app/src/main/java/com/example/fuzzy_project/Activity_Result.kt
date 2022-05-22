package com.example.fuzzy_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import com.example.fuzzy_project.databinding.ActivityResultBinding
import com.example.fuzzy_project.databinding.AlertActivityBinding

class Activity_Result : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    private lateinit var alertActivityBinding: AlertActivityBinding
    private var result_detail = ""
    private var result : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        getResult()
        declareAnimation()
        detail()
    }

    private fun declareAnimation(){
        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        val ttb2 = AnimationUtils.loadAnimation(this,R.anim.ttb2)
        val btt = AnimationUtils.loadAnimation(this,R.anim.btt)
        val btt2 = AnimationUtils.loadAnimation(this,R.anim.btt2)

        binding.textView.startAnimation(ttb2)
        binding.TvResult.startAnimation(ttb)
        binding.imageResult.startAnimation(btt)
        binding.TvTips.startAnimation(btt2)
    }

    private fun getResult(){
        result = intent.getStringExtra("result")
        binding.TvResult.text = result
        if(result == "Tidak Sehat"){
            binding.layout.setBackgroundResource(R.drawable.tidaksehat_background)
            binding.imageResult.setImageResource(R.drawable.sakit)
            binding.TvTips.text = getString(R.string.tidak_sehat)
        }else if(result == "Agak Sehat"){
            binding.layout.setBackgroundResource(R.drawable.agaksehat_background)
            binding.imageResult.setImageResource(R.drawable.agak_sakit)
            binding.TvTips.text = getString(R.string.agak_sehat)
        }else if(result == "Sehat"){
            binding.layout.setBackgroundResource(R.drawable.sehat_background)
            binding.imageResult.setImageResource(R.drawable.sehat)
            binding.TvTips.text = getString(R.string.sehat)
        }else if(result == "Sangat Sehat"){
            binding.layout.setBackgroundResource(R.drawable.sehat_background)
            binding.imageResult.setImageResource(R.drawable.sangat_sehat)
            binding.TvTips.text = getString(R.string.sangat_sehat)
        }else{
            binding.layout.setBackgroundResource(R.drawable.tidaksehat_background)
            binding.imageResult.setImageResource(R.drawable.ic_baseline_warning_24)
            binding.TvTips.text = "Terjadi kesalahan pada Inputanmu, perbaikilah! "
        }
    }

    override fun onPause() {
        super.onPause()
        result = ""
    }

    private fun detail(){
        val result_tb = intent.extras?.getSerializable("result_tb") as? Array<*>?
        val dk_tb = intent.getStringArrayExtra("dk_tb")
        val result_bb = intent.extras?.getSerializable("result_bb") as? Array<*>?
        val dk_bb = intent.getStringArrayExtra("dk_bb")
        val number_inference = intent.extras?.getSerializable("number_inference") as? Array<*>?
        val dk_inference = intent.getStringArrayExtra("dk_inference")
        val number_disjunction = intent.extras?.getSerializable("number_disjunction") as? Array<*>?
        val dk_disjunction = intent.getStringArrayExtra("dk_disjunction")

        binding.TvDetail.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alertActivityBinding = AlertActivityBinding.inflate(layoutInflater)
            alert.apply {
                setView(alertActivityBinding.root)
            }
            val alertDialog = alert.create()
            alertDialog.window?.attributes?.windowAnimations = R.style.myDialogAnimation
            alertActivityBinding.close.setOnClickListener {
                alertDialog.dismiss()

            }
            result_detail = """
            Fuzzyfication:
            ${result_tb?.toList()}
            ${dk_tb?.toList()}
            ${result_bb?.toList()}
            ${dk_bb?.toList()}
            Inference :
             ${number_inference?.toList()}
             ${dk_inference?.toList()}
            Disjunction : 
            ${number_disjunction?.toList()}
            ${dk_disjunction?.toList()}
        """.trimIndent()
            alertActivityBinding.tvDetail.text = result_detail
            alertDialog.show()





        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java).also{
            result = ""
            result_detail = ""
        })
        finish()
    }
}