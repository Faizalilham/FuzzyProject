package com.example.fuzzy_project

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import com.example.fuzzy_project.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var TB = 0.0
    private var BB = 0.0

    // FuzzyFication
    var result_TB :Array<Double> = arrayOf()
    var DK_TB :Array<String> = arrayOf()

    fun rumus_pertama(x: Double,c :Double, d : Double):Double{
        val hitung = (-(x - d ))/(d - c)
        return hitung
    }
    fun rumus_kedua(x: Double,a :Double, b : Double):Double{
        val hitung = (x-a)/(b-a)
        return hitung
    }

    var result_BB :Array<Double> = arrayOf()
    var DK_BB :Array<String> = arrayOf()

    //Variabel Inference
    var number_inference : Array<Double> = arrayOf()
    var DK_inference : Array<String> = arrayOf()
    var key : Int = 0
    var success : Boolean = false

    var number_disjuction : Array<Double> = arrayOf()
    var DK_disjuction : Array<String> = arrayOf()

    //Variabel  DefuzziFication
    val range_ts = Array<Int>(5){it*5}
    val range_as = Array<Int>(5){it*5+25}
    val range_s = Array<Int>(5){it*5+51}
    val range_ss = Array<Int>(5){it*5+76}
    var final_result = 0.0
    var string_result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggle.setOnClickListener {
            showMenu(it,R.menu.bottom_nav)
        }


        Result()
        declareAnimation()

    }

    // Show Popup Info
    private fun showMenu(v: View,  menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener {
            when(it.itemId){
               R.id.info -> startActivity(Intent(this,Activity_Info::class.java).also { finish() })
            }
            true
        }
        popup.setOnDismissListener {
            popup.dismiss()
        }
        // Show the popup menu.
        popup.show()
    }



    private fun fuzzyfication(){
        when{
            TB < 0.0 -> {
                result_TB += 0.0
            }
            TB in 40.0..115.9 -> {
                DK_TB +="Sangat Pendek"
                result_TB += 1.0
            }
            TB in 116.0..119.9 ->{
                val a = 115.0
                val b = 120.0
                DK_TB += "Sangat Pendek"
                DK_TB += "Pendek"
                result_TB += rumus_pertama(TB,a,b)
                result_TB += rumus_kedua(TB,a,b)

            }
            TB in 120.0..140.9 -> {
                DK_TB +="Pendek"
                result_TB += 1.0;
            }
            TB in 141.0..144.9 -> {
                val a = 140.0
                val b = 145.0
                DK_TB += "Pendek"
                DK_TB += "Sedang"
                result_TB += rumus_pertama(TB,a,b)
                result_TB += rumus_kedua(TB,a,b)
            };
            TB in 145.0..160.9 -> {
                DK_TB +="Sedang"
                result_TB += 1.0;
            }
            TB in 161.0..164.9 ->{
                val a = 160.0
                val b = 165.0
                DK_TB += "Sedang"
                DK_TB += "Tinggi"
                result_TB += rumus_pertama(TB,a,b)
                result_TB += rumus_kedua(TB,a,b)
            }
            TB in 165.0..180.9 -> {
                DK_TB += "Tinggi"
                result_TB += 1.0;
            }
            TB in 181.0..184.9 -> {
                val a = 180.0
                val b = 185.0
                DK_TB += "Tinggi"
                DK_TB += "Sangat Tinggi"
                result_TB += rumus_pertama(TB,a,b)
                result_TB += rumus_kedua(TB,a,b)
            }
            TB in 185.0..250.9 -> {
                DK_TB += "Sangat Tinggi"
                result_TB += 1.0;
            }
            TB > 250.9 -> {
                result_TB += 0.0
            }
            TB > 300.0 -> {
                Toast.makeText(this, "Data Tidak Valid", Toast.LENGTH_SHORT).show()
                println("Data Tidak Valid")
            }
        }



        when{
            BB < 0.0 -> result_TB += 0.0 ;
            BB in 2.5..40.9 -> {
                DK_BB +="Sangat Kurus"
                result_BB += 1.0
            }
            BB in 41.0..44.9 ->{
                val a = 40.0
                val b = 45.0
                DK_BB += "Sangat Kurus"
                DK_BB += "Kurus"
                result_BB += rumus_pertama(BB,a,b)
                result_BB += rumus_kedua(BB,a,b)

            }
            BB in 45.0..50.9 -> {
                DK_BB +="Kurus"
                result_BB += 1.0;
            }
            BB in 51.0..54.9 -> {
                val a = 50.0
                val b = 55.0
                DK_BB += "Kurus"
                DK_BB += "Biasa"
                result_BB += rumus_pertama(BB,a,b)
                result_BB += rumus_kedua(BB,a,b)
            };
            BB in 55.0..60.9 -> {
                DK_BB +="Biasa"
                result_BB += 1.0;
            }
            BB in 61.0..64.9 ->{
                val a = 60.0
                val b = 65.0
                DK_BB += "Biasa"
                DK_BB += "Berat"
                result_BB += rumus_pertama(BB,a,b)
                result_BB += rumus_kedua(BB,a,b)
            }
            BB in 65.0..80.9 -> {
                DK_BB += "Berat"
                result_BB += 1.0;
            }
            BB in 81.0..84.9 -> {
                val a = 80.0
                val b = 85.0
                DK_BB += "Berat"
                DK_BB += "Sangat Berat"
                result_BB += rumus_pertama(BB,a,b)
                result_BB += rumus_kedua(BB,a,b)
            }
            BB in 85.0..200.9 -> {
                DK_BB += "Sangat Berat"
                result_BB += 1.0;
            }
            BB > 200.9 -> {
                result_BB += 0.0
            }
            BB > 300.0 -> {
                Toast.makeText(this, "Data tidak valid", Toast.LENGTH_SHORT).show()
                println("Data Tidak Valid")
            };
        }
    }

    fun disjunction(arr : Array<String>){
        var tempo : Array<Int> = arrayOf()
        var tempo2 : Array<Int> = arrayOf()
        var tempo3 : Array<Double> = arrayOf()
        var tempo4 : Array<Int> = arrayOf()
        var tempo5 : Array<Int> = arrayOf()

        // Mengecek nama yang sama pada array, jika ada dikelompokkan dan masukkan index tsb ke dalam array baru
        for(i in DK_inference.indices){
            if(DK_inference[0] == DK_inference[i]){
                tempo += i
            }else{
                tempo2 += i
            }
        }

        for(j in tempo2){
            val a = tempo2[0]
            if(DK_inference[a] == DK_inference[j]){
                tempo4 += j
            }else{
                tempo5 += j
            }
        }


        println(tempo.toList())
        println(tempo2.toList())

        var result : Array<Double> = arrayOf()
        var result2 : Array<Double> = arrayOf()
        // Pengecekan Index, ambil index dgn nilai terbesar masukkan ke dalam variabel tempo3
        if(tempo5.isNotEmpty()){
            println(tempo.toList())
            println(tempo4.toList())
            println(tempo5.toList())
            // Clear
            if(tempo.size == 2){
                // Ambil Nilai string dari index terbesar
                if(tempo[0] > tempo[1]){
                    val a = tempo[0]
                    DK_disjuction += DK_inference[a]
                }else{
                    val a = tempo[1]
                    DK_disjuction += DK_inference[a]
                }


                // Ambil nilai integer dari index terbesar
                for(i in tempo4){
                    result += number_inference[i]
                }
                tempo3 += result.toList().sortedDescending().first()

                for(i in tempo4){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }
                for(i in tempo5){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }


            }else if(tempo4.size == 2){
                if(tempo4[0] > tempo4[1]){
                    val a = tempo4[0]
                    DK_disjuction += DK_inference[a]
                }else{
                    val a = tempo4[1]
                    DK_disjuction += DK_inference[a]
                }
                for(i in tempo4){
                    result += number_inference[i]
                }
                tempo3 += result.toList().sortedDescending().first()
                for(i in tempo){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }
                for(i in tempo5){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }

            }else if(tempo5.size == 2){
                if(tempo5[0] > tempo5[1]){
                    val a = tempo5[0]
                    DK_disjuction += DK_inference[a]
                }else{
                    val a = tempo5[1]
                    DK_disjuction += DK_inference[a]
                }
                for(i in tempo5){
                    result += number_inference[i]
                }
                tempo3 += result.toList().sortedDescending().first()
                for(i in tempo){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }
                for(i in tempo4){
                    tempo3 += number_inference[i]
                    DK_disjuction += DK_inference[i]
                }

            }else{
                println("error")
            }


        }else if(tempo.size == 2 && tempo2.size == 2){
            for(i in tempo){
                result += number_inference[i]
            }
            tempo3 += result.toList().sortedDescending().first()
            result = arrayOf()

            if(tempo[0] > tempo[1]){
                val a = tempo[0]
                DK_disjuction += DK_inference[a]
            }else{
                val a = tempo[0]
                DK_disjuction += DK_inference[a]
            }

            if(tempo2[0] > tempo2[1]){
                val a = tempo2[0]
                DK_disjuction += DK_inference[a]
            }else{
                val a = tempo2[0]
                DK_disjuction += DK_inference[a]
            }

//            for(i in tempo3){
//                DK_disjuction += DK_inference[i.toInt()]
//            }

            for(i in tempo2){
                result += number_inference[i]
            }

            result2 += result.toList().sortedDescending().first()

            for(i in result2){
//                DK_disjuction += DK_inference[i.toInt()]
                tempo3 += i
            }

        }else if(tempo.size == 3 && tempo2.size == 1){
            for(i in tempo){
                result += number_inference[i]
            }
            tempo3 += result.toList().sortedDescending().first()

            // Ambil nilai string dari tempo3
            for(i in tempo3){
                DK_disjuction += DK_inference[i.toInt()]
            }

            for(i in tempo2){
                DK_disjuction += DK_inference[i]
                tempo3 += number_inference[i]

            }

        }else if(tempo.size == 1 && tempo2.size == 3){
            for(i in tempo2){
                result += number_inference[i]

            }
            tempo3 += result.toList().sortedDescending().first()

            // Ambil nilai string dari tempo3
            for(i in tempo3){
                DK_disjuction += DK_inference[i.toInt()]
            }

            for(i in tempo){
                DK_disjuction += DK_inference[i]
                tempo3 += number_inference[i]

            }
        }else{
            println("Ups Ada data yang tidak terduga")
        }



        number_disjuction += tempo3

        println(number_disjuction.toList())
        println(DK_disjuction.toList())
    }

    private fun Inference(){
        if(TB < 0.0 || BB < 0.0){
            Toast.makeText(this, "Data tidak valid 1", Toast.LENGTH_SHORT).show()
            println("Data Tidak Valid 1")
        }else{
            if(TB >= 300.0 || BB >= 300.0){
                Toast.makeText(this, "Data tidak valid 2", Toast.LENGTH_SHORT).show()
                println("Data Tidak Valid 2")
            }else{
                if(DK_TB.isNotEmpty() && DK_BB.isNotEmpty()){
                    for(i in result_TB){
                        for(j in result_BB){
                            if(i <= j ){
                                number_inference += i
                            }else{
                                number_inference += j
                            }
                        }
                    }
                    success = true
                }else{
//                    Toast.makeText(this, "Data tidak valid 3", Toast.LENGTH_SHORT).show()
                    println("Data Tidak Valid 3")
                }
            }
        }

        for(i in DK_TB){
            for(j in DK_BB){
                if(i == "Sangat Pendek" && j == "Sangat Kurus"){
                    DK_inference += "Sangat Sehat"
                } else if(i == "Sangat Pendek" && j == "Kurus"){
                    DK_inference += "Sehat"
                }else if(i == "Sangat Pendek" && j == "Biasa"){
                    DK_inference += "Agak Sehat"
                }else if(i == "Sangat Pendek" && j == "Berat"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Sangat Pendek" && j == "Sangat Berat"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Pendek" && j == "Sangat Kurus"){
                    DK_inference += "Sehat"
                }else if(i == "Pendek" && j == "Kurus"){
                    DK_inference += "Sangat Sehat"
                }else if(i == "Pendek" && j == "Biasa"){
                    DK_inference += "Sehat"
                }else if(i == "Pendek" && j == "Berat"){
                    DK_inference += "Agak Sehat"
                }else if(i == "Pendek" && j == "Sangat Berat"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Sedang" && j == "Sangat Kurus"){
                    DK_inference += "Agak Sehat"
                }else if(i == "Sedang" && j == "Kurus"){
                    DK_inference += "Sangat Sehat"
                }else if(i == "Sedang" && j == "Biasa"){
                    DK_inference += "Sangat Sehat"
                }else if(i == "Sedang" && j == "Berat"){
                    DK_inference += "Agak Sehat"
                }else if(i == "Sedang" && j == "Sangat Berat"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Tinggi" && j == "Sangat Kurus"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Tinggi" && j == "Kurus"){
                    DK_inference += "Sehat"
                }else if(i == "Tinggi" && j == "Biasa"){
                    DK_inference += "Sangat Sehat"
                }else if(i == "Tinggi" && j == "Berat"){
                    DK_inference += "Sehat"
                }else if(i == "Tinggi" && j == "Sangat Berat"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Sangat Tinggi" && j == "Sangat Kurus"){
                    DK_inference += "Tidak Sehat"
                }else if(i == "Sangat Tinggi" && j == "Kurus"){
                    DK_inference += "Agak Sehat"
                }else if(i == "Sangat Tinggi" && j == "Biasa"){
                    DK_inference += "Sangat Sehat"
                }else if(i == "Sangat Tinggi" && j == "Berat"){
                    DK_inference += "Sehat"
                }else if(i == "Sangat Tinggi" && j == "Sangat Berat"){
                    DK_inference += "Agak Sehat"
                }else{
                    Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
                    println("Data tidak tersedia")
                }
            }
        }

        var cek = 0

        if(DK_inference.size > 2){
            for(i in DK_inference.indices){
                for(j in 1..DK_inference.size-1){
                    // Pengecekan apakah ada nama yang sama pada suatu array
                    if(DK_inference[0] == DK_inference[j] || DK_inference[i] == DK_inference[j]){
                        key = 1
                    }
                }
            }
        }else if(DK_inference.size == 2){
            if(DK_inference[0] == DK_inference[1]){
                cek = 1
            }
        }else{
            println("Lempar Defuzzi karena key = 0")
        }

        // Jika ada nama yang sama menjalankan disjunction
        // Jika ada nama yang sama menjalankan disjunction
        if(key == 1){
            disjunction(DK_inference)
        }else if(cek == 1){
            DK_disjuction += DK_inference[0]
            if(number_inference[0] > number_inference[1]){
                number_disjuction += number_inference[0]
            }else{
                number_disjuction += number_inference[1]
            }
        }
        else{
            for(i in number_inference){
                number_disjuction += i
            }
            for(i in DK_inference){
                DK_disjuction += i
            }
        }
    }

    private fun DefuzziFication(){
        if(DK_disjuction.isNotEmpty()){
            if(DK_disjuction.size == 4){
                if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Agak Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Agak Sehat" && DK_disjuction[3] == "Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2]) + (range_s.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Agak Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_as.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Tidak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ts.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Tidak Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sangat Sehat" && DK_disjuction[3] == "Tidak Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2]) + (range_ts.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Tidak Sehat" && DK_disjuction[3] == "Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2]) + (range_s.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Tidak Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ts.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Agak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_as.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Tidak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ts.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Agak Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Tidak Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Tidak Sehat" && DK_disjuction[3] == "Sangat Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2]) + (range_ss.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Tidak Sehat" && DK_disjuction[3] == "Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2]) + (range_s.sum() * number_disjuction[3])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ) + ( number_disjuction[3] * 5 ))
                }else{
                    println("Terjadi Kesalahan 1")
                }

            }else if(DK_disjuction.size == 3){
                if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_ts.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_as.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sangat Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_ss.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Sangat Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_s.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Tidak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_ts.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Tidak Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat" && DK_disjuction[2] == "Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])+ (range_s.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Sehat" && DK_disjuction[2] == "Agak Sehat"){
                    final_result = ( (range_ss.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])+ (range_as.sum() * number_disjuction[2])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5) + ( number_disjuction[2] * 5 ))
                }else{
                    println("Terjadi Kesalahan 2")
                }


            }else if(DK_disjuction.size == 2){
                if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Agak Sehat"){
                    final_result =  ( (range_ts.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sehat"){
                    final_result =  ( (range_ts.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Tidak Sehat" && DK_disjuction[1] == "Sangat Sehat"){
                    final_result =  ( (range_ts.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Tidak Sehat"){
                    final_result =  ( (range_as.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sehat"){
                    final_result =  ( (range_as.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Agak Sehat" && DK_disjuction[1] == "Sangat Sehat"){
                    final_result =  ( (range_as.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Tidak Sehat"){
                    final_result =  ( (range_s.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Agak Sehat"){
                    final_result =  ( (range_s.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sehat" && DK_disjuction[1] == "Sangat Sehat"){
                    final_result =  ( (range_s.sum() * number_disjuction[0] ) + (range_ss.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Tidak Sehat"){
                    final_result =  ( (range_ss.sum() * number_disjuction[0] ) + (range_ts.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Agak Sehat"){
                    final_result =  ( (range_ss.sum() * number_disjuction[0] ) + (range_as.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else if(DK_disjuction[0] == "Sangat Sehat" && DK_disjuction[1] == "Sehat"){
                    final_result =  ( (range_ss.sum() * number_disjuction[0] ) + (range_s.sum() * number_disjuction[1])) / (( number_disjuction[0] * 5 ) + (number_disjuction[1] *5))
                }else{
                    println("Terjadi Kesalahan 3")
                }

            }else{
                if(DK_disjuction[0] == "Tidak Sehat"){
                    final_result =   (range_ts.sum() * number_disjuction[0] ) / ( number_disjuction[0] * 5 )
                }else if(DK_disjuction[0] == "Agak Sehat"){
                    final_result =   (range_as.sum() * number_disjuction[0] ) / ( number_disjuction[0] * 5 )
                }else if(DK_disjuction[0] == "Sehat"){
                    final_result =   (range_s.sum() * number_disjuction[0] ) / ( number_disjuction[0] * 5 )
                }else if(DK_disjuction[0] == "Sangat Sehat"){
                    final_result =   (range_ss.sum() * number_disjuction[0] ) / ( number_disjuction[0] * 5 )
                }else{
                    println("Terjadi Kesalahan 4")
                }
            }
        }



        if(success == true){
            if(final_result.toInt() in 0..25){
                string_result = "Tidak Sehat"
            }else if(final_result.toInt() in 26..51){
                string_result = "Agak Sehat"
            }else if(final_result.toInt() in 52..76){
                string_result = "Sehat"
            }else if(final_result.toInt() in 77..150){
                string_result = "Sangat Sehat"
            }else{
                string_result = "Data kesehatan Tidak Valid"
            }
        }else{
            if(TB in 0.1..39.9 || BB in 0.1..2.4){
                string_result = "Data Inputan terlalu rendah untuk manusia bahkan bayi sekalipun"
            }else{
                string_result = "Data Tidak Valid "
            }

        }
    }

    private fun Result() {
        binding.BtnHitung.setOnClickListener {
            if(binding.ETTB.text!!.isNotBlank() && binding.ETBB.text!!.isNotBlank()){
                TB = binding.ETTB.text.toString().toDouble()
                BB = binding.ETBB.text.toString().toDouble()
                fuzzyfication()
                Inference()
                DefuzziFication()
                if(string_result != ""){
                    startActivity(Intent(this, Activity_Result::class.java).also {
                        it.putExtra("result", string_result)
                        it.putExtra("result_tb", result_TB)
                        it.putExtra("dk_tb", DK_TB)
                        it.putExtra("result_bb", result_BB)
                        it.putExtra("dk_bb", DK_BB)
                        it.putExtra("number_inference", number_inference)
                        it.putExtra("dk_inference", DK_inference)
                        it.putExtra("number_disjunction", number_disjuction)
                        it.putExtra("dk_disjunction", DK_disjuction)
                    })
                }else{
                    Snackbar.make(binding.layoutUtama,"Data tidak ada",Snackbar.LENGTH_SHORT).show()
                }

            }else{
                Snackbar.make(binding.layoutUtama,"Data tidak boleh kosong",Snackbar.LENGTH_SHORT).show()
            }
        }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun declareAnimation(){
        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        val rtl = AnimationUtils.loadAnimation(this,R.anim.rtl)
        val ltr = AnimationUtils.loadAnimation(this,R.anim.ltr)
        val ltr2 = AnimationUtils.loadAnimation(this,R.anim.ltr2)
        val ltr3 = AnimationUtils.loadAnimation(this,R.anim.ltr3)

        binding.image.startAnimation(ttb)
        binding.toggle.startAnimation(rtl)
        binding.textView.startAnimation(ltr)
        binding.textView2.startAnimation(ltr)
        binding.ETTB.startAnimation(ltr2)
        binding.ETBB.startAnimation(ltr3)
        binding.inputlayout1.startAnimation(ltr2)
        binding.inputlayout2.startAnimation(ltr3)
        binding.BtnHitung.startAnimation(ltr3)

    }



    }



