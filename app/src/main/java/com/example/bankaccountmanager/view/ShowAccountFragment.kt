package com.example.bankaccountmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bankaccountmanager.VM.ShowAccountVM
import com.example.bankaccountmanager.databinding.FragmentShowAccountBinding

class ShowAccountFragment : Fragment() {
    lateinit var binding: FragmentShowAccountBinding
    val vm : ShowAccountVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initView()
    }
    private fun initView() {
       vm.nextEnableLD.observe(viewLifecycleOwner){enable ->
            binding.nextBtn.isEnabled = enable
       }
        vm.backEnableLD.observe(viewLifecycleOwner){enable ->
            binding.backBtn.isEnabled = enable
        }
        vm.numberLiveData.observe(viewLifecycleOwner){number ->
            binding.number.text = number.toString()
        }
        vm.countAccountsNumberLD.observe(viewLifecycleOwner){number ->
        }
        vm.bankAccountLD.observe(viewLifecycleOwner){ account ->
            binding.accountType.text = account.accountType
            binding.showCardNumber.text = account.cardNumber.toString()
            binding.showBalance.text = account.balance.toString()
        }
         binding.nextBtn.setOnClickListener{
             vm.next()
         }
        binding.backBtn.setOnClickListener{
            vm.back()
        }
















    }
}