package com.renditriyadi.schedulerapat.activities

import android.app.Activity
import android.app.AlertDialog
import com.renditriyadi.schedulerapat.R

class LoadingDialog (private val mActivity:Activity){
    private lateinit var isdialog:AlertDialog
    fun startLoading(){
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }

    fun isDismiss() {
        isdialog.dismiss()
    }
}