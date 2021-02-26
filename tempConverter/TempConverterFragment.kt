package com.example.tempConverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firstKotlinApp.R
import com.example.firstKotlinApp.databinding.FragmentTempConverterBinding

class TempConverterFragment: Fragment(R.layout.fragment_temp_converter){
    private lateinit var binding: FragmentTempConverterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTempConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val celsiusButton = binding.celsiusButton
        val fahrenheitButton = binding.farenheitButton
        celsiusButton.setOnClickListener {
            celsiusFunction(celsiusButton)
        }
        fahrenheitButton.setOnClickListener{
            farenheitFunction(fahrenheitButton)
        }
    }

    fun celsiusFunction(view: View){
        val fahrenheitView = binding!!.userTemp
        val fahrenheitValue = fahrenheitView.text.toString()

        if(!fahrenheitValue.isBlank()){
            val celsiusCovertedValue = (fahrenheitValue.toDouble() - 32) * 5/9
            val celsiusValue = String.format("%.2f", celsiusCovertedValue)
            Toast.makeText(activity,
                    "$fahrenheitValue fahrenheit is $celsiusValue degrees celsius",
                    Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(activity, "You must enter a value to convert", Toast.LENGTH_LONG).show()
        }
    }

    fun farenheitFunction(view: View){
        val celsiusView = binding.userTemp
        val celsiusValue = celsiusView.text.toString()

        if(!celsiusValue.isBlank()){
            val farenheitConvertedValue = celsiusValue.toDouble() * 9/5 + 32
            val farenheitValue = String.format("%.2f", farenheitConvertedValue)
            Toast.makeText(activity,
                    "$celsiusValue degrees celsius is $farenheitValue farenheit",
                    Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(activity, "You must enter a value to convert", Toast.LENGTH_LONG).show()
        }
    }
}