package userinterface.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.platform.Font
import java.io.File

/* Font resources:
https://www.fontspace.com/playful-time-star-font-f109394
https://www.fontspace.com/magic-saturday-font-f88356

 */

val playfulFont = FontFamily(
    Font(
        resource = "font/PlayfulTime-BLBB8.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)

val magicSaturdayFont = FontFamily(
    Font(
        resource = "font/MagicSaturday-rg1OA.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)


val AppTypography = Typography(
    caption = TextStyle(
        fontFamily = magicSaturdayFont,
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

