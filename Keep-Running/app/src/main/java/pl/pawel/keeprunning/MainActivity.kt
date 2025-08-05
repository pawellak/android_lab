package pl.pawel.keeprunning

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import pl.pawel.keeprunning.cycling.CyclingFragment
import pl.pawel.keeprunning.databinding.ActivityMainBinding
import pl.pawel.keeprunning.running.RunningFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickedHandle = when (item.itemId) {
            R.id.reset_running -> {
                showConfirmationDialog(
                    "Reset Running Records",
                    "Are you sure you want to reset all running records?",
                    RUNNING
                )
                true
            }

            R.id.reset_cyclig -> {
                showConfirmationDialog(
                    "Reset Cycling Records",
                    "Are you sure you want to reset all cycling records?",
                    CYCLING
                )
                true
            }

            R.id.reset_all -> {
                showConfirmationDialog(
                    "Reset Cycling Records",
                    "Are you sure you want to reset all records?",
                    ALL
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return menuClickedHandle
    }

    private fun showConfirmationDialog(title: String, message: String, section: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Yes") { _, _ ->

                when (section) {
                    ALL -> {
                        getSharedPreferences(
                            RunningFragment.FILE_NAME,
                            MODE_PRIVATE
                        ).edit { clear() }
                        getSharedPreferences(
                            CyclingFragment.FILE_NAME,
                            MODE_PRIVATE
                        ).edit { clear() }
                    }

                    else -> getSharedPreferences(section, MODE_PRIVATE).edit { clear() }
                }

                refreshCurrentFragment()
                showConfirmation(section)

            }
            .setNegativeButton("NO")
            { dialog, _ ->
                dialog.dismiss()
            }

            .show()
    }

    private fun showConfirmation(section: String) {
        Snackbar.make(
            binding.frameContent,
            "$section records have been reset",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleMeuListener()
    }

    private fun handleMeuListener() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_cycling -> onCyclingClicked()
                R.id.nav_running -> onRunningClicked()
                else -> false
            }
        }
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit { replace(R.id.frame_content, RunningFragment()) }
        return true
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit { replace(R.id.frame_content, CyclingFragment()) }
        return true
    }

    companion object {
        const val RUNNING = "running"
        const val CYCLING = "cycling"
        const val ALL = "all"
    }
}
