package com.app.neosoft

import android.app.AlertDialog.THEME_HOLO_LIGHT
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity


class ProgressDialogFragment : DialogFragment() {

    companion object{

        val TAG = ProgressDialogFragment.javaClass.name
        private val progressDialogFragment : ProgressDialogFragment =
            ProgressDialogFragment()

        fun show(appCompatActivity: AppCompatActivity) {
            appCompatActivity.supportFragmentManager.beginTransaction()
                .add(
                    progressDialogFragment,
                    TAG
                )
                .commitAllowingStateLoss()
        }

        fun dismiss(appCompatActivity: AppCompatActivity) {
            appCompatActivity.supportFragmentManager.beginTransaction()
                .remove(progressDialogFragment)
                .commitAllowingStateLoss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val dialog = ProgressDialog(activity, THEME_HOLO_LIGHT)
        dialog.setTitle(activity?.resources?.getString(R.string.str_please_wait))
        dialog.setMessage(activity?.resources?.getString(R.string.str_loading))
        dialog.isIndeterminate = true
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        return dialog
    }
}