package org.example.geoblinker.presentation.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.geoblinker.presentation.features.devices.ListScreen
import org.example.geoblinker.presentation.features.map.MapScreen
import org.example.geoblinker.presentation.features.profile.ProfileScreen

@Composable
fun MainContainer(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    label = { Text("Карта") },
                    selected = state.currentTab == MainTab.MAP,
                    onClick = { viewModel.onEvent(MainEvent.SelectTab(MainTab.MAP)) }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Список") },
                    selected = state.currentTab == MainTab.LIST,
                    onClick = { viewModel.onEvent(MainEvent.SelectTab(MainTab.LIST)) }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Профиль") },
                    selected = state.currentTab == MainTab.PROFILE,
                    onClick = { viewModel.onEvent(MainEvent.SelectTab(MainTab.PROFILE)) }
                )
            }
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)
        when (state.currentTab) {
            MainTab.MAP -> MapScreen(getViewModel()) // Условно получение VM
            MainTab.LIST -> ListScreen(state.listState) { viewModel.onEvent(it) }
            MainTab.PROFILE -> ProfileScreen(state.profileState) { viewModel.onEvent(it) }
        }
    }
}
