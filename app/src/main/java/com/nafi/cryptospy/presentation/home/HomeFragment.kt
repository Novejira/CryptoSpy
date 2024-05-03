package com.nafi.cryptospy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.nafi.cryptospy.R
import com.nafi.cryptospy.data.model.Coin
import com.nafi.cryptospy.databinding.FragmentHomeBinding
import com.nafi.cryptospy.presentation.detail.DetailActivity
import com.nafi.cryptospy.presentation.home.adapter.CoinAdapter
import com.nafi.cryptospy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

    private val coinAdapter: CoinAdapter by lazy {
        CoinAdapter {
            DetailActivity.startActivity(requireContext(), it.id)
        }
    }

    private fun showUserData() {
        homeViewModel.getCurrentUser()?.let { user ->
            binding.layoutHead.tvUsernameTitle.text =
                getString(R.string.text_welcome_user, user.fullName)
        }
    }

    private fun setupCoin() {
        binding.rvCoin.apply {
            adapter = coinAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        showUserData()
        setupCoin()
        proceedCoin()
    }

    private fun proceedCoin() {
        homeViewModel.getCoins().observe(viewLifecycleOwner) {
            it?.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.rvCoin.isVisible = true
                    it.payload?.let { data ->
                        bindCoinList(data)
                    }
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.rvCoin.isVisible = true
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.rvCoin.isVisible = false
                },
            )
        }
    }

    private fun bindCoinList(data: List<Coin>) {
        coinAdapter.submitData(data)
    }

}
