package ie.setu.mobileappdevelopmentca1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import com.google.android.material.snackbar.Snackbar
import ie.setu.mobileappdevelopmentca1.R
import ie.setu.mobileappdevelopmentca1.databinding.ActivityMainBinding
import ie.setu.mobileappdevelopmentca1.main.MainApp
import ie.setu.mobileappdevelopmentca1.models.EventModel
import timber.log.Timber.i
import java.util.Calendar

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

        val datePicker: DatePicker = findViewById(R.id.eventDate)
        val today = Calendar.getInstance()
        datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            event.year = year
            event.month = month+1
            event.day = day
        }

        val eventTypes = resources.getStringArray(R.array.event_types)
        val arrayAdapter = ArrayAdapter(this, R.layout.event_type_dropdown, eventTypes)
        val eventTypeTV = findViewById<AutoCompleteTextView>(R.id.eventType)
        eventTypeTV.setAdapter(arrayAdapter)

        app = application as MainApp
        i("Event Activity started...")

        if (intent.hasExtra("event_edit")) {
            edit = true
            event = intent.extras?.getParcelable("event_edit")!!
            binding.eventTitle.setText(event.title)
            binding.eventDescription.setText(event.description)
            binding.eventDate.updateDate(event.year, event.month-1, event.day)
            binding.eventType.setText(event.type, false) //https://stackoverflow.com/questions/29906928/setting-value-in-autocompletetextview
            binding.btnAdd.setText(R.string.save_event)
        }

        binding.btnAdd.setOnClickListener() {
            event.title = binding.eventTitle.text.toString()
            event.description = binding.eventDescription.text.toString()
            event.year = binding.eventDate.year
            event.month = binding.eventDate.month+1 //months start at 0
            event.day = binding.eventDate.dayOfMonth
            event.type = binding.eventType.text.toString()

            if (event.title.isEmpty() || event.description.isEmpty() || event.year == 0 || event.type.isEmpty()) {
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
