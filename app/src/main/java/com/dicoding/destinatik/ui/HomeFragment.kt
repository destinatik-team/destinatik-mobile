package com.dicoding.destinatik.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.data.local.preferences.auth.AuthPreferences
import com.dicoding.destinatik.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val authPreferences: AuthPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = authPreferences.getToken()
        if (token.isNullOrEmpty()) {
            // Token is missing, redirect to LoginActivity
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.apply {
            profileImage.setOnClickListener {
                navigateToProfile()
            }

            settingsIcon.setOnClickListener {
                navigateToSettings()
            }
        }
    }

    private fun navigateToProfile(){
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }

    private fun navigateToSettings(){
        findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}