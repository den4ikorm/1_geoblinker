package org.example.geoblinker.presentation.features.auth
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.geoblinker.presentation.features.devices.DeviceListScreen

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(backgroundColor = Color(0xFF050A18)) {
            Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
                Text("GEOBLINKER", color = Color(0xFF00E5FF), style = MaterialTheme.typography.h4)
                Spacer(Modifier.height(40.dp))
                Button(
                    onClick = { navigator.replace(DeviceListScreen()) },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00E5FF))
                ) { Text("ВОЙТИ", color = Color.Black) }
            }
        }
    }
}
