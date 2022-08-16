package com.example.bancos.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancos.databinding.BancoFilaBinding

class BancoAdapter : RecyclerView.Adapter<BancoAdapter.BancoViewHolder>(){

    inner class BancoViewHolder(private val itemBinding: BancoFilaBinding):
        RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BancoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BancoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}