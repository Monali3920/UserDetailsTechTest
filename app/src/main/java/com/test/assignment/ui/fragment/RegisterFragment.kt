package com.test.assignment.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.test.assignment.R
import com.test.assignment.databinding.FragmentRegisterBinding
import com.test.assignment.domain.entities.UserDataModel
import com.test.assignment.domain.entities.UserViewModel
import com.test.assignment.utils.ApiConstants.EMAIL_ADDRESS_PATTERN
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: UserViewModel by viewModels()
    private var existingModel: UserDataModel? = null
    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }


    private fun setClickListeners() {
        sharedpreferences = activity?.getSharedPreferences(LoginFragment.SHARED_PREFS, Context.MODE_PRIVATE)!!
        binding.submitButton.setOnClickListener {

            if (isValid()) {
                if (existingModel != null) {
                    viewModel.addUser(
                        UserDataModel(existingModel!!.id, binding.emailTextField.text.toString(),
                        binding.passwordTextField.text.toString(),
                            Integer.parseInt(binding.ageTextField.text.toString()))

                    )
                    existingModel = null
                } else {
                    viewModel.addUser(
                        UserDataModel(
                            Date().time, binding.emailTextField.text.toString(),
                        binding.passwordTextField.text.toString(), Integer.parseInt(binding.ageTextField.text.toString()))
                    )
                }

                binding.emailTextField.setText("")
                binding.passwordTextField.setText("")
                binding.ageTextField.setText("")

                val editor = sharedpreferences.edit()
                editor.putBoolean("app_launch", true)
                editor.apply()

                navigateUserDetails()
            }
        }
    }

    private fun isValid(): Boolean {
        return if (binding.emailTextField.text.toString().trim().isEmpty() &&
            checkEmail(binding.emailTextField.text.toString())) {
            binding.emailTextField.error = "Please enter valid email"
            //showToast("Please enter valid email")
            false
        } else if (binding.passwordTextField.text.toString().trim().isEmpty() &&
            binding.passwordTextField.text.toString().trim().length < 6) {
            //showToast("Please enter password")
            binding.passwordTextField.error = "Please enter password"
            false
        } else if (binding.ageTextField.text.toString().trim().isEmpty()) {
            //showToast("Please enter age")
            binding.ageTextField.error = "Please enter age"
            false
        } else {
            true
        }
    }

    fun setObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    private fun navigateUserDetails() {
        Log.i("MYTAG","navigate to home screen")
        Navigation.findNavController(binding.root)
            .navigate(
                R.id.action_registerFragment_to_userDetailsFragment)
    }

    override fun onDestroyView() {
        if (view != null) {
            val parentViewGroup = view?.parent as ViewGroup?
            parentViewGroup?.removeAllViews();
        }
        super.onDestroyView()
    }

    private fun checkEmail(email: String) : Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}