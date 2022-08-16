package com.example.bancos.ui.banco

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bancos.R
import com.example.bancos.databinding.FragmentAddBancoBinding
import com.example.bancos.databinding.FragmentBancoBinding

import com.example.bancos.viewmodel.BancoViewModel

class BancoFragment : Fragment() {

    private lateinit var bancoViewModel: BancoViewModel
    private var _binding: FragmentBancoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bancoViewModel = ViewModelProvider(this)[BancoViewModel::class.java]
        _binding = FragmentBancoBinding.inflate(inflater, container, false)
         binding.addLugarFabButton.setOnClickListener {
             findNavController().navigate(R.id.action_nav_banco_to_addBancoFragment)
         }
             return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}