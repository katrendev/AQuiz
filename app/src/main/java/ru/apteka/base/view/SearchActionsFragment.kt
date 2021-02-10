package ru.apteka.base.view

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import ru.apteka.R

open class SearchActionsFragment : Fragment() {

    val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    fun openScanner() {
    }

    // Create an intent that can start the Speech Recognizer activity
    fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        try {
            // Start the activity, the intent will be populated with the speech text
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            context?.let {
                Toast.makeText(it, getString(R.string.no_speech_recognition_support), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            SPEECH_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val spokenText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
                    }
                    else -> super.onActivityResult(requestCode, resultCode, data)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val SPEECH_REQUEST_CODE = 4
    }
}