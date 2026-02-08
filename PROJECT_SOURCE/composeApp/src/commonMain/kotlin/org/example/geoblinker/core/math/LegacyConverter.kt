package org.example.geoblinker.core.math

import kotlin.math.roundToLong

object LegacyConverter {
    // Координаты из API (Int) в Double (Бетон)
    fun convertCoordinate(value: Long): Double = value.toDouble() / 1_000_000.0
    
    // Скорость из км/ч в м/с для расчетов
    fun kmhToMs(speed: Double): Double = speed / 3.6
    
    // IMEI из String в Long (для отправки на gps666)
    fun imeiToLong(imei: String): Long = imei.toLongOrNull() ?: 0L
}
