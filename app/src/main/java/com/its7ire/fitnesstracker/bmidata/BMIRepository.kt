package com.its7ire.fitnesstracker.bmidata

class BMIRepository(

    private val dao: BMIDao

){


    suspend fun saveBMI(profile: BMI_Data){

        dao.insertBMI(profile)

    }



    suspend fun getBMI(): BMI_Data?{

        return dao.getBMI()

    }

}