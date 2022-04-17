package com.example.bankaccountmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
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
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var saveInfo : SharedPreferences = requireActivity().getSharedPreferences("personalInformation", Context.MODE_PRIVATE)
//        var count = saveInfo.getString("savedNumberOfBankAccount",null)
        binding.number1.text = vm.userNumberOfAccounts.toString()
        var spinner = binding.spinner

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
//        var numberObserver = Observer<Int> {number ->
//            binding.number.text = number.toString()
//        }
//        vm.countOfAccounts.observe(viewLifecycleOwner,numberObserver)


        binding.registerButton.setOnClickListener{
           if ( checkFields()){
               if (vm.checkRepeat(binding.cardNumber.text.toString().toInt())){
                   savaData()
                   vm.checkNumberOfAddAccounts()
               }
               else{
                   Toast.makeText(context, "این شماره کارت قبلا وارد شده است.", Toast.LENGTH_SHORT).show()
               }
            
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
        var accountType :String= p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}