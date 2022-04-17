package com.example.bankaccountmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.bankaccountmanager.VM.SelectVM
import com.example.bankaccountmanager.databinding.FragmentSelectAccountBinding

class SelectAccountFragment : Fragment() {
    lateinit var binding: FragmentSelectAccountBinding
    val vm : SelectVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentSelectAccountBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.findButton.setOnClickListener{
            if(vm.checkRepeat(binding.selectedCardNumber.text.toString().toInt())){
                findAccount(binding.selectedCardNumber.text.toString().toInt())

            }else{
                Toast.makeText(context, "چنین شماره کارتی وجود ندارد.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun findAccount(cardNumber:Int) {
    var chosenAccount = vm.findAccount(cardNumber)
        binding.selectedAccountType.text = chosenAccount.accountType
        binding.selectedBalance.text = chosenAccount.balance.toString()
    }
}