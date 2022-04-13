package com.example.bankaccountmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.bankaccountmanager.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment(), AdapterView.OnItemSelectedListener  {
        lateinit var binding: FragmentCreateAccountBinding
        val vm :CreateAccountVM by viewModels()
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
        var spinner = binding.spinner

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.accountType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }
        binding.registerButton.setOnClickListener{
           if ( checkFields()){
               savaData()
               vm.addAccount(BankAccount(0,accountType,cardNumber,balance))
           }

        }

    }

    private fun savaData() {
        accountType = binding.spinner.selectedItem.toString()
//        Toast.makeText(context, vm.x, Toast.LENGTH_SHORT).show()
        cardNumber = binding.cardNumber.text.toString().toInt()
        balance = binding.balance.text.toString().toDouble()
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