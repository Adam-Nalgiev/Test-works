package com.na.rmuniverse.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.na.rmuniverse.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf)),
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf)),
        fontWeight = FontWeight.Bold,
        fontSize = 31.sp,
        lineHeight = 37.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),

)