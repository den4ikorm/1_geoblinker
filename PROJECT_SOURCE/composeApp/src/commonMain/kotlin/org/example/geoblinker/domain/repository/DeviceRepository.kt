package org.example.geoblinker.domain.repository

import org.example.geoblinker.core.network.*
import org.example.geoblinker.core.math.LegacyConverter

class DeviceRepository(private val authRepo: AuthRepository) {
    
    suspend fun getDevices(): List<Map<String, Any>> {
        val sid = authRepo.getSid() ?: return emptyList()
        // Тут будет логика запроса get_cars
        return emptyList() 
    }
}
