package ie.setu.mobileappdevelopmentca1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.mobileappdevelopmentca1.R
import ie.setu.mobileappdevelopmentca1.main.MainApp

class EventListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)
        app = application as MainApp
    }
}