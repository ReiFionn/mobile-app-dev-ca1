package ie.setu.mobileappdevelopmentca1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.mobileappdevelopmentca1.databinding.ActivityMainBinding
import timber.log.Timber
import timber.log.Timber.i

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Main Activity started...")

        binding.btnAdd.setOnClickListener() {
            val eventTitle = binding.eventTitle.text.toString()
            if (eventTitle.isNotEmpty()) {
                i("add Button Pressed: $eventTitle")
            }
            else {
                Snackbar.make(it,"Please nter a title", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}