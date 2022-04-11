package com.example.bankaccountmanager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bankaccountmanager.databinding.FragmentShowProfileInfoBinding


class ShowProfileInfoFragment : Fragment() {
    lateinit var binding: FragmentShowProfileInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowProfileInfoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSavedInfo()
        binding.edit.setOnClickListener{
            findNavController().navigate(R.id.action_showProfileInfoFragment_to_profileFragment)
        }

    }

    private fun showSavedInfo() {
        var saveInfo: SharedPreferences =
            requireActivity().getSharedPreferences("personalInformation", Context.MODE_PRIVATE)
        binding.fullNameTv.setText(saveInfo.getString("savedName", null))
        binding.fatherNameTv.setText(saveInfo.getString("savedFather'sName", null))
        binding.phone.setText(saveInfo.getString("savedPhone", null))
        binding.postalCodeTv.setText(saveInfo.getString("savedPostalCode", null))
        binding.numberOfBankAccountsTv.setText(saveInfo.getString("savedNumberOfBankAccount", null))

    }

}