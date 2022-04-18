package com.example.bankaccountmanager.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bankaccountmanager.R
import com.example.bankaccountmanager.VM.CreateAccountVM
import com.example.bankaccountmanager.databinding.FragmentCreateAccountBinding
import com.example.bankaccountmanager.database.BankAccount


class CreateAccountFragment : Fragment(), AdapterView.OnItemSelectedListener  {
        lateinit var binding: FragmentCreateAccountBinding
        val vm : CreateAccountVM by viewModels()
    var accountType = ""
    var cardNumber = 0
    var balance = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var spinner = binding.spinner
        var saveInfo : SharedPreferences = requireActivity().getSharedPreferences("personalInformation", Context.MODE_PRIVATE)
        vm.userNumberOfAccounts = saveInfo.getString("savedNumberOfBankAccount","0")!!.toInt()
        binding.number1.text = vm.userNumberOfAccounts.toString()
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.accountType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }
        vm.countOfAccounts.observe(viewLifecycleOwner){ number ->
            binding.number.text = number.toString()
        }
        vm.registerButtonEnable.observe(viewLifecycleOwner){ enable ->
            binding.registerButton.isEnabled = enable
        }

        binding.registerButton.setOnClickListener{
            if (saveInfo.getString("savedNumberOfBankAccount",null) != null){
                if ( checkFields()){
                    if (vm.checkRepeat(binding.cardNumber.text.toString().toInt())){
                        Toast.makeText(context, "این شماره کارت قبلا وارد شده است.", Toast.LENGTH_SHORT).show()
                    }
                    else if (vm.countOfAccounts.value!! < vm.userNumberOfAccounts ){
                        savaData()
                        vm.checkNumberOfAddAccounts()
                        Toast.makeText(context, "ثبت", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "همه حساب های مورد نظر خود را وارد کرده اید.", Toast.LENGTH_SHORT).show()
                        binding.registerButton.isEnabled = false
                    }

                }
//                vm.checkNumberOfAddAccounts()
            }else{
                Toast.makeText(context, "ابتدا مشخصات خود را وارد کنید.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_createAccountFragment_to_profileFragment)
            }

        }
    }

    private fun savaData() {
        accountType = binding.spinner.selectedItem.toString()
        cardNumber = binding.cardNumber.text.toString().toInt()
        balance = binding.balance.text.toString().toDouble()
        vm.addAccount(BankAccount(0,accountType,cardNumber,balance))

    }

    fun checkFields():Boolean{
        if(binding.cardNumber.text.isNullOrBlank()){
            binding.cardNumber.error = "این فیلد را پر کنید."
            return false
        }
        if(binding.balance.text.isNullOrBlank()){
            binding.balance.error = "این فیلد را پر کنید."
            return false
        }
        return true
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}