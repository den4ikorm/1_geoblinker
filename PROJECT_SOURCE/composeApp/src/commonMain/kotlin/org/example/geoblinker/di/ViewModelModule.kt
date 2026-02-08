package org.example.geoblinker.di
import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf

val viewModelModule = module {
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/core/base/BaseViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/map/main/MapViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/map/MapViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/geofences/edit/GeofenceEditViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/geofences/list/GeofenceListViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/auth/AuthViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/auth/PhoneViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/auth/phone/PhoneViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/profile/ProfileViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/settings/SettingsViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/notifications/NotificationsViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/support/SupportViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/about/AboutViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/details/DeviceDetailsViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/binding/DeviceBindingViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/device_list/DeviceListViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/main/MainViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/main/RootViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/edit/DeviceEditViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/history/main/HistoryViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/history/player/PlayerViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/history/track/TrackViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/commands/main/CommandViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/commands/main/CommandConfirmViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/devices/DeviceListViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/root/RootViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/player/PlayerViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/commandconfirm/CommandConfirmViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/tripdetails/TripDetailsViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/commandhistory/CommandHistoryViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/androidMain/kotlin/org/example/geoblinker/core/platform/PlatformViewModel.kt(get()) }
    factory { /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/iosMain/kotlin/org/example/geoblinker/core/platform/PlatformViewModel.kt(get()) }
}
