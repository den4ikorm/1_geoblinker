package org.example.geoblinker

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.example.geoblinker
import org.example.geoblinker

@Composable
fun App() {
    GeoBlinkerTheme {
        Navigator(PhoneScreen())
    }
}
