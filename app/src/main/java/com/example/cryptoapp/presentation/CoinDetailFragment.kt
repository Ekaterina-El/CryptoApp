package com.example.cryptoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {
	private lateinit var binding: FragmentCoinDetailBinding
	private lateinit var viewModel: CoinViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val fromSymbol = requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_FROM_SYMBOL)
		viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
		viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner, Observer {
			binding.coinInfo = it
			Picasso.get().load(it.imageUrl).into(binding.ivLogoCoin)
		})
	}

	companion object {
		private const val EMPTY_FROM_SYMBOL = ""
		private const val EXTRA_FROM_SYMBOL = "fSym"

		fun newInstance(fromSymbol: String): CoinDetailFragment {
			return CoinDetailFragment().apply {
				arguments = Bundle().apply {
					putString(EXTRA_FROM_SYMBOL, fromSymbol)
				}
			}
		}
	}
}
