package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentHomeBinding
import com.example.capstoneproject.viewmodels.BalanceViewModel
import com.example.capstoneproject.viewmodels.ExpensesViewModel
import com.example.capstoneproject.viewmodels.IncomeViewModel
import java.lang.NullPointerException

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModelIncome: IncomeViewModel by viewModels()
    private val viewModelExpense: ExpensesViewModel by viewModels()
    private val viewModelBalance: BalanceViewModel by viewModels()

    /**
     * All the functions and elements in the layout are here initialized. 
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeResult()
    }

    /**
     * The function observeResult is adding the values automatically to the HomeFragment layout.
     */
    private fun observeResult() {
        viewModelBalance.totalBalance.observe(viewLifecycleOwner, { totalBalance ->
        try {
                this.binding.tvNumber1.text = getString(R.string.format, totalBalance)
        }catch(e:NullPointerException){
            e.stackTrace
            binding.tvNumber1.text = getString(R.string.defaultValue)
        }})

        viewModelIncome.totalIncomes.observe(viewLifecycleOwner, { totalIncomes ->
        try {
                this.binding.tvNumber2.text = getString(R.string.format, totalIncomes)
        }catch (e:NullPointerException){
            e.stackTrace
            binding.tvNumber2.text = getString(R.string.defaultValue)
        }})

        viewModelExpense.totalExpenses.observe(viewLifecycleOwner, { totalExpenses ->
        try {
                this.binding.tvNumber3.text = getString(R.string.format, totalExpenses)
        }catch (e:NullPointerException){
            e.stackTrace
            binding.tvNumber3.text = getString(R.string.defaultValue)
        }})

        viewModelBalance.totalSavings.observe(viewLifecycleOwner, { totalSavings ->
            try {
                this.binding.tvNumber4.text = getString(R.string.format, totalSavings)
            }catch (e:NullPointerException){
                e.stackTrace
                binding.tvNumber4.text = getString(R.string.defaultValue)
            }})
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}