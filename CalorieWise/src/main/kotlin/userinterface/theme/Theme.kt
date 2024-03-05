package userinterface.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/* references:
https://www.jetpackcompose.net/themes-in-jetpack-compose
 */

private val LightColorPalette = lightColors(
    primary = Indigo,
    primaryVariant = DarkerIndigo,
    onPrimary = WhiteSmoke,
    secondary = Red,
    error = Red,
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
