package com.test.assignment.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.test.assignment.R
import com.test.assignment.databinding.FragmentLoginBinding
import com.test.assignment.domain.entities.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        setObserver()
        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {
        sharedpreferences = activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)!!

        binding.loginButton.setOnClickListener {
            if (binding.userNameTextField.text.toString() != ""
                || binding.passwordTextField.text.toString() != ""){
                val email=binding.userNameTextField.text.toString()
                val password=binding.passwordTextField.text.toString()
                viewModel.isValidUser(email, password)
            }else{
                Toast.makeText(activity,"Please enter credentials to login", Toast.LENGTH_SHORT).show()
            }
        }

        binding.SignUpButton.setOnClickListener {
            navigateToRegisterUser()
        }
    }

    fun setObserver(){
        viewModel.userLiveData.observe(viewLifecycleOwner){
            if(it != null){
                Toast.makeText(activity, "Logged in successfully", Toast.LENGTH_SHORT).show()
                val editor = sharedpreferences.edit()
                editor.putBoolean("app_launch", true)
                editor.apply()

                navigateUserDetails()
                //valid user
                //Toast.makeText(activity, "Valid user", Toast.LENGTH_SHORT).show()
            }else{
                //invalid
                Toast.makeText(activity, "InValid user, Register yourself", Toast.LENGTH_SHORT).show()
                navigateToRegisterUser()
            }
        }
    }

    private fun navigateToRegisterUser() {
        Navigation.findNavController(binding.root)
            .navigate(
                R.id.action_loginFragment_to_homeFragment)
    }

    private fun navigateUserDetails() {
        Navigation.findNavController(binding.root)
            .navigate(
                R.id.action_loginFragment_to_userDetailsFragment)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Clear the systemUiVisibility flag
        activity?.window?.decorView?.systemUiVisibility = 0
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        if (view != null) {
            val parentViewGroup = view?.parent as ViewGroup?
            parentViewGroup?.removeAllViews();
        }
        super.onDestroyView()
    }

    companion object {
        const val SHARED_PREFS = "shared_prefs"
    }
}