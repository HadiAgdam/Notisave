package ir.hadiagdamapps.notisave.ui.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color.primary,
            background = Color.background,
            surface = Color.surface,
        )
    ) {
        CompositionLocalProvider(LocalContentColor provides Color.textColor) {
            content()
        }
    }


}