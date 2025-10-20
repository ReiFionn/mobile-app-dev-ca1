package ie.setu.mobileappdevelopmentca1.models

interface EventStore {
    fun findAll(): List<EventModel>
    fun create(event: EventModel)
    fun update(event: EventModel)
    fun delete(event: EventModel)
}