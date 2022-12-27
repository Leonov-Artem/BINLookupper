package com.cft.binlookuper.presentation.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.cft.binlookuper.R

class SpeedLimitExceededFragmentDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.dialog_message_speed_limit_exceeded)
            .setPositiveButton(R.string.dialog_button_ok) { _, _ -> }
            .create()

    companion object {
        fun newInstance() = SpeedLimitExceededFragmentDialog()
    }
}