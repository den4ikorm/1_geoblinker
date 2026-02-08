```kotlin
package org.example.geoblinker

class CoordinatesFormatter {
    fun format(latitude: Double, longitude: Double): String {
        val latDir = if (latitude >= 0) "N" else "S"
        val lonDir = if (longitude >= 0) "E" else "W"
        return "${kotlin.math.abs(latitude).format(6)}° $latDir, ${kotlin.math.abs(longitude).format(6)}° $lonDir"
    }

    private fun Double.format(decimals: Int): String {
        return "%.${decimals}f".format(this)
    }
}
```


