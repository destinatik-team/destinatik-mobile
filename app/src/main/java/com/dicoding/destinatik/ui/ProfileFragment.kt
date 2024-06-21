package com.dicoding.destinatik.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.domain.viewmodel.UsersViewModel
import com.dicoding.destinatik.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val usersViewModel: UsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch and display the user information
        usersViewModel.fetchUser()

        usersViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.RUsername.setText(it.username) // Assuming your FragmentProfileBinding has an EditText with id RUsername
                binding.REmail.setText(it.email) // Assuming your FragmentProfileBinding has an EditText with id REmail
//                binding.RPhone.setText(it.phone) // Assuming your FragmentProfileBinding has an EditText with id RPhone
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
