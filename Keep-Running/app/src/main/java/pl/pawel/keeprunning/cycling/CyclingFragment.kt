package pl.pawel.keeprunning.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.pawel.keeprunning.databinding.FragmentCyclingBinding
import pl.pawel.keeprunning.EditRecordActivity
import pl.pawel.keeprunning.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment : Fragment() {
    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupClickListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = runningPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewLongestRideDate.text = runningPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBiggestClimbValue.text = runningPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBiggestClimbDate.text = runningPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBestSpeedValue.text = runningPreferences.getString("Best Speed ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBestSpeedDate.text = runningPreferences.getString("Best Speed ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
    }

    private fun setupClickListeners() {
        binding.containerBestSpeed.setOnClickListener {
            launchCyclingRecordScreen(
                "Best Speed",
                "Average Speed"
            )
        }
        binding.containerLongestRide.setOnClickListener {
            launchCyclingRecordScreen(
                "Longest Ride",
                "Distance"
            )
        }
        binding.containerBiggestClimb.setOnClickListener {
            launchCyclingRecordScreen(
                "Biggest Climb",
                "Height"
            )
        }
    }

    private fun launchCyclingRecordScreen(type: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(
                record = type,
                recordFieldHint = recordFieldHint,
                sharedPreferencesName = FILE_NAME
            )
        )
        startActivity(intent)
    }

    companion object {
        const val FILE_NAME = "cycling"
    }
}
