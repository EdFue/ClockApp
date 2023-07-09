package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmFragment : Fragment() {

    private var selectedTime: String? = null
    private var selectedSound: String? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial selected time and sound
        updateTimeTextView()
        updateSoundTextView()
        updateAlarmLabel("")

        val timeButton: Button = view.findViewById(R.id.setTimeButton)
        timeButton.setOnClickListener {
            val fragment = AlarmTimePickerFragment().apply {
                arguments = Bundle().apply {
                    putString("currentTime", selectedTime)
                }
            }
            fragment.show(parentFragmentManager, "timePicker")
        }

        //presets button
//        val presetAlarmButton: Button = view.findViewById(R.id.presetAlarmButton)
//        presetAlarmButton.setOnClickListener {
//            val fragment = AlarmFragment.newInstance()
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.alarmFragment, fragment)
//                ?.addToBackStack(null)
//                ?.commit()
//        }

        //sound picker button
        val soundButton: Button = view.findViewById(R.id.soundButton)
        soundButton.setOnClickListener {
            val bundle = Bundle()
            selectedSound?.let { bundle.putString("selectedSound", it) }
            findNavController().navigate(R.id.alarmSoundPickerFragment, bundle)
        }

        // Listen for results from AlarmSoundPickerFragment
        setFragmentResultListener("selectedSound") { _, bundle ->
            val sound = bundle.getString("sound")
            updateAlarmSound(sound ?: "")
        }

        val labelButton: Button = view.findViewById(R.id.labelButton)
        labelButton.setOnClickListener {
            findNavController().navigate(R.id.action_alarmFragment_to_alarmLabelFragment)
        }
        setFragmentResultListener("selectedLabel") { _, bundle ->
            val label = bundle.getString("label")
            updateAlarmLabel(label ?: "")
        }

    }

    // Function to update the selected time
    fun updateAlarmTime(time: String) {
        selectedTime = time
        updateTimeTextView()
    }

    fun updateAlarmSound(sound: String) {
        selectedSound = sound
        updateSoundTextView()
    }

    private fun updateTimeTextView() {
        val timeTextView: TextView = view?.findViewById(R.id.timeTextView) ?: return
        timeTextView.text = selectedTime
    }

    private fun updateSoundTextView() {
        val soundTextView: TextView = view?.findViewById(R.id.soundTextView) ?: return
        soundTextView.text = selectedSound
    }

    private fun updateAlarmLabel(label: String) {
        val labelTextView: TextView = view?.findViewById(R.id.labelTextView) ?: return
        labelTextView.text = label
        if (label.isNotEmpty()) {
            labelTextView.visibility = View.VISIBLE
            labelTextView.isFocusable = true
            labelTextView.isFocusableInTouchMode = true
            labelTextView.requestFocus()
        } else {
            labelTextView.visibility = View.INVISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): AlarmFragment {
            return AlarmFragment()
        }
    }
}