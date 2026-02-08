```kotlin
package org.example.geoblinker

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.example.geoblinker

val platformModule = module {
    single { LocationProvider(androidContext()) }
}
```


