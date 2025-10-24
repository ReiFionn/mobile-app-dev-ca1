package ie.setu.mobileappdevelopmentca1.models

import timber.log.Timber.i

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
        val foundEvent: EventModel? = events.find { e -> e.id == event.id }
        if (foundEvent != null) {
            foundEvent.title = event.title
            foundEvent.description = event.description
            foundEvent.year = event.year
            foundEvent.month = event.month
            foundEvent.day = event.day
            foundEvent.type = event.type
            logAll()
        }
    }

    override fun delete(event: EventModel) {
        val foundEvent: EventModel? = events.find { e -> e.id == event.id }
        if (foundEvent != null) {
            events.remove(event)
            logAll()
        }
    }

    fun logAll() {
        events.forEach{ i("$it")}
    }
}