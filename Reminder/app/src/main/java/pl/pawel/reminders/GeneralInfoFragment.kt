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
import pl.pawel.reminders.databinding.FragmentGeneralBinding


class GeneralInfoFragment : Fragment() {

    private lateinit var binding: FragmentGeneralBinding

    private val preferences by lazy {
        requireActivity().getSharedPreferences(
            "general",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()

        binding.cardViewBin.setOnClickListener {
            showEditDialog(PREF_BIN)
        }

        binding.cardViewInsuranceNo.setOnClickListener {
            showEditDialog(PREF_INSURANCE)
        }

        binding.cardViewWeddingAnniversary.setOnClickListener {
            showEditDialog(PREF_ANNIVERSARY)
        }
    }

    private fun showEditDialog(prefKey: String) {

        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)

        dialogBinding.editTextValue.setText(preferences.getString(prefKey,null ))

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
        binding.textViewBinValue.text = preferences.getString(PREF_BIN, null)
        binding.textViewInsuranceNoValue.text = preferences.getString(PREF_INSURANCE, null)
        binding.textViewWeddingAnniversaryValue.text = preferences.getString(PREF_ANNIVERSARY, null)
    }

    companion object {
        const val PREF_BIN = "pref_bin";
        const val PREF_INSURANCE = "pref_insurance";
        const val PREF_ANNIVERSARY = "pref_anniversary";
    }
}
