package userinterface.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp


val playfulFont = FontFamily(
    Font(
        resource = "font/PlayfulTime-BLBB8.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)

val shineTypewriterFont = FontFamily(
    Font(
        resource = "font/ShineTypewriter-lgwzd.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)


val AppTypography = Typography(
    // subtitle2 used as emphasized subtitle
    subtitle2 = TextStyle(
        fontFamily = shineTypewriterFont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    // h4 used as app name display
    h4 = TextStyle(
        fontFamily = playfulFont,
        fontWeight = FontWeight.Thin,
        fontSize = 75.sp,
        letterSpacing = 7.sp
    ),
    // h5 used as small subtitle
    h5 = TextStyle(
        fontFamily = shineTypewriterFont,
        fontWeight = FontWeight.Thin,
        fontSize = 25.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    )
)

