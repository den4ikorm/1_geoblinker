package org.example.geoblinker.core.network

object NetworkConstants {
    const val BASE_URL_IBRONEVIK = "https://ibronevik.ru/taxi/c/0/api/v1/"
    const val BASE_URL_GPS666 = "https://www.gps666.net/mapi"
    
    // Эндпоинты Ibronevik
    const val LOGIN = "login"
    const val GET_CARS = "get_cars"
    const val GET_HISTORY = "get_history"
    
    // Эндпоинты GPS666 (IMEI)
    const val IMEI_LOGIN = "login"
    const val IMEI_DEVICES = "get_devices"
    const val IMEI_TRACK = "get_track"
}
