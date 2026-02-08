package org.example.geoblinker.presentation.features.map

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.geoblinker.domain.repository.DeviceRepository

class MapViewModel(private val repository: DeviceRepository) : ViewModel() {
    
    // Функция конвертации из Legacy (целые числа) в Double для карты
    private fun convertCoordinate(coord: Long): Double = coord.toDouble() / 1_000_000.0

    // Состояние позиции маркера
    private val _currentLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val currentLocation: StateFlow<Pair<Double, Double>?> = _currentLocation

    fun updateMarker(rawLat: Long, rawLon: Long) {
        val lat = convertCoordinate(rawLat)
        val lon = convertCoordinate(rawLon)
        _currentLocation.value = Pair(lat, lon)
    }
}
