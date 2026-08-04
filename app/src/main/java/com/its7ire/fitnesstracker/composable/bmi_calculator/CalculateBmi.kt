package com.its7ire.fitnesstracker.composable.bmi_calculator




fun CalculateBmi(
   height: String,
   weight: String
): Double? {

   val h = height.toDoubleOrNull()
   val w = weight.toDoubleOrNull()

   if (h == null || w == null) {
      return null
   }

   if (h <= 0.0 || w <= 0.0) {
      return null
   }

   return w / (h * h)
}