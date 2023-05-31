package com.example.tenandlesmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tenandlesmetro.databinding.FragmentStartPageBinding
import java.io.File

class StartPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentStartPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_page, container, false)

        File(context?.filesDir, getString(R.string.jsonFileName)).showContinueIfExistsPersonData(binding.continueGame)

        binding.createPersonButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_startPageFragment_to_newPersonFragment)
        }
        return binding.root
    }

    private fun File.showContinueIfExistsPersonData(continueButton: Button) {
        if(exists()) {
            continueButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_startPageFragment_to_gameFragment)
            }
            continueButton.visibility = View.VISIBLE
        }
    }
}