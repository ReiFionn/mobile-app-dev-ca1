package ie.setu.mobileappdevelopmentca1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.mobileappdevelopmentca1.R
import ie.setu.mobileappdevelopmentca1.databinding.ActivityEventListBinding
import ie.setu.mobileappdevelopmentca1.databinding.CardEventBinding
import ie.setu.mobileappdevelopmentca1.main.MainApp
import ie.setu.mobileappdevelopmentca1.models.EventModel

class EventListActivity : AppCompatActivity() {
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
        binding.recyclerView.adapter = EventAdapter(app.events)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

class EventAdapter constructor(private var events: List<EventModel>) :
    RecyclerView.Adapter<EventAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardEventBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val event = events[holder.adapterPosition]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    class MainHolder(private val binding : CardEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventModel) {
            binding.eventTitle.text = event.title
            binding.eventDescription.text = event.description
        }
    }
}