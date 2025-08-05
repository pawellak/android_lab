package pl.pawel.selfpromoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import pl.pawel.selfpromoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.previewButton.setOnClickListener {
            onPreviewClicked()

        }

        val spinnerValues: Array<String> = arrayOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
        binding.spinnerJobTitle.adapter = adapter

    }

    private fun onPreviewClicked() {
        val previewIntent = Intent(this, PreviewActivity::class.java)
        previewIntent.putExtra("Contact Name",binding.editTextContactName.toString())
        startActivity(previewIntent)
    }
}