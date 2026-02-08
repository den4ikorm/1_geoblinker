```kotlin
package org.example.geoblinker

expect class DateFormatter() {
    fun formatDateTime(timestamp: Long): String
    fun formatDate(timestamp: Long): String
    fun formatTime(timestamp: Long): String
}
```


