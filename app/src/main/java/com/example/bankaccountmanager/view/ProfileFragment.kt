package com.example.bankaccountmanager.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bankaccountmanager.R
import com.example.bankaccountmanager.VM.CreateAccountVM
import com.example.bankaccountmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    val vm: CreateAccountVM by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfo()

        binding.register.setOnClickListener{

            if (isFieldsFull()){
                saveInfo()
                Toast.makeText(context,"مشخصات شما ثبت شد.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profileFragment_to_showProfileInfoFragment)
            }
        }
    }


    private fun showInfo() {
        var saveInfo : SharedPreferences = requireActivity().getSharedPreferences("personalInformation", Context.MODE_PRIVATE)
        binding.fullName.setText(saveInfo.getString("savedName",null))
        binding.fatherName.setText(saveInfo.getString("savedFather'sName",null))
        binding.phone.setText(saveInfo.getString("savedPhone",null))
        binding.postalCode.setText(saveInfo.getString("savedPostalCode",null))
        binding.numberOfBankAccounts.setText(saveInfo.getString("savedNumberOfBankAccount",null))
    }

    private fun saveInfo() {
        var saveInfo : SharedPreferences = requireActivity().getSharedPreferences("personalInformation", Context.MODE_PRIVATE)
        var editor = saveInfo.edit()
        editor.putString("savedName",binding.fullName.text.toString())
        editor.putString("savedFather'sName",binding.fatherName.text.toString())
        editor.putString("savedPostalCode",binding.postalCode.text.toString())
        editor.putString("savedNumberOfBankAccount",binding.numberOfBankAccounts.text.toString())
        editor.putString("savedPhone",binding.phone.text.toString())
        editor.apply()
        vm.userNumberOfAccounts = saveInfo.getString("savedNumberOfBankAccount", binding.numberOfBankAccounts.text.toString())!!.toInt()
        Toast.makeText(context,"مشخصات شما ثبت شد.", Toast.LENGTH_SHORT).show()


    }

    fun isFieldsFull():Boolean{
        if (  binding.fullName.text.isNullOrBlank()){
            binding.fullName.error = "این فیلد را پر کنید."
            return false
        }
        if(binding.fatherName.text.isNullOrBlank()){
            binding.fatherName.error = "این فیلد را پر کنید."
            return false
        }
        if(binding.phone.text.isNullOrBlank()){
            binding.phone.error = "این فیلد را پر کنید."
            return false
        }
        if (binding.phone.text.toString().length != 11) {
            binding.phone.error = "شماره تلفن باید 11 رقم باشد."
            return false
        }
        if(binding.postalCode.text.isNullOrBlank()){
            binding.postalCode.error = "این فیلد را پر کنید."
            return false
        }
        if(binding.numberOfBankAccounts.text.isNullOrBlank()){
            binding.numberOfBankAccounts.error = "این فیلد را پر کنید."
            return false
        }
        return true
    }

}