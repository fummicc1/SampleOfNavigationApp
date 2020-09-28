package dev.fummicc1.sample.sampleofnavigationapp

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.fummicc1.sample.sampleofnavigationapp.data.CountRecord
import dev.fummicc1.sample.sampleofnavigationapp.databinding.FragmentCountUpBinding
import kotlinx.android.synthetic.main.fragment_count_up.*
import kotlinx.coroutines.*
import java.util.*

class CountUpFragment : Fragment() {

    private var countRecord: CountRecord = CountRecord()
    private val timer: Timer = Timer()
    private val mainScope: CoroutineScope = MainScope()
    private lateinit var binding: FragmentCountUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCountUpBinding>(
            inflater,
            R.layout.fragment_count_up,
            container,
            false
        )
        binding.apply {
            countRecord = this@CountUpFragment.countRecord
            normalCountUpButton.setOnClickListener {
                this@CountUpFragment.countRecord.amount += 1
                invalidateAll()
            }
            fiveTimesCountUpButton.setOnClickListener {
                this@CountUpFragment.countRecord.amount += 5
                invalidateAll()
            }
            tenTimesCountUpButton.setOnClickListener {
                this@CountUpFragment.countRecord.amount += 10
                invalidateAll()
            }
        }
        startTimer()
        return binding.root
    }

    private fun startTimer() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                updateTime()
            }
        }, 0, 1000)
    }

    @UiThread
    fun updateTime() {
        if (countRecord.time == 0) {
            mainScope.launch {
                Toast.makeText(context, getString(R.string.time_up), Toast.LENGTH_SHORT).show()
                timer.cancel()
                // need to specify function to return.
                findNavController().navigate(CountUpFragmentDirections.actionCountUpFragmentToResultFragment(countRecord))
                return@launch
            }
        }
        countRecord.time -= 1
        binding.invalidateAll()
    }
}