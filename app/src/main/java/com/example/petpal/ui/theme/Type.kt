package com.example.petpal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.petpal.R
import org.w3c.dom.Text

val DmSansFamily = FontFamily(
    Font(R.font.dm_sans_regular, FontWeight.Normal),
    Font(R.font.dm_sans_medium, FontWeight.Medium),
    Font(R.font.dm_sans_semibold, FontWeight.SemiBold),
)

val FrauncesFamily = FontFamily(
    Font(R.font.fraunces_72pt_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.fraunces_72pt_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.fraunces_72pt_bold_italic, FontWeight.Bold, FontStyle.Italic),
)

//eyebrow styles
val EyebrowStyle = TextStyle(
    fontFamily = DmSansFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 10.sp,
    lineHeight = 14.sp,
    letterSpacing = 1.4.sp,
)

//italic headlines
val SerifDisplayStyle = TextStyle(
    fontFamily = FrauncesFamily,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Italic,
    fontSize = 48.sp,
    lineHeight = 40.5.sp,
    letterSpacing = (-0.3).sp,
)

//normal italic accent
val SerifHeadlineStyle = TextStyle(
    fontFamily = FrauncesFamily,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 22.sp,
    lineHeight = 26.sp,
    letterSpacing = (-0.2).sp,
)

//small serif
val SerifTitleStyle = TextStyle(
    fontFamily = FrauncesFamily,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = (-0.1).sp,
)

val Typography = Typography(
    //display, largest text
    displayLarge = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-1).sp
    ),
    displayMedium = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.5).sp
    ),
    displaySmall = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp
    ),

    //Headlines, screen titles
    headlineLarge = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.3).sp
    ),
    headlineMedium = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.2).sp
    ),
    headlineSmall = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),

    //section headers
    titleLarge = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    titleSmall = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),

    //normal text
    bodyLarge = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),

    //utilities, labels, chips etc
    labelLarge = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.1).sp
    ),
    labelMedium = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle(
        fontFamily    = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.6.sp
    ),
)
