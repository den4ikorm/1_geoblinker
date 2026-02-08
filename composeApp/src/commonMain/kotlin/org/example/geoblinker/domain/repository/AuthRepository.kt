package org.example.geoblinker.domain.repository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class AuthRepository(private val client: HttpClient, private val session: SessionManager) {
    suspend fun login(user: String, pass: String): Boolean {
        // Здесь логика запроса к ibronevik.ru
        // При успехе: session.saveToken(response.sid)
        return true // Пока заглушка успеха для тестов навигации
    }
}
