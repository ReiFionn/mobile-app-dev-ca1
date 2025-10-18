package ie.setu.mobileappdevelopmentca1.models

import timber.log.Timber.i
import kotlin.math.log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class EventMemStore : EventStore {
    val events = ArrayList<EventModel>()

    override fun findAll(): List<EventModel> {
        return events
    }

    override fun create(event: EventModel) {
        event.id = getId()
        events.add(event)
        logAll()
    }

    override fun update(event: EventModel) {
        var foundEvent: EventModel? = events.find { e -> e.id == event.id }
        if (foundEvent != null) {
            foundEvent.title = event.title
            foundEvent.description = event.description
            logAll()
        }
    }
    fun logAll() {
        events.forEach{ i("$it")}
    }
}