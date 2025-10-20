package ie.setu.mobileappdevelopmentca1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.mobileappdevelopmentca1.R
import ie.setu.mobileappdevelopmentca1.adapters.EventAdapter
import ie.setu.mobileappdevelopmentca1.adapters.EventListener
import ie.setu.mobileappdevelopmentca1.databinding.ActivityEventListBinding
import ie.setu.mobileappdevelopmentca1.main.MainApp
import ie.setu.mobileappdevelopmentca1.models.EventModel

class EventListActivity : AppCompatActivity(), EventListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityEventListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = EventAdapter(app.events.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, EventActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.events.findAll().size)
        }
    }

    override fun onEventClick(event: EventModel) {
        val launcherIntent = Intent(this, EventActivity::class.java)
        launcherIntent.putExtra("event_edit", event)
        getClickResult.launch(launcherIntent)
    }

    override fun onDeleteButtonClicked(event: EventModel) {
        app.events.delete(event)
        (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.events.findAll().size)
        (binding.recyclerView.adapter)?.notifyDataSetChanged() //https://stackoverflow.com/questions/4198425/updating-the-list-view-when-the-adapter-data-changes
    }

    private val getClickResult =
        registerForActivityResult (ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.events.findAll().size)
            }
        }
}