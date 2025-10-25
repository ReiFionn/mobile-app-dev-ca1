package ie.setu.mobileappdevelopmentca1

import ie.setu.mobileappdevelopmentca1.models.EventModel
import org.junit.Test
import kotlin.test.assertEquals

class EventModelTest {
    @Test
    fun eventModelDefaultTest() {
        val event = EventModel()

        assertEquals(0, event.id)
        assertEquals("", event.title)
        assertEquals("", event.description)
        assertEquals(0, event.year)
        assertEquals(0, event.month)
        assertEquals(0, event.day)
        assertEquals("", event.type)
        assertEquals(0, event.capacity)
    }

    @Test
    fun eventModelFullTest() {
        val event = EventModel(
            id = 999,
            title = "underscores",
            description = "her she comes",
            year = 2026,
            month = 4,
            day = 26,
            type = "Electronic",
            capacity = 666
        )

        assertEquals(999, event.id)
        assertEquals("underscores", event.title)
        assertEquals("her she comes", event.description)
        assertEquals(2026, event.year)
        assertEquals(4, event.month)
        assertEquals(26, event.day)
        assertEquals("Electronic", event.type)
        assertEquals(666, event.capacity)
    }
}