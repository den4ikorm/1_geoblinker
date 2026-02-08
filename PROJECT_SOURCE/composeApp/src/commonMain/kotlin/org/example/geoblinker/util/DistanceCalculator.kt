```kotlin
package org.example.geoblinker

import kotlin.math.*

interface DistanceCalculator {
    fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double

    fun formatDistance(meters: Double): String
}

class DistanceCalculatorImpl : DistanceCalculator {
    override fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val earthRadius = 6371000.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }

    override fun formatDistance(meters: Double): String {
        return when {
            meters < 1000 -> "${meters.roundToInt()} m"
            meters < 10000 -> "${(meters / 1000).format(1)} km"
            else -> "${(meters / 1000).roundToInt()} km"
        }
    }

    private fun Double.format(decimals: Int): String {
        return "%.${decimals}f".format(this)
    }
}
```


