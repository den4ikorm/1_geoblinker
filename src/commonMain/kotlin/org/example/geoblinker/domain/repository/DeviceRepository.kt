package org.example.geoblinker.domain.repository
import org.example.geoblinker.domain.model.Devices

interface DeviceRepository {
    suspend fun getDevices(): Result<List<Devices>>
}
