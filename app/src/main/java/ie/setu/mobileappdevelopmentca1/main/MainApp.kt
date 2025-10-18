package ie.setu.mobileappdevelopmentca1.main

import android.app.Application
import ie.setu.mobileappdevelopmentca1.models.EventMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val events = EventMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Event started")
    }
}