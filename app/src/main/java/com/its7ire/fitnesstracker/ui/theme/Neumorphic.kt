package com.its7ire.fitnesstracker.ui.theme

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neumorphic(
    cornerRadius: Dp = 20.dp,
    elevation: Dp = 6.dp,
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
    backgroundColor: Color? = null
): Modifier = composed {
    val isDark = isSystemInDarkTheme()
    
    val baseColor = backgroundColor ?: MaterialTheme.colorScheme.background
    val lightShadow = lightShadowColor ?: if (isDark) Color(0x1AFFFFFF) else Color(0xCCFFFFFF)
    val darkShadow = darkShadowColor ?: if (isDark) Color(0x66000000) else Color(0x33000000)
    
    this.drawBehind {
        val blurRadius = elevation.toPx()
        val offset = blurRadius / 2f
        val cornerRadiusPx = cornerRadius.toPx()
        
        // Light shadow (top-left)
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = lightShadow.toArgb()
            if (blurRadius > 0) {
                frameworkPaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
            
            canvas.save()
            canvas.translate(-offset, -offset)
            canvas.drawRoundRect(
                left = 0f, top = 0f, right = size.width, bottom = size.height,
                radiusX = cornerRadiusPx, radiusY = cornerRadiusPx, paint = paint
            )
            canvas.restore()
        }
        
        // Dark shadow (bottom-right)
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = darkShadow.toArgb()
            if (blurRadius > 0) {
                frameworkPaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
            
            canvas.save()
            canvas.translate(offset, offset)
            canvas.drawRoundRect(
                left = 0f, top = 0f, right = size.width, bottom = size.height,
                radiusX = cornerRadiusPx, radiusY = cornerRadiusPx, paint = paint
            )
            canvas.restore()
        }
    }.background(baseColor, RoundedCornerShape(cornerRadius))
}
