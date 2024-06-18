package com.dicoding.destinatik.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.data.local.preferences.auth.AuthPreferences
import com.dicoding.destinatik.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var authPreferences: AuthPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authPreferences = AuthPreferences(requireContext())

        // Handle logout button click
        binding.apply {
            btnLogout.setOnClickListener {
                logout()
            }
            ivBack.setOnClickListener {
                home()
            }
        }

        // Handle back navigation
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // If the user is not authenticated, block the back navigation
                    if (authPreferences.getToken().isNullOrEmpty()) {
                        navigateToLoginActivity()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )
    }

    private fun logout() {
        // Clear user data (e.g., AuthPreferences)
        authPreferences.clearToken()

        // Navigate back to login screen and clear the back stack
        navigateToLoginActivity()
    }

    private fun home() {
        // Navigate back to home screen
        // Adjust your navigation logic here if needed
        findNavController().navigate(R.id.action_settingsFragment_to_homeFragment, null)
    }

    private fun navigateToLoginActivity() {
        // Create an intent to start LoginActivity
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}