package org.example.geoblinker

import org.example.geoblinker

interface GeoBlinkerApi {
    suspend fun fetchDevices(): List<Devices>
    suspend fun getDeviceDetails(id: String): Devices
}
