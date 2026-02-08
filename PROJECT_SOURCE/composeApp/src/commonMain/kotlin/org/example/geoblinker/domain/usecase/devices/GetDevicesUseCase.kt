package org.example.geoblinker

import org.example.geoblinker

class GetDevicesUseCase(private val repository: DeviceRepository) {
    suspend operator fun invoke() = repository.getDevices()
}
