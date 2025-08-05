package pl.pawel.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.pawel.reminders.databinding.DialogEditReminderBinding
import pl.pawel.reminders.databinding.FragmentPasswordsBinding


class PasswordsFragment : Fragment() {

    private lateinit var binding: FragmentPasswordsBinding

    private val preferences by lazy {
        requireActivity().getSharedPreferences(
            "passwords",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()

        binding.cardViewWifi.setOnClickListener {
            showEditDialog(PREF_WIFI)
        }

        binding.cardViewTabletPin.setOnClickListener {
            showEditDialog(PREF_TABLET)
        }

        binding.cardViewBikeLock.setOnClickListener {
            showEditDialog(PREF_BIKE)
        }
    }

    private fun showEditDialog(prefKey: String) {

        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)

        dialogBinding.editTextValue.setText(preferences.getString(prefKey, null))

        MaterialAlertDialogBuilder(requireContext()).setTitle("Update value")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->

                preferences.edit {
                    putString(prefKey, dialogBinding.editTextValue.text.toString())
                }

                displayValues()
            }
            .setNegativeButton("Cancel") { _, _ ->
            }.show()
    }

    private fun displayValues() {
        binding.textViewWifiValue.text = preferences.getString(PREF_WIFI, null)
        binding.textViewTabletPinValue.text = preferences.getString(PREF_TABLET, null)
        binding.textViewBikeLockValue.text = preferences.getString(PREF_BIKE, null)
    }

    companion object {
        const val PREF_WIFI = "pref_wifi";
        const val PREF_TABLET = "pref_tablet_pin";
        const val PREF_BIKE = "pref_bike_lock";
    }
}
