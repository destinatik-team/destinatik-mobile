// com/dicoding/destinatik/ui/HomeFragment.kt
package com.dicoding.destinatik.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.data.adapter.DestinatikAdapter
import com.dicoding.destinatik.core.data.adapter.SearchAdapter
import com.dicoding.destinatik.core.data.local.preferences.auth.AuthPreferences
import com.dicoding.destinatik.core.domain.viewmodel.MainViewModel
import com.dicoding.destinatik.core.domain.viewmodel.UsersViewModel
import com.dicoding.destinatik.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val authPreferences: AuthPreferences by inject()
    private val mainViewModel: MainViewModel by viewModel()
    private val usersViewModel: UsersViewModel by viewModel()
    private lateinit var destinatikAdapter: DestinatikAdapter
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accessToken = "AIzaSyCSz005H-W0qwWWkMTdb2G5xv_IffRcOEc"

        val token = authPreferences.getToken()
        if (token.isNullOrEmpty()) {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
            return
        }

        val userId = authPreferences.getUserId()
        if (userId == null) {
            Snackbar.make(binding.root, "User ID not found", Snackbar.LENGTH_LONG).show()
            return
        }

        // Fetch and display the user information
        usersViewModel.fetchUser()

        usersViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.userName.text = it.username
                binding.userName.visibility = View.VISIBLE
            } ?: run {
                binding.userName.visibility = View.GONE
            }
        })

        setupRecyclerView()
        observeViewModel()

        binding.apply {
            profileImage.setOnClickListener { navigateToProfile() }
            settingsIcon.setOnClickListener { navigateToSettings() }
            searchBar.addTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    binding.recyclerView.adapter = destinatikAdapter
                } else {
                    mainViewModel.searchPlaces(accessToken, text.toString())
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        mainViewModel.fetchRecommendations(userId, "Bandung")
    }

    private fun setupRecyclerView() {
        destinatikAdapter = DestinatikAdapter { place ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(place, null)
            findNavController().navigate(action)
        }

        searchAdapter = SearchAdapter { searchModel ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(null, searchModel)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = destinatikAdapter
        }
    }

    private fun observeViewModel() {
        mainViewModel.recommendations.observe(viewLifecycleOwner, Observer { places ->
            Log.d("HomeFragment", "Places received: $places")
            destinatikAdapter.setPlaces(places)
        })

        mainViewModel.error.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })

        mainViewModel.searchResults.observe(viewLifecycleOwner, Observer { searchResults ->
            if (searchResults.isNotEmpty()) {
                searchAdapter.setSearchResults(searchResults)
                binding.recyclerView.adapter = searchAdapter
            } else {
                binding.recyclerView.adapter = destinatikAdapter
            }
        })
    }

    private fun navigateToProfile() {
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }

    private fun navigateToSettings() {
        findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
