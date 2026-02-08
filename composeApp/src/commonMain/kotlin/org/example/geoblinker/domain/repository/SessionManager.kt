package org.example.geoblinker.domain.repository
import com.russhwolf.settings.Settings

class SessionManager(private val settings: Settings) {
    private val KEY_TOKEN = "auth_token"
    fun saveToken(token: String) = settings.putString(KEY_TOKEN, token)
    fun getToken(): String? = settings.getStringOrNull(KEY_TOKEN)
    fun clear() = settings.remove(KEY_TOKEN)
}
