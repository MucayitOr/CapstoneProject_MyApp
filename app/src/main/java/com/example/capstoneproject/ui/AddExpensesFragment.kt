package com.example.capstoneproject.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentAddExpensesBinding
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.viewmodels.ExpensesViewModel
import java.time.LocalDate

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddExpensesFragment : Fragment() {
    private lateinit var binding: FragmentAddExpensesBinding

    private val viewModel: ExpensesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddExpensesBinding.inflate(layoutInflater)

        requireActivity().invalidateOptionsMenu()

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSave.setOnClickListener {
            onSaveExpenses()
        }
    }

    /**
     * This function will save the values in the database.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSaveExpenses() {
        val title = binding.etTitle.text.toString()
        val date: LocalDate
        //The date of given by user is been checked,
        // if it is empty or something else it wil show a toast
        try {
            date = LocalDate.of(
                binding.etYear.text.toString().toIntOrNull()!!,
                binding.etMonth.text.toString().toIntOrNull()!!,
                binding.etDay.text.toString().toIntOrNull()!!
            )
        } catch (e: NullPointerException) {
            Toast.makeText(
                activity,
                R.string.invalid_date,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val description = binding.etDescription.text.toString()
        val amount = binding.etAmount.text.toString()
        //The forms can not be blank otherwise the function will not save the values.
        if (title.isNotBlank() && description.isNotBlank() && amount.isNotBlank()) {
            viewModel.insertExpense(Expense(title, description, amount.toDouble(), date ))

            findNavController().navigate(R.id.action_addExpensesFragment_to_expensesOverviewFragment)
        } else {
            Toast.makeText(
                activity,
                R.string.empty_field,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}