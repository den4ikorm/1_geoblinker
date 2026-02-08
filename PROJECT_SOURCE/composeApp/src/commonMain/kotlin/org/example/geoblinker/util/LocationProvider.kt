```kotlin
package org.example.geoblinker

data class Location(
    val latitude: Double,
    val longitude: Double
)

expect class LocationProvider {
    suspend fun getCurrentLocation(): Location?
    suspend fun reverseGeocode(latitude: Double, longitude: Double): String?
}
```


