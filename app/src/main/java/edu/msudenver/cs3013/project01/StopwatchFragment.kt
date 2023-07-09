package edu.msudenver.cs3013.project01

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopwatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StopwatchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var startTime: Long = 0
    private lateinit var stopwatchHandler: Handler
    private lateinit var stopwatchRunnable: Runnable
    private lateinit var stopwatchTextView: TextView

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
        val view = inflater.inflate(R.layout.fragment_stopwatch, container, false)

        stopwatchTextView = view.findViewById(R.id.stopwatch_text_view)
        stopwatchHandler = Handler(Looper.getMainLooper())

        return view
    }

    override fun onStart() {
        super.onStart()

        startTime = SystemClock.uptimeMillis()

        stopwatchRunnable = Runnable {
            val timeInMillis = SystemClock.uptimeMillis() - startTime
            val hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timeInMillis),
                TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60,
                TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60)

            stopwatchTextView.text = hms

//            stopwatchHandler.postDelayed(this, 1000)
            stopwatchHandler.postDelayed(stopwatchRunnable, 1000)
        }

        stopwatchHandler.post(stopwatchRunnable)
    }

    override fun onStop() {
        super.onStop()
        stopwatchHandler.removeCallbacks(stopwatchRunnable)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StopwatchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StopwatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}