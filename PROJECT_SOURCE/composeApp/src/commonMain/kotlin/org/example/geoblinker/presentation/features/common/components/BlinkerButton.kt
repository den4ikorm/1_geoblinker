package org.example.geoblinker
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BlinkerButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, isLoading: Boolean = false) {
    Button(onClick = onClick, modifier = modifier, enabled = !isLoading) {
        if (isLoading) CircularProgressIndicator() else Text(text)
    }
}
