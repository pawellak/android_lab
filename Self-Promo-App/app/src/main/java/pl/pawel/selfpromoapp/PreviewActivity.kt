package pl.pawel.selfpromoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import pl.pawel.selfpromoapp.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contactName: String = intent.getStringExtra("Contact Name") ?: "EmptyTest"

        binding.textViewMessage.text = contactName
    }
}
