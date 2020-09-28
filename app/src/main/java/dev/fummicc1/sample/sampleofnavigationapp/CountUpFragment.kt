package dev.fummicc1.sample.sampleofnavigationapp

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import dev.fummicc1.sample.sampleofnavigationapp.data.CountRecord
import dev.fummicc1.sample.sampleofnavigationapp.databinding.FragmentCountUpBinding
import java.util.*

class CountUpFragment : Fragment() {

    private var countRecord: CountRecord = CountRecord()
    private val timer: Timer = Timer()
    private lateinit var handler: Handler
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
        binding.countRecord = countRecord
        // TODO: fix deprecated Handler constructor.
        // TODO: use coroutine instead of Handler.
        handler = Handler()
        startTimer(handler)
        return binding.root
    }

    private fun startTimer(handler: Handler) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (countRecord.time == 0) {
                        Toast.makeText(context, getString(R.string.time_up), Toast.LENGTH_SHORT).show()
                        timer.cancel()
                        // need to specify function to return.
                        findNavController().navigate(CountUpFragmentDirections.actionCountUpFragmentToResultFragment(countRecord))
                        return@post
                    }
                    countRecord.time -= 1
                    binding.invalidateAll()
                }
            }
        }, 0, 1000)
    }
}