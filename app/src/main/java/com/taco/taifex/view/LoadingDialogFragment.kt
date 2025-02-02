package com.taco.taifex.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.taco.taifex.R

class LoadingDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_loading)
        dialog.setCancelable(false)
        return dialog
    }
}