package com.example.capstoneproject.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ItemIncomeBinding
import com.example.capstoneproject.model.Income
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class IncomeAdapter(private val incomes: List<Income>):
    RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemIncomeBinding.bind(itemView)

        @RequiresApi(Build.VERSION_CODES.O)
        fun dataBind(income: Income){
            binding.tvDate.text = income.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            binding.tvTitle.text = income.title
            binding.tvDescription.text = income.description
            binding.tvNumber.text = income.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_income, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return incomes.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(incomes[position])
    }
}