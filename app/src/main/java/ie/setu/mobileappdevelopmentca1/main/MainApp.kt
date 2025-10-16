package ie.setu.mobileappdevelopmentca1.main

import android.app.Application
import ie.setu.mobileappdevelopmentca1.models.EventModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val events = ArrayList<EventModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Event started")
        events.add(EventModel("One", "About one..."))
        events.add(EventModel("Two", "About two..."))
        events.add(EventModel("Three", "About three..."))
    }
}