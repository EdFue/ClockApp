package edu.msudenver.cs3013.project01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmLabelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmLabelFragment : Fragment() {
    private var label: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarm_label, container, false)

        val labelEditText: EditText = view.findViewById(R.id.labelEditText)
        val doneButton: Button = view.findViewById(R.id.doneButton)

        doneButton.setOnClickListener {
            label = labelEditText.text.toString()
            // Use the parentFragmentManager to set the result
            parentFragmentManager.setFragmentResult("selectedLabel", bundleOf("label" to label))

            // Navigate back
            findNavController(this).navigateUp()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = AlarmLabelFragment()
    }
}
