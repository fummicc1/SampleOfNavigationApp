package dev.fummicc1.sample.sampleofnavigationapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dev.fummicc1.sample.sampleofnavigationapp.data.CountRecord
import dev.fummicc1.sample.sampleofnavigationapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val countRecord = args.countRecord
        binding.apply {
            countAmountTextView.text = countRecord.amount.toString()
            homeButton.setOnClickListener {
                findNavController().popBackStack()
            }
            shareButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, "amout: ${countRecord.amount}")
                if (intent.resolveActivity(requireActivity().packageManager) == null) {
                    Toast.makeText(context, "no activity found", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                startActivity(intent)
            }
        }
        return binding.root
    }
}