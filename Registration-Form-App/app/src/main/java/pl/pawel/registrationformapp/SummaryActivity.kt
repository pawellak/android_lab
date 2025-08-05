package pl.pawel.registrationformapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import pl.pawel.registrationformapp.databinding.ActivitySummaryBinding
import androidx.core.net.toUri

class SummaryActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveUser()
        displayUser()
        setupClickListeners()

    }

    private fun retrieveUser() {
        user = intent.getSerializableExtra("User") as User
    }

    private fun displayUser() {
        binding.textViewAccountDetails.text = user.getFullName()
        binding.textViewEmailAddress.text = user.email
        binding.textViewPhone.text = user.phone
    }

    private fun setupClickListeners() {
        binding.textViewEmailAddress.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto: ${user.email}".toUri();
            startActivity(intent)
        }

        binding.textViewPhone.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel: ${user.phone}".toUri();
            startActivity(intent)
        }
    }
}
