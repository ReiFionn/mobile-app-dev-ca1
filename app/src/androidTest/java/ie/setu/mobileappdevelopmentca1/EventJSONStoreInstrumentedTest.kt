package ie.setu.mobileappdevelopmentca1

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import ie.setu.mobileappdevelopmentca1.models.EventJSONStore
import ie.setu.mobileappdevelopmentca1.models.EventModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class EventJSONStoreInstrumentedTest {

    private lateinit var context: Context
    private lateinit var store: EventJSONStore

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteFile("events.json")
        store = EventJSONStore(context)
    }

    @Test
    fun serialisationAndDeserialisationWorks() {
        val event = EventModel(title = "underscores")
        store.create(event)

        val newStore = EventJSONStore(context)
        val fetchedEvent = newStore.findOne(event.id)

        assertNotNull(fetchedEvent)
        assertEquals("underscores", fetchedEvent?.title)
    }
}