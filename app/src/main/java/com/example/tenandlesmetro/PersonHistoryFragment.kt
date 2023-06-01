package com.example.tenandlesmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tenandlesmetro.data.Person
import com.example.tenandlesmetro.databinding.FragmentPersonHistoryBinding
import kotlinx.serialization.json.Json
import java.io.File

class PersonHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPersonHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_person_history, container, false)
        val file = File(context?.filesDir, getString(R.string.jsonFileName))
        val person = Json.decodeFromString(Person.serializer(), file.readText())
        binding.personHistoryText.text = person.description
        return binding.root
    }
}