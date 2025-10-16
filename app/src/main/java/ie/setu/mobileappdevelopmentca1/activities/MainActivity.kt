package ie.setu.mobileappdevelopmentca1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.setu.mobileappdevelopmentca1.databinding.ActivityMainBinding
import ie.setu.mobileappdevelopmentca1.models.EventModel
import timber.log.Timber
import timber.log.Timber.i

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var event = EventModel()
    val events = ArrayList<EventModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Main Activity started...")

        binding.btnAdd.setOnClickListener() {
            event.title = binding.eventTitle.text.toString()
            event.description = binding.eventDescription.text.toString()

            if (event.title.isNotEmpty() && event.description.isNotEmpty()) {
                i("ADD button pressed: ${event.title} + ${event.description}")
                events.add(event)
                i("All current events: $events")
            }
            else {
                Snackbar.make(it,"Please enter a title and description", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}