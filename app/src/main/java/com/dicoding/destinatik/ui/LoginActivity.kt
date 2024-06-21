package com.dicoding.destinatik.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.destinatik.MainActivity
import com.dicoding.destinatik.R
import com.dicoding.destinatik.core.data.local.preferences.auth.AuthPreferences
import com.dicoding.destinatik.core.domain.viewmodel.AuthViewModel
import com.dicoding.destinatik.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModel()
    private val authPreferences: AuthPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            RBtnLogin1.setOnClickListener {
                val usernameOrEmail = REdtEmailWlcm.text.toString()
                val password = REdtPasswordWlcm.text.toString()
                binding.progressBar.visibility = View.VISIBLE

                authViewModel.login(usernameOrEmail, password)
            }

            RForgotPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
        }

        authViewModel.loginResult.observe(this) { response ->
            binding.progressBar.visibility = View.GONE
            if (response != null) {
                Log.d("LoginActivity", "Login successful: $response")

                val token = response.token
                if (token != null) {
                    Log.d("LoginActivity", "Saving token: $token")
                    authPreferences.saveToken(token)
                } else {
                    Log.e("LoginActivity", "Login failed: Token is null")
                    showErrorDialog("Login failed")
                    return@observe
                }

                val userId = response.userId
                if (userId != null) {
                    Log.d("LoginActivity", "Saving userId: $userId")
                    authPreferences.saveUserId(userId)
                } else {
                    Log.e("LoginActivity", "Login failed: User ID is null")
                    showErrorDialog("Login failed")
                    return@observe
                }

                // Navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showErrorDialog("Something went wrong or username and password are incorrect")
            }
        }

        authViewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        authViewModel.error.observe(this) { error ->
            if (error != null) {
                showErrorDialog(error)
            }
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
