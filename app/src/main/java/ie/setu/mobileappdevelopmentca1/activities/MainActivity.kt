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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Main Activity started...")

        binding.btnAdd.setOnClickListener() {
            event.title = binding.eventTitle.text.toString()
            if (event.title.isNotEmpty()) {
                Timber.i("add Button Pressed: $event.title")
            }
            else {
                Snackbar.make(it,"Please enter a title", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}