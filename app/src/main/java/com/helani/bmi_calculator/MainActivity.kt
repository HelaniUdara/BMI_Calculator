package com.helani.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightTex = findViewById<EditText>(R.id.etHeight)
        val calculateBtn = findViewById<Button>(R.id.btnCalculate)

        calculateBtn.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightTex.text.toString()

            if (validateInput(weight,height)) {
                val bmi = (weight.toFloat()) / (((height.toFloat() / 100).toDouble()).pow(2.0))
                val bmi2decimal = String.format("%.2f", bmi).toFloat()
                displayBMI(bmi2decimal)
            }
        }
    }

    fun validateInput(weight:String?, height:String?): Boolean {
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    fun displayBMI(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}