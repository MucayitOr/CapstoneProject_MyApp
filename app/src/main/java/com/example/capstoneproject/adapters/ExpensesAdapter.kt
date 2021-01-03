package com.example.capstoneproject.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ItemExpensesBinding
import com.example.capstoneproject.model.Expense
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ExpensesAdapter(private val expense: ArrayList<Expense>):
    RecyclerView.Adapter<ExpensesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemExpensesBinding.bind(itemView)

        @RequiresApi(Build.VERSION_CODES.O)
        fun dataBind(expense: Expense){
            binding.tvDate.text = expense.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            binding.tvTitle.text = expense.title
            binding.tvDescription.text = expense.description
            binding.tvNumber.text = expense.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_expenses, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return expense.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(expense[position])
    }
}