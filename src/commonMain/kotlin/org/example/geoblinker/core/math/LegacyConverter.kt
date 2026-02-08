package org.example.geoblinker.core.math
object LegacyConverter {
    fun formatSpeed(speedMps: Double): String = "${(speedMps * 3.6).toInt()} км/ч"
    fun getStatusColor(isConnected: Boolean): Long = if (isConnected) 0xFF4CAF50 else 0xFF9E9E9E
    fun formatRadius(radius: Float): String = "${radius.toInt()} м"
}
