```kotlin
package org.example.geoblinker

interface RecentSearchManager {
    suspend fun addSearch(query: String)
    suspend fun getRecentSearches(): List<String>
    suspend fun removeSearch(query: String)
    suspend fun clearAll()
}

class RecentSearchManagerImpl : RecentSearchManager {
    private val searches = mutableListOf<String>()
    private val maxSearches = 10

    override suspend fun addSearch(query: String) {
        val trimmed = query.trim()
        if (trimmed.isBlank()) return

        searches.remove(trimmed)
        searches.add(0, trimmed)

        if (searches.size > maxSearches) {
            searches.removeAt(searches.lastIndex)
        }
    }

    override suspend fun getRecentSearches(): List<String> {
        return searches.toList()
    }

    override suspend fun removeSearch(query: String) {
        searches.remove(query)
    }

    override suspend fun clearAll() {
        searches.clear()
    }
}
```

---

**ПРОДОЛЖЕНИЕ В ЧАСТИ 2** (из-за ограничения по длине ответа)

Следующие части будут включать:
- AUTH FEATURES (Login, Register)
- MAP FEATURE
- POINT DETAILS FEATURE (полный рефакторинг из MODULE #21)
- ADD POINT FEATURE (полный рефакторинг из MODULE #21)
- SEARCH FEATURE (полный рефакторинг из MODULE #21)
- PROFILE, SETTINGS, NOTIFICATIONS
- DEPENDENCY INJECTION (Koin)

Продолжить?

# GEOBLINKER - ЧАСТЬ 2: PRESENTATION LAYER

---

## ЧАСТЬ 4: AUTH FEATURES (Modules 01-05)

---


