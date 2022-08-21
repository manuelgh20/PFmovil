package com.example.bancos.ui.banco

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bancos.R
import com.example.bancos.databinding.FragmentAddBancoBinding
import com.example.bancos.model.Banco
import com.example.bancos.viewmodel.BancoViewModel


class AddBancoFragment : Fragment() {
    private lateinit var bancoViewModel: BancoViewModel
    private var _binding: FragmentAddBancoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bancoViewModel = ViewModelProvider(this)[BancoViewModel::class.java]
        _binding = FragmentAddBancoBinding.inflate(inflater, container, false)
        binding.btAgregar.setOnClickListener{
        insertarBanco()
        }
        return binding.root
    }

    private fun insertarBanco() {

        val cedula=binding.etCedula.text.toString()
        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreo.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val cuenta=binding.etCuenta.text.toString()
        val codigo=binding.etCodigo.text.toString()
        val banco= Banco("",cedula,nombre,correo,telefono,cuenta,codigo)
        bancoViewModel.addBanco(banco)
        Toast.makeText(requireContext(),getString(R.string.msg_agregado),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addBancoFragment_to_nav_banco)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    }


