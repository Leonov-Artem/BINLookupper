package com.cft.binlookupper.presentation.history

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.cft.binlookupper.R
import com.cft.binlookupper.presentation.common.BOOLEAN_KEY_DELETE
import com.cft.binlookupper.presentation.common.REQUEST_KEY_DELETE

private const val ARG_MESSAGE = "ARG_MESSAGE"

class DeletionAlertDialogFragment : DialogFragment() {

    private val message: String?
        get() = arguments?.getString(ARG_MESSAGE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setNegativeButton(R.string.dialog_button_no) { _, _ ->
                setFragmentResult(delete = false)
            }
            .setPositiveButton(R.string.dialog_button_yes) { _, _ ->
                setFragmentResult(delete = true)
            }
            .create()

    private fun setFragmentResult(delete: Boolean) {
        val result = Bundle().apply {
            putBoolean(BOOLEAN_KEY_DELETE, delete)
        }
        setFragmentResult(REQUEST_KEY_DELETE, result)
    }

    companion object {
        fun newInstance(message: String) = DeletionAlertDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MESSAGE, message)
            }
        }
    }
}