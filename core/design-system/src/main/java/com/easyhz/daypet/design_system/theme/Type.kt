package com.easyhz.daypet.design_system.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.easyhz.daypet.design_system.R

private val AppleSDGothicNeo = FontFamily(
    Font(R.font.apple_sd_gothic_neo_extra_bold, FontWeight.ExtraBold),
    Font(R.font.apple_sd_gothic_neo_bold, FontWeight.Bold),
    Font(R.font.apple_sd_gothic_neo_semi_bold, FontWeight.SemiBold),
    Font(R.font.apple_sd_gothic_neo_medium, FontWeight.Medium),
    Font(R.font.apple_sd_gothic_neo_regular, FontWeight.Normal),
    Font(R.font.apple_sd_gothic_neo_light, FontWeight.Light),
    Font(R.font.apple_sd_gothic_neo_ultra_light, FontWeight.ExtraLight),
    Font(R.font.apple_sd_gothic_neo_thin, FontWeight.Thin),
)

private val LetterSpacing = 0.sp

val Heading1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Bold,
    color = Primary2,
    fontSize = 22.sp,
    lineHeight = 22.sp,
    letterSpacing = LetterSpacing,
)

val Body1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    letterSpacing = LetterSpacing,
)
