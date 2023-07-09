package edu.msudenver.cs3013.project01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class AlarmSoundPickerFragment : DialogFragment() {
    private lateinit var soundList: List<String>
    private var selectedSound: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the list of available alarm sounds
        soundList = listOf("Sound 1", "Sound 2", "Sound 3", "Sound 4")

        // Retrieve the selected sound from the arguments
        selectedSound = arguments?.getString("selectedSound")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_alarm_sound_picker, container, false)
        setupSoundButtons(view)
        return view
    }

    private fun setupSoundButtons(view: View) {
        val soundButtonContainer: LinearLayout = view.findViewById(R.id.soundButtonContainer)

        soundList.forEach { sound ->
            val soundButton = Button(requireContext())
            soundButton.text = sound
            soundButton.setOnClickListener {
                val result = bundleOf("sound" to sound)
                parentFragmentManager.setFragmentResult("selectedSound", result)
                findNavController().popBackStack()
            }
            soundButtonContainer.addView(soundButton)
        }
    }
}
