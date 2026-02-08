package org.example.geoblinker.domain.repository

import org.example.geoblinker.core.network.*
import io.ktor.client.request.*
import io.ktor.client.call.*

class AuthRepository(private val client: HttpClientFactory) {
    private var currentSid: String? = null

    suspend fun login(user: String, pass: String): Result<String> {
        return try {
            // Имитация или реальный вызов к ibronevik
            // val response: BaseResponse<String> = client.create().post(NetworkConstants.BASE_URL_IBRONEVIK + "login") { ... }
            currentSid = "test_session_id_123" // Заглушка, пока не прописали эндпоинты
            Result.success(currentSid!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getSid() = currentSid
}
