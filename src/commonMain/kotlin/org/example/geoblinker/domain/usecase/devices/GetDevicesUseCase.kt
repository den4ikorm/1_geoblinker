package org.example.geoblinker.domain.usecase.devices

import org.example.geoblinker.domain.repository.DeviceRepository

class GetDevicesUseCase(private val repository: DeviceRepository) {
    suspend operator fun invoke() = repository.getDevices()
}
