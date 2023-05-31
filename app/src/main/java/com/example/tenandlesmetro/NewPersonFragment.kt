package com.example.tenandlesmetro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tenandlesmetro.data.Inventory
import com.example.tenandlesmetro.data.Person
import com.example.tenandlesmetro.data.Sex
import com.example.tenandlesmetro.databinding.FragmentNewPersonBinding
import com.example.tenandlesmetro.utils.saveToJson
import kotlinx.serialization.json.Json
import java.io.File

class NewPersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNewPersonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_person, container, false)
        binding.startGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_newPersonFragment_to_gameFragment)
            val checkedId = binding.sexGroup.checkedRadioButtonId
            Person(
                name = binding.personName.text.toString(),
                age = binding.ageText.text.toString(),
                sex = when(checkedId) {
                    R.id.male -> Sex.Male
                    R.id.female -> Sex.Female
                    else -> Sex.Male
                },
                description = binding.personDescription.text.toString(),
                goodPersonTrait = binding.good.text.toString(),
                badPersonTrait = binding.bad.text.toString(),
                secret = binding.personSecret.text.toString(),
                inventory = Inventory(emptySet())
            ).saveToJson(context?.filesDir, getString(R.string.jsonFileName))
        }
        return binding.root
    }

}
