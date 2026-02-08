package org.example.geoblinker.core.settings

class SessionManager {
    private var token: String? = null
    var sid: String? = null

    fun saveSession(newToken: String, newSid: String) {
        token = newToken
        sid = newSid
    }

    fun getSid(): String? = sid
}
