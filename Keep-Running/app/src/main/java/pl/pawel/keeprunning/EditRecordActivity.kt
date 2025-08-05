package pl.pawel.keeprunning

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import pl.pawel.keeprunning.databinding.ActivityEditRecordBinding
import java.io.Serializable

const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val recordPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName,
            MODE_PRIVATE
        )
    }
    private val screenData: ScreenData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                INTENT_EXTRA_SCREEN_DATA,
                ScreenData::class.java
            ) as ScreenData
        } else {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home -> {
                
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFieldHint

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun displayRecord() {

        binding.editTextRecord.setText(
            recordPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(
            recordPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                null
            )
        )
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        recordPreferences.edit {
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                record
            )
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                date
            )
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCE_DATE_KEY")
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable

    companion object {
        const val SHARED_PREFERENCE_RECORD_KEY = "record"
        const val SHARED_PREFERENCE_DATE_KEY = "date"
    }
}
