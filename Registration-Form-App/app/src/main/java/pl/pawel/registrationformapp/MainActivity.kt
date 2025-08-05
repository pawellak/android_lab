package pl.pawel.registrationformapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import pl.pawel.registrationformapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupButton()
    }

    private fun setupButton() {
        binding.buttonCreateAccount.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {

        val user = User(
            title = binding.spinnerTitle.selectedItem.toString(),
            firstName = binding.editTextFirstName.text.toString(),
            lastName = binding.editTextLastName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phone = binding.editTextPhoneNumber.text.toString(),
            password = binding.editTextPassword.text.toString()
        )

        val previewIntent = Intent(this, SummaryActivity::class.java)
        previewIntent.putExtra("User", user)
        startActivity(previewIntent)

    }

    private fun setupSpinner() {
        val titles = arrayOf("Miss", "Mrs", "Ms", "Mr", "Dr", "Prof")
        val titleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, titles)
        binding.spinnerTitle.adapter = titleAdapter
    }
}
