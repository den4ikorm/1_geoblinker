package org.example.geoblinker.data.repository

import org.example.geoblinker.cache.AppDatabaseQueries
import org.example.geoblinker.domain.models.Devices
import org.example.geoblinker.domain.repository.DeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceRepositoryImpl(
    private val queries: AppDatabaseQueries
) : DeviceRepository {

    override suspend fun initializeDatabase() = withContext(Dispatchers.Default) {
        val current = queries.selectAllDevices().executeAsList()
        if (current.isEmpty()) {
            // Вставляем эталонное устройство из твоего плана
            queries.insertDevice(
                id = "1",
                imei = "8600123456789",
                name = "Lada Vesta (Test)",
                isConnected = 1L,
                registrationPlate = "A123BC86",
                modelName = "GT06",
                powerRate = 12600L,
                speed = 45.5,
                lat = 61.24,
                lng = 73.45,
                lastUpdate = 1707123456L
            )
        }
    }

    override suspend fun getDevices(): Result<List<Devices>> = withContext(Dispatchers.Default) {
        try {
            val list = queries.selectAllDevices().executeAsList().map { entity ->
                Devices(
                    id = entity.id,
                    imei = entity.imei,
                    name = entity.name,
                    isConnected = entity.isConnected,
                    registrationPlate = entity.registrationPlate,
                    modelName = entity.modelName,
                    powerRate = entity.powerRate,
                    speed = entity.speed,
                    lat = entity.lat,
                    lng = entity.lng,
                    markerId = 0L,
                    signalRate = 4L,
                    simei = "SIM-86",
                    bindingTime = entity.lastUpdate,
                    deviceType = "car",
                    typeStatus = "active"
                )
            }
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDeviceById(id: String): Result<Devices> {
        return try {
            val entity = queries.selectDeviceById(id).executeAsOne()
            Result.success(getDevices().getOrThrow().first { it.id == id })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
