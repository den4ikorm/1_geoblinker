package org.example.geoblinker
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun GeoBlinkerTheme(content: @Composable () -> Unit) {
    val darkColorScheme = darkColorScheme(primary = BlinkerBlue, background = BlinkerDark)
    MaterialTheme(colorScheme = darkColorScheme, content = content)
}
