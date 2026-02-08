package org.example.geoblinker

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiService(private val client: HttpClient) {
    private val BASE_URL = "https://api.geoblinker.ru"

    // 1. Запрос кода (СМС)
    suspend fun requestOtp(phone: String): Boolean {
        return try {
            val response = client.post("$BASE_URL/auth/request-otp") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone))
            }
            response.status == HttpStatusCode.OK
        } catch (e: Exception) { false }
    }

    // 2. Проверка кода и получение токена
    suspend fun verifyOtp(phone: String, code: String): String? {
        return try {
            val response = client.post("$BASE_URL/auth/verify") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "code" to code))
            }
            if (response.status == HttpStatusCode.OK) response.bodyAsText() else null
        } catch (e: Exception) { null }
    }
}
