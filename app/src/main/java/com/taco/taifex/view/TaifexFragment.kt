package com.taco.taifex.view

import androidx.recyclerview.widget.DefaultItemAnimator
import com.taco.taifex.databinding.FragmentTaifexBinding
import com.taco.taifex.viewmodel.TaifexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaifexFragment : BaseFragment<FragmentTaifexBinding>(), TaifexBottomSheetFragment.BottomSheetListener {

    private val viewModel: TaifexViewModel by viewModel()
    private lateinit var taifexAdapter: TaifexAdapter
    private lateinit var loadingDialog: LoadingDialogFragment

    override fun initView() {
        taifexAdapter = TaifexAdapter(emptyList())
        binding.rvTaifex.adapter = taifexAdapter
        binding.rvTaifex.itemAnimator = DefaultItemAnimator()

        loadingDialog = LoadingDialogFragment()

        viewModel.loading.observe(this) {
            if (it) {
                loadingDialog.show(childFragmentManager, loadingDialog.tag)
            } else {
                loadingDialog.dismiss()
            }
        }

        viewModel.getTaifexData()
        viewModel.taifexData.observe(this) {
            taifexAdapter.updateData(it)
        }

        binding.ivMenu.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = TaifexBottomSheetFragment()
        bottomSheetFragment.setBottomSheetListener(this)
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    override fun onSortDataDescending() {
        taifexAdapter.sortDataDescending(compareBy { it.code })
    }

    override fun onSortDataAscending() {
        taifexAdapter.sortDataAscending(compareBy { it.code })
    }
}