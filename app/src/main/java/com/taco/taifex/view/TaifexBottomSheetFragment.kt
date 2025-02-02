package com.taco.taifex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.taco.taifex.databinding.BottomSheetLayoutBinding

class TaifexBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!
    private var listener: BottomSheetListener? = null

    interface BottomSheetListener {
        fun onSortDataDescending()
        fun onSortDataAscending()
    }

    fun setBottomSheetListener(listener: BottomSheetListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDataDescending.setOnClickListener {
            listener?.onSortDataDescending()
            dismiss()
        }

        binding.tvAscending.setOnClickListener {
            listener?.onSortDataAscending()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}