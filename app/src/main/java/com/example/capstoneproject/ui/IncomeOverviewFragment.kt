package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.IncomeAdapter
import com.example.capstoneproject.databinding.FragmentIncomeOverviewBinding
import com.example.capstoneproject.model.Income
import com.example.capstoneproject.viewmodels.IncomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class IncomeOverviewFragment : Fragment() {
    private lateinit var binding: FragmentIncomeOverviewBinding

    private val viewModel: IncomeViewModel by viewModels()

    private val incomes = arrayListOf<Income>()
    private val incomeAdapter = IncomeAdapter(incomes)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIncomeOverviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Button for navigating to the AddFragment.
        binding.fabAddIncome.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        //Button for deleting everything on the overview.
        binding.fabDeleteIncome.setOnClickListener {
            viewModel.deleteAllIncomes()
        }

        initRv()

        observeAddIncomeResult()
    }

    /**
     * Here all the components on the fragment will be initialized.
     */
    private fun initRv() {
        binding.rvIncomes.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvIncomes.adapter = incomeAdapter
        binding.rvIncomes.addItemDecoration(
                DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                )
        )

        createItemTouchHelper().attachToRecyclerView(binding.rvIncomes)
    }


    /**
     * This function helps to refresh the layout of the fragment.
     */
    private fun observeAddIncomeResult() {
        //fill the text fields with the current text and title from the viewmodel
        viewModel.incomes.observe(viewLifecycleOwner, { incomes ->
            this.incomes.clear()
            this.incomes.addAll(incomes)
            incomeAdapter.notifyDataSetChanged()
        })
    }

    
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        viewModel.deleteIncome(incomes[position])
                    }
                }
            }
        }

        return ItemTouchHelper(callback)
    }
}