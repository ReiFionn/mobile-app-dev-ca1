package ie.setu.mobileappdevelopmentca1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.mobileappdevelopmentca1.R
import ie.setu.mobileappdevelopmentca1.databinding.ActivityMainBinding
import ie.setu.mobileappdevelopmentca1.main.MainApp
import ie.setu.mobileappdevelopmentca1.models.EventModel
import timber.log.Timber.i

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var event = EventModel()
    lateinit var app : MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Event Activity started...")

        if (intent.hasExtra("event_edit")) {
            edit = true
            event = intent.extras?.getParcelable("event_edit")!!
            binding.eventTitle.setText(event.title)
            binding.eventDescription.setText(event.description)
            binding.btnAdd.setText(R.string.save_event)
        }

        binding.btnAdd.setOnClickListener() {
            event.title = binding.eventTitle.text.toString()
            event.description = binding.eventDescription.text.toString()

            if (event.title.isEmpty() || event.description.isEmpty()) {
                Snackbar.make(it,R.string.enter_event_details, Snackbar.LENGTH_LONG).show()
            }
            else {
                if (edit) {
                    app.events.update(event.copy())
                } else {
                    app.events.create(event.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}