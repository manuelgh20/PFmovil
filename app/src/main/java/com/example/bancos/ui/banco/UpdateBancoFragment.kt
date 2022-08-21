package com.example.bancos.ui.banco

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bancos.R
import com.example.bancos.databinding.FragmentAddBancoBinding
import com.example.bancos.databinding.FragmentUpdateBancoBinding
import com.example.bancos.model.Banco
import com.example.bancos.viewmodel.BancoViewModel


class UpdateBancoFragment : Fragment() {
    private lateinit var bancoViewModel: BancoViewModel

    private val args by navArgs<UpdateBancoFragmentArgs >()

    private var _binding: FragmentUpdateBancoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bancoViewModel = ViewModelProvider(this)[BancoViewModel::class.java]
        _binding = FragmentUpdateBancoBinding.inflate(inflater, container, false)

        binding.etCedula.setText(args.banco.Cedula)
        binding.etNombre.setText(args.banco.nombre)
        binding.etCorreo.setText(args.banco.correo)
        binding.etTelefono.setText(args.banco.telefono)
        binding.etCuenta.setText(args.banco.cuenta)
        binding.etCodigo.setText(args.banco.codigo)




        binding.btActualizar.setOnClickListener{
        updateBanco()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId ==R.id.menu_delete) {
            deleteBanco()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateBanco() {

        val cedula=binding.etCedula.text.toString()
        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreo.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val cuenta=binding.etCuenta.text.toString()
        val codigo=binding.etCodigo.text.toString()
        val banco= Banco(args.banco.id,cedula,nombre,correo,telefono,cuenta,codigo)
        bancoViewModel.updateBanco(banco)
        Toast.makeText(requireContext(),getString(R.string.msg_actualizado),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateBancoFragment_to_nav_banco)

    }

    private fun deleteBanco(){
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            bancoViewModel.deleteBanco(args.banco)
            findNavController().navigate(R.id.action_updateBancoFragment_to_nav_banco)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_segurro_borrar)+"${args.banco.nombre}?")
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    }


