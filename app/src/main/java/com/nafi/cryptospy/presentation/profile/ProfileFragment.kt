package com.nafi.cryptospy.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.nafi.cryptospy.R
import com.nafi.cryptospy.databinding.FragmentProfileBinding
import com.nafi.cryptospy.presentation.login.LoginActivity
import com.nafi.cryptospy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupForm()
        showUserData()
        setClickListener()
        observeEditMode()
    }

    private fun setupForm() {
        binding.tilName.isVisible = false
        binding.etName.isEnabled = false
        binding.btnSaveProfile.isVisible = false
        binding.btnSaveProfile.isEnabled = false
    }

    private fun showUserData() {
        profileViewModel.getCurrentUser()?.let {
            binding.etName.setText(it.fullName)
            binding.tvUsername.text = it.fullName
            binding.tvEmail.text = it.email
            binding.ivUser.load(it.photoUrl) {
                crossfade(true)
                placeholder(R.drawable.iv_user)
                error(R.drawable.iv_user)
            }
        }
    }

    private fun setClickListener() {
        binding.btnEditProfile.setOnClickListener {
            profileViewModel.changeEditMode()
        }
        binding.btnSaveProfile.setOnClickListener {
            if (checkNameValidation()) {
                changeProfileData()
            }
        }
        binding.btnLogout.setOnClickListener {
            doLogOut()
        }
    }

    private fun changeProfileData() {
        val fullName = binding.etName.text.toString().trim()
        profileViewModel.updateFullName(fullName)
    }

    private fun checkNameValidation(): Boolean {
        val fullName = binding.etName.text.toString().trim()
        return if (fullName.isEmpty()) {
            binding.tilName.isErrorEnabled = true
            binding.tilName.error = getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.tilName.isErrorEnabled = false
            true
        }
    }

    private fun observeEditMode() {
        profileViewModel.isEditMode.observe(viewLifecycleOwner) { isEditMode ->
            binding.tilName.isVisible = isEditMode
            binding.etName.isEnabled = isEditMode
            binding.btnSaveProfile.isVisible = isEditMode
            binding.btnSaveProfile.isEnabled = isEditMode
            binding.btnEditProfile.isVisible = !isEditMode
        }
        observeData()
    }

    private fun observeData() {
        profileViewModel.changeProfileResult.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.btnSaveProfile.isVisible = true
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_change_profile_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                    binding.etName.isEnabled = false
                    binding.btnSaveProfile.isEnabled = false
                    navigateBackToProfile()
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.btnSaveProfile.isVisible = true
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_change_profile_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                    binding.etName.isEnabled = false
                    binding.btnSaveProfile.isEnabled = false
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.btnSaveProfile.isVisible = false
                },
            )
        }
    }

    private fun navigateBackToProfile() {
        findNavController().navigate(R.id.menu_tab_profile)
    }

    private fun doLogOut() {
        val dialog =
            AlertDialog.Builder(requireContext()).setMessage(getString(R.string.text_want_to_logout))
                .setPositiveButton(
                    "Yes",
                ) { dialog, id ->
                    profileViewModel.doLogout()
                    navigateToLogin()
                }
                .setNegativeButton(
                    "No",
                ) { dialog, id ->
                }.create()
        dialog.show()
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }
}
