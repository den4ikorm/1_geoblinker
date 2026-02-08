package org.example.geoblinker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.geoblinker

@Composable
expect fun BlinkerMap(
    modifier: Modifier,
    devices: List<Devices>,
    onMarkerClick: (String) -> Unit
)
