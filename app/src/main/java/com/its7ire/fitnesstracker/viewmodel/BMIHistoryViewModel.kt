package com.its7ire.fitnesstracker.viewmodel

import android.util.Log
import com.its7ire.fitnesstracker.bmidata.BMIRepository
import com.its7ire.fitnesstracker.bmidata.BMI_Data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class BMIViewModel(

    private val repository: BMIRepository

): ViewModel(){



    var age by mutableStateOf(0)

    var height by mutableStateOf(0f)

    var weight by mutableStateOf(0f)

    var bmi by mutableStateOf(0f)




    init {

        loadBMI()

    }



    private fun loadBMI(){


        viewModelScope.launch {


            val profile = repository.getBMI()


            profile?.let {


                age = it.age

                height = it.height

                weight = it.weight

                bmi = it.bmi


            }


        }


    }




    fun saveBMI(){


        viewModelScope.launch {


            val profile = BMI_Data(


                age = age,

                height = height,

                weight = weight,

                bmi = bmi


            )


            repository.saveBMI(profile)
            Log.d("BMI_DATABASE","Saved: $profile")


        }


    }


}