package com.example.homeworktwenty.presentation.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homeworktwenty.R
import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.databinding.FragmentUserBinding
import com.example.homeworktwenty.domain.model.UserResponse
import com.example.homeworktwenty.presentation.base.BaseFragment
import com.example.homeworktwenty.presentation.model.User
import com.example.homeworktwenty.presentation.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()
    override fun setUp() {

    }

    override fun listeners() {
        binding.btnAddUser.setOnClickListener {
            viewModel.insertUser(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                age = binding.etAge.text.toString().toIntOrNull(),
                email = binding.etEmail.text.toString()
            )

            bindInsert()
        }
        binding.btnRemoveUser.setOnClickListener {
            viewModel.deleteUser(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                age = binding.etAge.text.toString().toIntOrNull(),
                email = binding.etEmail.text.toString()
            )
            bindDelete()
        }

        binding.btnUpdateUser.setOnClickListener {
            viewModel.updateUser(   newFirstName = binding.etFirstName.text.toString(),
                newLastName = binding.etLastName.text.toString(),
                newAge = binding.etAge.text.toString().toIntOrNull(),
                email = binding.etEmail.text.toString())
            bindUpdate()
        }
    }

    private fun bindInsert() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.insertUserFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.tvIdentifier.apply {
                                text = getString(R.string.loading)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.white
                                    )
                                )
                            }
                        }

                        is Resource.Success -> {
                            binding.tvIdentifier.apply {
                                text = getString(R.string.user_added_successfuly)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                            }
                        }

                        is Resource.Failed -> {
                            binding.tvIdentifier.apply {
                                text = it.message
                                setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindDelete() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deleteUserFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.tvIdentifier.apply {
                                text = getString(R.string.loading)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.white
                                    )
                                )
                            }
                        }

                        is Resource.Success -> {
                            binding.tvIdentifier.apply {
                                text = getString(R.string.user_deleted_successfuly)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                            }
                        }

                        is Resource.Failed -> {
                            binding.tvIdentifier.apply {
                                text = it.message
                                setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindUpdate() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.updateUserFlow.collect {
                    when (it) {

                        is Resource.Loading -> {

                            binding.tvIdentifier.apply {
                                text = getString(R.string.loading)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.white
                                    )
                                )
                            }


                        }

                        is Resource.Success -> {
                            binding.tvIdentifier.apply {
                                text = getString(R.string.user_updates_successfuly)
                                setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                            }


                        }

                        is Resource.Failed -> {
                            binding.tvIdentifier.apply {
                                text = it.message
                                setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                            }

                        }

                    }
                }
            }
        }
    }


}