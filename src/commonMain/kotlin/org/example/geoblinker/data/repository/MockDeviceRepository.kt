package org.example.geoblinker.data.repository
import org.example.geoblinker.domain.model.Devices
import org.example.geoblinker.domain.repository.DeviceRepository

class MockDeviceRepository : DeviceRepository {
    override suspend fun getDevices(): Result<List<Devices>> = Result.success(listOf(
        Devices("861234567890123", "Toyota Camry", true, 15.5, 55.7558, 37.6173, "SIM001", 1707168000, 1),
        Devices("861234567890124", "Грузовик Scania", true, 0.0, 55.7512, 37.6184, "SIM002", 1707168500, 3),
        Devices("861234567890125", "Трекер Ребенка", false, 0.0, 55.7599, 37.6120, "SIM003", 1707168900, 10)
    ))
}
