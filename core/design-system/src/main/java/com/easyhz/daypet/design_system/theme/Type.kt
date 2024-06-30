package com.easyhz.daypet.design_system.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    color = TextColor,
    fontSize = 22.sp,
    lineHeight = 22.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Heading2 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Heading3 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.SemiBold,
    color = TextColor,
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Heading4 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Bold,
    color = TextColor,
    fontSize = 18.sp,
    lineHeight = 18.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Heading5 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.SemiBold,
    color = TextColor,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Body1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = TextColor,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
)

val Body2 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.SemiBold,
    color = TextColor,
    fontSize = 14.sp,
    letterSpacing = LetterSpacing,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Body3 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = TextColor,
    fontSize = 18.sp,
    letterSpacing = LetterSpacing,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Body4 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = TextColor,
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)


val SubHeading1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = TextColor,
    fontSize = 14.sp,
    lineHeight = 14.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Medium,
    color = SubTextColor,
    fontSize = 14.sp,
    lineHeight = 14.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody2 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = SubTextColor,
    fontSize = 12.sp,
    lineHeight = 12.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody3 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = SubTextColor,
    fontSize = 18.sp,
    letterSpacing = LetterSpacing,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)


val Caption1 = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Normal,
    color = SubTextColor,
    fontSize = 10.sp,
    letterSpacing = LetterSpacing,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Title = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.Bold,
    color = TextColor,
    fontSize = 24.sp,
    lineHeight = 24.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)


val SubTitle = TextStyle(
    fontFamily = AppleSDGothicNeo,
    fontWeight = FontWeight.SemiBold,
    color = TextColor,
    fontSize = 18.sp,
    lineHeight = 18.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)