package org.example.geoblinker.core.network

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: String? = null,
    val code: Int? = null,
    val data: T? = null,
    val message: String? = null,
    val sid: String? = null // Для сохранения сессии
)
