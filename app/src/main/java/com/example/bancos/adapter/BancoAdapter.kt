package com.example.bancos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bancos.databinding.BancoFilaBinding
import com.example.bancos.model.Banco
import com.example.bancos.ui.banco.BancoFragmentDirections

class BancoAdapter : RecyclerView.Adapter<BancoAdapter.BancoViewHolder>(){

    private var listaBancos = emptyList<Banco>()

    inner class BancoViewHolder(private val itemBinding: BancoFilaBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        fun bind (banco: Banco){

            itemBinding.tvCedula.text = banco.Cedula
            itemBinding.tvNombre.text = banco.nombre
            itemBinding.tvCorreo.text = banco.correo
            itemBinding.tvTelefono.text = banco.telefono
            itemBinding.tvCuenta.text = banco.cuenta
            itemBinding.tvCodigo.text = banco.codigo
            itemBinding.vistaFila.setOnClickListener{
                val action=BancoFragmentDirections.actionNavBancoToUpdateBancoFragment(banco)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BancoViewHolder {
        val itemBinding = BancoFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return BancoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BancoViewHolder, position: Int) {
        val banco=listaBancos[position]
        holder.bind(banco)
    }

    override fun getItemCount(): Int {
        return listaBancos.size
    }

    fun setdata(bancos : List<Banco>){
        this.listaBancos=bancos
        notifyDataSetChanged()
    }
}