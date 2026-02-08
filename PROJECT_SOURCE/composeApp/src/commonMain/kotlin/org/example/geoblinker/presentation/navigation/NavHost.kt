package org.example.geoblinker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

@Serializable
sealed class Destination {
    @Serializable data object Phone : Destination()
    @Serializable data object Map : Destination()
    @Serializable data object List : Destination()
    @Serializable data object Settings : Destination()
    @Serializable data object History : Destination()
    @Serializable data object GeofenceList : Destination()
    @Serializable data object Commands : Destination()
    @Serializable data object Notifications : Destination()
    @Serializable data object Profile : Destination()
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Map) {
        composable<Destination.Phone> { PhoneScreen().Content() }
        composable<Destination.Map> { MapScreen().Content() }
        composable<Destination.List> { DeviceListScreen().Content() }
        composable<Destination.Settings> { SettingsMainScreen().Content() }
        composable<Destination.History> { HistoryScreen().Content() }
        composable<Destination.GeofenceList> { GeofenceListScreen().Content() }
        composable<Destination.Commands> { CommandScreen().Content() }
        composable<Destination.Notifications> { NotificationScreen().Content() }
        composable<Destination.Profile> { ProfileScreen().Content() }
    }
}
