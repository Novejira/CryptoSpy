package com.nafi.cryptospy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nafi.cryptospy.databinding.FragmentHomeBinding
import com.nafi.cryptospy.presentation.detail.DetailActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToDetail.setOnClickListener {
            navigateToDetail("ethereum")
        }
    }

    private fun navigateToDetail(id: String) {
        DetailActivity.startActivity(requireContext(), id)
    }
}
