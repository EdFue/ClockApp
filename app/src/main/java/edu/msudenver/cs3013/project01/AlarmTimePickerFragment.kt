package edu.msudenver.cs3013.project01

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.text.DateFormat
import java.util.Calendar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [AlarmTimePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmTimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()

        arguments?.getString("currentTime")?.let { currentTime ->
            val parts = currentTime.split(":")
            c.set(Calendar.HOUR_OF_DAY, parts[0].toInt())
            c.set(Calendar.MINUTE, parts[1].toInt())
        }

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(), this, hour, minute, false)
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val formattedTime = String.format("%02d:%02d", hourOfDay, minute)

        // Pass the selected time back to the SetAlarmFragment
        val parentFragment = parentFragmentManager.fragments.firstOrNull { it is AlarmFragment }
        if (parentFragment is AlarmFragment) {
            parentFragment.updateAlarmTime(formattedTime)
        }
    }
}
