package org.example.geoblinker
import org.example.geoblinker

interface DeviceRepository {
    suspend fun getDevices(): Result<List<Devices>>
}
