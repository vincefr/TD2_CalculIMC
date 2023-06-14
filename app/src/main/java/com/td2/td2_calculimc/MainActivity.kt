package com.td2.td2_calculimc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editTextName: EditText
    lateinit var calculateButton: Button
    lateinit var resultsTextView: TextView
    lateinit var heightEditText: NumberPicker
    lateinit var weightEditText: NumberPicker
    lateinit var imageViewBodyType: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.setBackgroundColor(Color.CYAN)
        editTextName = findViewById(R.id.editTextName)
        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        resultsTextView = findViewById(R.id.resultsTextView)
        calculateButton = findViewById(R.id.calculateButton)
        imageViewBodyType = findViewById(R.id.imageViewBodyType)
        resultsTextView.visibility= View.INVISIBLE
        imageViewBodyType.visibility= View.INVISIBLE

        calculateButton.setOnClickListener{
            var name = editTextName.text.toString()
            var weight = weightEditText.value.toString().toDouble()
            var height = heightEditText.value.toString().toDouble() /100
            var bmi = roundToNumDigits(weight / Math.pow(height,2.0), 2)

            heightEditText.minValue = 20
            heightEditText.maxValue = 250
            heightEditText.setValue(160)
            heightEditText.wrapSelectorWheel =false
            weightEditText.minValue =20
            weightEditText.maxValue =250
            weightEditText.setValue(60)
            weightEditText.wrapSelectorWheel = false

            resultsTextView.visibility = View.VISIBLE
            imageViewBodyType.visibility= View.VISIBLE

            val bmiResult: Any = when {
                bmi < 18.5 -> {
                    imageViewBodyType.setImageResource(R.drawable.maigre)
                    "Vous êtes maigre."
                }
                bmi < 25 -> {
                    imageViewBodyType.setImageResource(R.drawable.normal)
                    "Vous avez une corpulence normale."
                }
                bmi < 30 -> {
                    imageViewBodyType.setImageResource(R.drawable.surpoids)
                    "Vous êtes en surpoids."
                }
                else -> {
                    imageViewBodyType.setImageResource(R.drawable.obese)
                    "Vous êtes obèse."
                }


            }

            resultsTextView.text = "\n Bonjour $name,\n Le verdict est $bmi IMC\n $bmiResult"
        }


    }
}