package org.example.geoblinker.presentation.features.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.geoblinker.core.resources.AppStrings
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AboutScreen(viewModel: AboutViewModel, strings: AppStrings, onBack: () -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is AboutEffect.NavigateBack -> onBack()
                is AboutEffect.OpenExternalUrl -> { /* Логика открытия браузера */ }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(strings.get(state.titleKey)) },
                navigationIcon = { IconButton(onClick = { viewModel.onEvent(AboutEvent.BackClick) }) { Icon(Icons.Default.ArrowBack, null) } }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(strings.get(state.descriptionKey))
            Spacer(Modifier.height(16.dp))
            Text(state.appVersion, style = MaterialTheme.typography.labelSmall)
            Spacer(Modifier.weight(1f))
            TextButton(onClick = { viewModel.onEvent(AboutEvent.PrivacyPolicyClick) }) {
                Text(strings.get("btn_privacy_policy"))
            }
        }
    }
}