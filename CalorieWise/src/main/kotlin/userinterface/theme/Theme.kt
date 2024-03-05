package userinterface.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = AppColors.Indigo,
    primaryVariant = AppColors.DarkerIndigo,
    onPrimary = AppColors.WhiteSmoke,
    secondary = AppColors.Red,
    error = AppColors.Red,
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
